package ru.testjava.addressbook;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class GroupCreationTests {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "c:\\Tools\\chromedriver_win32\\chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get("http://localhost/addressbook/edit.php");
    login("user", "admin", "pass", "secret", By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]"));
  }

  private void login(String user, String admin, String pass, String secret, By xpath) {
    driver.findElement(By.name(user)).click();
    driver.findElement(By.name(user)).clear();
    driver.findElement(By.name(user)).sendKeys(admin);
    driver.findElement(By.name(pass)).clear();
    driver.findElement(By.name(pass)).sendKeys(secret);
    driver.findElement(xpath).click();
  }

  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage("groups");
    initGroupCreation("new");
    fillGroupForm();
    submitGroupCreation("submit");
    returnToGroupPage("group page");
  }

  private void returnToGroupPage(String s) {
    gotoGroupPage(s);
  }

  private void submitGroupCreation(String submit) {
    driver.findElement(By.name(submit)).click();
  }

  private void fillGroupForm() {
    driver.findElement(By.name("group_name")).click();
    driver.findElement(By.name("group_name")).clear();
    driver.findElement(By.name("group_name")).sendKeys("test1");
    driver.findElement(By.name("group_header")).click();
    driver.findElement(By.name("group_header")).clear();
    driver.findElement(By.name("group_header")).sendKeys("test2");
    driver.findElement(By.name("group_footer")).clear();
    driver.findElement(By.name("group_footer")).sendKeys("test3");
  }

  private void initGroupCreation(String s) {
    driver.findElement(By.name(s)).click();
  }

  private void gotoGroupPage(String groups) {
    driver.findElement(By.linkText(groups)).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
