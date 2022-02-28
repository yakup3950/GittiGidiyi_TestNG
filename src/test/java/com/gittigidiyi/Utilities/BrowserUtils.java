package com.gittigidiyi.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BrowserUtils {





    //THREAD SLEEP HANDLING

    /**
     * Performs a pause
     *
     * @param seconds
     */
    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //IMPLICIT WAIT
        public static void implicitWait(int seconds) throws InterruptedException {
        WebDriver driver = Driver.get();
        driver.manage().window().wait(10);
    }

    //EXPLICIT WAIT (ElementToBeClickable)
        public static void waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), timeout);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }

    //REFRESH WINDOW
        public static void refresh(){
        WebDriver driver = Driver.get();
        driver.navigate().refresh();
    }



}
