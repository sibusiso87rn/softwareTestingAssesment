import co.za.testing.page.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.ArrayList;

public class ShoppingStepdefs {

    private ArrayList<String> cartList = new ArrayList<>();
    private double expectedCartTotal = 0.0;

    @Given("The user navigates to the Shopping Website")
    public void theUserNavigatesToTheShoppingWebsite() {
        new LogInPage().waiforPageToLoad();
    }

    @And("The user enters the username {string} and password {string}")
    public void theUserEntersTheUsernameAndPassword(String username, String password) {
        new LogInPage().enterUserNameAndPassword(username,password);
    }

    @And("The user is navigated to the login page")
    public void theUserIsNavigatedToTheLoginPage() {
        new LogInPage().isOnLogonPage();
    }

    @And("The user clicks the login button")
    public void theUserClicksTheLoginButton() {
        new LogInPage().clickLogInButton();
    }

    @And("The user adds item {string} to the shopping cart")
    public void theUserAddsItemToTheShoppingCart(String cartItem) {
        cartList.addAll(new ProductList().addToCart(Integer.parseInt(cartItem)).getSelectedProductList());
    }

    @Then("The cart icon displays {string} items on the list")
    public void theCartIconDisplaysItemsOnTheList(String expectedCartItemCount) {
        new MainShoppingPage().validateCartItemCount(Integer.parseInt(expectedCartItemCount));
    }

    @And("The user clicks the cart icon")
    public void theUserClicksTheCartIcon() {
        new MainShoppingPage().viewCartItems();
    }

    @Then("The user is navigated to the cart summary page")
    public void theUserIsNavigatedToTheCartSummaryPage() {
        new CartList().validateCartListPage();
    }

    @And("The user is presented with a correct list of cart items")
    public void theUserIsPresentedWithAListOfCartItems() {
        new CartList().validateCartItemAvailable(cartList).validateCartSize(2);
    }

    @And("The user clicks checkout button")
    public void theUserClicksCheckoutButton() {
        expectedCartTotal = new CartList().computeCartTotal().checkOut().getCartTotal();
    }

    @Then("The user is presented with shopping screen with a list of items to shop")
    public void theUserIsPresentedWithLoginScreenWithAListOfItemsToShop() {
        new MainShoppingPage()
                .waiforPageToLoad()
                .validatePage();
        new ProductList().validateTheresShoppingItems();
    }

    @And("The user is presented with the checkout info page")
    public void theUserIsPresentedWithTheCheckoutInfoPage() {
        new CheckoutInfoPage().waiforPageToLoad().validateCheckoutInfoPageTitle();
    }

    @And("The user enters the customer name {string} the customer surname {string} and the zipcode {string}")
    public void theUserEntersTheCustomerNameTheCustomerSurnameAndTheZipcode(String customerName, String customerSurname, String customerZipcode) {
        new CheckoutInfoPage().enterCustomerInformation(customerName,customerSurname,customerZipcode);
    }

    @And("The customer continue from the checkout information page")
    public void theCustomerContinueFromTheCheckoutInformationPage() {
        new CheckoutInfoPage().clickContinueButton();
    }

    @Then("The user is navigated to the checkout overview page")
    public void theUserIsNavigatedToTheCheckoutOverviewPage() {
        new CheckoutOverview().waiforPageToLoad().validateCheckoutOverviewPageTitle();
    }

    @And("The pre tax total item price is calculated correctly")
    public void thePreTaxTotalItemPriceIsCalculatedCorrectly() {
        new CheckoutOverview().validateChargedAmount(expectedCartTotal);
    }

    @And("The user clicks the finish button")
    public void theUserClicksTheFinishButton() {
        new CheckoutOverview().clickFinishButton();
    }
}
