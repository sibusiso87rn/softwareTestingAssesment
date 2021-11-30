package co.za.testing.page;

import co.za.testing.common.StringHelperFunctions;
import co.za.testing.core.AbstractBasePage;
import co.za.testing.aspect.annotations.Entity;
import co.za.testing.core.bean.DriverCreatedCondition;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Conditional(DriverCreatedCondition.class)
@Entity
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

    private final Logger logger
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
        getCommonFunction().validateText(getHdrPageTitle(),"YOUR CART");
        return this;
    }

    public CartList validateCartItemAvailable(List<String> expectedItems){
        getCommonFunction().validateListContains(getLblItemName(),expectedItems);
        return this;
    }

    public CartList validateCartSize(int expectedCartSize){
        logger.info("Validating if the cart has size {}",expectedCartSize);
        getCommonFunction().validateListHasSize(getLblItemName(),expectedCartSize);
        return this;
    }

    public CartList computeCartTotal(){

        logger.info("Computing cart total");

        List<String> cartTotalList = getCommonFunction().
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
        getCommonFunction().clickElement(getBtnCheckout());
        return this;
    }

    @Override
    public CartList waitForPageToLoad() {
        getCommonFunction().getFluentWait().until(ExpectedConditions.visibilityOf(getHdrPageTitle()));
        return this;
    }
}
