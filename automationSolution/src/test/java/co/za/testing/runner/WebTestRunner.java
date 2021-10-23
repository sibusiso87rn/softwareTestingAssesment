package co.za.testing.runner;

import co.za.testing.core.WebDriverFactory;
import co.za.testing.core.reporter.CucumberReport;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.util.Properties;

@CucumberOptions(
        plugin  = {
                "pretty",
                "json:target/cucumber-report.json",
        },
        features    = {"src/test/resources/features" },
        glue        = {""},
        tags        = "@Regression"
)
public class WebTestRunner {


    private TestNGCucumberRunner testNGCucumberRunner;
    private static final Logger logger
            = LoggerFactory.getLogger(WebTestRunner.class);


    @BeforeClass(alwaysRun = true)
    @Parameters({"browser.version","browser"})
    public void setTestNGProperties(String browserVersion,String browser){
        logger.info("Test suit starting");
        WebDriverFactory.getInstance().createThreadProperties(getTestProperties(browserVersion,browser));
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass(){
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber scenarios", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickleEvent, FeatureWrapper cucumberFeature) throws Throwable {
        testNGCucumberRunner.runScenario(pickleEvent.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }

    @AfterSuite(alwaysRun = true)
    public void tearCreateReport() {
        //Create and finalize the report - This is done once, only after the tests have been completed.

        new CucumberReport().createReport();
    }


    private Properties getTestProperties(String browserVersion, String browser){
        Properties props = new Properties();
        props.setProperty("browser.version",browserVersion);
        props.setProperty("browser",browser);
        return props;
    }

}
