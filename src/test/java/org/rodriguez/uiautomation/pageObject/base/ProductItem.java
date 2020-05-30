package org.rodriguez.uiautomation.pageObject.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.rodriguez.uiautomation.DriverFactory;

import java.time.Duration;


public class ProductItem {
    private static Logger log = LogManager.getLogger(ProductItem.class);

    private WebElement root;

    private By name = By.className("item-name");

    private DriverFactory driverFactory;

    public ProductItem(WebElement root, DriverFactory driverFactory) { this.root = root; this.driverFactory = driverFactory; }

    public void open() {
        new FluentWait<>(driverFactory.getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .until(ExpectedConditions.elementToBeClickable(root)).click();
    }
}
