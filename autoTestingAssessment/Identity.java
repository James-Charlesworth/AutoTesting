package autoTestingAssessment;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Identity {

	WebDriver driver;
	Actions builder;
		
		@FindBy(className = "logo img-responsive")
		WebElement logo;

		@FindBy(className = "title")
		WebElement title;
		
		@FindBy(id = "search_query_top")
		WebElement searchbar;
		
		@FindBy(name = "submit_search")
		WebElement submitSearch;
		
		@FindBy(className = "logout")
		WebElement logout;
		
		@FindBy(id = "firstname")
		WebElement firstname;
		
		@FindBy(id = "lastname")
		WebElement lastname;
		
		@FindBy(id = "old_passwd")
		WebElement oldPass;
		
		@FindBy(id = "passwd")
		WebElement newPass;
		
		@FindBy(id = "confirmation")
		WebElement confNewPass;
		
		@FindBy(name = "submitIdentity")
		WebElement saveButton;
		
		public Identity(WebDriver driver) {
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
		
		public void logout(){
			logout.click();
		}
		
		public void enterDetails(String first, String sur, String old, String newP){
			
			firstname.sendKeys(Keys.CONTROL+"a" + Keys.BACK_SPACE);
			firstname.sendKeys(first);
			lastname.sendKeys(Keys.CONTROL+"a", Keys.BACK_SPACE);
			lastname.sendKeys(sur);
			oldPass.sendKeys(old);
			newPass.sendKeys(newP);
			confNewPass.sendKeys(newP);
			saveButton.click();
		}
		
		
}
