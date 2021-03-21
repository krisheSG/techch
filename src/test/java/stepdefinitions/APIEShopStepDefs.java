package stepdefinitions;



import com.fasterxml.jackson.annotation.JsonSubTypes;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.APIReusuableLibrary;
import helper.HeroRecord;
import helper.HeroSerializationData;
import io.cucumber.datatable.TypeReference;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
//import com.fasterxml.jackson.core.type.TypeReference;


import org.junit.Assert;
import pages.TheOppenheimerPage;

import javax.xml.bind.SchemaOutputResolver;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;


public class APIEShopStepDefs extends MasterStepDefs {
    ValidatableResponse response;
    String strProductEndpointUri;
    String strInsertHeroSingleRecordEndpoint;
    String strInsertHeroMultipleRecordEndpoint;
    String strTaxReliefCalcutor;
    String strSingleRecordPayload;
    String strheroSerializationDataMapper;
    String strPostBodyContent;
    String strApiHost;
    String strApiHostURL;


    public APIEShopStepDefs() {
        String strBackendIP = System.getenv("backendPrivateIP");
        if (strBackendIP == null || strBackendIP.isEmpty()) {
            strApiHost = System.getProperty("apiHost");
            LOG.info("API URL Fetched from POM Settings");
        } else {
            strApiHost = strBackendIP;
            LOG.info("API URL Fetched from AWS ENVIRONMENT");
        }
        strApiHostURL = "http://" + strApiHost + ":8080";
        LOG.info("API URL : " + strApiHostURL);

        strInsertHeroSingleRecordEndpoint = strApiHostURL + readData().getProperty("InsertHeroSingleRecordEndpoint");
        strInsertHeroMultipleRecordEndpoint = strApiHostURL + readData().getProperty("InsertHeroMultipleRecordEndpoint");
        strTaxReliefCalcutor = strApiHostURL + readData().getProperty("TaxReliefCalcutor");
    }

    @Given("^An API endpoint for Products$")
    public void userSetGETPostsAPIEndpoint() {
        strProductEndpointUri = strProductEndpointUri + "all";
    }

    @When("^User send GET HTTP request$")
    public void sendGETHTTPRequest() {
        LOG.info("Executed this step");
    }

