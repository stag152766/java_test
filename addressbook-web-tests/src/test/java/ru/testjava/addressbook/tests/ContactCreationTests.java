package ru.testjava.addressbook.tests;

import org.testng.annotations.Test;
import ru.testjava.addressbook.tests.TestBase;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationTests() {
    app.gotoAddNew();
    app.fillContactForm();
    app.submitContactCreation();
  }

}
