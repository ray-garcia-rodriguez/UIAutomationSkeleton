package org.rodriguez.uiautomation.stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.rodriguez.uiautomation.DriverFactory;
import org.rodriguez.uiautomation.pageObject.base.BasePage;
import org.rodriguez.uiautomation.pageObject.result.ResultsPage;
import org.rodriguez.uiautomation.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AmmoInStock {
    @Autowired
    BasePage basePage;
    @Autowired
    ResultsPage resultsPage;

    @Autowired
    DriverFactory driverFactory;

    @Given("User is on Basspro landing page")
    public void user_is_on_Basspro_landing_page() {
        basePage.getLanding();
    }

    @Given("User navigates to handgun ammo from Navbar")
    public void user_navigates_to_handgun_ammo_from_Navbar() {
        basePage.navigateTo("SHOOTING", "HANDGUN_AMMO");
    }

    @When("User filters {string} section with {string}")
    public void user_filters_section_with(String fltrPnlSctnTypStr, String fltrItmTypStrs) {
        resultsPage.filter(fltrPnlSctnTypStr, fltrItmTypStrs.split(","));
    }

    @Then("Some results should be in stock")
    public void some_results_should_be_in_stock() {
        resultsPage.collectItmLinks();
    }

    @After
    public void tearDown(Scenario scenario) {
        byte[] snip;
        if (scenario.isFailed()) {
            snip = ((TakesScreenshot) driverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(snip, "image/png", scenario.getName());
            try (FileOutputStream fos = new FileOutputStream(FileUtil.usrTstRsrcDir+"failure.png")) {
                fos.write(snip);
                //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driverFactory.getDriver().close();
    }
}
