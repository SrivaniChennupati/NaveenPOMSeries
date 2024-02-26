package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

//this class will help me to set something

public class OptionsManager {

	private Properties prop;

	private ChromeOptions co;
	
	private FirefoxOptions fo;
	
	//this constructor does not need driver , its only need properties object to read the data from the properties file

	public OptionsManager(Properties prop) {

		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {

		co = new ChromeOptions();

		if (Boolean.parseBoolean(prop.getProperty("headless"))) {

			co.addArguments("--headless");

		}

		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {

			co.addArguments("--incognito");
		}
		
		return co;

	}
	
	public FirefoxOptions getFirefoxOptions() {

		fo = new FirefoxOptions();

		if (Boolean.parseBoolean(prop.getProperty("headless"))) {

			co.addArguments("--headless");

		}

		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {

			co.addArguments("--incognito");
		}

		return fo;

	}
	


}