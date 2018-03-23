package autop.cucumberdemo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import autop.seluitests.pagebojects.Autop_CommonPage;
import autop.seluitests.pagebojects.Autop_HomePage;
import autop.seluitests.pagebojects.Autop_LoginPage;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import lib.WebUILib;

@CucumberOptions(
		strict=true,
		features = "src/test/java/autop/cucumberdemo/features",
		glue = "autop.cucumberdemo.stepdefs",
		plugin = {"pretty",
			"html:target/cucumber-html-report",
			"json:target/cucumberReport.json",
			"testng:target/testNGCucumber.xml"
			},
		tags = {"@Autop_TestScenario"}
		)


public class CucumberRunner {
	
	private TestNGCucumberRunner testNGCucumberRunner;
	
	WebDriver driver;
	public Autop_HomePage homePage = null;
	public Autop_CommonPage commonPage = null;
	public Autop_LoginPage loginPage = null;

	public void initPages(WebDriver driver) {
		this.driver = driver;
		homePage = new Autop_HomePage(driver);
		commonPage = new Autop_CommonPage(driver);
		loginPage = new Autop_LoginPage(driver);
	}
	
	@BeforeClass
	public void setUpCucumberRunner() {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "Cucumber",
			description = "This is a cucumber runner test. It does not validate anything",
			dataProvider = "cucumberFeatureDataProvider")
	public void runCucumberTests(CucumberFeatureWrapper cucumberFeatures) {
		testNGCucumberRunner.runCucumber(cucumberFeatures.getCucumberFeature());
	}
	
	@DataProvider(name = "cucumberFeatureDataProvider")
	public Object[][] getFeatureData(){
		return testNGCucumberRunner.provideFeatures();
	}
	
	@AfterClass
	public void tearDown() {
		testNGCucumberRunner.finish();
	}
	
}
