import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.List;

public class PassParameters {

    public WebDriver driver;
    SoftAssert softAssertz= new SoftAssert();
    public String car_item;

    @BeforeTest()
    public void login(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.get("https://jo.opensooq.com/en");
        driver.manage().window().maximize();
    }

    @Test()
    public void test(){
        driver.findElement(By.xpath("//*[@id=\"headerThreeDesktop\"]/ul/li[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"pageContent\"]/main/section[2]/div[3]/div[1]/a[11]")).click();

        List<WebElement> cars=driver.findElements(By.className("breakWord"));

        String car=cars.get(0).getText();

        this.car_item=car.replace("BMW X6M 2020 in Amman","BMW");
    }

    @Test()
    public void test2(){
        driver.findElement(By.xpath("//*[@id=\"search\"]")).sendKeys(this.car_item+Keys.ENTER);
        List<WebElement> cars2= driver.findElements(By.className("breakWord"));

        for (int i=0; i<cars2.size();i++){
            String result=cars2.get(i).getText();

            softAssertz.assertEquals(result.contains("BMW"),true);
            softAssertz.assertAll();
        }
    }
}



