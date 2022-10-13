package tests.termin18;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.termin18.LoginPage;
import pages.termin18.NavbarPage;
import pages.termin18.StudentsPage;

/**
 * Resenje prvog zadatka sa Termina 18 Resenje nadogradjuje prvi zadatak sa
 * termina 17 tako sto ukljucuje PageObject sablon
 * 
 *
 *
 */
public class Zadatak1 {

	private WebDriver driver;
	private NavbarPage navbarPage;
	private LoginPage loginPage;
	private StudentsPage studentsPage;

	@BeforeSuite
	public void initalize() {

		// Create a Selenium WebDriver instance
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();

		navbarPage = new NavbarPage(driver);

		// Configure browser if required
		// Maximize browser window
		driver.manage().window().maximize();

		// Wait 5 seconds for loading the page before Exception
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		// Wait 1 second before very action
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		// Navigate to the required web page
		driver.navigate().to("http://localhost:8080/#/");

	}

	@Test
	public void login() {
//		WebElement accountLink = driver.findElement(By.id("account-menu"));
//		assertTrue(accountLink.isDisplayed());
//		accountLink.click();
		navbarPage.clickAccountLink();

//		WebElement singInLink = driver.findElement(By.xpath("//a[@ui-sref='login']"));
//		assertTrue(singInLink.isDisplayed());
//		singInLink.click();
		navbarPage.clickSignInLink();

//		populateFieldById("username", "admin");
//		populateFieldById("password", "admin");
//
//		WebElement singInBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/form/button"));
//		assertTrue(singInBtn.isDisplayed());
//		singInBtn.click();
		loginPage = new LoginPage(driver);
		loginPage.setUsername("admin");
		loginPage.setPassword("admin");
		loginPage.clickSignInButton();

//		WebElement msgDiv = driver.findElement(By.xpath("//div[@translate='main.logged.message']"));
//		String msgString = msgDiv.getText();
		String msgString = loginPage.getWelcomeTxt();
		assertEquals(msgString, "You are logged in as user \"admin\".");
	}

	@Test(dependsOnMethods = { "login" })
	public void testAddStudent() throws InterruptedException {

//		WebElement entitiesLink = driver.findElement(By.cssSelector("#navbar-collapse > ul > li:nth-child(2) > a"));
//		assertTrue(entitiesLink.isDisplayed());
//		entitiesLink.click();
		navbarPage.clickEntitiesLink();

//		WebElement studentsLink = driver.findElement(By.xpath("//a[@ui-sref='studenti']"));
//		assertTrue(studentsLink.isDisplayed());
//		studentsLink.click();
		navbarPage.clickStudentsLink();

//		int numOfTableRowsBefore = driver.findElements(By.xpath("//table/tbody/tr")).size();
		studentsPage = new StudentsPage(driver);
		int numOfTableRowsBefore = studentsPage.numberOfStudentsInTable();

//		WebElement addNewStudentBtn = driver.findElement(By.xpath("//button[@ui-sref='studenti.new']"));
//		assertTrue(addNewStudentBtn.isDisplayed());
//		addNewStudentBtn.click();
		studentsPage.clickAddStudent();
		
		Thread.sleep(3000);

		// add first student
//		populateFieldById("field_indeks", "E1234");
//		populateFieldById("field_ime", "Marko");
//		populateFieldById("field_prezime", "Markovic");
//		populateFieldById("field_grad", "Novi Sad");
//
//		WebElement saveStudentBtn = driver.findElement(By.xpath("//span[@translate='entity.action.save']"));
//		assertTrue(saveStudentBtn.isDisplayed());
//		saveStudentBtn.click();

		studentsPage.populateStudentsFields("E1234", "Marko", "Markovic", "Novi Sad");
		
		Thread.sleep(3000);

		// attention: we just relocate web element
//		addNewStudentBtn = driver.findElement(By.xpath("//button[@ui-sref='studenti.new']"));
//		assertTrue(addNewStudentBtn.isDisplayed());
//		addNewStudentBtn.click();
		studentsPage.clickAddStudent();

		// add second student
//		populateFieldById("field_indeks", "E5652");
//		populateFieldById("field_ime", "Nikola");
//		populateFieldById("field_prezime", "Nikolic");
//		populateFieldById("field_grad", "Beograd");
//
//		// attention: we just relocate web element
//		saveStudentBtn = driver.findElement(By.xpath("//span[@translate='entity.action.save']"));
//		assertTrue(saveStudentBtn.isDisplayed());
//		saveStudentBtn.click();
		
		studentsPage.populateStudentsFields("E5652", "Nikola", "Nikolic", "Beograd");

		Thread.sleep(3000);

		// check if students are inside table
//		WebElement indexLink = driver.findElement(By.linkText("E1234"));
//		assertTrue(indexLink.isDisplayed(), "Can't locate added student E1234");
		assertTrue(studentsPage.checkExistance("E1234"), "Can't locate added student E1234");

//		indexLink = driver.findElement(By.linkText("E5652"));
//		assertTrue(indexLink.isDisplayed(), "Can't locate added student E5652");
		assertTrue(studentsPage.checkExistance("E5652"), "Can't locate added student E5652");

//		int numOfTableRowsAfter = driver.findElements(By.xpath("//table/tbody/tr")).size();
		int numOfTableRowsAfter = studentsPage.numberOfStudentsInTable();

		// assert that number of table rows increase by 2
		assertEquals(numOfTableRowsAfter, numOfTableRowsBefore + 2);
	}

