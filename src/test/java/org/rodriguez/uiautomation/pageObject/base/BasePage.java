package org.rodriguez.uiautomation.pageObject.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.rodriguez.uiautomation.DriverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BasePage {
    private static Logger log = LogManager.getLogger(BasePage.class);

    @Autowired
    private DriverFactory driverFactory;
    @Value("${baseUrl}")
    private String baseUrl;
    @Autowired
    private NavBar navBar;

    private final By prflLnkBtnBy = By.id("Header_GlobalLogin_signInQuickLink");

    public void getLanding() {
        driverFactory.getDriver().get(baseUrl);
        landed();
    }

    private void landed() {
        new FluentWait<>(driverFactory.getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .until(ExpectedConditions.visibilityOfElementLocated(prflLnkBtnBy));
    }

    public void navigateTo(String navBarItemType, String navBarSubItemType) {
        navBar.navigateTo(navBarItemType, navBarSubItemType);
    }
}
