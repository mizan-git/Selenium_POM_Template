package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Leave {

	
	
	@FindBy(how = How.XPATH, using = "//a[text()='Apply']")
	private WebElement apply;
	
	@FindBy(how = How.XPATH, using = "//div//h6[text()='Apply Leave']")
	private WebElement applyLevel;
	
	
	
	public void Apply() {
		apply.click();
	}
	
	
	public boolean assertText() {
		String txt=applyLevel.getText();
		if(txt.contains("Apply Leave")) {
			return true;
			
		}else {
			return false;
		}
	}
}
