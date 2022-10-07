package pages.termin18;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CalculatorVolumePage {

	private WebDriver driver;

	public CalculatorVolumePage(WebDriver driver) {
		super();
		this.driver = driver;
	};

	public WebElement getBaseRadius() {
		return driver.findElement(By.id("ctankradius"));
	}

	public void setBaseRadius(String baseRadiusValue) {
		WebElement baseRadiusElement = getBaseRadius();
		baseRadiusElement.clear();
		baseRadiusElement.sendKeys(baseRadiusValue);
	}

	public WebElement getHeight() {
		return driver.findElement(By.id("ctanklength"));
	}

	public void setHeight(String heightValue) {
		WebElement heightElement = getHeight();
		heightElement.clear();
		heightElement.sendKeys(heightValue);
	}

	public Select getBaseRadiusUnit() {
		WebElement el = driver.findElement(By.name("ctankradiusunit"));
		Select select = new Select(el);
		return select;
	}

	public void setBaseRadiusUnit(String value) {
		Select sel = getBaseRadiusUnit();
		sel.selectByValue(value);
	}

	public Select getHeightUnit() {
		WebElement el = driver.findElement(By.name("ctanklengthunit"));
		Select select = new Select(el);
		return select;
	}

	public void setHeightUnit(String value) {
		Select sel = getHeightUnit();
		sel.selectByValue(value);
	}

	public void clickCalculateButton() {
		driver.findElement(By.name("cylcal")).click();
	}

	public float getResult() {
		String resulTxt = driver.findElement(By.xpath("//*[@id=\"content\"]/table[4]/tbody/tr[4]/td[2]/font/b"))
				.getText();
		float result = Float.parseFloat(resulTxt.split(" ")[0]);
		return result;
	}

	// U ovom slucaju vracamo String jer ce nam uvek samo trebati text poruke a ne
	// ceo WebElement (skracujemo sebi posao u buducnosti)
	public String getResultMsg() {
		WebElement rezultat = driver.findElement(By.xpath("//*[@id=\"content\"]/table[4]/tbody/tr[4]/td[2]/font/b"));
		return rezultat.getText();
	}

	// U ovom slucaju vracamo true/false jer me samo zanima da li je poruka
	// prikazana ili ne. Ne treba nam ceo WebElement (skracujemo sebi posao u
	// buducnosti)
	public Boolean isHeightErrorMsgPreasent() {
		List<WebElement> lista = driver.findElements(By.xpath("//font[text() = 'The height needs to be positive.']"));
		if (lista.size() == 0)
			return false;
		else
			return true;
	}

	public Boolean isBaseRadiusErrorMsgPreasent() {
		List<WebElement> lista = driver
				.findElements(By.xpath("//font[text() = 'The base radius needs to be positive.']"));
		if (lista.size() == 0)
			return false;
		else
			return true;
	}

}
