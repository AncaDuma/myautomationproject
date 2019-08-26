package Test;

import help.BaseTest;
import help.Helpermethods;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingbagTest extends BaseTest {

    public Helpermethods functions=new Helpermethods(driver);

    @Test

    public void Shoppingbag () {

        driver.navigate().refresh();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();

        //hover pe watches si selectez din lista de produse categoria rose gold

        WebElement watchesButton=driver.findElement(By.xpath("//li//a[@href='/us/en/watches.html']"));
        functions.hovermethod(watchesButton,driver);

        List<WebElement> watchesweb=driver.findElements(By.xpath("//li[@class='dropdown dropdown-mega levelOne'][3]//a[contains(text(),\"Women's Watches By Type\")]/..//a[@class='link text-none-capitalize ']"));
        for (int contor=0; contor<watchesweb.size(); contor++) {

            if(contor==2)
            {
                functions.clickmethod(watchesweb.get(contor));
                break;
            }
        }

        //selectez primul produs de pe prima linie

        WebElement firstproduct=driver.findElement(By.xpath("//article[@data-productlist-index='0']"));
        functions.clickmethod(firstproduct);

        //validez cosul gol de pe pagina de produs

        String emptybag=BaseTest.getvalue("emptybag");
        WebElement emptybagweb=driver.findElement(By.xpath("//span[@class='minicart-count text-xs']"));
        new WebDriverWait(driver,6500).until(ExpectedConditions.visibilityOf(emptybagweb));
        functions.validatetext(emptybagweb,emptybag);

        //merg pe pagina de shopping bag

        WebElement shoppingbag=driver.findElement(By.xpath("//i[@class='fa fa-shopping-cart']"));
        functions.clickmethod(shoppingbag);

        //validez titlul paginii shopping bag

        String expectedshoppingbagpage=BaseTest.getvalue("shoppingbagpagetitle");
        functions.validatepagetitle(expectedshoppingbagpage,driver);

        //validez cosul gol de pe pagina de shopping bag

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

        //inapoi pe pagina de produs unde adaug un produs in cos

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

        //validez ca este un produs in cos pe pagina de produs

        String oneproductbag=BaseTest.getvalue("oneproduct");
        WebElement oneproductweb=driver.findElement(By.xpath("//span[@class='minicart-count text-xs']"));
        new WebDriverWait(driver,6500).until(ExpectedConditions.visibilityOf(oneproductweb));
        functions.validatetext(oneproductweb,oneproductbag);

        //merg inapoi pe pagina de shopping bag

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement shoppingbag1=driver.findElement(By.xpath("//i[@class='fa fa-shopping-cart']"));
        new WebDriverWait(driver,6500).until(ExpectedConditions.visibilityOf(shoppingbag1));
        functions.clickmethod(shoppingbag1);

        //elimin textul din pretul produsului ($) si convertesc in Bigdecimal valoarea initiala a produsului

        WebElement watchpriceweb=driver.findElement(By.xpath("//div[@class='col-xs-12 col-sm-4']/p/span/span"));
        new WebDriverWait(driver,6500).until(ExpectedConditions.visibilityOf(watchpriceweb));
        String watchpricetext = watchpriceweb.getText();
        String priceAfterSplit=watchpricetext.substring(1);
        BigDecimal priceVal=new BigDecimal("0.0"); //initializez un obiect de tipul BigDecimal
        priceVal=new BigDecimal(priceAfterSplit);

        //am declarat un string care imi ia din input properties cantitatea de produs pe care o doresc si o convertesc in integer

        String expectedQuantity=BaseTest.getvalue("quantityValue");
        int expectedQuantitySize = Integer.parseInt(expectedQuantity);

        //am declarat o lista de webelemente pt quantity field si dau click pe quantity-ul din input property

        List<WebElement> quantityweb = driver.findElements(By.xpath("//select[@alt='Select Quantity']/option"));
        functions.clickmethod(quantityweb.get(expectedQuantitySize-1));
        WebElement updatebutton = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-update pull-right text-uppercase']"));
        functions.clickmethod(updatebutton);

        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //pretul final de pe site convertit in BigDecimal

        WebElement totalprice = driver.findElement(By.xpath("//span[@class='order-summary-item-value text-right price']"));
        new WebDriverWait(driver, 10000).until(ExpectedConditions.visibilityOf(totalprice));
        String actualprice = totalprice.getText();
        String splitActualFinalValue=actualprice.substring(1,actualprice.length());
        BigDecimal actualFinalpriceVal=new BigDecimal("0.0");
        actualFinalpriceVal=new BigDecimal(splitActualFinalValue);


        //construiesc pretul meu actual pe care urmeaza sa il compar cu cel de pe site

        BigDecimal finalPriceValue=new BigDecimal("0.0");
        for(int index=0;index<expectedQuantitySize;index++)
        {
            finalPriceValue=finalPriceValue.add(priceVal);
        }

        //comparam cele 2 valori obtinute

        Assert.assertEquals(actualFinalpriceVal,finalPriceValue);

        //merg inapoi pe pagina de produs si adaug un produs gravat in cos

        driver.navigate().back();

        WebElement engravemebutton=driver.findElement(By.xpath("//button[@class=\"btn btn-default pdp-margin-bottom--small btn-product-customization\"]"));
        functions.clickmethod(engravemebutton);

        WebElement line1web=driver.findElement(By.xpath("//input[@id='id-engraving-line-1']"));
        String field1=BaseTest.getvalue("line1");
        functions.sendkeysmethod(line1web,field1);

        WebElement line2web=driver.findElement(By.xpath("//input[@id='id-engraving-line-2']"));
        String field2=BaseTest.getvalue("line2");
        functions.sendkeysmethod(line2web,field2);

        WebElement line3web=driver.findElement(By.xpath("//input[@id='id-engraving-line-3']"));
        String field3=BaseTest.getvalue("line3");
        functions.sendkeysmethod(line3web,field3);

        WebElement addengravingbutton=driver.findElement(By.xpath("//button[@class='btn btn-primary btn-block btn-customization--add']"));
        functions.clickmethod(addengravingbutton);

        WebElement addtobagbutton1=driver.findElement(By.xpath("//article/div/div[8]/div[3]/form/button/span[1]"));
        new WebDriverWait(driver, 10000).until(ExpectedConditions.visibilityOf(addtobagbutton1));
        functions.clickmethod(addtobagbutton1);

        WebElement closebutton1=driver.findElement(By.xpath("//div[@class='modal-dialog 15']/div/div[@class='modal-header clearfix title-container']/button[@class='close']"));
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        functions.clickmethod(closebutton1);

        WebElement shoppingbag2=driver.findElement(By.xpath("//i[@class='fa fa-shopping-cart']"));
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        functions.clickmethod(shoppingbag2);

        //validez mesajul care apare cand este si un produs gravat in cos

        String mesajeligibil=BaseTest.getvalue("mesajeligibil");
        WebElement mesajeligibilweb=driver.findElement(By.xpath("//div[@class='bopis-eligible-message col-xs-12 text-danger']"));
        String actualmesajeligibil=mesajeligibilweb.getText();
        Assert.assertEquals(mesajeligibil,actualmesajeligibil);

        //sterg produsul fara gravare din cos

        WebElement removeproduct=driver.findElement(By.xpath("//div[@class='col-xs-10 visible-sm-block visible-md-block visible-lg-block action-items']/button[@class='btn-remove']"));
        functions.clickmethod(removeproduct);

        WebElement yesbutton=driver.findElement(By.xpath("//a[@class='btn btn-default yes']"));
        functions.clickmethod(yesbutton);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //checkout

        WebElement checkoutweb=driver.findElement(By.xpath("//a[@class='btn btn-checkout btn-block']"));
        functions.clickmethod(checkoutweb);

        //validez pagina de checkout

        String expectedcheckoutpage=BaseTest.getvalue("checkoutpagetitle");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        functions.validatepagetitle(expectedcheckoutpage,driver);

        }


    }

