package ru.testjava.addressbook.tests;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationTests() {
    app.getNavigationHelper().gotoAddNew();
    app.getContactHelper().fillContactForm();
    app.getContactHelper().submitContactCreation();
  }

}
