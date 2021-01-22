package org.rodriguez.uiautomation.stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.rodriguez.uiautomation.DriverFactory;
import org.rodriguez.uiautomation.pageObject.base.BasePage;
import org.springframework.beans.factory.annotation.Autowired;

public class AmmoInStock {
    @Autowired
    BasePage basePage;

    @Autowired
    DriverFactory driverFactory;

    @Given("User is on Basspro landing page")
    public void user_is_on_Basspro_landing_page() {
        basePage.getLanding();
        basePage.landed();
    }

    @Given("User navigates to handgun ammo from Navbar")
    public void user_navigates_to_handgun_ammo_from_Navbar() {
        basePage.navigateTo("SHOOTING", "HANDGUN_AMMO");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed())
            scenario.embed(((TakesScreenshot)driverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES),"image/png", scenario.getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driverFactory.getDriver().close();
    }
}
