package ru.testjava.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testjava.addressbook.modul.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().ContactCreation(new ContactData("Alex", null, "Jackson", "My street", "+79135467841", "test1"), true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size() + 1);

  }
}




