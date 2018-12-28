package ru.testjava.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationTests() {
    app.gotoAddNew();
    app.fillContactForm();
    app.submitContactCreation();
  }

}
