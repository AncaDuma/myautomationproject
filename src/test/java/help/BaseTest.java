package help;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
//    public WebDriver driver;
    public static Properties property;
    public FileInputStream file;
    public WebDriver driver;

    @Before
    public void test () throws FileNotFoundException {


        System.setProperty("webdriver.chrome.driver", "C:\\automation\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.fossil.com/us/en.html?borderFreeCountry=n");
        driver.manage().window().maximize();



     //open chrome in an incognito mode

//        DesiredCapabilities capabilities=DesiredCapabilities.chrome();
//        ChromeOptions options=new ChromeOptions();
//        options.addArguments("--incognito");
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//
//        System.setProperty("webdriver.chrome.driver", "C:\\automation\\ChromeDriver\\chromedriver.exe");
//        WebDriver driver=new ChromeDriver(capabilities);
//        driver.manage().window().maximize();
//        driver.get("https://www.fossil.com/us/en.html?borderFreeCountry=n");

        //Wait implicit
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loadproperty();
    }
    public void loadproperty () throws FileNotFoundException {

        property=new Properties();
        file=new FileInputStream("C:\\Users\\anca duma\\IdeaProjects\\Automationproject\\src\\test\\resources\\Inputdata.properties");
        try {
            property.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //metoda care returneaza val dintr-un fisier properties pe baza unei chei

    public static String getvalue (String key){

        return property.getProperty(key);
    }
}
