package org.rodriguez.uiautomation.stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.rodriguez.uiautomation.DriverFactory;
import org.rodriguez.uiautomation.pageObject.base.BasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Cart {
    @Autowired
    BasePage basePage;

    @Autowired
    DriverFactory driverFactory;

    @Given("User is on Juice Shop landing page")
    public void user_is_on_Juice_Shop_landing_page() {
        basePage.getLanding();
    }

    @When("User select {string} item")
    public void user_select_item(String string) {
        basePage.openFirstProductItem();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed())
            scenario.embed(((TakesScreenshot)driverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES),"image/png", scenario.getName());
        driverFactory.getDriver().close();
    }
}
