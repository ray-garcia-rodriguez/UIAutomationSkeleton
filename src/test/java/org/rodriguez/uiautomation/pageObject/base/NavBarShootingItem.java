package org.rodriguez.uiautomation.pageObject.base;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.rodriguez.uiautomation.DriverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class NavBarShootingItem {
    @Autowired
    private DriverFactory driverFactory;

    public void navigateTo(String navBarShtngItmType) {
        new FluentWait<>(driverFactory.getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .until(ExpectedConditions.elementToBeClickable(By.id("subcategoryLink_"+NavBarShtngItmType.valueOf(navBarShtngItmType).getSubCtgryId())))
                .click();
    }
}
