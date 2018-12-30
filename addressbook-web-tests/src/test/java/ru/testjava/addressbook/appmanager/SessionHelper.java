package ru.testjava.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper {
  private WebDriver driver;

  public SessionHelper(WebDriver driver) {
    this.driver = driver;
  }

  public void login(String user, String username, String pass, String password, By xpath) {
    driver.findElement(By.name(user)).click();
    driver.findElement(By.name(user)).clear();
    driver.findElement(By.name(user)).sendKeys(username);
    driver.findElement(By.name(pass)).clear();
    driver.findElement(By.name(pass)).sendKeys(password);
    driver.findElement(xpath).click();
  }
}
