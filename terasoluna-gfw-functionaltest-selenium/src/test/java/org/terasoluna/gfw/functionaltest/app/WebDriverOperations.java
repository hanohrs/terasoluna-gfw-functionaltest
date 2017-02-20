/*
 * Copyright (C) 2013-2017 NTT DATA Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.terasoluna.gfw.functionaltest.app;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * Class that provides a (logic for WebDriver) browser operation.
 */
public class WebDriverOperations {

    protected final WebDriver webDriver;

    protected long defaultTimeoutSecForImplicitlyWait = 5;

    public WebDriverOperations(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Set the default timeout value of the waiting process to find the element.
     * @param defaultTimeoutSecForImplicitlyWait The default timeout value of the waiting process to find the element (s)
     */
    public void setDefaultTimeoutForImplicitlyWait(
            long defaultTimeoutSecForImplicitlyWait) {
        this.defaultTimeoutSecForImplicitlyWait = defaultTimeoutSecForImplicitlyWait;
    }

    /**
     * Check the specified element exists.
     * @param by Identifier to look for elements
     * @return And returns true if the specified element is present.
     */
    public boolean exists(By by) {
        webDriver.findElement(By.tagName("body"));
        setTimeoutForImplicitlyWait(0, TimeUnit.SECONDS);
        boolean existsElement = true;
        try {
            webDriver.findElement(by).getText();
        } catch (NoSuchElementException e) {
            existsElement = false;
        } finally {
            setDefaultTimeoutForImplicitlyWait();
        }
        return existsElement;
    }

    /**
     * Set to the default value of the timeout value waiting process to find the element.
     */
    public void setDefaultTimeoutForImplicitlyWait() {
        setTimeoutForImplicitlyWait(defaultTimeoutSecForImplicitlyWait,
                TimeUnit.SECONDS);
    }

    /**
     * Set the time-out value of the waiting process to find the element.
     */
    public void setTimeoutForImplicitlyWait(long timeout, TimeUnit timeUnit) {
        webDriver.manage().timeouts().implicitlyWait(timeout, timeUnit);
    }
}
