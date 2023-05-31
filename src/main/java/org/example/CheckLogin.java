package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CheckLogin {

    String URL_login = "http://127.0.0.1:5500/login.html";
    String URL_dashBoard = "http://127.0.0.1:5500/action_page.php";
    String submitBtn = "submit";

    WebDriver driver = new ChromeDriver();
    public void login(String userName, String passWord) {
//        driver.findElement(By.name("login")).click();
        driver.findElement(By.name("uname")).sendKeys(userName);
        driver.findElement(By.name("psw")).sendKeys(passWord);
        driver.findElement(By.name(submitBtn)).click();
    }

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/kvdong/Documents/java/chromedriver_mac_arm64/chromedriver");
        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
        driver.navigate().to(URL_login);
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void loginWithBlankField() {
        login("", "");
//        Assert.assertEquals(driver.getCurrentUrl(), URL_login);
    }
    @Test
    public void loginByAdmin() {
//        System.out.println("print");
        login("username_01", "pass123");
        Assert.assertEquals(driver.getCurrentUrl(), URL_dashBoard);
    }


    @AfterMethod
    public void testDown() {
        driver.quit();
    }
}
