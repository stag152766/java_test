package ru.testjava.addressbook.tests;

import org.testng.annotations.Test;
import ru.testjava.addressbook.modul.ContactData;

public class ContactModificationTests extends TestBase {


  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getGroupHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("test1", "test2", "test3", "test4", "test5"));
    app.getGroupHelper().updateContact();
    app.getNavigationHelper().returnToHomePage();




  }
}
