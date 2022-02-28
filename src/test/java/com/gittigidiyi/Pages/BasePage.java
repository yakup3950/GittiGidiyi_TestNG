package com.gittigidiyi.Pages;

import com.gittigidiyi.Utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import java.util.List;

public class BasePage {

    public BasePage(){
        PageFactory.initElements(Driver.get(), this);
    }
    //SEARCH "BILGISAYAR" WORD FROM SEARCH ENGINE!..
    @FindBy(css=".tyj39b-5.lfsBU")
    public WebElement closeCookie;

    @FindBy(xpath="//div/input")
    public WebElement searchBar;

    @FindBy(xpath="//button[@type='submit']")
    public WebElement searchButton;

    //SECOND PAGE IS OPENING
    @FindBy(xpath="//a[span='2']")
    public WebElement secondPage;

    //GET A RANDOM WEBELEMENT FROM SECONDPAGE
    @FindBy(xpath="//li/article/div[2]/a")
    public List<WebElement> choosableElementList;

    //GET THE PRICE OF CHOSEN PRODUCT WRITE THE TEXT FILE
    @FindBy(css=".title.r-onepp-title")
    public WebElement productName;

    @FindBy(css="#sp-price-discountPrice")
    public WebElement productPrice;

    //ADD PRODUCT TO BASKET
    @FindBy(css="form button.control-button.gg-ui-button.plr10.gg-ui-btn-default")
    public WebElement basket;

    //CHECK THE PRICE OF BASKET
    @FindBy(css="div#sp-price-lowPrice")
    public WebElement pricePage;

    @FindBy(css=".new-price")
    public WebElement basketPrice;

    //CLICK ON THE BUTTON SEND YOU TO BASKET
    @FindBy(css=".gg-ui-btn-default.padding-none")
    public WebElement toBasket;

    //VERIFY IF IT IS 2 OR NOT
    @FindBy(css="div select.amount")
    public WebElement dropdownList;

    //ERASE THE VALUE FROM BASKET AND CHECK IF IT IS ERASED
    @FindBy(css="i.gg-icon.gg-icon-bin-medium")
    public WebElement erase;

    @FindBy(xpath="//div/h2[1]")
    public WebElement noProductInTheBasket;

}
