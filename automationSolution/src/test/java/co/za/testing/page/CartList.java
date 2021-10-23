package co.za.testing.page;

import co.za.testing.common.CommonFunctions;
import co.za.testing.common.StringHelperFunctions;
import co.za.testing.core.AbstractBasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class CartList extends AbstractBasePage {

    @FindBy(css = "span[class='title']")
    private WebElement hdrPageTitle;

    @FindBy(css = "div[class='inventory_item_name']")
    private List<WebElement> lblItemName;

    @FindBy(css = "div[class='inventory_item_price']")
    private List<WebElement> lblItemPrice;

    @FindBy(css = "button[class='btn btn_action btn_medium checkout_button']")
    private WebElement btnCheckout;

    private double cartTotal = 0.0;

    private static final Logger logger
            = LoggerFactory.getLogger(CartList.class);

    private WebElement getHdrPageTitle() {
        return hdrPageTitle;
    }

    private List<WebElement> getLblItemName() {
        return lblItemName;
    }

    private List<WebElement> getLblItemPrice() {
        return lblItemPrice;
    }

    private WebElement getBtnCheckout() {
        return btnCheckout;
    }

    public CartList validateCartListPage(){
        logger.info("Validate cart page");
        CommonFunctions.validateText(getHdrPageTitle(),"YOUR CART");
        return this;
    }

    public CartList validateCartItemAvailable(List<String> expectedItems){
        CommonFunctions.validateListContains(getLblItemName(),expectedItems);
        return this;
    }

    public CartList validateCartSize(int expectedCartSize){
        logger.info("Validating if the cart has size {}",expectedCartSize);
        CommonFunctions.validateListHasSize(getLblItemName(),expectedCartSize);
        return this;
    }

    public CartList computeCartTotal(){

        logger.info("Computing cart total");

        List<String> cartTotalList = CommonFunctions.
                getListElementText(getLblItemPrice());

        cartTotal = StringHelperFunctions.computeListTotal(cartTotalList);

        logger.info("Computed cart total {}",cartTotal);

       return this;
    }

    public double getCartTotal() {
        return cartTotal;
    }


    public CartList checkOut(){
        logger.info("Checking out cart");
        CommonFunctions.clickElement(getBtnCheckout());
        return this;
    }

    @Override
    public CartList waiforPageToLoad() {
        CommonFunctions.getFluentWait().until(ExpectedConditions.visibilityOf(getHdrPageTitle()));
        return this;
    }
}