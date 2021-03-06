package ru.testjava.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.testjava.addressbook.model.ContactData;
import ru.testjava.addressbook.model.Contacts;
import ru.testjava.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWork());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    //attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0 ) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }


  public void selectContact(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    driver.switchTo().alert().accept();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("entry"));
  }

  public void create(ContactData contact, boolean creation) {
    click(By.linkText("add new"));
    fillContactForm(contact, creation);
    submitContactCreation();
    contactCache = null;
    click(By.linkText("home"));
  }

  public void modify(ContactData contact) {
    selectEditedContactById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  private void selectEditedContactById(int id) {
    driver.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();

  }

  public void returnToHomePage() {
    if (isElementPresent(By.tagName("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void initContactModification(int index) {
    driver.findElements(By.xpath("//img[@title='Edit']")).get(index).click();

  }

  public void submitContactModification() {
    click(By.xpath("/html[1]/body[1]/div[1]/div[4]/form[1]/input[1]"));
  }

  public Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = driver.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return new Contacts(contactCache);
  }

  public Set<ContactData> all2() {
    Set<ContactData> contacts = new HashSet<>();
    List<WebElement> rows = driver.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).
              withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
    }
    return contacts;
  }

  public void delete(ContactData deletedContact) {
    selectContactById(deletedContact.getId());
    deleteSelectedContact();
    contactCache = null;
    returnToHomePage();
  }

  private void selectContactById(int id) {
    driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public int count() {
    return driver.findElements(By.name("selected[]")).size();
  }

  //вспомогательный метод который загружает информацию из формы редактирования
  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
    String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
    String home = driver.findElement(By.name("home")).getAttribute("value");
    String work = driver.findElement(By.name("work")).getAttribute("value");
    String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
    String address = driver.findElement(By.name("address")).getAttribute("value");
    String email = driver.findElement(By.name("email")).getAttribute("value");
    String email2 = driver.findElement(By.name("email2")).getAttribute("value");
    String email3 = driver.findElement(By.name("email3")).getAttribute("value");

    return new ContactData().withId(contact.getId()).withFirstname(firstname)
            .withLastname(lastname).withHome(home).withWork(work).withMobile(mobile)
            .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
  }


  private void initContactModificationById(int id) {
    //driver.findElement(By.xpath(String.format("a[href='edit.php?id=%s']", id))).click();
    //driver.findElement(By.xpath(String.format("input[]value=%s/../../..td[8]/a", id))).click();
    //driver.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
    driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  //способ последовательных приближений
  private void initContactModificationById2(int id) {
    WebElement checkbox = driver.findElement(By.cssSelector(String.format("input[value=%s]", id)));
    WebElement row = checkbox.findElement(By.xpath("./../../"));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", ""); //заменить все вхождения чего-то на что-то
  }

  public ContactData infoFromDetailsForm(ContactData contact) {
    initContactDetailsById(contact.getId());
    String details = driver.findElement(By.xpath("//body//div//div[4]")).getText();

    return new ContactData().withAll(details);
  }

  private void initContactDetailsById(int id) {
    driver.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
  }

  public void addTo(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    selectToGroup(group.getId());

  }

  private void selectToGroup(int id) {
    driver.findElement(By.xpath("//select[@name='to_group']//option[@value='" + id + "']")).click();
    driver.findElement(By.name("add")).click();
  }

  public void remove(ContactData contact, GroupData group) {
    selectGroup(group.getId());
    selectContactById(contact.getId());
    removeFrom();

  }

  private void selectGroup(int id) {
    driver.findElement(By.xpath("//select[@name='group']//option[@value='" + id + "']")).click();
  }

  private void removeFrom() {
    driver.findElement(By.name("remove")).click();
  }
}
