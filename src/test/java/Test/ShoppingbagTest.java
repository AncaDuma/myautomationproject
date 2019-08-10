package Test;

import help.BaseTest;
import help.Helpermethods;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ShoppingbagTest extends BaseTest {

    public Helpermethods functions=new Helpermethods(driver);

    @Test

    public void Shoppingbag () {

        //solutie in caz de orice
        driver.navigate().refresh();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();

        WebElement watchesButton=driver.findElement(By.xpath("//a[@href=\"/us/en/watches.html\"]"));
        functions.hovermethod(watchesButton,driver);


        List<WebElement> watchesweb=driver.findElements(By.xpath("//li[@class='dropdown dropdown-mega levelOne'][3]//a[contains(text(),\"Women's Watches By Type\")]/..//a[@class='link text-none-capitalize ']"));
        for (int contor=0; contor<watchesweb.size(); contor++) {

            if(contor==2)
            {
                functions.clickmethod(watchesweb.get(contor));
                break;
            }
        }

        WebElement firstproduct=driver.findElement(By.xpath("//article[@data-productlist-index='0']"));
        functions.clickmethod(firstproduct);

        //validate empty shopping bag on the product page
        String emptybag=BaseTest.getvalue("emptybag");
        WebElement emptybagweb=driver.findElement(By.xpath("//span[@class='minicart-count text-xs']"));
        new WebDriverWait(driver,6500).until(ExpectedConditions.visibilityOf(emptybagweb));
        functions.validatetext(emptybagweb,emptybag);



        WebElement shoppingbag=driver.findElement(By.xpath("//i[@class='fa fa-shopping-cart']"));
        functions.clickmethod(shoppingbag);

        //validate shopping bag page title

        String expectedshoppingbagpage=BaseTest.getvalue("shoppingbagpagetitle");
        functions.validatepagetitle(expectedshoppingbagpage,driver);

        //validate empty shopping bag and 0 price on the shopping bag page

//        String emptybag2=BaseTest.getvalue("0itembag");
//        WebElement emptybagweb2=driver.findElement(By.xpath("//span[@class='text-accent']"));
//        new WebDriverWait(driver,6500).until(ExpectedConditions.visibilityOf(emptybagweb2));
//        functions.validatetext(emptybagweb2,emptybag2);

        for (int index=0; index<5; index++) {

            WebElement emptybagweb2=driver.findElement(By.xpath("//span[@class='text-accent']"));
            WebElement price=driver.findElement(By.xpath("//span[@class='order-summary-item-value']"));
            if (index==0) {

                String valoare ="(0 ITEMS)";
                functions.validatetext(emptybagweb2,valoare);
                String pret="0.00";
                functions.validatetext(price,pret);
            }


        }

        driver.navigate().back();

        WebElement addtobagbutton=driver.findElement(By.xpath("//article/div/div[8]/div[3]/form/button/span[1]"));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        functions.clickmethod(addtobagbutton);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement closebutton=driver.findElement(By.xpath("//div[@class='modal-dialog 15']/div/div[@class='modal-header clearfix title-container']//button[@type='button']/span"));
        functions.clickmethod(closebutton);



        //validate that there is one product in the shopping bag

        String oneproductbag=BaseTest.getvalue("oneproduct");
        WebElement oneproductweb=driver.findElement(By.xpath("//span[@class='minicart-count text-xs']"));
        new WebDriverWait(driver,6500).until(ExpectedConditions.visibilityOf(oneproductweb));
        functions.validatetext(oneproductweb,oneproductbag);














    }
}
