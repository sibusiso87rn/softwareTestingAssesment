package co.za.testing.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class WebDriverFactory {

    private static final Logger logger
            = LoggerFactory.getLogger(WebDriverFactory.class);

    private static WebDriverFactory webDriverFactoryInstance     = null;
    private static final ThreadLocal<WebDriver> webDriverThreadLocal         = new ThreadLocal<>();
    private static final ThreadLocal<Properties> driverPropertiesThreadLocal = new ThreadLocal<>();

    private WebDriverFactory(){
        driverPropertiesThreadLocal.set(new Properties());
        driverPropertiesThreadLocal.get().setProperty("driver.created","false");
    }

    public static WebDriverFactory getInstance(){
        if(webDriverFactoryInstance==null){
            logger.info("WebDriver factory is instance is null, creating new instance");
            webDriverFactoryInstance = new WebDriverFactory();
        }
        return webDriverFactoryInstance;
    }

    public WebDriver getThreadLocalWebDriver(){
        return webDriverThreadLocal.get();
    }

    public Properties getThreadLocalProperties(){
        return driverPropertiesThreadLocal.get();
    }

    public void quitThreadLocalWebDriver(){
        logger.debug("Quiting web driver");
        getThreadLocalWebDriver().quit();
        //Cleanup
        logger.debug("Removing driver instance");
        webDriverThreadLocal.remove();

    }

    public void removeThreadLocalProperties(){
        //Cleanup
        logger.debug("Removing properties instance");
        driverPropertiesThreadLocal.remove();
    }

    public void createThreadLocalDriver() throws Exception {

        logger.info("Creating thread local driver");

        //Get desired caps
        DesiredCapabilities desiredCapabilities = getDesiredCapabilities(getThreadLocalProperties().getProperty("browser.version"));

        //Set thread local browser
        webDriverThreadLocal.set(getLocalWebDriverObject(desiredCapabilities));
        driverPropertiesThreadLocal.get().setProperty("driver.created","true");
    }

    public void createThreadProperties(Properties driverProperties){
        logger.info("Setting properties");
        driverPropertiesThreadLocal.set(driverProperties);
    }

    private DesiredCapabilities getDesiredCapabilities(String browserVersion){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browser", "chrome");
        capabilities.setCapability("browser_version", browserVersion);
        return capabilities;
    }

    private WebDriver getLocalWebDriverObject(DesiredCapabilities capabilities){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1200");
        options.merge(capabilities);
        return new ChromeDriver(options);
    }

    public boolean driverCreated(){
        return Boolean.parseBoolean(getThreadLocalProperties().getProperty("driver.created"));
    }

    public boolean configsSet(){
        return Boolean.parseBoolean(getThreadLocalProperties().getProperty("configs.set"));
    }

}