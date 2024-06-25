package listeners;

import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseClass;

public class ExtentReport_WithTestNGListeners extends BaseClass implements ITestListener, ISuiteListener {

//	private ExtentReports extent = ExtentManager.createExtentReportInstance();
	protected static ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();

	private static ExtentReports extent;
	private static ExtentSparkReporter spark;
	
	Date date = new Date();
	
	public void onStart(ISuite suite) {
		
		String unique = date.toString().replace(" ", "_").replace(":", "_");

		extent = new ExtentReports();
		spark = new ExtentSparkReporter("target/Reports/ExtentReports/Extent" + unique + ".html");
		extent.attachReporter(spark);

		// Adding some config for spark Reporter
		spark.config().setEncoding("utf-8");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Lokesh Generating Automation Test Report");
		spark.config().setReportName("Automation Report");

		// Adding some system info
		extent.setSystemInfo("Tester", "Lokesh");
		extent.setSystemInfo("Build Number", "1.256.5636");
		extent.setSystemInfo("Organization", "Automation from Home");
	}
	
	public void onTestStart(ITestResult result) {

		String testName = result.getClass().getTypeName() + " - " + result.getMethod().getMethodName();
		extentTestThread.set(extent.createTest(testName));
	}

	public void onTestSuccess(ITestResult result) {
		String testName = result.getClass().getTypeName() + " - " + result.getMethod().getMethodName();
		extentTestThread.get().pass(MarkupHelper.createLabel(testName, ExtentColor.GREEN));
	}

	public void onTestSkipped(ITestResult result) {
		String testName = result.getClass().getTypeName() + " - " + result.getMethod().getMethodName();
		extentTestThread.get().skip(MarkupHelper.createLabel(testName, ExtentColor.YELLOW));
	}

	public void onTestFailure(ITestResult result) {
		String testName = result.getClass().getTypeName() + " - " + result.getMethod().getMethodName();		
		String exceptionMessage = result.getThrowable().toString();
		
		extentTestThread.get().fail(MarkupHelper.createCodeBlock(exceptionMessage)); // adding exception message
		// adding screenshot
		extentTestThread.get().fail("<b><font color = red>Evidence of Failure</font></b><br>",
				MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\lokesh\\Desktop\\abc.jpeg").build());
		extentTestThread.get().fail(MarkupHelper.createLabel(testName, ExtentColor.RED)); // Failing step
	}

	public void onFinish(ISuite suite) {
		if (extent != null) {
			extent.flush();
		}
	}

}
