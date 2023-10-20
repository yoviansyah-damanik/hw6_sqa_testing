package swagLabs.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";
    @Given("User is on the login page")
    public void userIsOnTheLoginPage(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().window().maximize();
        driver.get(baseUrl);

        Boolean loginFormDisplayed = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div"))
                .isDisplayed();
        Assert.assertEquals(loginFormDisplayed,true);
    }


    @When("User input username")
    public void userInputUsername(){
        driver.findElement(By.id("user-name"))
                .sendKeys("standard_user");
    }
    @And("User input password")
    public void userInputPassword(){
        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");
    }
    @And("Click on Login Button")
    public void clickOnLoginButton(){
        driver.findElement(By.xpath("//input[@type='submit']"))
                .click();
    }

    @Then("Homepage should be displayed")
    public void homepageShouldBeDisplayed(){
        String dashboardHeader = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div"))
                .getText();
        Assert.assertEquals(dashboardHeader,"Swag Labs");
        driver.quit();
    }

    @And("User input invalid password")
    public void userInputInvalidPassword() {
       driver.findElement(By.id("password"))
          .sendKeys("passwordasal");
    }

    @Then("Error message should be displayed")
    public void errorMessageShouldBeDisplayed() {
        Boolean notification = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"))
                .isDisplayed();
        Assert.assertEquals(notification,true);
        driver.quit();
    }

    @And("User input (.*) as password$")
    public void userInputTddAsPassword(String password) {
        driver.findElement(By.id("password"))
                .sendKeys(password);
    }

    @When("User input (.*) as username$")
    public void userInputTddAsUsername(String username) {
        driver.findElement(By.id("user-name"))
                .sendKeys(username);
    }

    @Then("User verify (.*) login result$")
    public void userVerifyFailedLoginResult(String status) {
        if(status.equals("success")){
            String dashboardHeader = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div"))
                    .getText();
            Assert.assertEquals(dashboardHeader,"Swag Labs");
        }else{
            Boolean notification = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"))
                    .isDisplayed();
            Assert.assertEquals(notification,true);
        }
        driver.quit();
    }
}
