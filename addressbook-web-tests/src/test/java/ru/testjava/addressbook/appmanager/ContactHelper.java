package ru.testjava.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.testjava.addressbook.modul.ContactData;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }


  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile());

    if (creation) { //если это форма создания, значит элемент должен быть, поэтому нужно выбирать элемент из выпадающего списка
      new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } //если вдруг этого элемента нет, то тест упадет - и так должно быть
    else { //а иначе , это форма модификации, в этом случае элемента быть не должно
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
    driver.switchTo().alert().accept();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void ContactCreation(ContactData contact, boolean creation) {
    click(By.linkText("add new"));
    fillContactForm(contact, creation);
    submitContactCreation();
    click(By.linkText("home"));
  }

  public void initContactModification() {
    click(By.name("selected[]"));
    click(By.xpath("//img[@title='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public int getContactCount() {
    return driver.findElements(By.name("selected[]")).size();
  }
}
