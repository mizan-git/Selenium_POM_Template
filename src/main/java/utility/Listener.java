package utility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class Listener extends BaseClass implements ITestListener {
	
	

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//author=prop.getProperty("AutomationEngineer");
		
		test.log(Status.FAIL, result.getName()).assignAuthor(author).assignDevice(browser);
		test.log(Status.FAIL, result.getThrowable());
		try {
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture()).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 report.flush();
	
		
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

		test.log(Status.SKIP, result.getName()).assignAuthor(author).assignDevice(browser);
		report.flush();
		
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		test.log(Status.PASS, result.getName()+" PASS").assignAuthor(author).assignDevice(browser);
		report.flush();
		
	}

}
