package dropdowns;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutoCompletionDropDown {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.get("https://www.twoplugs.com/");
		
		driver.findElement(By.xpath("//a[text()='Live Posting']")).click();
		
		WebElement searchBox = driver.findElement(By.id("autocomplete"));
		searchBox.clear();
		searchBox.sendKeys("Toronto");
		
		Thread.sleep(2000);
		
		String attrText;
		do {
			searchBox.sendKeys(Keys.ARROW_DOWN);
			attrText = searchBox.getAttribute("value");
			
			if (attrText.equals("Toronto, OH, USA")) {
				searchBox.sendKeys(Keys.ENTER);
				break;
			}
			Thread.sleep(2000);
		} while (!attrText.isEmpty());
	}

}
