package com.moduleone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonUtils {
	private WebDriver driver;

	public void sendKeysByXpath(String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(value);
	}
	public void clickelementByXpath(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}
}
