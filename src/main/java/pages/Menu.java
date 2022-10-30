package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Menu {

	// Master Setting

	
	
	@FindBy(how=How.XPATH,using="//span[text()='Leave']")
	private WebElement leave;
	
	@FindBy(how = How.LINK_TEXT, using = "Assign Leave")
	private WebElement assignLeave;

	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Welcome")
	private WebElement profile;

	@FindBy(how = How.LINK_TEXT, using = "Logout")
	private WebElement logout;
	
	
	@FindBy(how = How.LINK_TEXT, using = "Timesheets")
	private WebElement timeSheets;
	
	
	
	
	@FindBy(how = How.XPATH, using = "Xpath")
	private WebElement ElementName;


	public void Leave() {
		leave.click();
	}

	public void AssignLeave() {
		assignLeave.click();
	}

	public void LogOut() {
		profile.click();
		logout.click();

	}
	
	
	public void Timesheets() {
		timeSheets.click();
	}

}

	

