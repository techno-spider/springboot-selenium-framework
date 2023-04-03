package com.testscripts;

import com.seleniumspringboot.utils.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.annotation.PostConstruct;

@SpringBootTest(classes = BrowserFactory.class)
public class SpringbootSeleniumTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ThreadLocal<WebDriver> driver;

    @Autowired
    private BrowserFactory browserFactory;

    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        browserFactory.setDriver(browserFactory.webDriver());
        browserFactory.setBrowser(browser);
    }

    @Test
    public void test1() {
        browserFactory.get("https://automation-realm.netlify.app/");
    }

    @Test
    public void test2() {
        Assert.assertEquals(browserFactory.getPageTitle(), "iShop - Online Shopping Store", "Page title is not matched.");
    }

    @AfterClass
    public void tearDown() {
        browserFactory.quitBrowser();
    }
}
