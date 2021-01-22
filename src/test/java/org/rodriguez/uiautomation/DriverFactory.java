package org.rodriguez.uiautomation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

enum DriverType {LOCAL, REMOTE, JENKINS}
enum BrowserType {CHROME, FIREFOX, INTERNET_EXPLORER}

@Component
public class DriverFactory {
    static Logger log = LogManager.getLogger(DriverFactory.class);

    private WebDriver webDriver;
    private Boolean isHeadless;

    @Autowired
    public DriverFactory (String driverType, String browserType, Boolean isHeadless) {
        this.isHeadless = isHeadless;

        switch (DriverType.valueOf(driverType)) {
            case LOCAL:
                webDriver = getLocalDriver(BrowserType.valueOf(browserType));
                break;
//            case REMOTE:
//                return getRemoteDriver();
//            case JENKINS:
//                return getJenkinsDriver();
            default:
                throw new IllegalArgumentException("Unexpected driverType"+driverType);
        }
    }

    private WebDriver getLocalDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                return getChromeDriver();
            default:
                throw new IllegalStateException("Unexpected value: " + browserType);
        }
    }

    private WebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();

        if (isHeadless)
            options.addArguments("--window-size=1920,1080", "--start-maximized", "--headless");

        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model

        return new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        return webDriver;
    }
}
