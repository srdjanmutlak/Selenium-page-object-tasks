package tests.termin18;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.termin18.CalculatorPercentagePage;

public class LocatorsExample {

	private WebDriver driver;
	private CalculatorPercentagePage calculatorPercentagePage;

	/**
	 * This method will be executed before the test start.
	 */
	@BeforeSuite
	public void initalize() {

		// Create a Selenium WebDriver instance
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();

		// Create Page instance, send driver in constructor
		calculatorPercentagePage = new CalculatorPercentagePage(driver);

		// Configure browser if required
		// Maximize browser window
		driver.manage().window().maximize();

		// Wait 5 seconds for loading the page before Exception
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		// Wait 1 second before very action
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

	}

	@BeforeMethod
	public void goToPage() {
		// Navigate to the required web page
		driver.navigate().to("https://www.calculator.net/percent-calculator.html");
	}

	/*
	 * Select and enter value in both input fields. Click calculate button. After
	 * that select value from result, extract text from it and convert it to int
	 * value. Create assertation. Clear form by clicking on Clear button.
	 */
	@Test
	public void testWithBothFields() {

		// Set value in first input field
		calculatorPercentagePage.setFirstNumber("10");

		// Set value in second input field
		calculatorPercentagePage.setSecondNumber("50");
		
		// Click on Calculate button
		calculatorPercentagePage.getCalculateButton().click();

		// Get text value from result element
		int resultInt = calculatorPercentagePage.getResult();
		
		// Cast from string to any other numeric value
//		int resultInt = Integer.parseInt(resultValue);

		// Assert that real value is same as expected
		assertEquals(resultInt, 5);

		// Print result in Java console
		System.out.println(" The Result is " + resultInt);

		// Click on Clear button
		calculatorPercentagePage.getClearButton().click();

	}

	/*
	 * Try form without entering inputs. Just click on calculate button. Select
	 * error text and compare it to expected value.
	 */
	@Test
	public void testWithEmpthyFields() {
		// Locate element by XPATH and click on it
//		driver.findElement(By.xpath(".//*[@id='content']/table[1]/tbody/tr[2]/td/input[2]")).click();
		
		calculatorPercentagePage.getCalculateButton().click();

//		WebElement errorTxt = driver.findElement(By.cssSelector("#content font"));
//		String actualTxtValue = errorTxt.getText();
		
		String actualTxtValue = calculatorPercentagePage.getErrorText();

		assertEquals(actualTxtValue, "Please provide two numeric values in any fields below.");
	}

	/*
	 * Select all links to other calculators from page. They represent a list of
	 * WebElements. If we want to print visible text from each of them, we need to
	 * iterate through list.
	 */
	@Test
	public void testAllCalculators() {
		int allLinksNum = calculatorPercentagePage.getAllCalcList();

//		for (WebElement webElement : allLinks) {
//			System.out.println(webElement.getText());
//		}

		assertEquals(allLinksNum, 8);
	}

	/**
	 * This method will be executed at the end of the test.
	 */
	@AfterSuite
	public void quitDriver() {

		// Close browser window
		driver.quit();
		driver = null;
	}

}
