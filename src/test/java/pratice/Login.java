package pratice;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class Login {
    @Test
    public void main(){
        WebDriver driver;
        String baseUrl = "https://www.saucedemo.com/";

        WebDriverManager.chromedriver().driverVersion("118").setup();

        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--remote-allow-origins=*");
        opt.addArguments("--start-maximized");

        String csvDir = System.getProperty("user.dir")+"/src/test/data/login-data.csv";

        try(CSVReader reader = new CSVReader(new FileReader(csvDir))){
            String[] line;
            while((line = reader.readNext()) != null){
                String username = line[0];
                String password = line[1];
                String status = line[2];

                driver = new ChromeDriver(opt);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
                driver.manage().window().maximize();
                driver.get(baseUrl);

                driver.findElement(By.id("user-name"))
                        .sendKeys(username);

                driver.findElement(By.id("password"))
                        .sendKeys(password);

                driver.findElement(By.xpath("//input[@type='submit']"))
                        .click();

                if(status.equals("success")){
                    String dashboardHeader = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div"))
                            .getText();
                    Assert.assertEquals(dashboardHeader,"Swag Labs");
                }else{
                    Boolean notification = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3/button"))
                            .isDisplayed();
                    Assert.assertEquals(notification,true);
                }

                driver.quit();
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
