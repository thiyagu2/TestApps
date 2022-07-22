package com.module3;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println("Test Started Successfully");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test passed Successfully");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed Successfully");
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped Successfully");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test Failed with success percentage Successfully");
	}

	public void onStart(ITestContext context) {
		System.out.println("Test on Started Successfully");
	}

	public void onFinish(ITestContext context) {
		System.out.println("Test on Finish Successfully");
	}

}
