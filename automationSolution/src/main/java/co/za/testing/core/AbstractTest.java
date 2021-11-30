package co.za.testing.core;

import co.za.testing.core.bean.TestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


@ContextConfiguration(locations = {"classpath:spring-bean.xml"})
@Component
public class AbstractTest extends AbstractTestNGSpringContextTests {

    private final Logger logger
            = LoggerFactory.getLogger(AbstractTest.class);

    private TestBean testBean = new TestBean();

    @BeforeTest(alwaysRun = true)
    public void initSpringProps() {
        logger.info("Creating driver");
        testBean.createWebDriver();
        testBean.deleteCookies();
        testBean.navigateLanding();
    }

    @AfterTest(alwaysRun = true)
    public void quitAppiumDriver() {
        logger.info("Quiting driver for Thread ID {}", Thread.currentThread().getId());
        testBean.quit();
    }
}
