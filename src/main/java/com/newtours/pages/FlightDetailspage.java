package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightDetailspage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "passCount")
    private WebElement passenger;

    @FindBy(name = "findFlights")
    private WebElement submitButton;

    public FlightDetailspage (WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
        PageFactory.initElements(driver, this);
    }

    public void selectPassengers(String numOfPassengers){
        this.wait.until(ExpectedConditions.visibilityOf(passenger));
        Select select = new Select(passenger);
        select.selectByValue(numOfPassengers);
    }

    public void goToFindFlightsPage(){
        this.submitButton.click();
    }
}
