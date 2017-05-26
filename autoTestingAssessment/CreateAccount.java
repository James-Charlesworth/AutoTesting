package autoTestingAssessment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccount {
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
		
		@FindBy(id = "customer_firstname")
		WebElement firstName;
		
		@FindBy(id = "customer_lastname")
		WebElement lastName;
		
		@FindBy(id = "passwd")
		WebElement password;
		
		@FindBy(id = "alias")
		WebElement alias;
		
		@FindBy(id = "address1")
		WebElement address;
		
		@FindBy(id = "postcode")
		WebElement city;
		
		@FindBy(id = "city")
		WebElement zip;
		
		@FindBy(id = "phone_mobile")
		WebElement mobileNumber;
		
		@FindBy(id = "id_state")
		WebElement state;
		
		@FindBy(id = "submitAccount")
		WebElement createAccountButton;
		
		public CreateAccount(WebDriver driver) {
			this.driver = driver;
			this.builder = new Actions(driver);
			try {
				Thread.sleep(3000);
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
		
		public void enterDetails(String first, String sur, String pass, String alias1, String address1, String postcode, String city1, String mobile, String state1){
			firstName.sendKeys(first);
			lastName.sendKeys(sur);
			password.sendKeys(pass);
			alias.sendKeys(alias1);
			address.sendKeys(address1);
			city.sendKeys(postcode);
			zip.sendKeys(city1);
			mobileNumber.sendKeys(mobile);
			state.sendKeys(state1);
		}

		public void clickCreateAccountButton() {
			createAccountButton.click();
		}
		

}
