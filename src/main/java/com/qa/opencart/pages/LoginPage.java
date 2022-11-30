package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;// should have its own driver , initially its  null , we initilaize it with the help of constrctor 
	
	private ElementUtil elementutil;
	
	//1.define the by locators amd they should be private 
	//2.create a constructor for each page
	
	private By Email = By.id("input-email");
	
	private By password = By.id("input-password");
	
	private By loginbutton = By.xpath("//button[text()='Login']");
	
	private By RegisterButton = By.xpath("//div[contains(@class,'list-group')]//a[text()='Register']");
	
	
	public LoginPage(WebDriver driver) {
		
		this.driver= driver;
		
		elementutil = new ElementUtil(driver);//// have to create object of element util to use the utilities we created and this element util expects one comstructor also
	}
	
	// 3.page Actions/functions/methods -these are public in Nature 
	
	
	@Step("login with username:{0} and password :{1}")
	
	
	public AccountPage doLogin(String Username,String Password) {
		
		
		//driver.findElement(Email).sendKeys(Username);
		
		elementutil.doSendKeys(Email, Username);
		
		//driver.findElement(password).sendKeys(Password);
		
		elementutil.doSendKeys(password, Password);
		
		//driver.findElement(loginbutton).click();
		
		elementutil.doClick(loginbutton);
		
		return new AccountPage(driver);
		
		//its the responsibillity of method to return the class object of landing page 
	}
	
	public RegisterPage doRegister() {
		
		elementutil.doClick(RegisterButton);
		
		return new RegisterPage(driver);
		
	}

}
