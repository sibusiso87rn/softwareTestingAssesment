package co.za.testing.demo;

import co.za.testing.core.AbstractTest;
import co.za.testing.core.bean.DriverCreatedCondition;
import co.za.testing.page.LogInPage;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

@Component
@Conditional(DriverCreatedCondition.class)
public class WebTests extends AbstractTest {

    @Test
    public void navigateToLandingPage(){
        new LogInPage()
                .waitForPageToLoad()
                .enterUserNameAndPassword("standard_user","secret_sauce")
                .clickLogInButton();
    }

}
