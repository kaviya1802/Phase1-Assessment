package com.ecommerce.amazom.tests;

import static org.junit.Assert.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.xml.xpath.XPath;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class AmazonPageTest {

	WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String siteUrl = "https://www.amazon.in/";

	@Before
	public void setUpDriver() throws Exception {
		// 1. setup properties
		System.getProperty("webdriver.chrome.driver",
				"/home/nallakavi35gmai/eclipse-workspace/Phase1-Selenium-Amazon-tests/driver/chromedriver");
		driver = new ChromeDriver();
		driver.get(siteUrl);

	}

	@After
	public void closeDriver() throws Exception {
		driver.close();
	}

	@Test
	public void testSourceURL() {
		Assert.assertEquals(siteUrl, driver.getCurrentUrl());

	}

	@Test
	public void testSourceTitle() {
		String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle);
	}

	@Test
	public void addLocation() throws InterruptedException {
		// find location element using locators
		driver.findElement(By.id("glow-ingress-line2")).click(); // locating the hello object
		Thread.sleep(2000);
		driver.findElement(By.id("GLUXZipUpdateInput")).sendKeys("560038");// entering the pincode
		driver.findElement(By.xpath("//*[@id=\"GLUXZipUpdate\"]/span/input")).click();// Applying the changes
	}

	@Test
	public void verifySearch() {
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));// finding the search box
		searchBox.sendKeys("Samsung M30s"); // sending search name
		WebElement submit = driver.findElement(By.id("nav-search-submit-button"));// searching the entered name
	}

	@Test
	public void verifyAddToCart() throws InterruptedException {
		
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));// finding the search box
		searchBox.sendKeys("iphone max pro 12"); // sending search name
		WebElement submit = driver.findElement(By.id("nav-search-submit-button"));// searching the entered name
		submit.click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div/div[1]/div/span[3]/div[2]/div[2]/div/span/div/div/div[2]/div[1]/div/div/span/a/div")).click();// item selection
		String mainWindow = driver.getWindowHandle();
		ArrayList<String> windows = new ArrayList<String> (driver.getWindowHandles()); //storing in array
		driver.switchTo().window(windows.get(1));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#add-to-cart-button")).click();
		Thread.sleep(2000);
		driver.close();		
		driver.switchTo().window(windows.get(0));
	}
	
	@Test
	public void verifyCatagories() {
		WebElement all = driver.findElement(By.id("searchDropdownBox"));
		Select catagories = new Select(all);
		catagories.selectByVisibleText("Books");
		WebElement selectedCatacoary = catagories.getFirstSelectedOption();
		Assert.assertEquals("Books", selectedCatacoary.getText());
	}

	}
