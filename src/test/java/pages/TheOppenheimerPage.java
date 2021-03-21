package pages;


import helper.CommuteTaxRelief;
import helper.HeroRecord;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.openqa.selenium.*;

import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * PageFactory For ShopPage
 */
public class TheOppenheimerPage extends MasterPageWeb {
    //protected static final Logger LOG = LoggerFactory.getLogger(TheOppenheimerPage.class);
    /**
     * All WebElements are identified by @FindBy annotation
     */

    @FindBy(xpath = "//input[@type='file']")
    WebElement btnFile;

    @FindBy(xpath = "//a[@href='dispense']")
    WebElement imgDispenseNow;

    @FindBy(id="app")
    WebElement txtCashdispensed;



        public TheOppenheimerPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //(tech chell)
    public void clickFile() {
        try {
            // btnFile.click();
            //   WebElement element = btnFile; //driver.findElement(By.id("gbqfd"));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", btnFile);
        } catch (ElementClickInterceptedException e) {
            //LOG.error(e.getMessage());
        }
    }

   public String verifyImgColor(){
       String color = imgDispenseNow.getCssValue("background-color");
       return Color.fromString(color).asHex();
   }

    public String verifyImgText(){
        return imgDispenseNow.getText();
    }

    public String verifyCashDispensedText(){
        imgDispenseNow.click();
    String aa =txtCashdispensed.getText();
        return aa;
    }


    //Set File path (tech chell)
    public void setFilePath(String strFirstName) {
        btnFile.sendKeys(strFirstName);
    }

 public String verifyTaxReliefCalculation(String salary, String taxpaid, String gender, String dob) throws ParseException {
   //public void verifyTaxReliefCalculation(List<String> age) {
     double commuteTaxRelief = 0;
     double dsalary = Double.parseDouble(salary);
     double dtaxpaid = Double.parseDouble(taxpaid);
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy", Locale.ENGLISH);
     LocalDate date = LocalDate.parse(dob, formatter);
     int genderBonus = getGenderBonus(gender);
     double variable = getVariableBasedonAge(calculateAge(date.toString()));
     commuteTaxRelief = ((dsalary-dtaxpaid)*variable)+genderBonus;
     //double roundOff = (double) Math.round(commuteTaxRelief * 100) / 100;
      double roundOff = getFinalTaxRelief(getTaxReliefWithRoundlogic(commuteTaxRelief));
    // System.out.println("commuteTaxRelief" + roundOff);
    // System.out.println("commuteTaxRelief : " + String.format("%.2f", roundOff));
     return String.format("%.2f", roundOff);
   }

    // public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
    public int calculateAge(String birthDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate bDate = LocalDate.parse(birthDate);
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(bDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public double getVariableBasedonAge(int mAge) {
        double aa=0.00;
        // System.out.print("Enter age: ");
        if (mAge <= 18) {
            aa= 1;
        } else if ((mAge >= 19) && (mAge <= 35)){
            aa= 0.8;
        } else if ((mAge >= 36) && (mAge <= 50)){
            aa= 0.5;
        } else if ((mAge >= 51) && (mAge <= 75)){
            aa= 0.367;
        }else if (mAge >= 76) {
            aa= 0.05;
        }
        return aa;
    }

    public int getGenderBonus(String gender){
        int genderBonus = -1;
        if(gender.contains("m")){
            genderBonus =0;
        }else if (gender.contains("f")){
            genderBonus =500;
        }
         return genderBonus;
    }

    public double getTaxReliefWithRoundlogic(double val){
       // double roundOff = Math.floor(val * 100) / 100; //(double) Math.round(val * 100) / 100;
        double roundOff = (double) Math.round(val);
    return roundOff;
    }

    public double getFinalTaxRelief(double val){
        if((val >=1) && (val <=50.00)){
            val = 50.00;
        }else if (val>=50.01){
            val = val;
        }else if (val==0.00) {
            val = val;
        }
        return val;
    }

    public void compareApiAndCalculatedvalue(String strSalary, String strTax, String strGender, String strBirthday, String strName,  ValidatableResponse response){
        List<CommuteTaxRelief> responseList = Arrays.asList(response.extract().as(CommuteTaxRelief[].class));
        String taxRelief1 =null;
                String aa=   responseList.stream().filter(calc -> calc.getName().equalsIgnoreCase(strName)).findFirst().get().getRelief();
        System.out.println(aa);
        try {
             taxRelief1 =  verifyTaxReliefCalculation(strSalary, strTax, strGender, strBirthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(aa,taxRelief1);
    }
    }
