package com.qa.opencart.test;

import org.testng.annotations.Test;




import com.qa.opencart.BaseTestUI.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic:100-Opencart App-design LoginPage")
@Story("US 101: Login Page Feature with some basic functionalities and modules")

public class LoginPageTest extends BaseTest {
	
// these are the allure annonations , we can customize them

	@Description("login page test....")
	
	@Severity(SeverityLevel.CRITICAL)
	
	@Test
	public void loginPageTest() {
		
		loginPage.doLogin("srivanichennupati@gmail.com", "Jesus@447");
		
		
		
	}
	

}
