package co.za.testing.core.bean;

import co.za.testing.core.WebDriverFactory;
import co.za.testing.core.interfaces.IEnvironmentProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
@Scope("singleton")
public class TestBean {

    private final Logger logger = LogManager.getLogger(TestBean.class);
    private WebDriverFactory webDriverFactory ;
    private final IEnvironmentProperties iEnvironmentProperties = ConfigFactory.create(IEnvironmentProperties.class);

    public TestBean(){
        logger.info("Creating TestBean instance");
        webDriverFactory = WebDriverFactory.getInstance();
    }

    public WebDriver getWebDriver(){
        return webDriverFactory.getThreadLocalWebDriver();
    }

    public Properties getThreadLocalProperties(){
        return webDriverFactory.getThreadLocalProperties();
    }

    public void quit(){
        getWebDriver().quit();
    }

    public void createWebDriver(){
        try {
            logger.info("Creating driver");
            webDriverFactory.createThreadLocalDriver();
        } catch (Exception e) {
            logger.error("Failed to create driver");
            e.printStackTrace();
        }
    }

    public void deleteCookies(){
        getWebDriver().manage().deleteAllCookies();
    }

    public byte[] getTakeScreenShot(){
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public void navigate(String url){
        getWebDriver().navigate().to(url);
    }

    public void navigateLanding(){
        navigate(getDefaultUrl());
    }

    public void setTestProperty(String key, String value){
        logger.info("Setting test property key {} value {}",key, value);
        webDriverFactory.getThreadLocalProperties().setProperty(key,value);
    }

    public void setTestProperties(Properties properties){
        logger.info("Setting test properties");
        properties.forEach((key,value) -> logger.info("Key:{} \tValue:{}",key,value));
        webDriverFactory.createThreadProperties(properties);
    }

    public boolean driverCreated(){
        return webDriverFactory.driverCreated();
    }

    public boolean configsSet(){
        return webDriverFactory.configsSet();
    }

    public IEnvironmentProperties getDefaultConfigs(){
        return iEnvironmentProperties;
    }

    public String getDefaultUrl(){
        return iEnvironmentProperties.getDefaultApplicationUrl();
    }

    public String getDefaultBrowserVersion(){
        return iEnvironmentProperties.getDefaultBrowserVersion();
    }

    public String getDefaultBrowser(){
        return iEnvironmentProperties.getDefaultBrowser();
    }


}
