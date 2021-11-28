package co.za.testing.page;

import co.za.testing.common.CommonFunctions;
import co.za.testing.core.AbstractBasePage;
import co.za.testing.core.bean.DriverCreatedCondition;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(DriverCreatedCondition.class)
public class LogInPage extends AbstractBasePage {

    private static final Logger logger
            = LoggerFactory.getLogger(LogInPage.class);


    @FindBy(className = "login_logo")
    private WebElement imgPageLogo;

    @FindBy(id = "user-name")
    private WebElement txtUsername;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(id = "login-button")
    private WebElement btnLogin;


    private WebElement getImgPageLogo() {
        return imgPageLogo;
    }

    private WebElement getTxtUsername() {
        return txtUsername;
    }

    private WebElement getTxtPassword() {
        return txtPassword;
    }

    private WebElement getBtnLogin() {
        return btnLogin;
    }

    @Override
    public LogInPage waitForPageToLoad() {
        logger.info("Waiting for page to load");
        CommonFunctions.getFluentWait().until(ExpectedConditions.visibilityOf(getImgPageLogo()));
        return this;
    }

    public LogInPage enterUserNameAndPassword(String username,String password){
        logger.info("About to log on user, using Username [{}] and Password [{}]",username,password);

        logger.info("Enter username {}",username);
        CommonFunctions.input(getTxtUsername(),username);

        logger.info("Enter password {}",password);
        CommonFunctions.input(getTxtPassword(),password);

        return this;
    }

    public LogInPage clickLogInButton(){
        logger.info("About to click the login button");
        CommonFunctions.clickElement(getBtnLogin());
        return this;
    }

    public LogInPage isOnLogonPage(){
        logger.info("Validate if we are on the login screen");
        CommonFunctions.waitElementClickable(getBtnLogin());
        return this;
    }

}
