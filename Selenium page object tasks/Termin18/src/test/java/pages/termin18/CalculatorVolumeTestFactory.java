package pages.termin18;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pageFactory.termin18.CalculatorVolumePageFactory;


public class CalculatorVolumeTestFactory {

	private WebDriver driver;
	private CalculatorVolumePageFactory calculatorVolumePageFactory;

	@BeforeSuite
	public void initalize() {

		// Create a Selenium WebDriver instance
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();

		// Create Page instance, send driver in constructor
		calculatorVolumePageFactory = new CalculatorVolumePageFactory(driver);

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
		calculatorVolumePageFactory.setBaseRadius("5");
		calculatorVolumePageFactory.setHeight("6");
		calculatorVolumePageFactory.clickCalculateButton();

		float rezultatBroj = calculatorVolumePageFactory.getResult();

		// zaokruzujemo brojeve na trecu decimalu, ukoliko zelite vecu preciznost
		// povecajte deltu, npr. 0.00001
		assertEquals(rezultatBroj, 471.23889803847, 0.001);
	}

	@Test
	public void pozitivanTestSaStringom() {
		calculatorVolumePageFactory.setBaseRadius("5");
		calculatorVolumePageFactory.setHeight("6");

		calculatorVolumePageFactory.clickCalculateButton();

		String rezultatText = calculatorVolumePageFactory.getResultMsg();
		assertEquals(rezultatText, "471.23889803847 meters3");
	}

	@Test
	public void pozitivanTestNulaRadius() {
		calculatorVolumePageFactory.setBaseRadius("0");
		calculatorVolumePageFactory.setHeight("6");

		calculatorVolumePageFactory.clickCalculateButton();

		String rezultatText = calculatorVolumePageFactory.getResultMsg();
		assertEquals(rezultatText, "0 meters3");
	}

	@Test
	public void pozitivanTestFeet() {
		calculatorVolumePageFactory.setBaseRadius("5");
		calculatorVolumePageFactory.setHeight("6");

		calculatorVolumePageFactory.setBaseRadiusUnit("feet");
		calculatorVolumePageFactory.setHeightUnit("feet");
		calculatorVolumePageFactory.clickCalculateButton();

		String rezultatText = calculatorVolumePageFactory.getResultMsg();
		assertEquals(rezultatText, "471.23889803847 feet3");

		calculatorVolumePageFactory.setBaseRadiusUnit("meters");
		calculatorVolumePageFactory.setHeightUnit("meters");
	}

	@Test
	public void negativanTest() {
		calculatorVolumePageFactory.setBaseRadius("5");
		calculatorVolumePageFactory.setHeight("dasf");

		calculatorVolumePageFactory.clickCalculateButton();

		assertTrue(calculatorVolumePageFactory.isHeightErrorMsgPreasent());
	}

	@Test
	public void negativanTestObaPolja() {
		calculatorVolumePageFactory.setBaseRadius("asfa");
		calculatorVolumePageFactory.setHeight("dasf");

		calculatorVolumePageFactory.clickCalculateButton();

		assertTrue(calculatorVolumePageFactory.isHeightErrorMsgPreasent());
		assertTrue(calculatorVolumePageFactory.isBaseRadiusErrorMsgPreasent());
	}

	@Test
	public void negativanTestMinusRadius() {
		calculatorVolumePageFactory.setBaseRadius("-1");
		calculatorVolumePageFactory.setHeight("23");

		calculatorVolumePageFactory.clickCalculateButton();

		assertFalse(calculatorVolumePageFactory.isHeightErrorMsgPreasent());
		assertTrue(calculatorVolumePageFactory.isBaseRadiusErrorMsgPreasent());
	}

	@Test
	public void negativanTestMinusHeight() {
		calculatorVolumePageFactory.setBaseRadius("23");
		calculatorVolumePageFactory.setHeight("-1");

		calculatorVolumePageFactory.clickCalculateButton();

		assertTrue(calculatorVolumePageFactory.isHeightErrorMsgPreasent());
		assertFalse(calculatorVolumePageFactory.isBaseRadiusErrorMsgPreasent());
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
