package org.rodriguez.uiautomation.pageObject.base;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.rodriguez.uiautomation.DriverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;


@Component
public class NavBar {
    @Autowired
    private DriverFactory driverFactory;
    @Autowired
    NavBarShootingItem navBarShootingItem;


    public void navigateTo(String navBarItemTypeStr, String navBarSubItemTypeStr) {
        NavBarItemType navBarItemType = NavBarItemType.valueOf(navBarItemTypeStr);
        new FluentWait<>(driverFactory.getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .until(ExpectedConditions.elementToBeClickable(By.id("departmentButton_"+navBarItemType.getDprtmntId())))
                .click();

        switch (navBarItemType) {
            case SHOOTING:
                navBarShootingItem.navigateTo(navBarSubItemTypeStr);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + navBarItemType);
        }
    }
}
