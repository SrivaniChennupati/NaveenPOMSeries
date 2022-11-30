package com.qa.opencart.listners;



import org.testng.ITestContext;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.ITestListener;
import com.qa.opencart.factory.DriverFactory;

public class AllureReportListener extends DriverFactory implements ITestListener{
	
	
    private static String getTestMethodName( ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG( WebDriver driver) {
        return (byte[])((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
    
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog( String message) {
        return message;
    }
    
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml( String html) {
        return html;
    }
    
    public void onStart( ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
    }
    
    public void onFinish( ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());
    }
    
    public void onTestStart( ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
    }
    
    public void onTestSuccess( ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
    }
    
    public void onTestFailure( ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
         Object testClass = iTestResult.getInstance();
        if (getDriver() instanceof WebDriver) {
            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveScreenshotPNG(getDriver());
        }
        saveTextLog(String.valueOf(getTestMethodName(iTestResult)) + " failed and screenshot taken!");
    }
    
    public void onTestSkipped( ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
    }
    
    public void onTestFailedButWithinSuccessPercentage( ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    } 

}
