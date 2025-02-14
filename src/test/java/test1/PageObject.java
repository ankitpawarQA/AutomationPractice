package test1;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageObject {

	@FindBy(xpath = "//*[@id=\"radio-btn-example\"]")
	WebElement radioBtn1;

	@FindBy(id = "autocomplete")
	WebElement suggestionClass;

	@FindBy(id = "checkBoxOption2")
	WebElement option2Checkbox;

}
