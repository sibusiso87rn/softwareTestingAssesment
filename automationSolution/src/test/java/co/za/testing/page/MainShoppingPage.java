package co.za.testing.page;

import co.za.testing.common.CommonFunctions;
import co.za.testing.core.AbstractBasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainShoppingPage extends AbstractBasePage {

    @FindBy(css = "span[class='title']")
    private WebElement hdrPageTitle;

    @FindBy(css = "a[class='shopping_cart_link']")
    private WebElement lnkCart;


    private static final Logger logger
            = LoggerFactory.getLogger(MainShoppingPage.class);

    private WebElement getHdrPageTitle() {
        return hdrPageTitle;
    }

    private WebElement getLnkCart() {
        return lnkCart;
    }

    public MainShoppingPage validatePage(){
        logger.info("Viewing cart items");
        CommonFunctions.validateText(getHdrPageTitle(),"PRODUCTS");
        return this;
    }

    public MainShoppingPage viewCartItems(){
        logger.info("Viewing cart items");
        CommonFunctions.clickElement(getLnkCart());
        return this;
    }

    public MainShoppingPage validateCartItemCount(int expectedCount){
        logger.info("Validating if the cart has item count of {}",expectedCount);
        CommonFunctions.validateText(lnkCart,Integer.toString(expectedCount));
        return this;
    }

    @Override
    public MainShoppingPage waiforPageToLoad() {
        CommonFunctions.getFluentWait().until(ExpectedConditions.visibilityOf(getHdrPageTitle()));
        return this;
    }
}
