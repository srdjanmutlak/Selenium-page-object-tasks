package tests.termin18;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.termin18.CalculatorVolumePage;

public class CalculatorVolumeTest {

	private WebDriver driver;
	private CalculatorVolumePage calculatorVolumePage;

	@BeforeSuite
	public void initalize() {

		// Create a Selenium WebDriver instance
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();

		// Create Page instance, send driver in constructor
		calculatorVolumePage = new CalculatorVolumePage(driver);

		// Configure browser if required
		// Maximize browser window
		driver.manage().window().maximize();

		// Wait 5 seconds for loading the page before Exception
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		// Wait 1 second before very action
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		driver.navigate().to("https://www.calculator.net/volume-calculator.html");
	}

	@Test
	public void pozitivanTest() {
		calculatorVolumePage.setBaseRadius("5");
		calculatorVolumePage.setHeight("6");
		calculatorVolumePage.clickCalculateButton();

		float rezultatBroj = calculatorVolumePage.getResult();

		// zaokruzujemo brojeve na trecu decimalu, ukoliko zelite vecu preciznost
		// povecajte deltu, npr. 0.00001
		assertEquals(rezultatBroj, 471.23889803847, 0.001);
	}

	@Test
	public void pozitivanTestSaStringom() {
		calculatorVolumePage.setBaseRadius("5");
		calculatorVolumePage.setHeight("6");

		calculatorVolumePage.clickCalculateButton();

		String rezultatText = calculatorVolumePage.getResultMsg();
		assertEquals(rezultatText, "471.23889803847 meters3");
	}

	@Test
	public void pozitivanTestNulaRadius() {
		calculatorVolumePage.setBaseRadius("0");
		calculatorVolumePage.setHeight("6");

		calculatorVolumePage.clickCalculateButton();

		String rezultatText = calculatorVolumePage.getResultMsg();
		assertEquals(rezultatText, "0 meters3");
	}

	@Test
	public void pozitivanTestFeet() {
		calculatorVolumePage.setBaseRadius("5");
		calculatorVolumePage.setHeight("6");

		calculatorVolumePage.setBaseRadiusUnit("feet");
		calculatorVolumePage.setHeightUnit("feet");
		calculatorVolumePage.clickCalculateButton();

		String rezultatText = calculatorVolumePage.getResultMsg();
		assertEquals(rezultatText, "471.23889803847 feet3");

		calculatorVolumePage.setBaseRadiusUnit("meters");
		calculatorVolumePage.setHeightUnit("meters");
	}

	@Test
	public void negativanTest() {
		calculatorVolumePage.setBaseRadius("5");
		calculatorVolumePage.setHeight("dasf");

		calculatorVolumePage.clickCalculateButton();

		assertTrue(calculatorVolumePage.isHeightErrorMsgPreasent());
	}

	@Test
	public void negativanTestObaPolja() {
		calculatorVolumePage.setBaseRadius("asfa");
		calculatorVolumePage.setHeight("dasf");

		calculatorVolumePage.clickCalculateButton();

		assertTrue(calculatorVolumePage.isHeightErrorMsgPreasent());
		assertTrue(calculatorVolumePage.isBaseRadiusErrorMsgPreasent());
	}

	@Test
	public void negativanTestMinusRadius() {
		calculatorVolumePage.setBaseRadius("-1");
		calculatorVolumePage.setHeight("23");

		calculatorVolumePage.clickCalculateButton();

		assertFalse(calculatorVolumePage.isHeightErrorMsgPreasent());
		assertTrue(calculatorVolumePage.isBaseRadiusErrorMsgPreasent());
	}

	@Test
	public void negativanTestMinusHeight() {
		calculatorVolumePage.setBaseRadius("23");
		calculatorVolumePage.setHeight("-1");

		calculatorVolumePage.clickCalculateButton();

		assertTrue(calculatorVolumePage.isHeightErrorMsgPreasent());
		assertFalse(calculatorVolumePage.isBaseRadiusErrorMsgPreasent());
	}

	/**
	 * This method will be executed at the end of the test.
	 */
	@AfterSuite
	public void quitDriver() {

		// Close browser window
		driver.quit();
	}
}
