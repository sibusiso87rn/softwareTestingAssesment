package co.za.testing.common;

import co.za.testing.core.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CommonFunctions {

    private static final int WAIT_TIME = 10;
    private static final Logger logger
            = LoggerFactory.getLogger(CommonFunctions.class);

    public static FluentWait getFluentWait(){
        return new FluentWait<>(WebDriverFactory.getInstance().getThreadLocalWebDriver())
                .withTimeout(Duration.ofSeconds(WAIT_TIME))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class);
    }

    public static void waitElementClickable(WebElement webElement){
        getFluentWait().until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void clickElement(WebElement webElement){
        waitElementClickable(webElement);
        webElement.click();
    }

    public static void input(WebElement webElement,String inputText){
        logger.debug("Executing Input Text on element {}",webElement.toString());
        webElement.sendKeys(inputText);
    }

    public static void clickElement(By selector){
        clickElement(getDriver().findElement(selector));
    }

    public static void validateText(WebElement element, String expectedText){
        logger.info(" Performing Text Compare, Actual ["+element.getText()+"] Expecting [" + expectedText+"]");
        getFluentWait().until(ExpectedConditions.visibilityOf(element));
        assertThat(element.getText(), is(equalTo(expectedText)));
    }

    public static void compareNumber(double actual, double expected){
        logger.info(" Performing Number Compare, Actual ["+actual+"] Expecting ["+expected+"]");
        assertThat(actual, comparesEqualTo(expected));
    }

    public static void validateListContains(List<WebElement> collection,String expectedItem) {
        logger.info("Validating if the list with size {} has item {}",collection.size(),expectedItem);
        assertThat(collection.stream().
                        map(WebElement::getText)
                        .collect(Collectors.toList()),
                hasItem(expectedItem));
    }

    public static List<String> getListElementText(List<WebElement> collection){
        return collection
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public static void validateListContains(List<WebElement> collection,List<String> expectedItemList) {
        List<String> actualItemList = collection
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        logger.debug("Actual {} \n Expected {}",actualItemList,expectedItemList);
        logger.debug("Actual size {} Expected size {}",actualItemList.size(),expectedItemList.size());
        expectedItemList.forEach(expectedItem -> validateListContains(collection,expectedItem));
    }

    public static void validateListNotEmpty(List<WebElement> collection) {
        logger.info("Validating if the list is not empty, list size is {}", collection.size());
        assertThat("List is empty ", collection.toArray(), is(arrayWithSize(greaterThan(0))));
    }

    public static void validateListHasSize(List<WebElement> collection, int expectedSize) {
        logger.info("Validating if the list with size {} has expected size {}", collection.size(),expectedSize);
        assertThat(collection, hasSize(expectedSize));
    }

    private static WebDriver getDriver(){
        return WebDriverFactory.getInstance().getThreadLocalWebDriver();
    }

}
