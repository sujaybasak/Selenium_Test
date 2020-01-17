package example;

import java.io.File;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assesmenttest {

	public static void takepicture(WebDriver driver, String filename) throws Exception {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(".\\screenshots\\" + filename + ".png"));
	}

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Desktop\\Share\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.amazon.in");
		driver.manage().window().maximize();
		String parent = driver.getWindowHandle();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Watches");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"/html/body/div[1]/div[1]/div[1]/div[2]/div/span[4]/div[1]/div[4]/div/span/div/div/div/div/div[2]/div/span/a/div/img"))
				.click();
		Set<String> childwindows = driver.getWindowHandles();
		for (String window : childwindows) {
			if (!parent.equals(window)) {
				driver.switchTo().window(window);
				driver.manage().window().maximize();
				takepicture(driver, "img");
				Thread.sleep(3000);
				driver.close();
			}
		}
		driver.switchTo().window(parent);
		Thread.sleep(3000);

		driver.close();
	}

}
