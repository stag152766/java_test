package ru.testjava.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.testjava.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactDetailsTests extends TestBase {


  @Test
  public void testContactDetails() {
    app.goTo().HomePage();
    ContactData contact = app.contact().all2().iterator().next();
    ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);
    assertThat(contact.getFirstname(), equalTo(infoFromDetailsForm.getFirstname()));


  }
}
