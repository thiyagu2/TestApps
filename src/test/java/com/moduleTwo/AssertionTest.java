package com.moduleTwo;

import javax.swing.SortingFocusTraversalPolicy;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionTest {
	SoftAssert softAssert = new SoftAssert();
	String txt = "Example";

	@Test

	public void testAsser1() {
		System.out.println("Am Launching my browser");
		Assert.assertEquals("User Name", "User");
		Assert.assertTrue(!(txt.isEmpty()), "successfully validated");
		System.out.println("Am Login to the application");
		softAssert.assertEquals("Home page title", "Dash board title");
		System.out.println("Am able to see the home page");
		softAssert.assertAll();
	}

	@Test(dependsOnMethods ="testAsser1", alwaysRun = true )
	public void test2() {
		System.out.println("dsfdsasdsadfsag*****************************************");
	}
	@Test
	public void tes32() {
		System.out.println("dsfdsasdsadfsag*****************************************");
	}
}
