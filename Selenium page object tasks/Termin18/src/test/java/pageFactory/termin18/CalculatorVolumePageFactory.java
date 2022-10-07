package pageFactory.termin18;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CalculatorVolumePageFactory {

	private WebDriver driver;

	@FindBy(id = "ctankradius")
	private WebElement baseRadius;

	@FindBy(id = "ctanklength")
	private WebElement height;

	@FindBy(name = "ctankradiusunit")
	private WebElement baseRadiusUnit;

	@FindBy(name = "ctanklengthunit")
	private WebElement heightUnit;

	@FindBy(name = "cylcal")
	private WebElement calculateButton;

	@FindBy(xpath = "//*[@id='content']/table[4]/tbody/tr[4]/td[2]/font/b")
	private WebElement resultElement;
	
	@FindBy(xpath = "//font[text() = 'The base radius needs to be positive.']")
	private List<WebElement> baseRadiousErrorMessage;
	
	@FindBy(xpath = "//font[text() = 'The height needs to be positive.']")
	private List<WebElement> heightErrorMessage;

	public CalculatorVolumePageFactory(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	};

	public void setBaseRadius(String baseRadiusValue) {
		baseRadius.clear();
		baseRadius.sendKeys(baseRadiusValue);
	}

	public void setHeight(String heightValue) {
		height.clear();
		height.sendKeys(heightValue);
	}

	public void setBaseRadiusUnit(String value) {
		WebElement el = baseRadiusUnit;
		Select sel = new Select(el);
		sel.selectByValue(value);
	}

	public void setHeightUnit(String value) {
		WebElement el = heightUnit;
		Select sel = new Select(el);
		sel.selectByValue(value);
	}

	public void clickCalculateButton() {
		calculateButton.click();
	}

	public float getResult() {
		String resulTxt = resultElement.getText();
		float result = Float.parseFloat(resulTxt.split(" ")[0]);
		return result;
	}

	// U ovom slucaju vracamo String jer ce nam uvek samo trebati text poruke a ne
	// ceo WebElement (skracujemo sebi posao u buducnosti)
	public String getResultMsg() {
		return resultElement.getText();
	}

	// U ovom slucaju vracamo true/false jer me samo zanima da li je poruka
	// prikazana ili ne. Ne treba nam ceo WebElement (skracujemo sebi posao u
	// buducnosti)
	public Boolean isHeightErrorMsgPreasent() {
		List<WebElement> lista = heightErrorMessage;
		if (lista.size() == 0)
			return false;
		else
			return true;
	}

	public Boolean isBaseRadiusErrorMsgPreasent() {
		List<WebElement> lista = baseRadiousErrorMessage;
		if (lista.size() == 0)
			return false;
		else
			return true;
	}

}
