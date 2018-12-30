package ru.testjava.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.fail;

public class ApplicationManager extends ContactHelper {

  WebDriver driver;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  public void init() {
    System.setProperty("webdriver.chrome.driver", "c:\\Tools\\chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get("http://localhost/addressbook/edit.php");
    groupHelper = new GroupHelper(navigationHelper.driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    sessionHelper.login("user", "admin", "pass", "secret", By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]"));
  }



  public void stop() {
    navigationHelper.driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      navigationHelper.driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      navigationHelper.driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = navigationHelper.driver.switchTo().alert();
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

  public void submitContactCreation() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]")).click();
  }

  public void fillContactForm() {
    groupHelper.driver.findElement(By.name("firstname")).click();
    groupHelper.driver.findElement(By.name("firstname")).clear();
    groupHelper.driver.findElement(By.name("firstname")).sendKeys("Fname");
    groupHelper.driver.findElement(By.name("middlename")).click();
    groupHelper.driver.findElement(By.name("middlename")).clear();
    groupHelper.driver.findElement(By.name("middlename")).sendKeys("Mname");
    groupHelper.driver.findElement(By.name("lastname")).click();
    groupHelper.driver.findElement(By.name("lastname")).clear();
    groupHelper.driver.findElement(By.name("lastname")).sendKeys("Lname");
    groupHelper.driver.findElement(By.name("nickname")).click();
    groupHelper.driver.findElement(By.name("nickname")).clear();
    groupHelper.driver.findElement(By.name("nickname")).sendKeys("Mtnick");
    groupHelper.driver.findElement(By.name("address")).click();
    groupHelper.driver.findElement(By.name("address")).clear();
    groupHelper.driver.findElement(By.name("address")).sendKeys("1st test");
    groupHelper.driver.findElement(By.name("mobile")).click();
    groupHelper.driver.findElement(By.name("mobile")).clear();
    groupHelper.driver.findElement(By.name("mobile")).sendKeys("213215461");
  }

  public void gotoAddNew() {
    groupHelper.driver.findElement(By.linkText("add new")).click();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
