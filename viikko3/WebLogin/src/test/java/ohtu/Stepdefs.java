package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.Random;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }
    
    @Given("new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }
    
    @When("incorrect username {string} and correct password {string} are given")
    public void incorrectUsernameAndCorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }
    
    @When("valid username and password {string} and matching password confirmation are entered")
    public void validUsernameAndPasswordAreGivenForSignup(String password) {
        createValidUser(password);
    }
    
    @When("too short username and password {string} and matching password confirmation are entered")
    public void tooShortUsernameAndValidPasswordAreGivenForSignup(String password) {
        createTooShortUser(password);
    }
    
    @When("valid username and too short password {string} and matching password confirmation are entered")
    public void validUsernameAndTooShortPasswordAreGivenForSignup(String password) {
        createValidUser(password);
    }
    
    @When("valid username and password {string} does not match password confirmation")
    public void validUsernameAndPasswordDoesNotMatchConfirmation(String password) {
        createUserWithoutMatchingPasswordConfirmation(password);
    }
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }
    
    @Then("user is not created and error {string} is given")
    public void userIsNotCreatedInAndErrorMessageIsGiven(String error) {
        pageHasContent("Create username and give password");
        pageHasContent(error);
    }
    
    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @Then("new user is created")
    public void newUserIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }
    
    private void createValidUser(String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(randomString(8));
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
    
    private void createTooShortUser(String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(randomString(2));
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
    
    private void createUserWithoutMatchingPasswordConfirmation(String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(randomString(2));
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password + "123");
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
    
    public static String randomString(int length) {
    
        final String SOURCE = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
          sb.append(SOURCE.charAt(new Random().nextInt(SOURCE.length())));
        }
        
        return sb.toString();
    }
}
