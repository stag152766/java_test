package ru.testjava.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HelperBase {
  protected WebDriver driver;

  public HelperBase(WebDriver driver) {
    this.driver = driver;
  }

  protected void click(By locator) {
    driver.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null) {
      //оптимизация, которая проверяет совпадение уже существующее значения, с тем которое мы пытаемся ввести
      String existingText = driver.findElement(locator).getAttribute("value");
      if (! text.equals(existingText)) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);}
      }
    }

  //метод для заполнения файлового поля ввода
  protected void attach(By locator, File file) {
    if (file != null) {
        driver.findElement(locator).sendKeys(file.getAbsolutePath());
    }
  }

    public boolean isAlertPresent() {
      try {
        driver.switchTo().alert(); //если окно есть, то успешно переключимся
        return true;
      } catch (NoAlertPresentException e) { // если нет, то возникает исключения типа NoAlertPresentException
        return false;
      }
    }

  protected boolean isElementPresent(By locator) {
    try {
    driver.findElement(locator);
    return true;
    } catch (NoSuchElementException ex) {
    return false;
     }
  }
}

