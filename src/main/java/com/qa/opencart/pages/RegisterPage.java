package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.JSUtil;

public class RegisterPage {
	
	private WebDriver driver;
	
	private ElementUtil elementutil;
	
	private JSUtil jsutil;
	
	By firstname = By.id("input-firstname");
	
	By lastname = By.id("input-lastname");
	
	By email = By.id("input-email");
	
	By password = By.id("input-password");
	
	By agreecheckbox = By.xpath("//input[@name='agree']");
	
	By continueButton = By.xpath("//button[text()='Continue']");
	
	
	public RegisterPage(WebDriver driver) {
		
		
		this.driver = driver;
		
		elementutil = new ElementUtil(driver);
		
		jsutil = new JSUtil(driver);
	}
	
	
	/*
	 * public void enterInputvaluesIntoRegistrationForm(String inputfield,String
	 * Value) {
	 * 
	 * String inputfieldxpath = "//input[@id='"+inputfield+"']";
	 * 
	 * 
	 * driver.findElement(By.xpath(inputfieldxpath)).clear();
	 * 
	 * driver.findElement(By.xpath(inputfieldxpath)).sendKeys(Value);
	 * 
	 * }
	 */
	 
	
	
	
	  public void doRegistrationForm(String firstname,String lastname,String
	  email,String password) {
	  
	  fillRegistrationForm(firstname, lastname, email, password);
	  
	  docheckbox();
	  
	  doClickOnContinue(); }
	 
	
	
	private void fillRegistrationForm(String firstname,String lastname,String email,String password) {
		
		
		elementutil.doSendKeys(this.firstname, firstname);
		
		elementutil.doSendKeys(this.lastname, lastname);
		elementutil.doSendKeys(this.email, email);
		elementutil.doSendKeys(this.password, password);
			
	}
	
	
	
	
	public  void docheckbox() {
		
		WebElement element = driver.findElement(agreecheckbox);
		//elementutil.doActionsClick(agreecheckbox);
		
		jsutil.clickElementByJS(element);
		
		
	}
	
	public void doClickOnContinue() {
		
		//elementutil.doClick(continueButton);
		
		WebElement element = driver.findElement(continueButton);
		
		jsutil.clickElementByJS(element);
		
		
		
	}

}
