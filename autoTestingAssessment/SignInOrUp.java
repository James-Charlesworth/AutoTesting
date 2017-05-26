package autoTestingAssessment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInOrUp {

	WebDriver driver;
	Actions builder;
		
		@FindBy(className = "login")
		WebElement loginButton;

		@FindBy(className = "logo img-responsive")
		WebElement logo;

		@FindBy(className = "title")
		WebElement title;
		
		@FindBy(id = "email_create")
		WebElement newEmail;
				
		@FindBy(id = "email")
		WebElement existingEmail;
		
		@FindBy(id = "passwd")
		WebElement password;
		
		@FindBy(id = "SubmitLogin")
		WebElement continueAsExisting;
		
		@FindBy(id = "SubmitCreate")
		WebElement signInButton;
		
		public SignInOrUp(WebDriver driver) {
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
		
		public void existingUser(String email, String pass){
			existingEmail.sendKeys(email);
			password.sendKeys(pass);
			continueAsExisting.click();
		}
		
		public void enterEmail(String email){
			newEmail.sendKeys(email);
			signInButton.click();
		}

}
