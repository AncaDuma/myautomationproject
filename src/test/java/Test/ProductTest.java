package Test;

import help.BaseTest;
import help.Helpermethods;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductTest extends BaseTest {

    public Helpermethods functions=new Helpermethods(driver);

    @Test

    public void product () {

        //validez pagina home page

        String expectedhomepage = BaseTest.getvalue("homepagetitle");
        functions.validatepagetitle(expectedhomepage, driver);

        //inchid pup-up-urile

        driver.navigate().refresh();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();

        //hover pe watches si selectez al 3-lea element din lista

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

        //validez ca sunt pe pagina de produs din categoria selectata

        String expectedrose=BaseTest.getvalue("rosegoldtitle");
        functions.validatepagetitle(expectedrose,driver);

        //click pe primul produs de pe prima linie

        WebElement firstproduct=driver.findElement(By.xpath("//article[@data-productlist-index='0']"));
        functions.clickmethod(firstproduct);

        //validez ca sunt pe pagina de produs selectat

        String expectedproduct=BaseTest.getvalue("productpagetitle");
        functions.validatepagetitle(expectedproduct,driver);

        WebElement engravemebutton=driver.findElement(By.xpath("//button[@class=\"btn btn-default pdp-margin-bottom--small btn-product-customization\"]"));
        functions.clickmethod(engravemebutton);

        //validez textul popup "make it yours"

        WebElement popupmakeityours=driver.findElement(By.xpath("//h4[contains(text(), \"Make it Yours\")]"));
        String expectedmessage1=BaseTest.getvalue("popuptitle");
        String actualmessage1=popupmakeityours.getText();
        new WebDriverWait(driver,6500).until(ExpectedConditions.visibilityOf(popupmakeityours));
        Assert.assertEquals(expectedmessage1, actualmessage1);

        //click engraving cu fieldurile goale

        WebElement addengravingbutton=driver.findElement(By.xpath("//button[@class='btn btn-primary btn-block btn-customization--add']"));
        functions.clickmethod(addengravingbutton);

        //validez mesajul de eroare

        String mesajeroare=BaseTest.getvalue("mesajeroarefieldgravare");
        WebElement message=driver.findElement(By.xpath("//span[contains(text(), 'This field is required.')]"));
        functions.validatetext(message,mesajeroare);

        //field completat cu caractere speciale

        WebElement line11web=driver.findElement(By.xpath("//input[@id='id-engraving-line-1']"));
        String field=BaseTest.getvalue("line1.1");
        functions.sendkeysmethod(line11web,field);

        functions.clickmethod(addengravingbutton);
        line11web.clear();

        //validez mesajul de eroare

        String mesajeroare2=BaseTest.getvalue("mesajeroarecaracterespecialegravare");
        WebElement message1=driver.findElement(By.xpath("//span[@class='input-message parsley-asciisubset']"));
        functions.validatetext(message1,mesajeroare2);

        line11web.clear();

        //completez field-urile cu valori valide

        WebElement line1web=driver.findElement(By.xpath("//input[@id='id-engraving-line-1']"));
        String field1=BaseTest.getvalue("line1");
        functions.sendkeysmethod(line1web,field1);

        WebElement line2web=driver.findElement(By.xpath("//input[@id='id-engraving-line-2']"));
        String field2=BaseTest.getvalue("line2");
        functions.sendkeysmethod(line2web,field2);

        WebElement line3web=driver.findElement(By.xpath("//input[@id='id-engraving-line-3']"));
        String field3=BaseTest.getvalue("line3");
        functions.sendkeysmethod(line3web,field3);

        WebElement choosefont=driver.findElement(By.xpath("//select[@id='id-engraving-font-type']"));
        String font=BaseTest.getvalue("font");
        functions.selectbyvaluedropdown(choosefont,font);

        //preview cu gravarea

        WebElement previewbutton=driver.findElement(By.xpath("//button[@class='btn btn-default btn-block btn-customization--preview']"));
        functions.clickmethod(previewbutton);

        //validez imaginea gravata

        WebElement imagewebelement = driver.findElement(By.xpath("//div[@class='item item-customization-preview active']/img[@class='img-responsive']"));
        functions.displayedmethod(imagewebelement);

        //adaug gravarea pe produs

        functions.clickmethod(addengravingbutton);

        //validez ca sunt inca pe pagina de produs

        functions.validatepagetitle(expectedproduct,driver);

        //validez mesajul de gravare cum ca a fost adaugat

        String mesajgravare=BaseTest.getvalue("mesajgravareadugata");
        WebElement mesajgravareweb=driver.findElement(By.xpath("//span[@class='btn-product-customization--success-message']"));
        new WebDriverWait(driver,6500).until(ExpectedConditions.visibilityOf(mesajgravareweb));
        String actualEngraveMessage=mesajgravareweb.getText();
        String updatedEngrave=actualEngraveMessage.toUpperCase();
        Assert.assertEquals(updatedEngrave,mesajgravare);

        WebElement removebutton=driver.findElement(By.xpath("//a[@class=\"btn-product-customization--remove\"]"));
        functions.clickmethod(removebutton);

        String removemessage=BaseTest.getvalue("mesajremove");
        WebElement removemessweb=driver.findElement(By.xpath("//h4[@id='modal-customization-delete-confirm__title']"));
        new WebDriverWait(driver,6500).until(ExpectedConditions.visibilityOf(removemessweb));
        functions.validatetext(removemessweb,removemessage);

        WebElement nobutton=driver.findElement(By.xpath("//div[@class='btn btn-group btn-group-justified']//button[@data-dismiss='modal']"));
        new WebDriverWait(driver,6500).until(ExpectedConditions.visibilityOf(nobutton));
        functions.clickmethod(nobutton);

        //adaug in cos un produs

        WebElement addtobagbutton=driver.findElement(By.xpath("//article/div/div[8]/div[3]/form/button/span[1]"));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        functions.clickmethod(addtobagbutton);

        //stochez textul produsului pentru validare

        WebElement productTitleWeb=driver.findElement(By.xpath("//h1[@class='text-display-4 product-title hidden-xs']"));
        String actualProductTitle=productTitleWeb.getText();
        String newActualProductTitle=actualProductTitle+" HAS BEEN ADDED TO YOUR BAG";


        //validez ca a fost adaugat produsul corect in cos

        WebElement addedproduct=driver.findElement(By.xpath("//div[@class='col-xs-12 col-sm-6']/p"));
        new WebDriverWait(driver,6500).until(ExpectedConditions.visibilityOf(addedproduct));
        String actualProductMessageSuccessMessage=addedproduct.getText();
        String newActualSuccessMessage=actualProductMessageSuccessMessage.toUpperCase();
        Assert.assertEquals(newActualProductTitle,newActualSuccessMessage);
        System.out.println();

        //inchid mesajul de confirmare

        WebElement closebutton=driver.findElement(By.xpath("//div[@class='modal-dialog 15']/div/div[@class='modal-header clearfix title-container']/button[@class='close']"));
        functions.clickmethod(closebutton);

    }
}
