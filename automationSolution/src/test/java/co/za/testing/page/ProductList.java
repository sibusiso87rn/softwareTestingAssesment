package co.za.testing.page;

import co.za.testing.common.CommonFunctions;
import co.za.testing.core.AbstractBasePage;
import co.za.testing.core.bean.DriverCreatedCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Conditional(DriverCreatedCondition.class)
public class ProductList extends AbstractBasePage {

    private static final Logger logger
            = LoggerFactory.getLogger(LogInPage.class);

    private static int addedItems = 0;

    private ArrayList<String> selectedProductList =  new ArrayList<>();

    @FindBy(css = "div[class='inventory_item_name']")
    private List<WebElement> lblItemName;

    @FindBy(css = "div[class='inventory_item_desc']")
    private List<WebElement> lblItemDescription;

    @FindBy(css = "div[class='inventory_item_price']")
    private List<WebElement> lblItemPrice;

    @FindBy(css = "button[data-test^='add-to-cart']")
    private List<WebElement> btnAddToCart;

    private List<WebElement> getLblItemName() {
        return lblItemName;
    }

    private List<WebElement> getLblItemDescription() {
        return lblItemDescription;
    }

    private List<WebElement> getLblItemPrice() {
        return lblItemPrice;
    }

    private List<WebElement> getBtnAddToCart() {
        return btnAddToCart;
    }

    public ProductList validateTheresShoppingItems(){
        logger.info("Validate there's a list of shopping items");
        CommonFunctions.validateListNotEmpty(getLblItemName());
        return this;
    }

    public ProductList addToCart(int index){

        String cartItem = "";

        if(addedItems == 0){
            cartItem = getLblItemName().get(index).getText();
        }else{
            String   attribute = getBtnAddToCart().get(index).getAttribute("id");
            cartItem = getDriver().findElement(By.xpath("//button[@id='"+attribute+"']/parent::div/preceding-sibling::div/a")).getText();
        }

        CommonFunctions.clickElement(getBtnAddToCart().get(index));
        logger.info("Added item {} to cart, item name is [{}]", index,cartItem);

        selectedProductList.add(cartItem);
        logger.info("Item [{}] added to our temp list ", cartItem);

        addedItems++;
        return this;
    }

    public ArrayList<String> getSelectedProductList(){
        return selectedProductList;
    }

    @Override
    public ProductList waitForPageToLoad() {
        return this;
    }
}
