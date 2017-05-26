package autoTestingAssessment;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResults {
	
	WebDriver driver;
	Actions builder;
		
		@FindBy(className = "login")
		WebElement loginButton;

		@FindBy(className = "logo img-responsive")
		WebElement logo;

		@FindBy(className = "title")
		WebElement title;
		
		@FindBy(id = "search_query_top")
		WebElement searchbar;
		
		@FindBy(name = "submit_search")
		WebElement submitSearch;
		
		@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div[2]/ul/li/div/div[2]/div[2]/a[1]/span")
		WebElement addToCartButton;
		
		public SearchResults(WebDriver driver) {
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
		
		public void setsearch(String searchTerm){
			searchbar.sendKeys(searchTerm);
			submitSearch.click();
		}
		
		public void clickAddtoCart(){
			addToCartButton.click();
		}

}
