package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSUtil {
	
	 WebDriver driver;
  
	
	public JSUtil(WebDriver driver) {
   	
   	
   	this.driver = driver;
   }
	
	
	/**
	 * This methood will help us to get the title of the Page using JS
	 * @return
	 */
	
	public String getTitleByJS() {

		JavascriptExecutor Js = (JavascriptExecutor) driver;

		return Js.executeScript(" return document.title;").toString();

	}
	
	/**
	 * This method will help us to get the Entire text present on the Page
	 * @return
	 */
	
	public String getPageInnerText() {
		
		JavascriptExecutor Js = (JavascriptExecutor) driver;
		 return Js.executeScript("retun document.documentElement.innerText;").toString();
		
	}
	
	/**
	 * This method will help us to generate the Alert
	 * @param message
	 */
	
	public void generateAlert(String message) {

		JavascriptExecutor Js = (JavascriptExecutor) driver;
		Js.executeScript("document.alert('" + message + "')");

	}
	
	/**
	 * This method will help us to refresh the browser
	 */
	
	public void refreshBrowserByJS() {

		JavascriptExecutor Js = (JavascriptExecutor) driver;

		Js.executeScript("history.go(0)");

	}
	
	/**
	 * This method will help us to scroll down 
	 */
	
	public void scrollPageDown() {

		JavascriptExecutor Js = (JavascriptExecutor) driver;

		Js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

	}
	
	/**
	 * This method will help us to Scroll up
	 */
	public void scrollPageUp() {

		JavascriptExecutor Js = (JavascriptExecutor) driver;

		Js.executeScript("window.scrollTo(document.body.scrollHeight,0)");

	}
	
	/**
	 * This method will help us to scroll down to a specific height
	 * @param Height
	 */
	
	public void scrollPageDown(String Height) {

		JavascriptExecutor Js = (JavascriptExecutor) driver;

		Js.executeScript("window.scrollTo(0,'" + Height + "')");

	}
	
	/**
	 * This method will help us to scroll up to a specific height
	 * @param Height
	 */
	
	public void scrollPageUp(String Height) {

		JavascriptExecutor Js = (JavascriptExecutor) driver;

		Js.executeScript("window.scrollTo('" + Height + "',0)");

	}
	
	/**
	 * This method will help us to scroll to the  Specific element 
	 * @param element
	 */
	
	public void scrollIntoViewElement(WebElement element) {

		JavascriptExecutor Js = (JavascriptExecutor) driver;

		Js.executeScript("arguments[0].scrollIntoView(true);", element);

	}
	
	/**
	 * This method will help us to click the element
	 * @param element
	 */
	
	
	public void clickElementByJS(WebElement element) {

		JavascriptExecutor Js = (JavascriptExecutor) driver;

		Js.executeScript("arguments[0].click();", element);

	}
	
	/**
	 * This method will help us to pass value 
	 * @param ID
	 * @param value
	 */
	
	public void doSendKeysUsingWithID(String ID, String value) {

		JavascriptExecutor Js = (JavascriptExecutor) driver;

		Js.executeScript("document.getElementByID('" + ID + "').value('" + value + "')");

	}
	
	public void flash(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 15; i++) {
			changeColor("rgb(0,200,0)", element);// 1
			changeColor(bgcolor, element);// 2
		}
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}
	
	public void drawBorder(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	} 

}
