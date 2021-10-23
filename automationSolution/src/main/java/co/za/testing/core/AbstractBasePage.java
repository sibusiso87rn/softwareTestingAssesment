package co.za.testing.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBasePage {

    private final WebDriver driver;
    private static final Logger logger
            = LoggerFactory.getLogger(AbstractBasePage.class);

    public AbstractBasePage(){
        logger.info("Getting driver for page");
        driver = WebDriverFactory.getInstance().getThreadLocalWebDriver();
        logger.info("Creating page object \t["+this.getClass().getSimpleName()+"]");
        PageFactory.initElements(driver,this);
    }

    protected WebDriver getDriver(){
        return driver;
    }

    public abstract AbstractBasePage waiforPageToLoad();

}
