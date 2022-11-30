package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JSUtil jsutil;
	
	//have created object of JS util here to call highlight method 

	/**
	 * Created this Constructor so that we can pass the driver from the BrowserUtil
	 * Luanchdriver method as it returns driver
	 * 
	 * @param driver
	 */

	public ElementUtil(WebDriver driver) {

		this.driver = driver;
		
		jsutil= new JSUtil(driver);

	}

	/**
	 * This method will help us to find or create the Web element
	 * 
	 * @param locator
	 * @return WebElement
	 */

	public WebElement getElement(By locator) {
		
		//first creating the element and then checking the condition
		
		WebElement element = driver.findElement(locator);
		
		// we get highlight value from properies file in the form of  string (true/false), since if else condition cant take strings , have to convert that to boolean true/fasle
		 //have called highlight variable from the Driverfactory with respect to class as it is a static
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			
			jsutil.flash(element);
		}
		return element;

		

	}

	/**
	 * This method will help us to create WebElement and pass the Value
	 * 
	 * @param locator
	 * @param value
	 */

	public void doSendKeys(By locator, String value) {

		getElement(locator).sendKeys(value);

	}

	/**
	 * This method will help us to click the WebElement
	 * 
	 * @param locator
	 */

	public void doClick(By locator) {

		getElement(locator).click();

	}

	/**
	 * This method will help us to get the Text
	 * 
	 * @param locator
	 * @return String
	 */

	public String dogetText(By locator) {

		return getElement(locator).getText();

	}

	/**
	 * This method will help us to check if the Webelement is displayed on the Page
	 * or not
	 * 
	 * @param locator
	 * @return boolean
	 */

	public boolean doIsDisplayed(By locator) {

		return getElement(locator).isDisplayed();

	}

	/**
	 * This method will help us to check of the Webelement is Enabled or not
	 * 
	 * @param locator
	 * @return boolean
	 */

	public boolean doIsEnabled(By locator) {

		return getElement(locator).isEnabled();

	}

	/**
	 * This method will help us to check the checkbox is selected or not
	 * 
	 * @param locator
	 * @return boolean
	 */

	public boolean doIsSelected(By locator) {

		return getElement(locator).isSelected();

	}

	/**
	 * This method will help us to get the Attribute value of any Attribute
	 * 
	 * @param locator
	 * @param AtrributeName
	 * @return String
	 */

	public String doGetAttributeValue(By locator, String AtrributeName) {

		return getElement(locator).getAttribute(AtrributeName);

	}

	/**
	 * This methood will help us to check the element is disabled or not
	 * @param locator
	 * @param AttributeName
	 * @return boolean
	 */

	public boolean checkElementDisabled(By locator, String AttributeName) {

		String AttributeValue = doGetAttributeValue(locator, AttributeName);

		if (AttributeValue.equals("true")) {

			return true;

		}

		return false;

	}

	/**
	 * This method will help us to get the list of WebElemet
	 * 
	 * @param locator
	 * @return List<WebElement>
	 */

	public List<WebElement> getElements(By locator) {

		return driver.findElements(locator);

	}

	/**
	 * This method will help us to click on a specific link from the list of links
	 * 
	 * @param locator
	 * @param linkvalue
	 */

	public void doLinkClick(By locator, String linkvalue) {

		List<WebElement> linksList = getElements(locator);

		for (WebElement e : linksList) {

			String LinkText = e.getText();

			if (LinkText.equals(linkvalue)) {

				e.click();
				break;
			}

		}

	}

	/**
	 * This method will help us to verify the element is displayed without using
	 * isdisplayed method
	 * @param locator
	 * @return boolean
	 */

	public boolean checkElementDisplayed(By locator) {

		if (getElements(locator).size() == 1) {

			return true;
		}

		return false;
	}

	/**
	 * This method will help us to check the element is present on the web page with
	 * out using displayed method
	 * @param locator
	 * @param Occurance
	 * @return
	 */

	public boolean checkElementDisplayed(By locator, int Occurance) {

		if (getElements(locator).size() == Occurance) {

			return true;
		}

		return false;
	}
	
	/*************************Dropdown Utilities******************************************/
	
	/**
	 * This method will help us to select value from dropdown using index
	 * @param locator
	 * @param index
	 */
	
	
	public void doslectByIndex(By locator,int index) {
		
		
		Select se = new Select(getElement(locator));
		
		se.selectByIndex(index);
		
		
	}
	
	
	
    /**
     * This method will help us to select the value from dropdown using text
     * @param locator
     * @param text
     * @return 
     */
	
	public boolean doslectByVisibleText(By locator,String text) {
		
		
		Select se = new Select(getElement(locator));
		
		se.selectByVisibleText(text);
		
		 return isDropdownValueSelected(se, text);
	}
	

	
  /**
   * This method will help us to select the value from dropdown using value atrribute value 
   * @param locator
   * @param value
 * @return 
   */
	
	public boolean doslectByValue(By locator,String value) {
		
		
		Select se = new Select(getElement(locator));
		
		se.selectByValue(value);
		
		 return isDropdownValueSelected(se, value);
	}
	
	
	/**
	 * This method will help us to verify if the particular value is selcted from the dropdown or not 
	 * @param se ( Select class reference passed) as I dont want to create the object of select class once again
	 * @param Expectedvalue
	 * @return
	 */
	
	public boolean isDropdownValueSelected(Select se, String Expectedvalue) {
		
		
		if( se.getFirstSelectedOption().getText().contains(Expectedvalue)){
			
			
			return true;
		}
		
		return false;
		
		
		
	}
	
	/**
	 * This method will help us to select value from dropdown without using select by text,value,index methods
	 * @param locator
	 * @param value
	 */
	
	
	public void doSelectDropdown(By locator,String value) {
		
		
		Select se = new Select(getElement(locator));
		
		List<WebElement> optionslist = se.getOptions();
		
		iterateDropdownAndSelect(optionslist,value);
		
		
	}
	
	/**
	 * This method will help us to select values from dropdown with out using select class
	 * @param locator
	 * @param value
	 */
	
	
	
	public void doSelectDropdownWithoutSelect(By locator,String value) {
		
		
		List<WebElement> Optionslist = getElements(locator);
		
		iterateDropdownAndSelect(Optionslist,value);
		
		
		}
	
	/**
	 * This method will help us to iterate the dropdown and select values 
	 * @param Optionslist
	 * @param value
	 */
	
	private void iterateDropdownAndSelect(List<WebElement> Optionslist , String value) {
		
		
		for(WebElement e : Optionslist) {
			
			
			String text = 	e.getText();
			
			if (text.equals(value)) {
				
				e.click();
				break;
			}
				
				
			}
		
	}
	
	
	/**
	 * This method wil help us to selct the value from Autosuggestions 
	 * @param searchLocator
	 * @param searchvalue
	 * @param Suggestionlocator
	 * @param Suggestionvalue
	 */
	
	
	public void googleSerachSelect(By searchLocator , String searchvalue , By Suggestionlocator , String Suggestionvalue) {
		
	   doSendKeys(searchLocator, searchvalue);
	   
	   try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   
	  List<WebElement> Suggestionslist = getElements(Suggestionlocator);
	  
	  iterateDropdownAndSelect(Suggestionslist, Suggestionvalue);
	  
		
		}
	
	/**
	 * This method will help us to the single selction , mutlti selection and all selection from dropdown
	 * @param locator
	 * @param value
	 */
	
	
	public void selectChoice(By locator, String... value) {

		List<WebElement> Choicelist = getElements(locator);

		if (!value[0].equalsIgnoreCase("ALL")) {

			for (int i = 0; i <= Choicelist.size() - 1; i++) {

				String text = Choicelist.get(i).getText();

				for (int j = 0; j <= value.length - 1; j++) {

					if (text.equals(value[j])) {

						Choicelist.get(i).click();

						break;

					}

				}

			}

		}

		else {

			try {
				for (WebElement e : Choicelist) {

					e.click();
				}
			} catch (Exception e) {

				System.out.println("All Choices are Selected");

			}

		}

	}
	
	/**
	 * This method will help us to mouse over on parent element and click on the  child element from the listed menu
	 * @param Parentlocator
	 * @param Childlocator
	 */
	public void parentChildMenuHandle(By Parentlocator, By Childlocator) {

		Actions ac = new Actions(driver);

		ac.moveToElement(getElement(Parentlocator)).build().perform();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getElement(Childlocator).click();

	}
	
	
	/**
	 * This method will help us to do the right click and get the all the right click list
	 * @param Rightclicklocator
	 * @param Expectedvalue
	 * @param itemslistlocator
	 * @return
	 */
	public List<String> getRightClickList(By Rightclicklocator,  By itemslistlocator) {

		List<String> ActualItemsList = new ArrayList<String>();

		Actions ac = new Actions(driver);

		ac.contextClick(getElement(Rightclicklocator)).build().perform();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<WebElement> itemslist = getElements(itemslistlocator);

		for (WebElement e : itemslist) {

			String text = e.getText();

			ActualItemsList.add(text);

		}

		return ActualItemsList;

	}
	
	/**
	 * This method will help us to get the count of the Right click Items List
	 * @param Rightlocator
	 * @param itemsListLocator
	 * @return
	 */
	
	public int rightClickItemsCount(By Rightlocator , By itemsListLocator) {
		
		
		return getRightClickList(Rightlocator, itemsListLocator).size();
		
		
		
	}
	
	/**
	 * This method will help us to click on right Click Items
	 * @param locator
	 * @param ItemsListLocator
	 * @param value
	 */
	
	public void clickOnRightClickItem(By locator, By ItemsListLocator, String value) {

		Actions ac = new Actions(driver);

		ac.contextClick(getElement(locator)).build().perform();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<WebElement> Itemslist = getElements(ItemsListLocator);

		for (WebElement e : Itemslist) {

			String text = e.getText();

			if (text.equals(value)) {

				e.click();
				break;
			}
		}
	}
	
	
	/**
	 * This method will help us to pass the value using Actions class when we get ElementnotInteractable Exception
	 * @param locator
	 * @param value
	 */
	
	public void doActionSendkeys(By locator, String value) {

		Actions ac = new Actions(driver);

		ac.sendKeys(getElement(locator), value).build().perform();

	}
	
	
	/**
	 * This method will help us to the click with Actions Class
	 * @param locator
	 */
	
	public void doActionsClick(By locator) {

		Actions ac = new Actions(driver);

		ac.click(getElement(locator)).build().perform();

	}
	
	/****************************************Wait Utils*******************************************************
	
	/**
	 * This method will help us to wait for specific element
	 * 
	 * @param locator
	 * @param timeout
	 * @return WebElement
	 */
	
	
	public WebElement waitForElementPresent(By locator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
	
	/**
	 * This method will help us to override the polling time or interval time
	 * 
	 * @param locator
	 * @param timeout
	 * @param pollingtime
	 * @return
	 */
	
	
	public WebElement waitForElementPresent(By locator, long timeout, long pollingtime) {

		WebDriverWait wait = new WebDriverWait(driver, timeout, pollingtime);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
	
	/**
	 * This method will help us to switch to the Alert with Synchronization
	 * 
	 * @param timeout
	 * @return Alert
	 */

	public Alert waitForJSAlert(int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		return wait.until(ExpectedConditions.alertIsPresent());

	}
	
	/**
	 * This method will help us to accept the Alert
	 * 
	 * @param timeout
	 */

	public void acceptAlert(int timeout) {

		waitForJSAlert(timeout).accept();

	}

	/**
	 * This method will help us to dismiss the Alert
	 * 
	 * @param timeout
	 */

	public void dismissAlert(int timeout) {

		waitForJSAlert(timeout).dismiss();

	}

	/**
	 * This method will help us to pass the text to the Alert
	 * 
	 * @param timeout
	 * @param value
	 */

	public void alertSendkeys(int timeout, String value) {

		waitForJSAlert(timeout).sendKeys(value);

	}

	/**
	 * This method will help us to get the Alert text
	 * 
	 * @param timeout
	 * @return
	 */

	public String alertGetText(int timeout) {

		return waitForJSAlert(timeout).getText();

	}
     
	/**
	 * This method will help us to validate the URL contains specifing string or not
	 * @param timeout
	 * @param URLFraction
	 * @return
	 */
   
	public Boolean waitForURLContains(int timeout, String URLFraction) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		return wait.until(ExpectedConditions.urlContains(URLFraction));

	}
   
	/**
	 * This method will help us to validate URL by passing URL Value
	 * @param timeout
	 * @param urlvalue
	 * @return
	 */
	
	public Boolean waitForURLToBe(int timeout, String urlvalue) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		return wait.until(ExpectedConditions.urlToBe(urlvalue));

	}
	
	/**
	 * This method will help us to verify the title 
	 * @param timeout
	 * @param titleFraction
	 * @return
	 */
	
	public String waitForTitleContains(int timeout, String titleFraction) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {

			return driver.getTitle();

		}

		return null;

	}
	
	/**
	 * This method will helo us to validate the title 
	 * @param timeout
	 * @param Titlevalue
	 * @return
	 */
	
	
	
	public String waitForTitleToBe(int timeout, String Titlevalue) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		if (wait.until(ExpectedConditions.titleIs(Titlevalue))) {

			return driver.getTitle();

		}

		return null;

	}
	   
	/**
	 * This method will help us to wait for the frame and switch to it with name or ID 
	 * @param timeout
	 * @param Framelocator
	 */
	
	public void waitForFrameAndSwitchUsingIDOrName(int timeout, String Framelocator) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Framelocator));

	}
	
	/**
	 * This method will help us to wait for the frame and switch to it with Index
	 * @param timeout
	 * @param index
	 */
	
	
	public void waitForFrameAndSwitchUsingIndex(int timeout, int index) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));

	}
	
	/**
	 * This method will help us to wait for the frame and switch to it using By locator
	 * @param timeout
	 * @param locator
	 */
	
	public void waitForFrameAndSwitchUsingByLocator(int timeout, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));

	}
	
	/**
	 * This method will help us to wait for the frame and switch to it using WebElement
	 * @param timeout
	 * @param locator
	 */
	
	public void waitForFrameAndSwitchUsingWebElement(int timeout, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));

	}
	
	/**
	 * This method will help us to checking the element present on the DOM of a page and visible 
	 * @param timeout
	 * @param locator
	 * @return
	 */
	

	public WebElement waitForElementVisible(int timeout, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	/**
	 * This method will help us to checking the element present on the DOM of a page and visible with polling time customization
	 * @param timeout
	 * @param locator
	 * @param pollingtime
	 * @return
	 */
	
	public WebElement waitForElementVisible(int timeout, By locator, int pollingtime) {

		WebDriverWait wait = new WebDriverWait(driver, timeout, pollingtime);

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	/**
	 * This method will wait for the element visibility
	 * @param timeout
	 * @param locator
	 * @return
	 */
	
	public WebElement waitForElementToBeClickable(int timeout, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		return wait.until(ExpectedConditions.elementToBeClickable(locator));

	}
	
	/**
	 * This method will help us to click the element when ready 
	 * @param timeout
	 * @param locator
	 */
	
	public void clickWhenReady(int timeout, By locator) {

		waitForElementToBeClickable(timeout, locator).click();

	}
	
	/**
	 * This method will help us to check the atleast 1 element is present in the list 
	 * @param locator
	 * @param timeouts
	 * @return
	 */
	
	
	public List<WebElement> waitForElementsPresence(By locator, int timeouts) {

		WebDriverWait wait = new WebDriverWait(driver, timeouts);

		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}
	
	/**
	 * This method will help us to get the text of the all the elementa vailable in string 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	
	
	public List<String> getElementsTextList(By locator, int timeout) {

		List<WebElement> elelist = waitForElementsPresence(locator, timeout);

		List<String> elevaluelist = new ArrayList<String>();

		for (WebElement e : elelist) {

			String text = e.getText();

			elevaluelist.add(text);

		}

		return elevaluelist;

	}
	
	/**
	 * This method will help us to print the text of the element list
	 * @param locator
	 * @param timeout
	 */
	
	public void printElementsTextList(By locator, int timeout) {

		List<WebElement> elelist = waitForElementsPresence(locator, timeout);

		for (WebElement e : elelist) {

			String text = e.getText();

			System.out.println(text);

		}

	}
	
	/**
	 * This method will help us to wait for the elements visibility
	 * @param locator
	 * @param timeouts
	 * @return
	 */
	
	
	public List<WebElement> waitForElementsVisible(By locator, int timeouts) {

		WebDriverWait wait = new WebDriverWait(driver, timeouts);

		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}
	
	/**
	 * This method will help us to get the text of the all the elementa available in list
	 * @param locator
	 * @param timeout
	 * @return
	 */
	
	
	public List<String> getElementsTextListUsingVisibility(By locator, int timeout) {

		List<WebElement> elelist = waitForElementsVisible(locator, timeout);

		List<String> elevaluelist = new ArrayList<String>();

		for (WebElement e : elelist) {

			String text = e.getText();

			elevaluelist.add(text);

		}

		return elevaluelist;

	}
	
	
	/**
	 * This method will help us to wait for the element visibility with fluent wait
	 * @param timeout
	 * @param locator
	 * @param pollingTime
	 * @return
	 */
	
	public WebElement waitForElementVisibleWithFluentWait(int timeout, By locator, int pollingTime) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.withMessage(Error.TIME_OUT_WEB_ELEMENT_MEG).pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class);

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	/**
	 * This method will help us to wait for the alert and switch to it with Fluentwait
	 * @param timeout
	 * @param pollingTime
	 * @return
	 */
	
	public Alert waitForJSAlertWithFluentWait(int timeout, int pollingTime) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.withMessage(Error.TIME_OUT_ALERT_MEG).pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class);

		return wait.until(ExpectedConditions.alertIsPresent());

	}
	
	/*******************************Custom wait **************************************************/
	
	
	
	/**
	 * This method will help us to wait for the element to visible on thepage
	 * @param timeout
	 * @param locator
	 * @param pollingtime
	 * @return
	 */
	
	
	public WebElement retringElement(int timeout, By locator, int pollingtime) {

		WebElement element = null;

		int attempts = 0;

		while (attempts < timeout) {

			try {

				element = getElement(locator);

				break;

			}

			catch (Exception e) {

				System.out.println("element is not found in " + attempts);

				try {
					Thread.sleep(pollingtime);
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}
			}

			attempts++;
		}

		return element;

	}
	
	
	
}
		
		

	
	
	
	
	




