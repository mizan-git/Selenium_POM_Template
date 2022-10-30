package mavenExecutable;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

import testCases.Leave_TestCase;

public class MavenExecutableJAR {

	static TestNG testng;
	public static void main(String[] args) {
		
		testng=new TestNG();
		String xmlPath= System.getProperty("user.dir")+"/TestCaseRunner.xml";
		System.out.println(xmlPath);
		List<String> xmlList =new ArrayList<String>();
		xmlList.add(xmlPath);
		testng.setTestSuites(xmlList);
		//testng.setTestClasses(new Class[] {Leave_TestCase.class});
		testng.run();

	}

}
