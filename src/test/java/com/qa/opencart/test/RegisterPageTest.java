package com.qa.opencart.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.BaseTestUI.BaseTest;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass

	public void registerPageSetUp() {

		registerPage = loginPage.doRegister();

	}

	
	  @DataProvider
	  
	  public Object[][] getRegisterPageData() {
	  
	  return new Object[][] {
	  
	  {"Revanth","Makkena","RevanthMakkena@gmail.com","Jesus@447"},
	  
	  };
	  
	  
	  }
	 
	 

	// every data provider should retun 2D objct array
	/*
	 * @DataProvider
	 * 
	 * 
	 * 
	 * public Object[][] getRegTestData() {
	 * 
	 * Object[][] data = ExcelUtil.getTestData("Register");
	 * 
	 * return data;
	 * 
	 * }
	 */
	 

	@Test(dataProvider ="getRegisterPageData")

	public void registerPageTest(String firstname, String lastname, String email, String password) {

		
		registerPage.doRegistrationForm(firstname, lastname, email, password);

	}

}
