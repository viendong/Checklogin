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
    String URL_homepage = "http://127.0.0.1:5500/action_page.html";
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
//        driver.navigate().to(URL_login);
        driver.get(URL_login);
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void loginByAdmin() {
//        System.out.println("print");
        login("username_01", "pass123");
        Assert.assertEquals(driver.getCurrentUrl(), URL_homepage);
    }

    @Test
    public void loginWithBlankField() {
        login("", "");
        Assert.assertEquals(driver.getCurrentUrl(), URL_login);
    }

    @Test
    public void loginWithSpaceChar() {
        login(" ", " ");
        Assert.assertEquals(driver.getCurrentUrl(), URL_login);
    }
    //fail
    @Test
    public void loginByUsername() {
//        System.out.println("print");
        login("username_01", "");
        Assert.assertEquals(driver.getCurrentUrl(), URL_login);
    }

    @Test
    public void loginByUsernameSpecial() {
//        System.out.println("print");
        login("username_01#", "");
        Assert.assertEquals(driver.getCurrentUrl(), URL_login);
    }
    @Test
    public void loginByUsernameSpace() {
//        System.out.println("print");
        login(" username_01", "");
        Assert.assertEquals(driver.getCurrentUrl(), URL_login);
    }
    @Test
    public void loginByUsernameH() {
//        System.out.println("print");
        login("Username_01", "");
        Assert.assertEquals(driver.getCurrentUrl(), URL_login);
    }
    @Test
    public void loginByPass() {
//        System.out.println("print");
        login("", "pass123");
        Assert.assertEquals(driver.getCurrentUrl(), URL_login);
    }

    @Test
    public void loginByPassSpecial() {
//        System.out.println("print");
        login("", "Pass123#");
        Assert.assertEquals(driver.getCurrentUrl(), URL_login);
    }
    @Test
    public void loginByPassSpace() {
        login("", " Pass123");
        Assert.assertEquals(driver.getCurrentUrl(), URL_login);
    }
    @Test
    public void loginByPassH() {
        login("", "Pass123");
        Assert.assertEquals(driver.getCurrentUrl(), URL_login);
    }

}
