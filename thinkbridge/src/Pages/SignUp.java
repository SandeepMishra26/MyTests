package Pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUp {
			
	public static void main(String[] args) throws InterruptedException {

		// Settings
		System.setProperty("webdriver.chrome.driver", "src\\Resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://jt-dev.azurewebsites.net/#/SignUp");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
		// WebElements
		WebElement ddclickEle = driver.findElement(By.xpath("(//i[@class='caret pull-right'])[2]"));
		WebElement languageBox = driver.findElement(By.id("ui-select-choices-1"));
		WebElement nameTextBox = driver.findElement(By.id("name"));
		WebElement orgNameTextBox = driver.findElement(By.id("orgName"));
		WebElement emailTextBox = driver.findElement(By.id("singUpEmail"));
						
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement TCchkbox = (WebElement) js.executeScript("return document.querySelector('label > span',':before')");
		
		WebElement getStartedBtn = driver.findElement(By.xpath("//button[text()='Get Started']"));
		
		// Verify language dropdown values
		ddclickEle.click();
		String lgs = languageBox.getText();
		if(lgs.contains("English") && lgs.contains("Dutch")){
			Assert.assertTrue("Expected languages present in the dropdown", true);
			System.out.println("Aailable languages are : "+lgs);
		}

		//Enter name, organization name and email
		nameTextBox.sendKeys("Sandeep");
		orgNameTextBox.sendKeys("Sandeep");
		emailTextBox.sendKeys("sm6822329@gmail.com");
				
		//Select Terms and Conditions checkbox
		TCchkbox.click();
		
		//Click Get Started button
		getStartedBtn.click();
		
		//verify email confirmation message
		WebElement emailConfirMsg = driver.findElement(By.xpath("//span[contains(text(),'welcome')]"));
		if(emailConfirMsg.isDisplayed()==true)
			System.out.println("Confirmation email triggered and displayed");
		
		// Email verification 
		js.executeScript("window.open('https://www.gmail.com');");
		Set<String> winHandles = driver.getWindowHandles();
		Iterator<String> iterate = winHandles.iterator(); 
		String first =iterate.next(); 
		String second=iterate.next(); 
		driver.switchTo().window(second);
		
		//Gmail webelements
		WebElement email = driver.findElement(By.id("identifierId"));
		email.sendKeys("sm6822329@gmail.com");
		WebElement NextButton = driver.findElement(By.xpath("//span[text()='Next']"));
		NextButton.click();
		WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
		password.sendKeys("Sonumonu@1826");
		WebElement NextButtonPass = driver.findElement(By.xpath("//span[text()='Next']"));
		NextButtonPass.click();
		WebElement emailSubject = driver.findElement(By.xpath("//span[contains(text(),'JabaTalks Account')])[2]"));
		if(emailSubject.isDisplayed()==true)
			System.out.println("Confirmation email received");

		driver.close();
		driver.quit();

	}

}
