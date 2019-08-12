package Test;

import help.BaseTest;
import help.Helpermethods;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInTest extends BaseTest {

    public Helpermethods functions=new Helpermethods(driver);
    public String emailvalue;
    public String passwordvalue;

    @Test

    public void Signin () {
        String expectedhomepage = BaseTest.getvalue("homepagetitle");
        functions.validatepagetitle(expectedhomepage, driver);

        //solutie in caz de orice
        driver.navigate().refresh();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();

        WebElement myaccountButton=driver.findElement(By.xpath("//div[@class='dropdown dropdown-mega dropdown-account text-center pull-left utilLink']//span[contains(text(),'My Account')]"));
        functions.hovermethod(myaccountButton,driver);
        Actions action=new Actions(driver);
        action.moveToElement(myaccountButton).build().perform();

        WebElement accountbutton=driver.findElement(By.xpath("//a[@data-signin-path='/content/fossil/us/en/sign-in.html']"));
        new WebDriverWait(driver,10000).until(ExpectedConditions.visibilityOf(accountbutton));
        functions.clickmethod(accountbutton);

        String expectedsignin=BaseTest.getvalue("signintitle");
        functions.validatepagetitle(expectedsignin,driver);

        for (int index=0; index<7; index++) {
            WebElement emailfield = driver.findElement(By.xpath("//input[@id='login-email']"));
            WebElement passwordfield = driver.findElement(By.xpath("//input[@id='login-password']"));
            WebElement signinbutton = driver.findElement(By.xpath("//button[@id='btnSubmit']"));

            //parse values for email and password

            emailvalue = "" + BaseTest.getvalue("signinemailvalues");
            passwordvalue = "" + BaseTest.getvalue("signinpasswordvalues");
            String[] parseEmail = emailvalue.split(",");
            String[] parsePassword = passwordvalue.split(",");

            //7. valid email and password

            if (index == 0) {
                emailvalue = parseEmail[5];
                passwordvalue = parsePassword[5];
                emailfield.sendKeys(emailvalue);
                passwordfield.sendKeys(passwordvalue);
                signinbutton.click();

            }

            //validate profile page

            String expectedprofile=BaseTest.getvalue("profilepagetitle");
            new WebDriverWait(driver,6500).until(ExpectedConditions.titleIs(BaseTest.getvalue("profilepagetitle")));
            functions.validatepagetitle(expectedprofile,driver);



            //sign out

            WebElement myaccountButton1=driver.findElement(By.xpath("//div[@class='dropdown dropdown-mega dropdown-account text-center pull-left utilLink']//span[contains(text(),'My Account')]"));
            functions.hovermethod(myaccountButton1,driver);
            Actions action1=new Actions(driver);
            action.moveToElement(myaccountButton1).build().perform();

            WebElement signoutbutton=driver.findElement(By.xpath("//a[@class='btn-action-signout']"));
            functions.clickmethod(signoutbutton);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //sign in again

            WebElement myaccountButton2=driver.findElement(By.xpath("//div[@class='dropdown dropdown-mega dropdown-account text-center pull-left utilLink']//span[contains(text(),'My Account')]"));
            functions.hovermethod(myaccountButton2,driver);
            Actions action2=new Actions(driver);
            action.moveToElement(myaccountButton2).build().perform();

            WebElement accountbutton1=driver.findElement(By.xpath("//a[@data-signin-path='/content/fossil/us/en/sign-in.html']"));
            new WebDriverWait(driver,10000).until(ExpectedConditions.visibilityOf(accountbutton1));
            functions.clickmethod(accountbutton1);


            //invalid cases
            //1.email and password that were never registrated

            if (index == 1) {
                emailvalue = parseEmail[1];
                passwordvalue = parsePassword[1];
                emailfield.sendKeys(emailvalue);
                passwordfield.sendKeys(passwordvalue);
                signinbutton.click();
                WebElement errormessage = driver.findElement(By.xpath("//span[@class='input-message parsley-loginError']"));
                String expectederrormessage = BaseTest.getvalue("errormessage1");
                String actualerrormessage = errormessage.getText();
                Assert.assertEquals(expectederrormessage, actualerrormessage);
                driver.navigate().refresh();

            }

            //2. all fields are empty

            if (index == 2) {
                emailvalue = parseEmail[2];
                passwordvalue = parsePassword[2];
                emailfield.sendKeys(emailvalue);
                passwordfield.sendKeys(passwordvalue);
                signinbutton.click();
                WebElement errormessage = driver.findElement(By.xpath("//div[@id='parsley-id-4']//span[@class='input-message parsley-required']"));
                String expectederrormessage = BaseTest.getvalue("errormessage2");
                String actualerrormessage = errormessage.getText();
                Assert.assertEquals(expectederrormessage, actualerrormessage);
                driver.navigate().refresh();

            }

            //3. special characters for all fields

            if (index == 3) {
                emailvalue = parseEmail[3];
                passwordvalue = parsePassword[3];
                emailfield.sendKeys(emailvalue);
                passwordfield.sendKeys(passwordvalue);
                signinbutton.click();
                WebElement errormessage = driver.findElement(By.xpath("//span[@class='input-message parsley-passwd']"));
                String expectederrormessage = BaseTest.getvalue("errormessage3");
                String actualerrormessage = errormessage.getText();
                Assert.assertEquals(expectederrormessage, actualerrormessage);
                driver.navigate().refresh();

            }

            //4. invalid email and password

            if (index == 4) {
                emailvalue = parseEmail[4];
                passwordvalue = parsePassword[4];
                emailfield.sendKeys(emailvalue);
                passwordfield.sendKeys(passwordvalue);
                signinbutton.click();
                WebElement errormessage = driver.findElement(By.xpath("//span[@class='input-message parsley-type']"));
                String expectederrormessage = BaseTest.getvalue("errormessage4");
                String actualerrormessage = errormessage.getText();
                Assert.assertEquals(expectederrormessage, actualerrormessage);
                WebElement errormessage1 = driver.findElement(By.xpath("//span[@class='input-message parsley-passwd']"));
                String expectederrormessage1 = BaseTest.getvalue("errormessage3");
                String actualerrormessage1 = errormessage1.getText();
                Assert.assertEquals(expectederrormessage1, actualerrormessage1);
                driver.navigate().refresh();

            }

            //5. invalid email and valid password

            if (index == 5) {
                emailvalue = parseEmail[4];
                passwordvalue = parsePassword[0];
                emailfield.sendKeys(emailvalue);
                passwordfield.sendKeys(passwordvalue);
                signinbutton.click();
                WebElement errormessage = driver.findElement(By.xpath("//span[@class='input-message parsley-type']"));
                String expectederrormessage = BaseTest.getvalue("errormessage4");
                String actualerrormessage = errormessage.getText();
                Assert.assertEquals(expectederrormessage, actualerrormessage);
                driver.navigate().refresh();

            }

            //6. valid email and invalid password

            if (index == 6) {
                emailvalue = parseEmail[0];
                passwordvalue = parsePassword[4];
                emailfield.sendKeys(emailvalue);
                passwordfield.sendKeys(passwordvalue);
                signinbutton.click();
                WebElement errormessage = driver.findElement(By.xpath("//span[@class='input-message parsley-passwd']"));
                String expectederrormessage = BaseTest.getvalue("errormessage3");
                String actualerrormessage = errormessage.getText();
                Assert.assertEquals(expectederrormessage, actualerrormessage);
                driver.navigate().refresh();

            }




        }















    }
}
