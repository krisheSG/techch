package stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.junit.Assert;
import pages.TheOppenheimerPage;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;


import java.io.*;

public class TheOppenheimerPageStepDef extends MasterStepDefs {
    TheOppenheimerPage theOppenheimerProjectPage;

    @Given("User launched OppenheimerProject login page in {string}")
    public void userLaunchedOppenheimerProjectLoginPageIn(String strDevice) {
        initializeDriver(strDevice);

        theOppenheimerProjectPage = new TheOppenheimerPage(driver);
        ExtentCucumberAdapter.addTestStepLog("Application launched successfully");
        try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenhot(driver));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        //  quit
        if (driver != null) {
            driver.quit();
        }
    }

    @When("User upload CSV file input of working class hero")
    public void userUploadCSVFileInputOfWorkingClassHero() {
        String currentDirectory = System.getProperty("user.dir");
        theOppenheimerProjectPage.clickFile();
        theOppenheimerProjectPage.setFilePath(currentDirectory + readData().getProperty("CSVFilePath"));
    }

    @Given("User verify first row of the csv file must be with {string}")
    public void userVerifyFirstRowOfTheCsvFileMustBeWith(String strFileHeader) throws IOException {
        String filePath = System.getProperty("user.dir") + readData().getProperty("CSVFilePath");
        Assert.assertEquals(strFileHeader, verifyFileHeader(filePath));
    }

    @Given("User verify subsequent rows of csv are the relevant details of each working class hero")
    public void userVerifySubsequentRowsOfCsvAreTheRelevantDetailsOfEachWorkingClassHero() throws IOException {
        String filePath = System.getProperty("user.dir") + readData().getProperty("CSVFilePath");
        Assert.assertTrue(verifyCSVFileValue(filePath));
    }

    @When("User verify the button on the screen must be red-colored {string}")
    public void userVerifyTheHeButtonOnTheScreenMustBeRedColored(String strColor) {
        Assert.assertEquals(strColor, theOppenheimerProjectPage.verifyImgColor());
    }

    @When("User verify the text on the button must be exactly {string}")
    public void userVerifyTheTextOnTheButtonMustBeExactly(String strImgText) {
        Assert.assertEquals(strImgText, theOppenheimerProjectPage.verifyImgText());
    }

    @When("User verify after clicking on the button, it should direct me to a page with a text that says {string}")
    public void userVerifyAfterClickingOnTheButtonItShouldDirectMeToAPageWithATextThatSays(String arg0) {
        Assert.assertEquals(arg0, theOppenheimerProjectPage.verifyCashDispensedText());
    }


}
