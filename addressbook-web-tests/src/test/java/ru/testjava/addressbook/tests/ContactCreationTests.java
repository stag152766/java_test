package ru.testjava.addressbook.tests;

import org.testng.annotations.Test;
import ru.testjava.addressbook.modul.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().ContactCreation(new ContactData( "Alex",null, "Jackson", "My street", "+79135467841", "test1"), true);
  }



}
