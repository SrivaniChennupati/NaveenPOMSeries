package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	
	// if we dont use the thread local cocept , when we have large number of test
	// cases , chances are very high that it gives false report/data mismatch
	// incasse of extent report
	public WebDriver driver;// normal driver

	public Properties prop;

	public static String highlight;// created as static as it is a common property value

	public OptionsManager optionsmanager;// to call the methods of options manager

	// get the thread local copy of driver ,Threadlocal is a class

	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();// thread local driver,it has 2 methods
																					// set and get ,currently webdriver
																					// is nit initilized , wehave to do
																					// that

	public WebDriver initDriver(Properties prop) {

		// initdriver method here accepts complete prop object

		optionsmanager = new OptionsManager(prop);

		highlight = prop.getProperty("highlight");

		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();

			// driver = new ChromeDriver(optionsmanager.getChromeOptions());//normal driver

			tldriver.set(new ChromeDriver(optionsmanager.getChromeOptions())); // thread local driver

		} else if (browser.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();

			// driver = new FirefoxDriver(optionsmanager.getFirefoxOptions());

			tldriver.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));// thread loca; driver
		}

		else {

			System.out.println("Please pass the Correct browser" + browser);
		}

		/*
		 * driver.manage().window().maximize();
		 * 
		 * driver.manage().deleteAllCookies();
		 * 
		 * driver.get(prop.getProperty("url"));
		 * 
		 * return driver;
		 */

		getDriver().manage().window().maximize();

		getDriver().manage().deleteAllCookies();

		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	// now we have to get the thread local driver

	// this getdriver method we are caling back to back to create to create thread
	// local so lets create synchronization ,

	public synchronized WebDriver getDriver() {

		return tldriver.get();

	}

	public Properties initProp() {

		prop = new Properties();

		FileInputStream ip = null;

		// system is a class in java , and method get property => help me to read the
		// command line arguemnent,it wont read anything from properties file

		String env = System.getProperty("env");

		if (env == null) {

			try {
				ip = new FileInputStream("./src/test/resources/config/Config.properties");

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else {

			System.out.println("Running on Environement" + env);

			switch (env.toLowerCase()) {

			case "qa":

				try {
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			case "stage":

				try {
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			case "prod":

				try {
					ip = new FileInputStream("./src/test/resources/config/prod.config.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			}
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;

	}

	public String getScreenshot() {

		// convert getdriver into take screenshot interface

		File srcfile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		// screenshot location , we have to pass the directory called user.dir and
		// create the folder screenshot and file name current time stamp

		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";

		// have to create destination

		File destination = new File(path);

		try {
			FileUtils.copyFile(srcfile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;

	}

	

}
