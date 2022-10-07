package com.newtours.tests;

import com.newtours.pages.*;
import com.tests.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookFlightTest extends BaseTests {
    private String expectedPrice;
    private String noOfPassengers;

    @BeforeTest
    @Parameters({ "noOfPassengers","expectedPrice"})
    public void setupParameters( String noOfPassengers , String expectedPrice){
        this.expectedPrice = expectedPrice;
        this.noOfPassengers = noOfPassengers;
    }

    @Test
    public void registrationPageTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("selenium", "docker");
        registrationPage.enterUserCredentials("selenium", "docker");
        registrationPage.submit();
    }

    @Test(dependsOnMethods = "registrationPageTest")
    public void registrationConfirmationPageTest(){
        RegistrationConfirmationPage confirmationPage = new RegistrationConfirmationPage(driver);
        confirmationPage.goToFlightDetailsPage();
    }

    @Test(dependsOnMethods = "registrationConfirmationPageTest")
    public void flightDetailsPageTest(){
        FlightDetailspage flightDetailspage = new FlightDetailspage(driver);
        flightDetailspage.selectPassengers(noOfPassengers);
        flightDetailspage.goToFindFlightsPage();

    }
    @Test(dependsOnMethods = "flightDetailsPageTest")
    public void findFlightPageTest(){
        FindFlightPage findFlightPage = new FindFlightPage(driver);
        findFlightPage.submitFindFlightPage();
        findFlightPage.goToConfirmationPage();
    }

    @Test(dependsOnMethods = "findFlightPageTest")
    public void printConfirmationPageTest() throws InterruptedException {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        String actualPrice = flightConfirmationPage.getPrice();
        Assert.assertEquals(actualPrice, expectedPrice);
    }


}
