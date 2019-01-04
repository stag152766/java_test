package ru.testjava.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

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
      String existingText = driver.findElement(locator).getAttribute("value"); //извлекаем из поля то значение, которое храниться
      if (! text.equals(existingText)) { //сравниваем по смыслу существующий текст с новым текстом
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);}
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
}

