package com.qa.opencart.BaseTestUI;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegisterPage;

public class BaseTest {
	
	public WebDriver driver;////its own driver intially , later we will call driver object here by creating object
	
	public DriverFactory df;
	
	public LoginPage loginPage;
	
	public Properties prop;//declared the properies reference to craete the object of prop
	
	public RegisterPage registerPage;
	
	public SoftAssert softassert;
	
	
	////all the pageclasses references will be maintained here  as each test class extends base test class
	
	
	@BeforeTest
	public void setUp() {
		
		//have to initialize the driver , have to call initdriver method here by creating object of driverfactory, since init method retuns driver , we give that driver to current class driver reference 
		
		//1.Created the object of Driver factory class to call initprop and initdriver methods as they are responsible for initializing the driver
		
		df = new DriverFactory();
		
	    //firstly called the initprop method as we have to read the data from properties file(like broswer), based on that driver will be initialized,stored in prop as this method returns properies
		prop = df.initProp();
		
		//instaed of passing broswertype to inidriver method ,passed complete prop object as wr can access all the propeties from prop in future as well 
		
		//here driver is holding parameter, wil store the return type of initdriver into driver so that same driver can be maintianed through out the session and same driver will be given to logi
		
		driver = df.initDriver(prop);
		
		loginPage = new LoginPage(driver);
		
		softassert = new SoftAssert(); //after using this in test class at the end , we have to use softassert.assertAll() method 
		
	
	//if we have to write multiple assertions for a single feature then we use soft asssert , otherwise we use hard assert,if it is s single assertion
	
	
	
	
	}

}
