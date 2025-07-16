package testCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.Leave;
import pages.Menu;
import utility.BaseClass;

@Listeners(utility.Listener.class)
public class Leave_TestCase extends BaseClass {

	Menu menu = PageFactory.initElements(driver, Menu.class);
	Leave leave=PageFactory.initElements(driver, Leave.class);
	SoftAssert softAssert = new SoftAssert();
	
	@Test(priority = 0)
	public void LoginOrangeHRM() throws Throwable {
		test = report.createTest(new Throwable().getStackTrace()[0].getMethodName());
		LoginUserType("General");
		
		//menu.AssignLeave();

	}
	@Test(priority = 1)
	public void OpenLeaveAndViewApplySubmenu() {
		test = report.createTest(new Throwable().getStackTrace()[0].getMethodName());
		menu.Leave();
		leave.Apply();
		boolean assertStatus=leave.assertText();
		softAssert.assertTrue(assertStatus, "Assert fail");
		
		softAssert.assertAll();
	}
}
