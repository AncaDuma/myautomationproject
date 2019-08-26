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

        //sign in with valid values

        WebElement emailfieldweb=driver.findElement(By.xpath("//input[@id='login-email']"));
        String emailfield1=BaseTest.getvalue("emailsigninvalid");
        functions.sendkeysmethod(emailfieldweb,emailfield1);

        WebElement passwordfieldweb=driver.findElement(By.xpath("//input[@id='login-password']"));
        String passwordfield1=BaseTest.getvalue("passwordsigninvalid");
        functions.sendkeysmethod(passwordfieldweb,passwordfield1);

        WebElement signinbuttonweb=driver.findElement(By.xpath("//button[@id='btnSubmit']"));
        functions.clickmethod(signinbuttonweb);

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

        functions.validatepagetitle(expectedsignin,driver);

        //sign in with empty fields

        WebElement signinbuttonweb1=driver.findElement(By.xpath("//button[@id='btnSubmit']"));
        functions.clickmethod(signinbuttonweb1);

        //validate error messages

        WebElement errormessageweb = driver.findElement(By.xpath("//div[@id='parsley-id-4']//span[@class='input-message parsley-required']"));
        String expectederrormessage5 = BaseTest.getvalue("errormessage2");
        String actualerrormessage5 = errormessageweb.getText();
        Assert.assertEquals(expectederrormessage5, actualerrormessage5);

        //sign in with email and password that were never registrated //pt proba

        WebElement emailfieldweb1=driver.findElement(By.xpath("//input[@id='login-email']"));
        String emailfield=BaseTest.getvalue("emailsigninnotregistrated");
        functions.sendkeysmethod(emailfieldweb1,emailfield);

        WebElement passwordfieldweb1=driver.findElement(By.xpath("//input[@id='login-password']"));
        String passwordfield=BaseTest.getvalue("passwordsigninnotregistrated");
        functions.sendkeysmethod(passwordfieldweb1,passwordfield);

        WebElement signinbuttonweb2=driver.findElement(By.xpath("//button[@id='btnSubmit']"));
        functions.clickmethod(signinbuttonweb2);

        //validez mesajul de eroare

        WebElement errormessagesite=driver.findElement(By.xpath("//span[@class='input-message parsley-loginError']"));
        String expectederrormessagesite=BaseTest.getvalue("errormessagefromsite");
        String actualerrormessagesite=errormessagesite.getText();
        Assert.assertEquals(expectederrormessagesite,actualerrormessagesite);

        emailfieldweb1.clear();
        passwordfieldweb1.clear();

        //sign in with email and passwords fields that contains special characters

        WebElement emailfieldweb2=driver.findElement(By.xpath("//input[@id='login-email']"));
        String emailfield2=BaseTest.getvalue("emailsigninspecialcharacters");
        functions.sendkeysmethod(emailfieldweb2,emailfield2);

        WebElement passwordfieldweb2=driver.findElement(By.xpath("//input[@id='login-password']"));
        String passwordfield2=BaseTest.getvalue("passwordsingspecialcharacters");
        functions.sendkeysmethod(passwordfieldweb2,passwordfield2);

        WebElement errormessage = driver.findElement(By.xpath("//span[@class='input-message parsley-passwd']"));
        String expectederrormessage = BaseTest.getvalue("errormessage3");
        String actualerrormessage = errormessage.getText();
        Assert.assertEquals(expectederrormessage, actualerrormessage);

        //validare mesaj eroare site

        Assert.assertEquals(expectederrormessagesite,actualerrormessagesite);

        emailfieldweb2.clear();
        passwordfieldweb2.clear();

        //sign in with invalid email and password

        WebElement emailfieldweb3=driver.findElement(By.xpath("//input[@id='login-email']"));
        String emailfield3=BaseTest.getvalue("invalidemail");
        functions.sendkeysmethod(emailfieldweb3,emailfield3);

        WebElement passwordfieldweb3=driver.findElement(By.xpath("//input[@id='login-password']"));
        String passwordfield3=BaseTest.getvalue("invalidpassword");
        functions.sendkeysmethod(passwordfieldweb3,passwordfield3);

        WebElement errormessage1 = driver.findElement(By.xpath("//span[@class='input-message parsley-type']"));
        String expectederrormessage1 = BaseTest.getvalue("errormessage4");
        String actualerrormessage1 = errormessage1.getText();
        Assert.assertEquals(expectederrormessage1, actualerrormessage1);
        WebElement errormessage2 = driver.findElement(By.xpath("//span[@class='input-message parsley-passwd']"));
        String expectederrormessage2 = BaseTest.getvalue("errormessage3");
        String actualerrormessage2 = errormessage2.getText();
        Assert.assertEquals(expectederrormessage2, actualerrormessage2);

        Assert.assertEquals(expectederrormessagesite,actualerrormessagesite);

        emailfieldweb3.clear();
        passwordfieldweb3.clear();

        //sign in with invalid email and valid password

        WebElement emailfieldweb4=driver.findElement(By.xpath("//input[@id='login-email']"));
        String emailfield4=BaseTest.getvalue("invalidemail");
        functions.sendkeysmethod(emailfieldweb4,emailfield4);

        WebElement passwordfieldweb4=driver.findElement(By.xpath("//input[@id='login-password']"));
        String passwordfield4=BaseTest.getvalue("passwordsigninvalid");
        functions.sendkeysmethod(passwordfieldweb4,passwordfield4);

        WebElement errormessage3 = driver.findElement(By.xpath("//span[@class='input-message parsley-type']"));
        String expectederrormessage3 = BaseTest.getvalue("errormessage4");
        String actualerrormessage3 = errormessage3.getText();
        Assert.assertEquals(expectederrormessage3, actualerrormessage3);

        Assert.assertEquals(expectederrormessagesite,actualerrormessagesite);

        emailfieldweb4.clear();
        passwordfieldweb4.clear();

        //sign in with valid email and invalid password

        WebElement emailfieldweb5=driver.findElement(By.xpath("//input[@id='login-email']"));
        String emailfield5=BaseTest.getvalue("emailsigninvalid");
        functions.sendkeysmethod(emailfieldweb5,emailfield5);

        WebElement passwordfieldweb5=driver.findElement(By.xpath("//input[@id='login-password']"));
        String passwordfield5=BaseTest.getvalue("invalidpassword");
        functions.sendkeysmethod(passwordfieldweb5,passwordfield5);

        WebElement errormessage4 = driver.findElement(By.xpath("//span[@class='input-message parsley-passwd']"));
        String expectederrormessage4 = BaseTest.getvalue("errormessage3");
        String actualerrormessage4 = errormessage4.getText();
        Assert.assertEquals(expectederrormessage4, actualerrormessage4);

        Assert.assertEquals(expectederrormessagesite,actualerrormessagesite);

        emailfieldweb5.clear();
        passwordfieldweb5.clear();

        //sign in with email and password that were never registrated

        WebElement emailfieldweb6=driver.findElement(By.xpath("//input[@id='login-email']"));
        String emailfield6=BaseTest.getvalue("emailsigninnotregistrated");
        functions.sendkeysmethod(emailfieldweb6,emailfield6);

        WebElement passwordfieldweb6=driver.findElement(By.xpath("//input[@id='login-password']"));
        String passwordfield6=BaseTest.getvalue("passwordsigninnotregistrated");
        functions.sendkeysmethod(passwordfieldweb6,passwordfield6);

        Assert.assertEquals(expectederrormessagesite,actualerrormessagesite);



    }
}
