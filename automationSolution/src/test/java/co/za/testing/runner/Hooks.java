package co.za.testing.runner;

import co.za.testing.common.StringHelperFunctions;
import co.za.testing.core.bean.ApplicationContext;
import co.za.testing.core.bean.DriverCreatedCondition;
import co.za.testing.core.bean.TestBean;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(DriverCreatedCondition.class)
public class Hooks {

    private final Logger logger
            = LoggerFactory.getLogger("JSONLOGGER");

    private final TestBean testBean = ApplicationContext.getTestBean();

    @Before
    public void beforeScenario(Scenario scenario){
        logger.info("------------------------------");
        logger.info(scenario.getName() + " - Status - " + scenario.getStatus());
        logger.info("------------------------------");

        createDriver();
    }

    @After
    public void afterScenario(Scenario scenario) {
        logger.info("------------------------------");
        logger.info(scenario.getName() + " - Status - " + scenario.getStatus());
        logger.info("------------------------------");
        takeScreenshot(scenario);
        testBean.quit();
    }

    private void createDriver(){
        testBean.createWebDriver();
        testBean.deleteCookies();
        testBean.navigateLanding();
    }

    private void takeScreenshotOnFailure(Scenario scenario){
        logger.info("Taking screenshot IF Test Failed");
        if (scenario.isFailed()) {
            takeScreenshot(scenario);
        }
    }

    private void takeScreenshot(Scenario scenario) {
        logger.info("Taking screenshot for scenario [{}]",scenario.getName());
        try {
            byte[] screenShot = testBean.getTakeScreenShot();
            scenario.attach(screenShot,"image/png", StringHelperFunctions.generateScreenshotName());
        } catch (Exception e) {
            logger.error("Failed to take screenshot , {}", e.getMessage());
        }
    }

}
