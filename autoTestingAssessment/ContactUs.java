package autoTestingAssessment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUs {

	WebDriver driver;
	Actions builder;
		
		@FindBy(className = "login")
		WebElement loginButton;

		@FindBy(className = "logo img-responsive")
		WebElement logo;

		@FindBy(className = "title")
		WebElement title;
				
		@FindBy(id = "id_contact")
		WebElement subject;
		
		@FindBy(id = "email")
		WebElement emailAddress;
		
		@FindBy(id = "id_order")
		WebElement orderRef;
		
		@FindBy(id = "message")
		WebElement message;
		
		@FindBy(id = "submitMessage")
		WebElement sendButton;
		
		public ContactUs(WebDriver driver) {
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
		
		public void enterDetails(String sub, String email, String ref, String text) {
			subject.sendKeys(sub);
			emailAddress.sendKeys(email);
			orderRef.sendKeys(ref);
			message.sendKeys(text);
		}
		
		public void send(){
			sendButton.click();
		}
		
		
}
