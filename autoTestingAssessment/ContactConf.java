package autoTestingAssessment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactConf {

	WebDriver driver;
	Actions builder;
		
		@FindBy(className = "login")
		WebElement loginButton;

		@FindBy(className = "logo img-responsive")
		WebElement logo;

		@FindBy(className = "title")
		WebElement title;
				
		@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/p")
		WebElement confirmation;
		
		public ContactConf(WebDriver driver) {
			this.driver = driver;
			this.builder = new Actions(driver);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// initalising all the elements with @Findby
			PageFactory.initElements(driver, this);
		}
		
		public String getTitle() {
			return driver.getTitle();
		}
		
		public void signIn(){
			loginButton.click();
		}
		
		public String getMessage(){
			return confirmation.getText();
		}
}
