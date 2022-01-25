package waits;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class Waits {
	
	static WebDriver driver;
	
	public static void main(String[] args) {
//		By elementloc = By.xpath("//h3");
//		waitForElementPresent(driver, 30, elementloc).click();
//		waitForElementWithFluentWait(driver, 30, 2, elementloc);	
	}
	
	/**
	 * Explicit Wait --> Wait for Element Present
	 * @param driver
	 * @param timeout
	 * @param locator
	 * @return element
	 */
	private static WebElement waitForElementPresent(WebDriver driver, int timeout, By locator) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
		return element;
	}
	
	/**
	 * Explicit Wait --> Wait for Element Visible
	 * @param driver
	 * @param timeout
	 * @param locator
	 * @return element
	 */
	private static WebElement waitForElementVisible(WebDriver driver, int timeout, By locator) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
		return element;
	}

	/**
	 * Fluent Wait utility library
	 * @param driver
	 * @param timeout
	 * @param polling
	 * @param locator
	 * @return element
	 * @author Navdeep Rathore
	 */
	public static WebElement waitForElementWithFluentWait(WebDriver driver, int timeout, int polling, final By locator) {
		
		Wait<WebDriver> myWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(polling))
				.ignoring(NoSuchElementException.class);
		
		WebElement element = myWait.until(new Function<WebDriver, WebElement>() {

			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}
	
	

}
