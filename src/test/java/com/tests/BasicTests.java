package com.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicTests {
	
	public static WebDriver driver;
	
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--no-sandbox"); // Bypass OS security model
		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("http://computer-database.herokuapp.com/computers");
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
	}
	
	
	
	@Test(priority = 1)
	public void addComputer() {
		driver.findElement(By.id("add")).click();
		driver.findElement(By.id("name")).sendKeys("Zephyr 7");
		driver.findElement(By.id("introduced")).sendKeys("2020-09-01");
		driver.findElement(By.id("discontinued")).sendKeys("2020-12-31");
		Select select = new Select(driver.findElement(By.id("company")));
		select.selectByVisibleText("RCA");
		
	}
	
	
	@Test(priority=2)
	public void editComputer() {
		
		driver.findElement(By.id("searchbox")).sendKeys("ACE");
		driver.findElement(By.id("searchsubmit")).click();
		driver.findElement(By.linkText("ACE")).click();
		driver.findElement(By.id("introduced")).sendKeys("2020-09-01");
		driver.findElement(By.id("discontinued")).sendKeys("2020-12-31");
		Select select = new Select(driver.findElement(By.id("company")));
		select.selectByVisibleText("IBM");
		
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
