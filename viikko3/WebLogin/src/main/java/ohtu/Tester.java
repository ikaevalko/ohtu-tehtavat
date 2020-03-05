package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        
        testLoginWithCorrectCredentials(driver);
        sleep(3);
        testLoginWithIncorrectPassword(driver);
        sleep(3);
        testCreateNewUser(driver);
        sleep(3);
        testLogoutAfterCreate(driver);
        
        driver.quit();
    }
    
    private static void testLoginWithCorrectCredentials(WebDriver driver) {
        
        driver.get("http://localhost:4567");
        
        sleep(2);
        
        clickLinkWithText("login", driver);

        sleep(2);

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        clickLinkWithText("login", driver);
        
        sleep(2);
        element.submit();
    }
    
    private static void testLoginWithIncorrectPassword(WebDriver driver) {
        
        driver.get("http://localhost:4567");
        
        sleep(2);
        
        clickLinkWithText("login", driver);

        sleep(2);

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("incorrect");
        clickLinkWithText("login", driver);
        
        sleep(2);
        element.submit();
    }
    
    private static void testCreateNewUser(WebDriver driver) {
        
        driver.get("http://localhost:4567");
        
        sleep(2);
        
        clickLinkWithText("register new user", driver);

        sleep(2);
        
        Random r = new Random();
    
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("testaaja"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("salainen1");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salainen1");
        clickLinkWithText("signup", driver);
        
        sleep(2);
        element.submit();
    }
    
    private static void testLogoutAfterCreate(WebDriver driver) {
        
        driver.get("http://localhost:4567");
        
        sleep(2);
        
        clickLinkWithText("register new user", driver);

        sleep(2);
        
        Random r = new Random();
    
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("testaaja"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("salainen1");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salainen1");
        clickLinkWithText("signup", driver);
        
        sleep(2);
        element.submit();
        sleep(2);
        clickLinkWithText("continue to application mainpage", driver);
        sleep(2);
        clickLinkWithText("logout", driver);
    }
    
    private static void clickLinkWithText(String text, WebDriver driver) {
        
        int trials = 0;
        
        while (trials++<5) {
            try {
                WebElement element = driver.findElement(By.linkText(text));
                element.click();
                break;           
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
