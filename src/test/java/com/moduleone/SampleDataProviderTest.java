package com.moduleone;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SampleDataProviderTest {
	String[][] data = { { "test1", "test1pwd" }, { "Test2", "test2pwd" }, {"Test3","test3Pwd"}};

	@DataProvider(name = "loginData")
	public String[][] loginproviderData() {
		return data;
	}

	@Test(dataProvider="loginData")
	public void login(String uname, String pwd) {
		System.out.println(uname + "*******" + pwd);
	}
}
