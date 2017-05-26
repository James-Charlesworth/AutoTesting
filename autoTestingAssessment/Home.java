package autoTestingAssessment;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {

WebDriver driver;
Actions builder;
	
	@FindBy(xpath = "/html/body/div/div[1]/header/div[2]/div/div/nav/div[1]/a")
	WebElement loginButton;

	@FindBy(className = "logo img-responsive")
	WebElement logo;

	@FindBy(className = "title")
	WebElement title;
	
	@FindBy(id = "search_query_top")
	WebElement searchbar;
	
	@FindBy(name = "submit_search")
	WebElement submitSearch;
	
	@FindBy(id = "contact-link")
	WebElement contactUs;
	
	public Home(WebDriver driver) {
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
	
	public void signIn(){
		loginButton.click();
	}
	
	public void setsearch(String searchTerm){
		searchbar.sendKeys(searchTerm);
		submitSearch.click();
	}
	
	public void contactUs(){
		contactUs.click();
	}

}
