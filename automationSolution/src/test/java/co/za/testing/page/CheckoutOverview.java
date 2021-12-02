package co.za.testing.page;

import co.za.testing.common.StringHelperFunctions;
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
public class CheckoutOverview extends AbstractBasePage {

    @FindBy(css = "span[class='title']")
    private WebElement hdrPageTitle;

    @FindBy(css = "div[class='summary_total_label']")
    private WebElement lblTotalWithTax;

    @FindBy(css = "div[class='summary_subtotal_label']")
    private WebElement lblTotalWithoutTax;

    @FindBy(id = "finish")
    private WebElement btnFinish;

    private final Logger logger
            = LoggerFactory.getLogger(CheckoutOverview.class);

    private WebElement getHdrPageTitle() {
        return hdrPageTitle;
    }

    private WebElement getLblTotalWithTax() {
        return lblTotalWithTax;
    }

    private WebElement getLblTotalWithoutTax() {
        return lblTotalWithoutTax;
    }

    public CheckoutOverview validateCheckoutOverviewPageTitle(){
        getCommonFunction().validateText(getHdrPageTitle(),"CHECKOUT: OVERVIEW");
        return this;
    }

    private WebElement getBtnFinish() {
        return btnFinish;
    }

    public CheckoutOverview clickFinishButton(){
        logger.info("About to click the checkout button");
        getCommonFunction().clickElement(getBtnFinish());
        return this;
    }

    public CheckoutOverview validateChargedAmount(double expectedTotal){
        logger.info("About validate the order total");
        String currentTotal = getLblTotalWithoutTax().getText().replace("Item total: ","");
        getCommonFunction().compareNumber(StringHelperFunctions.getDoubtAmount(currentTotal),expectedTotal);
        return this;
    }

    @Override
    public CheckoutOverview waitForPageToLoad() {
        getCommonFunction().getFluentWait().until(ExpectedConditions.visibilityOf(getHdrPageTitle()));
        return this;
    }
}
