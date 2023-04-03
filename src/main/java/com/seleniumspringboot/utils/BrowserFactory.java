package com.seleniumspringboot.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Set;

@Configuration
public class BrowserFactory {

    private final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    @Autowired
    public BrowserFactory() {
    }

    @Bean
        public ThreadLocal<WebDriver> webDriver(){
        return new ThreadLocal<>();
    }

    public void setDriver(ThreadLocal<WebDriver> driver) {
        tlDriver.set(driver.get());
    }

    public WebDriver getDriver() {
        return tlDriver.get();
    }

    public void setBrowser(String browserName) {

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            tlDriver.set(new ChromeDriver());
        } else if (browserName.equalsIgnoreCase("chrome headless")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            tlDriver.set(new ChromeDriver(options));
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            tlDriver.set(new EdgeDriver());
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            tlDriver.set(new FirefoxDriver());
        } else if (browserName.isBlank() || browserName.isEmpty()) {
            WebDriverManager.chromedriver().setup();
            tlDriver.set(new ChromeDriver());
        } else {
            throw new IllegalArgumentException("Invalid browser name" + browserName);
        }

        deleteCookies();
        maximizeWindow();
    }

    public void maximizeWindow() {
        getDriver().manage().window().maximize();
    }

    public void minimizeWindow() {
        getDriver().manage().window().minimize();
    }

    void deleteCookies() {
        getDriver().manage().deleteAllCookies();
    }

    public void get(String url) {
        getDriver().get(url);
    }

    public void navigateToUrl(String url) {
        getDriver().navigate().to(url);
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    public String getUrl() {
        return getDriver().getCurrentUrl();
    }

    public void implicitWait(long seconds) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public void waitForPageLoad(long seconds) {
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seconds));
    }

    public void explicitWait(WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public String getWindow() {
        return getDriver().getWindowHandle();
    }

    public Set<String> getWindows() {
        return getDriver().getWindowHandles();
    }

    public void closeBrowser() {
        getDriver().close();
    }

    public void quitBrowser() {
        getDriver().quit();
    }
}
