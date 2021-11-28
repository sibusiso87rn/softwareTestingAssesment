package co.za.testing.core;

import co.za.testing.core.bean.ApplicationContext;
import co.za.testing.core.bean.DriverCreatedCondition;
import co.za.testing.core.bean.TestBean;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(DriverCreatedCondition.class)
public abstract class AbstractBasePage {

    private TestBean testBean = ApplicationContext.getTestBean();

    private final Logger logger
            = LoggerFactory.getLogger(AbstractBasePage.class);

    public AbstractBasePage(){
        logger.info("Getting driver for page");
        logger.info("Creating page object \t["+this.getClass().getSimpleName()+"]");
        PageFactory.initElements(testBean.getWebDriver(),this);
    }

    public WebDriver getDriver(){
        return testBean.getWebDriver();
    }

    public abstract AbstractBasePage waitForPageToLoad();

}
