package help;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helpermethods {

    WebDriver driver;


    //declaram un constructor


    public Helpermethods(WebDriver driver) {

        this.driver=driver;

    }

    //click method

    public void clickmethod(WebElement element) {

        element.click();
    }

    //select dupa valoare method

    public void selectbyvaluedropdown(WebElement element,String value) {

        Select elementselect=new Select(element);
        elementselect.selectByValue(value);

    }

    //metoda ca sa validam titlul unei pagini

    public void validatepagetitle(String expectedvalue, WebDriver driver) {

        if (expectedvalue.length()>0) {

            String actualvalue=driver.getTitle();
            Assert.assertEquals(expectedvalue,actualvalue);
        }
    }

    //metoda ca sa validam un text

    public void validatetext(WebElement element, String value) {
        String actualmessage=element.getText();
        Assert.assertTrue(value.equals(actualmessage));

    }

    //metoda pentru wait explicit

    public void waitmethode (WebElement element) {

        new WebDriverWait(driver,4500).until(ExpectedConditions.visibilityOf(element));
    }

    //metoda cu hover

    public void hovermethod(WebElement element, WebDriver driver) {

        Actions hover=new Actions(driver);
        hover.moveToElement(element).build().perform();

    }

    //metoda cu sendkeys

    public void sendkeysmethod(WebElement element, String value) {

        element.sendKeys(value);

    }

    //metoda pt validarea unei imagini

    public void displayedmethod (WebElement element) {

        if (element.isDisplayed()) {
            Assert.assertTrue(element.isDisplayed());
        }
    }
}
