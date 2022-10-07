package pages.termin18;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SubjectsPage {

	private WebDriver driver;

	public SubjectsPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void clickAddSubject() {
		driver.findElement(By.xpath("//button[@ui-sref='predmeti.new']")).click();
	}

	public WebElement getName() {
		return driver.findElement(By.id("field_naziv"));
	}
	
	public void setName(String value) {
		WebElement nameElement = this.getName();
		nameElement.clear();
		nameElement.sendKeys(value);
	}
	
	public void selectStudent(String nameLastName) {
		Select studentsDropdown = new Select(driver.findElement(By.name("studenti")));
		studentsDropdown.selectByVisibleText(nameLastName);
	}
	
	public void selectTeacher(String nameLastName) {
		Select teachersDropdown = new Select(driver.findElement(By.name("nastavnici")));
		teachersDropdown.selectByVisibleText(nameLastName);
	}
	
	public void clickSaveSubject() {
		driver.findElement(By.xpath("//span[@translate='entity.action.save']")).click();
	}
	
	public boolean checkExistance(String value) {
		return driver.findElement(By.linkText(value)).isDisplayed();
	}
	
	public void populateSubjectFields(String name, String nameOfStudent, String nameOfTeacher) {
		this.setName(name);
		this.selectStudent(nameOfStudent);
		this.selectTeacher(nameOfTeacher);
		this.clickSaveSubject();
	}
	
	public int numberOfSubjectsInTable() {
		return driver.findElements(By.xpath("//table/tbody/tr")).size();
	}
}
