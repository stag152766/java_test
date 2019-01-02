package ru.testjava.addressbook.tests;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {


  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHome();
    app.getGroupHelper().selectContact();
    app.getGroupHelper().editContact();
    app.getGroupHelper().updateContact();




  }
}
