package tests.termin18;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.termin18.LoginPage;
import pages.termin18.NavbarPage;
import pages.termin18.StudentsPage;
import pages.termin18.SubjectsPage;
import pages.termin18.TeachersPage;

/**
 * Resenje drugog zadatka sa Termina 18 Resenje nadogradjuje drugi zadatak sa
 * termina 17 tako sto ukljucuje PageObjecy sablon
 * 
 * @author minamedic
 *
 */
public class Zadatak2 {

	private WebDriver driver;
	private NavbarPage navbarPage;
	private LoginPage loginPage;
	private TeachersPage teachersPage;
	private StudentsPage studentsPage;
	private SubjectsPage subjectsPage;

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
	public void testAddTeacher() throws InterruptedException {

//		WebElement entitiesLink = driver.findElement(By.cssSelector("#navbar-collapse > ul > li:nth-child(2) > a"));
//		assertTrue(entitiesLink.isDisplayed());
//		entitiesLink.click();
		navbarPage.clickEntitiesLink();

//		WebElement teachersLink = driver.findElement(By.xpath("//a[@ui-sref='nastavnici']"));
//		assertTrue(teachersLink.isDisplayed());
//		teachersLink.click();
		navbarPage.clickTeachersLink();

//		int numOfTableRowsBefore = driver.findElements(By.xpath("//table/tbody/tr")).size();
		teachersPage = new TeachersPage(driver);
		int numOfTableRowsBefore = teachersPage.numberOfTeachersInTable();

//		WebElement addNewTeacherBtn = driver.findElement(By.xpath("//button[@ui-sref='nastavnici.new']"));
//		assertTrue(addNewTeacherBtn.isDisplayed());
//		addNewTeacherBtn.click();
		teachersPage.clickAddTeacher();

		Thread.sleep(3000);

		// add teacher
//		populateFieldById("field_ime", "Milan");
//		populateFieldById("field_prezime", "Markovic");
//		populateFieldById("field_zvanje", "Profesor");

//		teachersPage.setName("Milan");
//		teachersPage.setLastName("Markovic");
//		teachersPage.setTitle("Profesor");

//		WebElement saveTecaherBtn = driver.findElement(By.xpath("//span[@translate='entity.action.save']"));
//		assertTrue(saveTecaherBtn.isDisplayed());
//		saveTecaherBtn.click();
//		teachersPage.clickSaveTeacher();

		teachersPage.populateTeachersFields("Milan", "Markovic", "Profesor");
//		teachersPage.populateTeachersFields("Jovan", "Markovic", "Profesor");

		Thread.sleep(3000);

		// check if teacher is inside table
//		WebElement nameLink = driver.findElement(By.linkText("Milan"));

		assertTrue(teachersPage.checkExistance("Milan"), "Can't locate added teacher Milan");

//		int numOfTableRowsAfter = driver.findElements(By.xpath("//table/tbody/tr")).size();
		int numOfTableRowsAfter = teachersPage.numberOfTeachersInTable();
		
		// assert that number of table rows increase by 1
		assertEquals(numOfTableRowsAfter, numOfTableRowsBefore + 1);
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

	@Test(dependsOnMethods = { "testAddStudent", "testAddTeacher" })
	public void createSubject() throws InterruptedException {

//		WebElement entitiesLink = driver.findElement(By.cssSelector("#navbar-collapse > ul > li:nth-child(2) > a"));
//		assertTrue(entitiesLink.isDisplayed());
//		entitiesLink.click();
		navbarPage.clickEntitiesLink();

//		WebElement subjectsLink = driver.findElement(By.xpath("//a[@ui-sref='predmeti']"));
//		assertTrue(subjectsLink.isDisplayed());
//		subjectsLink.click();
		navbarPage.clickSubjectsLink();
		
//		WebElement addNewSubjectBtn = driver.findElement(By.xpath("//button[@ui-sref='predmeti.new']"));
//		assertTrue(addNewSubjectBtn.isDisplayed());
//		addNewSubjectBtn.click();
		subjectsPage = new SubjectsPage(driver);
		subjectsPage.clickAddSubject();
		
		Thread.sleep(3000);
		
//		populateFieldById("field_naziv", "Matematika");
//
//		Select studentsDropdown = new Select(driver.findElement(By.name("studenti")));
//		studentsDropdown.selectByVisibleText("Marko Markovic");
//		Thread.sleep(1000);
//
//		Select teachersDropdown = new Select(driver.findElement(By.name("nastavnici")));
//		teachersDropdown.selectByVisibleText("Milan Markovic");
//		Thread.sleep(1000);
//
//		WebElement saveSubjectBtn = driver.findElement(By.xpath("//span[@translate='entity.action.save']"));
//		assertTrue(saveSubjectBtn.isDisplayed());
//		saveSubjectBtn.click();
		
		subjectsPage.populateSubjectFields("Matematika", "Marko Markovic", "Milan Markovic");
		
//		WebElement mathSubjectRow = driver.findElement(By.xpath("//tr[td/a/text() = 'Matematika']"));
//		WebElement name = mathSubjectRow.findElement(By.linkText("Matematika"));
//		WebElement student = mathSubjectRow.findElement(By.linkText("Marko Markovic"));
//		WebElement teacher = mathSubjectRow.findElement(By.linkText("Milan Markovic"));
		
		assertTrue(subjectsPage.checkExistance("Matematika"));
		assertTrue(subjectsPage.checkExistance("Marko Markovic"));
		assertTrue(subjectsPage.checkExistance("Milan Markovic"));
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
