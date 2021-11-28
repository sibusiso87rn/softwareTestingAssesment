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
public class CheckoutInfoPage extends AbstractBasePage {

    @FindBy(css = "span[class='title']")
    private WebElement hdrPageTitle;

    @FindBy(id = "first-name")
    private WebElement txtFirstName;

    @FindBy(id = "last-name")
    private WebElement txtLastName;

    @FindBy(id = "postal-code")
    private WebElement txtPostalCode;

    @FindBy(id = "continue")
    private WebElement btnContinue;

    private static final Logger logger
            = LoggerFactory.getLogger(CheckoutInfoPage.class);

    private WebElement getHdrPageTitle() {
        return hdrPageTitle;
    }

    private WebElement getTxtFirstName() {
        return txtFirstName;
    }

    private WebElement getTxtLastName() {
        return txtLastName;
    }

    private WebElement getTxtPostalCode() {
        return txtPostalCode;
    }

    private WebElement getBtnContinue() {
        return btnContinue;
    }

    public CheckoutInfoPage validateCheckoutInfoPageTitle(){
        CommonFunctions.validateText(getHdrPageTitle(),"CHECKOUT: YOUR INFORMATION");
        return this;
    }

    public CheckoutInfoPage enterCustomerInformation(String firstName,String lastName,String postalCode){
        logger.info("About to log to enter customer info , using firstName [{}], lastName [{}] and postalCode [{}]",firstName,lastName,postalCode);

        logger.info("Enter firstName {}",firstName);
        CommonFunctions.input(getTxtFirstName(),firstName);

        logger.info("Enter lastName {}",lastName);
        CommonFunctions.input(getTxtLastName(),lastName);

        logger.info("Enter postalCode {}",postalCode);
        CommonFunctions.input(getTxtPostalCode(),postalCode);
        return this;
    }

    public CheckoutInfoPage clickContinueButton(){
        logger.info("About to click the continue button");
        CommonFunctions.clickElement(getBtnContinue());
        return this;
    }

    @Override
    public CheckoutInfoPage waitForPageToLoad() {
        CommonFunctions.getFluentWait().until(ExpectedConditions.visibilityOf(getHdrPageTitle()));
        return this;
    }
}
