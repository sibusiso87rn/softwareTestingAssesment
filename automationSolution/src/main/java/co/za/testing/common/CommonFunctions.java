package co.za.testing.common;

import co.za.testing.core.WebDriverFactory;
import co.za.testing.aspect.annotations.LogExecutionTime;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Component
public class CommonFunctions {

    private final int WAIT_TIME = 10;
    private final Logger logger = LoggerFactory.getLogger(CommonFunctions.class);

    public FluentWait getFluentWait(){
        return new FluentWait<>(WebDriverFactory.getInstance().getThreadLocalWebDriver())
                .withTimeout(Duration.ofSeconds(WAIT_TIME))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class);
    }

    public void waitElementClickable(WebElement webElement){
        getFluentWait().until(ExpectedConditions.elementToBeClickable(webElement));
    }

    @LogExecutionTime
    public void clickElement(WebElement webElement){
        waitElementClickable(webElement);
        webElement.click();
    }

    @LogExecutionTime
    public void input(WebElement webElement,String inputText){
        logger.debug("Executing Input Text on element {}",webElement.toString());
        webElement.sendKeys(inputText);
    }

    public void clickElement(By selector){
        clickElement(getDriver().findElement(selector));
    }

    public void validateText(WebElement element, String expectedText){
        logger.info(" Performing Text Compare, Actual ["+element.getText()+"] Expecting [" + expectedText+"]");
        getFluentWait().until(ExpectedConditions.visibilityOf(element));
        assertThat(element.getText(), is(equalTo(expectedText)));
    }

    public void compareNumber(double actual, double expected){
        logger.info(" Performing Number Compare, Actual ["+actual+"] Expecting ["+expected+"]");
        assertThat(actual, comparesEqualTo(expected));
    }

    public void validateListContains(List<WebElement> collection,String expectedItem) {
        logger.info("Validating if the list with size {} has item {}",collection.size(),expectedItem);
        assertThat(collection.stream().
                        map(WebElement::getText)
                        .collect(Collectors.toList()),
                hasItem(expectedItem));
    }

    public List<String> getListElementText(List<WebElement> collection){
        return collection
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void validateListContains(List<WebElement> collection,List<String> expectedItemList) {
        List<String> actualItemList = collection
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        logger.debug("Actual {} \n Expected {}",actualItemList,expectedItemList);
        logger.debug("Actual size {} Expected size {}",actualItemList.size(),expectedItemList.size());
        expectedItemList.forEach(expectedItem -> validateListContains(collection,expectedItem));
    }

    public void validateListNotEmpty(List<WebElement> collection) {
        logger.info("Validating if the list is not empty, list size is {}", collection.size());
        assertThat("List is empty ", collection.toArray(), is(arrayWithSize(greaterThan(0))));
    }

    public void validateListHasSize(List<WebElement> collection, int expectedSize) {
        logger.info("Validating if the list with size {} has expected size {}", collection.size(),expectedSize);
        assertThat(collection, hasSize(expectedSize));
    }

    private WebDriver getDriver(){
        return WebDriverFactory.getInstance().getThreadLocalWebDriver();
    }

}
