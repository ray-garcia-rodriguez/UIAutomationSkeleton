package org.rodriguez.uiautomation.pageObject.result;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.rodriguez.uiautomation.DriverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResultsPage {

    @Autowired
    private DriverFactory driverFactory;
    @Autowired
    private FilterPanel filterPanel;

    private By rsltsTtlBy = By.id("PageHeading_4_-2001_3074457345618277136");

    public void landed() {
        new FluentWait<>(driverFactory.getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .until(ExpectedConditions.visibilityOfElementLocated(rsltsTtlBy));
    }

    public void filter(String fltrPnlSctnTypStr, String ... fltrItmTypStrs) {
        filterPanel.filterSection(fltrPnlSctnTypStr, fltrItmTypStrs);
    }

    public void collectItmLinks() {
        while (true) {
            collectPgItmLinks();
            if (isLstPg())
                break;
            clickNextPage();
        }
    }

    private void collectPgItmLinks() {
        int tries = 0;
        List<WebElement> prdcts;
        while (true) {
            try {
                prdcts = new FluentWait<>(driverFactory.getDriver())
                        .withTimeout(Duration.ofSeconds(10))
                        .pollingEvery(Duration.ofMillis(500))
                        .ignoring(StaleElementReferenceException.class)
                        .until(ExpectedConditions
                                .visibilityOfNestedElementsLocatedBy(By.className("product_listing_container"),
                                        By.tagName("a")))
                        .stream().filter(x -> x.getAttribute("id").contains("catalogEntry_img"))
                        .collect(Collectors.toList());

                System.out.println(prdcts.size());
                prdcts.forEach(x -> System.out.println(x.getAttribute("href")));
                break;
            } catch (StaleElementReferenceException | TimeoutException e) {
                if (tries > 5)
                    throw new RuntimeException("id stayed stale");
                tries++;
            }
        }
    }

    private void clickNextPage() {
        int tries = 0;
        while (true) {
            try {
                new FluentWait<>(driverFactory.getDriver())
                        .withTimeout(Duration.ofSeconds(10))
                        .pollingEvery(Duration.ofMillis(500))
                        .until(ExpectedConditions.elementToBeClickable(
                                By.id("WC_SearchBasedNavigationResults_pagination_link_right_categoryResults_top")))
                        .click();
                return;
            } catch (StaleElementReferenceException sere) {
                if (tries > 5) {
                    throw new RuntimeException("could not click after retrying 5 times");
                }
                tries++;
            }
        }
    }

    private Boolean isLstPg() {
        WebElement pgCnt = new FluentWait<>(driverFactory.getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("num_products")));
        List<String> pgCntTkns = Arrays.stream(pgCnt.getText().split("-| |of"))
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());
        return Integer.parseInt(pgCntTkns.get(1)) >= Integer.parseInt(pgCntTkns.get(2));
    }
}
