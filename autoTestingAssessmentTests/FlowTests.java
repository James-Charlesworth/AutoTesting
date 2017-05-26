package autoTestingAssessmentTests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import autoTestingAssessment.*;


public class FlowTests {
	
	ExtentReports report;
	ExtentTest test;
	WebDriver driver;
	
	Home homepage;
	SearchResults searchResults;
	Selection selectionInCart;
	CartSummary cartSummary;
	SignInOrUp signInOrUp;
	AddressConf addressConf;
	ShippingConf shippingConf;
	PaymentChoice paymentChoice;
	PaymentConf paymentConf;
	OrderConf orderConf;
	MyAccount myAccount;
	CreateAccount createAccount;
	ContactUs contactUs;
	ContactConf contactConf;
	Identity identity;
	
	
	@BeforeClass
	public void setup(){
		report = new ExtentReports("C:\\Users\\Administrator\\Desktop\\Extent\\AutoTestingAssessment.html", true);
	}
	
//	@BeforeTest
//	public void testSetup(){
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\chromedriver.exe");
//		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\geckodriver.exe");
//		driver = new ChromeDriver();
//		//driver = new FirefoxDriver();
//		driver.get("http://automationpractice.com");
//	}

	//buy item as existing user
	@Test(priority = 1, enabled = true)
	public void testFlow1() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\geckodriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.get("http://automationpractice.com");
		
		test = report.startTest("Search for and purchase an item (existing user)");
		test.log(LogStatus.INFO, "Browser started");
		
		String searchTerm = "green";
		String emailAddress1 = "testNo1@test.com";
		String password = "password";
		
		//go to homepage and assert page is correct
		homepage = new Home(driver);
		if (homepage.getTitle().equals("My Store")){
			test.log(LogStatus.PASS, "verify home page loaded");
		}else{
			test.log(LogStatus.FAIL, "verify home page loaded");
			test.log(LogStatus.INFO, "login failed, likely issue with driver");
			assertEquals(homepage.getTitle(),"My Store");
		}
		//search for a product
		homepage.setsearch(searchTerm);
		//add a product to basket
		searchResults = new SearchResults(driver);
		if (searchResults.getTitle().equals("Search - My Store")){
			test.log(LogStatus.PASS, "verify search results page");
		}else{
			test.log(LogStatus.FAIL, "verify search results page");
			test.log(LogStatus.INFO, "search failed, likely issue with Home.java");
			assertEquals(searchResults.getTitle(),"Search - My Store");
		}
		searchResults.clickAddtoCart();
		//view basket
		selectionInCart = new Selection(driver);
		selectionInCart.clickCheckout();
		//confirm basket
		cartSummary = new CartSummary(driver);
		if (cartSummary.getTitle().equals("Order - My Store")){
			test.log(LogStatus.PASS, "verify order page reached");
		}else{
			test.log(LogStatus.FAIL, "verify order page reached");
			test.log(LogStatus.INFO, "search failed, likely issue with Selection.java");
			assertEquals(cartSummary.getTitle(),"Order - My Store");
		}
		cartSummary.clickCheckout();
		//sign in as an existing user
		signInOrUp = new SignInOrUp(driver);
		if (signInOrUp.getTitle().equals("Login - My Store")){
			test.log(LogStatus.PASS, "verify login page reached");
		}else{
			test.log(LogStatus.FAIL, "verify login page reached");
			test.log(LogStatus.INFO, "search failed, likely issue with CartSummary.java");
			assertEquals(signInOrUp.getTitle(),"Login - My Store");
		}
		signInOrUp.existingUser(emailAddress1, password);
		//confirm address
		addressConf = new AddressConf(driver);
		if (addressConf.getTitle().equals("Order - My Store")){
			test.log(LogStatus.PASS, "verify address confirmation page reached");
		}else{
			test.log(LogStatus.FAIL, "verify address confirmation page reached");
			test.log(LogStatus.INFO, "search failed, likely issue with SignInOrUp.java");
			assertEquals(addressConf.getTitle(),"Order - My Store");
		}
		
		addressConf.clickProcessAddress();
		//conmfirm shipping options
		shippingConf = new ShippingConf(driver);
		shippingConf.tickTerms();
		shippingConf.acceptShipping();
		//select payment method
		paymentChoice = new PaymentChoice(driver);
		paymentChoice.selectWire();
		//paymentChoice.selectCheque();
		
