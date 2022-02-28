package com.gittigidiyi.Tests;

import com.gittigidiyi.Pages.BasePage;
import com.gittigidiyi.Utilities.BrowserUtils;
import com.gittigidiyi.Utilities.ConfigurationReader;
import com.gittigidiyi.Utilities.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestBase extends BasePage {

    WebDriver driver = Driver.get();
    String url = ConfigurationReader.get("baseUrl"); //READING FROM CONFIGURATION READER CLASS
    Actions act = new Actions(driver); //ACTIONS
    WebDriverWait wait = new WebDriverWait(driver, 20); //EXPLICIT WAIT

    @BeforeTest
    public WebDriver launchBrowser() {
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    @Test
    public void searchItem() throws Exception {

        //SEARCH "BILGISAYAR" WORD FROM SEARCH ENGINE!..
        closeCookie.click();
        searchBar.sendKeys("Bilgisayar");
        searchButton.click();

        //SECOND PAGE IS OPENING
        act.moveToElement(secondPage).click().perform();

        //GET A RANDOM WEBELEMENT FROM SECONDPAGE
        List<WebElement> chooseRandom = choosableElementList;
        System.out.println("SIZE = " + chooseRandom.size());
        int sizeNum = chooseRandom.size();
        Random rd = new Random();
        int num = rd.nextInt(sizeNum);
        System.out.println(num);
        WebElement elm = chooseRandom.get(num - 1);  //WebElement elm = chooseRandom.get(num-1);
        System.out.println(elm);
        elm.click();

        //GET THE PRICE OF CHOSEN PRODUCT WRITE THE TEXT FILE
        FileWriter fileWriter = new FileWriter("priceName.txt");
        BufferedWriter read = new BufferedWriter(fileWriter);

        String namePro = productName.getAttribute("innerHTML");
        String textName = productName.getText();
        String textPrice = productPrice.getText();

        System.out.println("text = " + textName);
        System.out.println("innerHTML =" + namePro);

        read.write(textName + "/" + textPrice);
        read.close();

        //ADD PRODUCT TO BASKET
        driver.navigate().refresh();
        Thread.sleep(2000);
        basket.click();

        //CHECK THE PRICE OF BASKET
        String pP = pricePage.getText();
        System.out.println("PAGE =" + pP);
        Thread.sleep(2000);
        toBasket.click();

        String pB = basketPrice.getAttribute("innerHTML");
        System.out.println("BASKET =" + pB);
        try {
            Assert.assertEquals(pP, pB);
        } catch (Exception e) {
            throw new Exception("PRICE IS NOT TRUE!..");
        }
        //INCREASE THE NUMBER OF PRODUCT AND CHECK IF IT IS 2
        Thread.sleep(2000);
        dropdownList.click();

        Select amount = new Select(dropdownList);

        List<WebElement> options = amount.getOptions();
        System.out.println("optionsSize =" + options.size());
        options.get(1).click();

        //VERIFY IF IT IS 2 OR NOT
        String actualNum = dropdownList.getAttribute("value"); //INCREASING VALUE IN DOME
        System.out.println("actualNum =" + actualNum);
        //driver.navigate().refresh(); //IT IS RESETTING THE NUMBER I HAVE INCREASED.
        String ExpectedNum = "2";
        Assert.assertEquals(ExpectedNum, actualNum, "IT IS NOT CORRECT!..");

        //ERASE THE VALUE FROM BASKET AND CHECK IF IT IS ERASED
        Thread.sleep(5000);
        erase.click();
        String actual = noProductInTheBasket.getAttribute("innerHTML");
        System.out.println("actual = " + actual);
        String expected = "Sepetinizde ürün bulunmamaktadır.";
        System.out.println("expected = " + expected);
        Assert.assertEquals(actual, expected, "IT IS NOT CORRECT!..");

    }

    @AfterTest
    public void Teardown() {
    driver.quit();
    }

}





