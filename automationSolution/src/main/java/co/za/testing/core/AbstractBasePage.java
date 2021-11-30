package co.za.testing.core;

import co.za.testing.common.CommonFunctions;
import co.za.testing.core.bean.ApplicationContext;
import co.za.testing.core.bean.DriverCreatedCondition;
import co.za.testing.core.bean.TestBean;
import co.za.testing.core.bean.TestConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;

@Component
@Conditional(DriverCreatedCondition.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes= TestConfiguration.class)
public abstract class AbstractBasePage {

    private final Logger logger  = LoggerFactory.getLogger(AbstractBasePage.class);
    private TestBean testBean    = ApplicationContext.getTestBean();

    @Resource
    private CommonFunctions commonFunctions;

    public AbstractBasePage(){
        logger.info("Getting driver for page");
        logger.info("Creating page object \t["+this.getClass().getSimpleName()+"]");
        PageFactory.initElements(testBean.getWebDriver(),this);
    }

    public WebDriver getDriver(){
        return testBean.getWebDriver();
    }

    public abstract AbstractBasePage waitForPageToLoad();

    protected CommonFunctions getCommonFunction(){
        return ApplicationContext.getCommonFunctions();
    }

}
