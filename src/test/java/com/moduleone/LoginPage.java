package com.moduleone;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.module3.TestListener;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import test.invokedmethodlistener.Success;
@Listeners(TestListener.class)
public class LoginPage {
	private WebDriver driver;
	private String userName = "//input[@name='UserName']";
	private String password = "//input[@name='Password']";
	private String login = "//input[@name='Login']";
	private String logout = "//a[@href='Login.html']";
	private Xls_Reader reader;
	public com.relevantcodes.extentreports.ExtentReports extent;
	public com.relevantcodes.extentreports.ExtentTest extentTest;

	@BeforeTest
	public void setExtend() {
		extent = new com.relevantcodes.extentreports.ExtentReports(
				System.getProperty("user.dir") + "/test-output/ExtentReportResults.html", true);
		extent.addSystemInfo("Host Name", "Megala");
		extent.addSystemInfo("User Name", "Megala");
		extent.addSystemInfo("Environment", "Stage");
	}

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}

	public String getScreenShot(WebDriver driver, String screenShotName) throws IOException {
		SimpleDateFormat dateName = new SimpleDateFormat("yyyyMMddhhmmss");
		String dateexact = dateName.format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/FailedTestCaseScreenShot/" + screenShotName + dateexact
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;

	}

	@BeforeMethod
	public void beforemethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demosite.executeautomation.com/Login.html");
	}

	@Test(dataProvider = "getInputData")
	public void launchBrowser(String usern, String pwd) {
		extentTest = extent.startTest("launchBrowser");
		sendKeysByXpath(userName, usern);
		sendKeysByXpath(password, pwd);
		clickelementByXpath(login);
		Assert.assertEquals(true, false);
		clickelementByXpath(logout);

	}

	@DataProvider
	public Iterator<Object[]> getInputData() {
		ArrayList<Object[]> testdata = getDataFromExcel();
		return testdata.iterator();
	}

	public ArrayList<Object[]> getDataFromExcel() {
		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		try {
			reader = new Xls_Reader("C:/Users/Naveen/workspace/TestApps/src/test/resources/data/TestData.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int rowNum = 2; rowNum <= reader.getRowCount("Sheet1"); rowNum++) {
			String username = reader.getCellData("Sheet1", "UserName", rowNum);
			String password = reader.getCellData("Sheet1", "Password", rowNum);
			Object ob[] = { username, password };
			myData.add(ob);
		}
		return myData;

	}

	public void sendKeysByXpath(String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(value);
	}

	public void clickelementByXpath(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS" + result.getName());
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS" + result.getThrowable());
			String screen = getScreenShot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screen));
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screen));
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "TEST CASE SKKIPED IS" + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "TEST CASE PASSED IS" + result.getName());
		}
		extent.endTest(extentTest);
		driver.quit();
	}
}
