package ru.testjava.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testjava.addressbook.modul.ContactData;

public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().ContactCreation(new ContactData( "Alex",null, "Jackson", "My street", "+79135467841", "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before -1);
  }

}
