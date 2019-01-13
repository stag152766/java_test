package ru.testjava.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testjava.addressbook.modul.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {


  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().ContactCreation(new ContactData( "Alex",null, "Jackson", "My street", "+79135467841", "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification(before.size()-1);
    app.getContactHelper().fillContactForm(new ContactData("test1", "test2", "test3", "test4", "test5", null), false);
    app.getContactHelper().submitContactModification(); //update = delete
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

  }
}