		//confirm order
		paymentConf = new PaymentConf(driver);
		if (paymentConf.getTitle().equals("My Store")){
			test.log(LogStatus.PASS, "verify payment confirmation page reached");
		}else{
			test.log(LogStatus.FAIL, "verify payment confirmation page reached");
			test.log(LogStatus.INFO, "search failed, likely issue with PaymentChoice.java");
			assertEquals(paymentConf.getTitle(),"My Store");
		}
		paymentConf.confirmOrder();
		//sign out
		orderConf = new OrderConf(driver);
		if (orderConf.getTitle().equals("Order confirmation - My Store")){
			test.log(LogStatus.PASS, "verify order confirmation page reached");
		}else{
			test.log(LogStatus.FAIL, "verify order confirmation page reached");
			test.log(LogStatus.INFO, "search failed, likely issue with PaymentConf.java");
			assertEquals(orderConf.getTitle(),"Order confirmation - My Store");
		}
		orderConf.signOut();
		//assert sign out has worked
		if (signInOrUp.getTitle().equals("Login - My Store")){
			test.log(LogStatus.PASS, "verify logout after test");
		}else{
			test.log(LogStatus.FAIL, "verify logout after test");
			test.log(LogStatus.INFO, "search failed, likely issue with OrderConf.java");
			assertEquals(signInOrUp.getTitle(),"Login - My Store");
		}
		
