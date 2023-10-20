package swagLabs.cucumber.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class invalidAccess {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";
    @Given("User is on login page")
    public void userIsOnLoginPage() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().window().maximize();
        driver.get(baseUrl);

        Boolean loginFormDisplayed = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div"))
                .isDisplayed();
        Assert.assertEquals(loginFormDisplayed,true);
    }

    @Then("User enter a link to the cart page via the address bar")
    public void userEnterALinkToTheCartPageViaTheAddressBar() {
        driver.get(baseUrl+"cart.html");
    }

    @Then("Error messages must be logged in first")
    public void errorMessagesMustBeLoggedInFirst() {
        Boolean notification = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"))
                .isDisplayed();
        Assert.assertEquals(notification,true);
        driver.quit();
    }
}
