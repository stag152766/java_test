package ru.testjava.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testjava.addressbook.modul.ContactData;

public class ContactModificationTests extends TestBase {


  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().ContactCreation(new ContactData( "Alex",null, "Jackson", "My street", "+79135467841", "test1"), true);
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("test1", "test2", "test3", "test4", "test5", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before -1);

  }
}