		test.log(LogStatus.INFO, "Test Finished");
		report.endTest(test);
		driver.close();
		

	}
	
	//create new account
	@Test(priority = 4, enabled = true)
	public void testFlow2(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\geckodriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.get("http://automationpractice.com");
		
		test = report.startTest("Verify create account");
		test.log(LogStatus.INFO, "Browser started");
		
		String emailAddress1 = "testNo38@test.com";
		String firstName = "FirstNameTest";
		String lastName = "SurNameTest";
		String password = "password";
		String alias = "Home";
		String address = "1 Test Road";
		String postcode = "00000";
		String city = "TestCity";
		String mobile = "12312312312";
		String state = "a + /n";
		
		//go to homepage and assert page is correct
		homepage = new Home(driver);
		if (homepage.getTitle().equals("My Store")){
			test.log(LogStatus.PASS, "verify home page loaded");
		}else{
			test.log(LogStatus.FAIL, "verify home page loaded");
			test.log(LogStatus.INFO, "search failed, likely issue with driver");
			test.log(LogStatus.INFO, "Test ended Early");
			report.endTest(test);
			driver.close();
			assertEquals(homepage.getTitle(),"My Store");
		}
		//go to sign in page
		homepage.signIn();
		//enter new email and click create account
		signInOrUp = new SignInOrUp(driver);
		signInOrUp.enterEmail(emailAddress1);
		boolean result = true;

		try{
			Thread.sleep(2000);
			driver.findElement(By.id("create_account_error")).isDisplayed();
		}catch(NoSuchElementException | InterruptedException e){
			e.printStackTrace();
			result = false;
		}
		if (result){
			test.log(LogStatus.FAIL, "verify email is unique");
			test.log(LogStatus.INFO, "search failed, likely not an issue with code, check email used for testing");
			test.log(LogStatus.INFO, "Test Ended Early");
			report.endTest(test);
			driver.close();
			assertEquals("2","1");
		}else{
			test.log(LogStatus.PASS, "verify email is unique");
			
			//enter new account details and click submit
			createAccount = new CreateAccount(driver);
			createAccount.enterDetails(firstName, lastName, password, alias, address, postcode, city, mobile, state);
			createAccount.clickCreateAccountButton();
			//sign out
			myAccount = new MyAccount(driver);
			myAccount.logout();
			//assert sign out successful
			signInOrUp = new SignInOrUp(driver);
			if (signInOrUp.getTitle().equals("Login - My Store")){
				test.log(LogStatus.PASS, "verify logout after test");
			}else{
				test.log(LogStatus.FAIL, "verify logout after test");
				test.log(LogStatus.INFO, "search failed, likely issue with MyAccount.java");
				assertEquals(signInOrUp.getTitle(),"Login - My Store");
			}
			
			
		}
		
		test.log(LogStatus.INFO, "Test Finished");
		report.endTest(test);
		driver.close();
		
	}
	
	//use contact us form
	@Test(priority = 2, enabled = true)
	public void testFlow3(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\geckodriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.get("http://automationpractice.com");
		
		test = report.startTest("Contact us form");
		test.log(LogStatus.INFO, "Browser started");
		
		String subject = "c + /n";
		String email = "test@test.com";
		String ref = "12345";
		String message = "example complaint goes here";
		
		//go to contact us form
		homepage = new Home(driver);
		if (homepage.getTitle().equals("My Store")){
			test.log(LogStatus.PASS, "verify home page loaded");
		}else{
			test.log(LogStatus.FAIL, "verify home page loaded");
			test.log(LogStatus.INFO, "search failed, likely issue with driver");
			assertEquals(homepage.getTitle(),"My Store");
		}
		homepage.contactUs();
		//enter form fields
		contactUs = new ContactUs(driver);
		if (contactUs.getTitle().equals("Contact us - My Store")){
			test.log(LogStatus.PASS, "verify contact us loaded");
		}else{
			test.log(LogStatus.FAIL, "verify contact us page loaded");
			test.log(LogStatus.INFO, "search failed, likely issue with Home.java");
			assertEquals(contactUs.getTitle(),"Contact us - My Store");
		}
		contactUs.enterDetails(subject, email, ref, message);
		contactUs.send();
		contactConf = new ContactConf(driver);
		if (contactConf.getMessage().contains("successfully")){
			test.log(LogStatus.PASS, "verify message sent");
		}else{
			test.log(LogStatus.FAIL, "verify message sent");
			test.log(LogStatus.INFO, "search failed, likely issue with ContactUs.java");
			assertEquals(contactConf.getMessage().contains("successfully"), false);
		}
		test.log(LogStatus.INFO, "Test Finished");
		report.endTest(test);
		driver.close();
		
	}
	
	//login as existing user and change details
	@Test(priority = 3, enabled = true)
	public void testflow4(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\geckodriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.get("http://automationpractice.com");
		
		test = report.startTest("change details of existing user");
		test.log(LogStatus.INFO, "Browser started");
		
		String email = "testNo2@test.com";
		String firstName = "first";
		String lastName = "last";
		String pass = "password";
		String newPass = "password";
		
		homepage = new Home(driver);
		if (homepage.getTitle().equals("My Store")){
			test.log(LogStatus.PASS, "verify home page loaded");
		}else{
			test.log(LogStatus.FAIL, "verify home page loaded");
			test.log(LogStatus.INFO, "search failed, likely issue with driver");
			assertEquals(homepage.getTitle(),"My Store");
		}
		homepage.signIn();
		//enter new email and click create account
		signInOrUp = new SignInOrUp(driver);
		signInOrUp.existingUser(email, pass);
		
		myAccount = new MyAccount(driver);
		if (myAccount.getTitle().equals("My account - My Store")){
			test.log(LogStatus.PASS, "verify reached account page");
		}else{
			test.log(LogStatus.FAIL, "verify reached account page");
			test.log(LogStatus.INFO, "login failed, likely signInOrUp.java");
			assertEquals(myAccount.getTitle(),"My account - My Store");
		}
		myAccount.clickInfo();
		
		identity = new Identity(driver);
		if (identity.getTitle().equals("Identity - My Store")){
			test.log(LogStatus.PASS, "verify change details page");
		}else{
			test.log(LogStatus.FAIL, "verify change details page");
			test.log(LogStatus.INFO, "search failed, likely issue with MyAccount.java");
			assertEquals(identity.getTitle(),"Identity - My Store");
		}
		identity.enterDetails(firstName, lastName, pass, newPass);
		identity = new Identity(driver);
		boolean result = true;
		try{
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div")).isDisplayed();
		}catch(NoSuchElementException | InterruptedException e){
			e.printStackTrace();
			result = false;
		}
		if (result && !driver.findElement(By.cssSelector(".alert.alert-danger")).isDisplayed()){
			test.log(LogStatus.FAIL, "verify details accepted");
			test.log(LogStatus.INFO, "search failed, likely issue with Identity.java");
			test.log(LogStatus.INFO, "Test Ended Early");
			report.endTest(test);
			driver.close();
			assertEquals("1","2");
		}else{
			test.log(LogStatus.PASS, "verify details accepted");
			myAccount.logout();
			signInOrUp = new SignInOrUp(driver);
			//assert sign out successful
			if (signInOrUp.getTitle().equals("Login - My Store")){
				test.log(LogStatus.PASS, "verify logout after test");
			}else{
				test.log(LogStatus.FAIL, "verify logout after test");
				test.log(LogStatus.INFO, "search failed, likely issue with MyAccount.java");
				assertEquals(signInOrUp.getTitle(),"Login - My Store");
			}
		}
		//identity = new Identity(driver);
//		if (identity.getTitle().equals("Identity - My Store")){
//			
//		}else{
//			test.log(LogStatus.FAIL, "verify details accepted");
//			test.log(LogStatus.INFO, "search failed, likely issue with Identity.java");
//			assertEquals(homepage.getTitle(),"My Store");
//		}
		
		
		test.log(LogStatus.INFO, "Test Finished");
		report.endTest(test);
		driver.close();
	}
	
	
	@AfterClass
	public void tearDown(){
		report.flush();
	}
}
