package org.rodriguez.uiautomation.pageObject.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
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

    private By productItemBy = By.className("product");
    private By closeDialogBtn = By.className("close-dialog");

    @Autowired
    private DriverFactory driverFactory;
    @Value("${baseUrl}")
    String baseUrl;

    public void getLanding() {
        driverFactory.getDriver().get(baseUrl);
        closeWelcomeDialog();
    }

    private void closeWelcomeDialog() {
        new FluentWait<>(driverFactory.getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .until(ExpectedConditions.elementToBeClickable(closeDialogBtn)).click();
    }

    public void openFirstProductItem() {
        List<ProductItem> productItems = new FluentWait<>(driverFactory.getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productItemBy))
                .stream().map(x -> new ProductItem(x, driverFactory)).collect(Collectors.toList());

        productItems.get(0).open();
    }
}
