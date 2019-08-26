package Test;

import help.BaseTest;
import help.Helpermethods;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RegisterTest extends BaseTest {

    public Helpermethods functions=new Helpermethods(driver);

    @Test
    public void registration ()  {
        String expectedhomepage= BaseTest.getvalue("homepagetitle");
        functions.validatepagetitle(expectedhomepage,driver);

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

        WebElement smallregister=driver.findElement(By.xpath("//small[contains(text(), 'Register')]"));
        functions.clickmethod(smallregister);

        String expectedregister=BaseTest.getvalue("registerpagetitle");
        new WebDriverWait(driver,6500).until(ExpectedConditions.titleIs(BaseTest.getvalue("registerpagetitle")));
        functions.validatepagetitle(expectedregister,driver);

        WebElement firstnameweb=driver.findElement(By.xpath("//input[@id='signup-firstname']"));
        String firstname=BaseTest.getvalue("firstnamevalues");
        functions.sendkeysmethod(firstnameweb,firstname);

        WebElement lastnameweb=driver.findElement(By.xpath("//input[@id='signup-lastname']"));
        String lastname=BaseTest.getvalue("lastnamevalues");
        functions.sendkeysmethod(lastnameweb,lastname);

        WebElement emailweb = driver.findElement(By.xpath("//input[@id='signup-email']"));
        String emailvalue = System.currentTimeMillis()+"@gmail.com";
        functions.sendkeysmethod(emailweb,emailvalue);

        WebElement checkbox=driver.findElement(By.xpath("//input[@name='emailSubscribe']"));
        functions.clickmethod(checkbox);

        WebElement passwordweb=driver.findElement(By.xpath("//input[@id='signup-password']"));
        String passwordvalue=BaseTest.getvalue("passwordvalue");
        functions.sendkeysmethod(passwordweb,passwordvalue);

        WebElement showpassword=driver.findElement(By.xpath("//input[@id='show-password']"));
        functions.clickmethod(showpassword);

//        WebElement registerbutton=driver.findElement(By.xpath("//button[@class='g-recaptcha btn btn-primary']"));
//        functions.clickmethod(registerbutton);
//
//        String expectedprofile=BaseTest.getvalue("profilepagetitle");
//        functions.validatepagetitle(expectedprofile,driver);
//
//        //validez titlu de pe pag de profil
//
//        WebElement message=driver.findElement(By.xpath("//span[@class='welcome']2"));
//        String expectedmessage="Welcome" + " " + BaseTest.getvalue("firstnamevalues");
//        String actualmessage=message.getText();
//        Assert.assertEquals(expectedmessage, actualmessage);



















































    }
}