	@Test(dependsOnMethods = { "testAddStudent" })
	public void testEditStudent() throws InterruptedException {

//		WebElement editStudentBtn = driver.findElement(By.xpath(
//				"//tr/td[a/text() = 'E5652']/following-sibling::td//button[@ui-sref='studenti.edit({id:studenti.id})']"));
//		assertTrue(editStudentBtn.isDisplayed());
//		editStudentBtn.click();
		studentsPage.clickEditButtonWithIndex("E5652");

//		populateStudentField("field_grad", "Kraljevo");

//		WebElement saveStudentBtn = driver.findElement(By.xpath("//span[@translate='entity.action.save']"));
//		assertTrue(saveStudentBtn.isDisplayed());
//		saveStudentBtn.click();
		
		studentsPage.setCity("Kraljevo");
		studentsPage.clickSaveStudent();

		Thread.sleep(3000);

//		String editedStudentCityTxt = driver
//				.findElement(By.xpath("//tr/td[a/text() = 'E5652']/following-sibling::td[3]")).getText();
		String editedStudentCityTxt = studentsPage.checkCityForStudentWithIndex("E5652");
		assertEquals(editedStudentCityTxt, "Kraljevo");

	}

	@Test(dependsOnMethods = { "testEditStudent" })
	public void testDeleteStudent() throws InterruptedException {

//		WebElement deleteStudentBtn = driver.findElement(
//				By.xpath("//tr[td/a/text() = 'E1234']/td//button[@ui-sref='studenti.delete({id:studenti.id})']"));
//		assertTrue(deleteStudentBtn.isDisplayed());
//		deleteStudentBtn.click();
		studentsPage.clickDeleteButtonWithIndex("E1234");

//		WebElement confirmButton = driver.findElement(By.xpath("//form[@name='deleteForm']//button[@type='submit']"));
//		confirmButton.click();
		studentsPage.clickConfirmDeleteButton();

		Thread.sleep(3000);

//		deleteStudentBtn = driver.findElement(
//				By.xpath("//tr[td/a/text() = 'E5652']/td//button[@ui-sref='studenti.delete({id:studenti.id})']"));
//		assertTrue(deleteStudentBtn.isDisplayed());
//		deleteStudentBtn.click();
		studentsPage.clickDeleteButtonWithIndex("E5652");

//		confirmButton = driver.findElement(By.xpath("//form[@name='deleteForm']//button[@type='submit']"));
//		confirmButton.click();
		studentsPage.clickConfirmDeleteButton();

		// check if students are inside table
//		WebElement indexLink = driver.findElement(By.linkText("E1234"));
//		assertFalse(indexLink.isDisplayed(), "Element still present, should be deleted E1234");
		assertFalse(studentsPage.checkExistance("E1234"), "Element still present, should be deleted E1234");

//		indexLink = driver.findElement(By.linkText("E5652"));
//		assertFalse(indexLink.isDisplayed(), "Element still present, should be deleted E5652");
		assertFalse(studentsPage.checkExistance("E5652"), "Element still present, should be deleted E5652");

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
