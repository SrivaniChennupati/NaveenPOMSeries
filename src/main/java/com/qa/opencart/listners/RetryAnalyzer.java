package com.qa.opencart.listners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{

    private int count;
    private static int maxTry;
    
   
    
	public boolean retry(ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) {// check if the test is not succeed
			if (count < maxTry) {// check if the maximum count is reached
				count++;// increase the count by 1

				iTestResult.setStatus(iTestResult.FAILURE);// mark test as failed

				return true;// test ng to re run the test
			}  else {
			iTestResult.setStatus(iTestResult.FAILURE);// if max count is reached , mark test as failed
		} 
		}else {	
			
			iTestResult.setStatus(iTestResult.SUCCESS);
		}

		return false;
	}
    
}
