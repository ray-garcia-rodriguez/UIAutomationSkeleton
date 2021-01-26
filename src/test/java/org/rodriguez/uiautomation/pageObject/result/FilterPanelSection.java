package org.rodriguez.uiautomation.pageObject.result;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.rodriguez.uiautomation.DriverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class FilterPanelSection {

    @Autowired
    private DriverFactory driverFactory;

    public void filterSectionWItms(String fltrPnlSctnTypStr, String ... fltrItmTypStrs) {
        viewMore(fltrPnlSctnTypStr);
        int tries = 0;
        try {
            for (String fltrItmTypStr : fltrItmTypStrs) {
                switch (FilterPanelSectionType.valueOf(fltrPnlSctnTypStr)) {
                    case CARTRIDGE_OR_GAUGE:
                        new FluentWait<>(driverFactory.getDriver())
                                .withTimeout(Duration.ofSeconds(10))
                                .pollingEvery(Duration.ofMillis(500))
                                .until(ExpectedConditions.elementToBeClickable(
                                        By.id(FilterCrtrdgGagItemType.valueOf(fltrItmTypStr)
                                                .getItmId())))
                                .click();
                        tries = 0;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + FilterPanelSectionType.valueOf(fltrPnlSctnTypStr));
                }
            }
        } catch (ElementClickInterceptedException ecie) {
            if (tries > 5)
                throw new RuntimeException("tried to filter unsuccessfully 5 times");
            tries++;
        }
    }

    public void viewMore(String fltrPnlSctnTypStr) {
        WebElement vwMreToClck = new FluentWait<>(driverFactory.getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .until(ExpectedConditions.presenceOfNestedElementLocatedBy(
                    By.id(FilterPanelSectionType.valueOf(fltrPnlSctnTypStr).getSctnId()),
                    By.className("viewMore")));

        new FluentWait<>(driverFactory.getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .until(ExpectedConditions.elementToBeClickable(vwMreToClck))
                .click();
    }
}