    /*@Then("^User receive valid HTTP response code \"([^\"]*)\"$")
    public void UserReceiveValidHTTPResponseCode(String strStatusCode) {
        try {
            response = apiDriver.sendNReceive(strProductEndpointUri, APIReusuableLibrary.SERVICEMETHOD.GET, null, Integer.parseInt(strStatusCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    @Then("^User receive valid HTTP response code \"([^\"]*)\"$")
    public void UserReceiveValidHTTPResponseCode(String strStatusCode) {
        try {
            response = apiDriver.sendNReceive(strProductEndpointUri, APIReusuableLibrary.SERVICEMETHOD.GET, null, Integer.parseInt(strStatusCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("^An API endpoint for Products with \"([^\"]*)\"$")
    public void userSetGETPostsAPIEndpointFor(String strName) {
        strProductEndpointUri = strProductEndpointUri + "search?productName=" + strName;
    }

    @And("^User set request body$")
    public void setRequestBody() {
        strPostBodyContent = apiDriver.readInput(readData().getProperty("PositivePaymentInputTemplate"));
    }

    @And("User set request body with single hero record")
    public void userSetRequestBodyWithSingleHeroRecord() {
        strPostBodyContent = apiDriver.readInput(readData().getProperty("SingleHeroInputTemplate"));
    }

    @And("User set request body with multiple hero records")
    public void userSetRequestBodyWithMultipleHeroRecords() {
        strPostBodyContent = apiDriver.readInput(readData().getProperty("MultipleHeroInputTemplate"));
    }


    @And("^User set invalid request body$")
    public void setInvalidRequestBody() {
        strPostBodyContent = apiDriver.readInput(readData().getProperty("NegativePaymentInputTemplate"));
    }

    @And("^User send POST HTTP request$")
    public void sendPOSTHTTPRequest() {
        LOG.info("Executed this step");
    }

    @Then("^User receive HTTP response code \"([^\"]*)\"$")
    public void UserReceiveHTTPResponseCode(String strStatusCode) {
        try {
            response = apiDriver.sendNReceive(strInsertHeroSingleRecordEndpoint, APIReusuableLibrary.SERVICEMETHOD.POST, APIReusuableLibrary.SERVICEFORMAT.JSON, strPostBodyContent, null,
                    Integer.parseInt(strStatusCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Then("User receives HTTP response code {string} for a single Hero record")
    public void userReceivesHTTPResponseCodeForASingleHeroRecord(String strStatusCode) {
        try {
            response = apiDriver.sendNReceive(strInsertHeroSingleRecordEndpoint, APIReusuableLibrary.SERVICEMETHOD.POST, APIReusuableLibrary.SERVICEFORMAT.JSON, strPostBodyContent, null,
                    Integer.parseInt(strStatusCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("User receives HTTP response code {string} for multiple Hero record")
    public void userReceivesHTTPResponseCodeForMultipleHeroRecord(String strStatusCode) {
        try {
            response = apiDriver.sendNReceive(strInsertHeroMultipleRecordEndpoint, APIReusuableLibrary.SERVICEMETHOD.POST, APIReusuableLibrary.SERVICEFORMAT.JSON, strPostBodyContent, null,
                    Integer.parseInt(strStatusCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Then("User receives HTTP response code {string} for single Hero record using Serialization")
    public void userReceivesHTTPResponseCodeForSingleHeroRecordUsingSerialization(String strStatusCode) {
          try {
              response = apiDriver.sendNReceive(strInsertHeroSingleRecordEndpoint, APIReusuableLibrary.SERVICEMETHOD.POST, APIReusuableLibrary.SERVICEFORMAT.JSON, strheroSerializationDataMapper, null,
                      Integer.parseInt(strStatusCode));
          }catch (Exception e){
              e.printStackTrace();
          }
    }


    @Given("An API endpoint for Hero Record")
    public void anAPIEndpointForHeroRecord() {
        LOG.info("Executed this step");
    }

    @Then("User receive response code {string}")
    public void userReceiveResponseCode(String strStatusCode) {
        try {
            response = apiDriver.sendNReceive(strInsertHeroSingleRecordEndpoint, APIReusuableLibrary.SERVICEMETHOD.POST, APIReusuableLibrary.SERVICEFORMAT.JSON, strSingleRecordPayload, null,
                    Integer.parseInt(strStatusCode));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("An API endpoint for Calculator Tax Releif")
    public void anAPIEndpointForCalculatorTaxReleif() {
        LOG.info("Executed this step");
    }

    @Then("User verify valid HTTP response code {string} for Calculator Tax Releif")
    public void userVerifyValidHTTPResponseCodeForCalculatorTaxReleif(String strStatusCode) {
        try {
            String strInsertHeroSingleRecord = readData().getProperty("TaxReliefCalcutor");
            strTaxReliefCalcutor = strApiHostURL + strInsertHeroSingleRecord;
            System.out.println(strTaxReliefCalcutor);
            response = apiDriver.sendNReceive(strTaxReliefCalcutor, APIReusuableLibrary.SERVICEMETHOD.GET, null, Integer.parseInt(strStatusCode));
            //System.out.println("hi"+response.log().all().toString());
            //System.out.println("hi"+response.extract().jsonPath().getString("name"));
            //System.out.println("hi"+response.extract().jsonPath().getString("name"));
            // System.out.println("hi"+response.extract().statusCode());
            // Assert.assertTrue("200", true);

            //List<String> jsonResponse = response.extract().jsonPath().getString("name") ;
            //List<String> jsonResponse = response.extract().jsonPath().getList("name") ;
            // System.out.println(jsonResponse.get(0));
            List<String> jsonResponse = response.extract().jsonPath().getList("$");
            System.out.println(jsonResponse.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @And("Response body contains all the valid fields for all the Tax Relief")
//    public void responseBodyContainsAllTheValidFieldsForAllTheTaxRelief() {
//
//    }


    @When("User verifies natid field must be masked from the fifth character onwards with dollar sign {string}")
    public void userVerifiesNatidFieldMustBeMaskedFromTheFifthCharacterOnwardsWithDollarSign(String arg0) throws Exception {
        String strInsertHeroSingleRecord = readData().getProperty("TaxReliefCalcutor");
        strTaxReliefCalcutor = strApiHostURL + strInsertHeroSingleRecord;
        System.out.println(strTaxReliefCalcutor);
        response = apiDriver.sendNReceive(strTaxReliefCalcutor, APIReusuableLibrary.SERVICEMETHOD.GET, null, Integer.parseInt("200"));
        List<String> jsonResponse = response.extract().jsonPath().getList("natid");
        verifyNatIDwithMaskedCondition(jsonResponse, arg0);
    }

    @When("User verifies computation of the tax relief is using the formula as described")
    public void userVerifiesComputationOfTheTaxReliefIsUsingTheFormulaAsDescribed() throws Exception {
        String strInsertHeroSingleRecord = readData().getProperty("TaxReliefCalcutor");
        strTaxReliefCalcutor = strApiHostURL + strInsertHeroSingleRecord;
        System.out.println(strTaxReliefCalcutor);
        //  response = apiDriver.sendNReceive(strTaxReliefCalcutor, APIReusuableLibrary.SERVICEMETHOD.GET, null, Integer.parseInt("200"));
        response = apiDriver.sendNReceive(strTaxReliefCalcutor, APIReusuableLibrary.SERVICEMETHOD.GET, null, Integer.parseInt("200"));
        //List<Object> jsonResponse = response.extract().jsonPath().getList("$");
        List<Object> jsonResponse = response.extract().jsonPath().getList("relief");
        System.out.println(jsonResponse.toString());

        /*//Tax Relief = ((salary-taxpaid)*variable)+gender bonus
        int age = 0;
        TheOppenheimerPage theOppenheimerPage = new TheOppenheimerPage(driver);
       // theOppenheimerPage.verifyTaxReliefCalculation(10000,1000,"f","1990-11-11");
        theOppenheimerPage.verifyTaxReliefCalculation(599,59.9,"m","1944-11-11");*/
    }


    @When("User verifies tax relief computation values with uploaded data {string}, {string}, {string}, {string}, {string}, {string}")
    public void userVerifiesTaxReliefComputationValuesWithUploadedData(String strBirthday, String strGender, String strName, String strNatid, String strSalary, String strTax) throws Exception {
      /*  String strInsertHeroSingleRecord = readData().getProperty("TaxReliefCalcutor");
        strTaxReliefCalcutor = strApiHostURL + strInsertHeroSingleRecord;
        System.out.println(strTaxReliefCalcutor);
        //  response = apiDriver.sendNReceive(strTaxReliefCalcutor, APIReusuableLibrary.SERVICEMETHOD.GET, null, Integer.parseInt("200"));
        response = apiDriver.sendNReceive(strTaxReliefCalcutor, APIReusuableLibrary.SERVICEMETHOD.GET, null, Integer.parseInt("200"));
         List<Object> jsonResponse = response.extract().jsonPath().getList("relief");
        System.out.println(jsonResponse.toString());
        //Tax Relief = ((salary-taxpaid)*variable)+gender bonus
        // theOppenheimerPage.verifyTaxReliefCalculation(10000,1000,"f","1990-11-11");
        String taxRelief1 = theOppenheimerPage.verifyTaxReliefCalculation(salary, tax, gender, birthday);
        Assert.assertEquals(jsonResponse.get(0).toString(), taxRelief1);*/
//        TheOppenheimerPage theOppenheimerPage = new TheOppenheimerPage(driver);
//        response = apiDriver.sendNReceive(strTaxReliefCalcutor, APIReusuableLibrary.SERVICEMETHOD.GET, null, Integer.parseInt("200"));
       /* List<HeroRecord> returnedArtworks = Arrays.asList(response.extract().as(HeroRecord[].class));
        String taxRelief1 = theOppenheimerPage.verifyTaxReliefCalculation(salary, tax, gender, birthday);*/
        response = apiDriver.sendNReceive(strTaxReliefCalcutor, APIReusuableLibrary.SERVICEMETHOD.GET, null, Integer.parseInt("200"));
//        JsonPath jsonPathEvaluator = response.extract().jsonPath();
        TheOppenheimerPage theOppenheimerPage = new TheOppenheimerPage(driver);
        theOppenheimerPage.compareApiAndCalculatedvalue(strSalary,strTax,strGender,strBirthday,strName,response);
     }

   /* @When("User verifies tax relief computation values with uploaded data {string}, {string}, {string}, {string}, {string}, {string} and {string}")
    public void userVerifiesTaxReliefComputationValuesWithUploadedDataAnd(String birthday, String gender, String name, String natid, String salary, String tax, String relief) throws Exception {

    }*/

    @And("User set request body with {string}, {string}, {string}, {string}, {string}, {string}")
    public void userSetRequestBodyWith(String strBirthday, String strGender, String strName, String strNatid, String strSalary, String strTax) {
        strSingleRecordPayload = apiDriver.registrationPayload(strBirthday,  strGender,  strName,  strNatid,  strSalary,  strTax);
        strSingleRecordPayload = apiDriver.heroSerializationDataMapper(strBirthday,  strGender,  strName,  strNatid,  strSalary,  strTax);
//
    }


    @And("User set serialization request body with {string}, {string}, {string}, {string}, {string}, {string}")
    public void userSetSerializationRequestBodyWith(String strBirthday, String strGender, String strName, String strNatid, String strSalary, String strTax) {
        strheroSerializationDataMapper = apiDriver.registrationPayload(strBirthday,  strGender,  strName,  strNatid,  strSalary,  strTax);
    }

    @Then("User verifies tax relief values for each {string}")
    public void userVerifiesTaxReliefValuesForEach(String arg0) {
        try {
            response = apiDriver.sendNReceive(strTaxReliefCalcutor, APIReusuableLibrary.SERVICEMETHOD.GET, null, Integer.parseInt("200"));
             List<HeroRecord> returnedArtworks = Arrays.asList(response.extract().as(HeroRecord[].class));
            System.out.println(returnedArtworks.size());
            System.out.println(returnedArtworks.get(0).getName().toString());
            System.out.println(returnedArtworks.get(1).getName().toString());
            System.out.println(returnedArtworks.get(2).getName().toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
