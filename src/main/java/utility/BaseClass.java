package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriverWait wait;
	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest test;
	ExtentSparkReporter spark;
	public static String screenshotName;

	public String projectPath = System.getProperty("user.dir");

	public Properties prop;
	public FileInputStream input;
	public String browser;
	public String environment;
	public String baseUrl;
	public String lg_userName;
	public String lg_password;
	public String VerifyUserName;
	public String VerifyUserPassword;
	public String author;

	public BaseClass() {
		try {
			LoadProperties();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void LoadProperties() throws Throwable {
		prop = new Properties();

		input = new FileInputStream(projectPath + "/Configurations/Configration.properties");
		prop.load(input);

		browser = prop.getProperty("browser");
		environment = prop.getProperty("environment");
		baseUrl = prop.getProperty("baseUrl");

		lg_userName = prop.getProperty("userName");
		lg_password = prop.getProperty("password");

		VerifyUserName = prop.getProperty("VerifyUserName");
		VerifyUserPassword = prop.getProperty("VerifyUserPassword");
		author = prop.getProperty("AutomationEngineer");

		if (driver == null) {
			report = new ExtentReports();
			spark = new ExtentSparkReporter(projectPath + "/Reports/ExtentReprot.html");
			// JSON config file
			final File reportConfigFile = new File(projectPath + "/Configurations/extentConfig.json");
			spark.loadJSONConfig(reportConfigFile);
			report.attachReporter(spark);

			driver = browserSetup(browser);
			SetEnvironment();
		}
	}

	public WebDriver browserSetup(String browser) {

		if (browser.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		

		else if (browser.contains("firefox")) {

			WebDriverManager.chromedriver().setup();
			driver = new FirefoxDriver();
		}

		else if (browser.contains("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().window().maximize();

		return driver;
	}

	public void SetEnvironment() {

		if (environment.equals("testing")) {

			driver.get(baseUrl);

		}

		else if (environment.equals("development")) {
			driver.get(baseUrl);
		}
	}

	public void LoginUserType(String UserType) throws Throwable {
		if (UserType.contains("General")) {
			lg_userName = prop.getProperty("userName");
			lg_password = prop.getProperty("password");
			UserLogin(lg_userName, lg_password);
		} 

	}

	public void UserLogin(String user, String password) throws Throwable {
		driver.findElement(By.name("username")).sendKeys(user);
		Thread.sleep(500);
		driver.findElement(By.name("password")).sendKeys(password);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(500);

	}

	public static String ScreenCapture() throws IOException {

		screenshotName = "Error_" + CurrentTime() + ".jpg";

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// System.out.println(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotName);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\Reports\\" + screenshotName));

		return screenshotName;

	}

	private static String CurrentTime() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy-hh_mm_ss");
		LocalDateTime CurrentTime = LocalDateTime.now();

		return (dateFormat.format(CurrentTime));
	}

	
	@AfterSuite()
	public void CloseBrowser() {
		driver.close(); 
		

	}

}
