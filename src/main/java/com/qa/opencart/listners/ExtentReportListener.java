package com.qa.opencart.listners;

import java.util.Calendar;
import java.util.Date;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.ITestResult;
import org.testng.ITestContext;
import java.nio.file.Path;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.IOException;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;
import org.testng.ITestListener;
import com.qa.opencart.factory.DriverFactory;

public class ExtentReportListener extends DriverFactory implements ITestListener{

    private static final String OUTPUT_FOLDER = "./build/";
    private static final String FILE_NAME = "TestExecutionReport.html";
    private static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test;
    private static ExtentReports extentReports;
    
    private static ExtentReports init() {
         Path path = Paths.get("./build/", new String[0]);
        if (!Files.exists(path, new LinkOption[0])) {
            try {
                Files.createDirectories(path, (FileAttribute<?>[])new FileAttribute[0]);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        ExtentReportListener.extentReports = new ExtentReports();
         ExtentSparkReporter reporter = new ExtentSparkReporter("./build/TestExecutionReport.html");
        reporter.config().setReportName("Automation Test Results");
        ExtentReportListener.extentReports.attachReporter(new ExtentObserver[] { (ExtentObserver)reporter });
        ExtentReportListener.extentReports.setSystemInfo("System", "WINDOWS");
        ExtentReportListener.extentReports.setSystemInfo("Author", "Naveen AutomationLabs");
        ExtentReportListener.extentReports.setSystemInfo("Build#", "1.1");
        return ExtentReportListener.extentReports;
    }
    
   
   
    
    public synchronized void onStart( ITestContext context) {
        System.out.println("Test Suite started!");
    }
    
    public synchronized void onFinish( ITestContext context) {
        System.out.println("Test Suite is ending!");
        ExtentReportListener.extent.flush();
        ExtentReportListener.test.remove();
    }
    
    public synchronized void onTestStart(ITestResult result) {
         String methodName = result.getMethod().getMethodName();
         String qualifiedName = result.getMethod().getQualifiedName();
         int last = qualifiedName.lastIndexOf(".");
         int mid = qualifiedName.substring(0, last).lastIndexOf(".");
         String className = qualifiedName.substring(mid + 1, last);
        System.out.println(methodName + " started!");
         ExtentTest extentTest = ExtentReportListener.extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        extentTest.assignCategory(new String[] { result.getTestContext().getSuite().getName() });
        extentTest.assignCategory(new String[] { className });
        ExtentReportListener.test.set(extentTest);
        ExtentReportListener.test.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }
    
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " passed!");
        ExtentReportListener.test.get().pass("Test passed");
        ExtentReportListener.test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }
    
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " failed!");
        ExtentReportListener.test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
        ExtentReportListener.test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }
    
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " skipped!");
        ExtentReportListener.test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
        ExtentReportListener.test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }
    
    public synchronized void onTestFailedButWithinSuccessPercentage( ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName());
    }
    
    private Date getTime(final long millis) {
         Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
    
    static {
        ExtentReportListener.extent = init();
        ExtentReportListener.test = new ThreadLocal<ExtentTest>();
    }

	
		
	}


