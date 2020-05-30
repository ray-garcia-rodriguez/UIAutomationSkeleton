package org.rodriguez.uiautomation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.rodriguez.uiautomation.util.FileUtil;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/org/rodriguez/uiautomation/feature"},
        glue = {"org.rodriguez.uiautomation.stepDefinition"},
        plugin = {"pretty", "json:src/test/reports/cucumber/cucumber.json"},
        strict = true
)
public class TestRunner {
    static Logger log = LogManager.getLogger(TestRunner.class);

    @BeforeClass
    public static void setLogger() {
        System.setProperty("log4j.configurationFile", FileUtil.usrTstRsrcDir+"log4j2.xml");
    }
}
