package pratice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class FailedLogin {
    @Test
    public void main(){
        WebDriver driver;
        String baseUrl = "https://www.saucedemo.com/";

        WebDriverManager.chromedriver().driverVersion("118").setup();

        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--remote-allow-origins=*");
        opt.addArguments("--start-maximized");

        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().window().maximize();
        driver.get(baseUrl);


        driver.findElement(By.id("user-name"))
                .sendKeys("standard_user");

        driver.findElement(By.id("password"))
                .sendKeys("passwordasal");

        driver.findElement(By.xpath("//input[@type='submit']"))
                .click();

        Boolean notification = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3/button"))
                .isDisplayed();

        Assert.assertEquals(notification,true);

//        driver.close();
    }
}
