package ru.testjava.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHome();
    app.getGroupHelper().selectContact();
    app.getGroupHelper().deleteContact();
  }

}