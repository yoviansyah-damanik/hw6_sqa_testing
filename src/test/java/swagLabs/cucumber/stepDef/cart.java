package swagLabs.cucumber.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class cart {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("User has login")
    public void userHasLogin() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().window().maximize();
        driver.get(baseUrl);

        driver.findElement(By.id("user-name"))
                .sendKeys("standard_user");
        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@type='submit']"))
                .click();

        String dashboardHeader = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div"))
                .getText();
        Assert.assertEquals(dashboardHeader,"Swag Labs");
    }

    @Then("User clicks Hamburger Menu Button")
    public void userClicksHamburgerMenuButton() {
        driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[1]/div/div[1]/div/button"))
                .click();
    }

    @Then("User clicks All Items Menu")
    public void userClicksAllItemsMenu() {
        driver.findElement(By.xpath("//*[@id=\"inventory_sidebar_link\"]"))
                .click();
    }

    @Then("The product has been successfully added to the basket by increasing the number of numbers on the basket icon")
    public void theProductHasBeenSuccessfullyAddedToTheBasketByIncreasingTheNumberOfNumbersOnTheBasketIcon() {
        Boolean cart_badge_displayed = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"))
                .isDisplayed();

        Assert.assertEquals(cart_badge_displayed,true);
        driver.quit();
    }

    @When("User clicks Add to Cart Button")
    public void userClicksAddToCartButton() {
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]"))
                .click();
    }
}
