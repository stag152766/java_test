package ru.testjava.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.testjava.addressbook.modul.ContactData;
import ru.testjava.addressbook.modul.GroupData;

import java.util.ArrayList;
import java.util.List;

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

  public void selectContact(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
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

  public void initContactModification(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
    click(By.xpath("//img[@title='Edit']"));
  }

  public void submitContactModification() {
    click(By.xpath("/html[1]/body[1]/div[1]/div[4]/form[1]/input[1]"));
  }

  public int getContactCount() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = driver.findElements(By.cssSelector("td:nth-child(3)"));
    for (WebElement element : elements) {
      String firstname = element.getText();
      ContactData contact = new ContactData(firstname, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
