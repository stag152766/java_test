package ru.testjava.addressbook.tests;

import org.testng.annotations.Test;
import ru.testjava.addressbook.appmanager.ContactHelper;
import ru.testjava.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {


  @Test
  public void testContactDetails() {
    app.goTo().HomePage();
    ContactData contact = app.contact().all2().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    app.goTo().HomePage();
    ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);
    assertThat(contactInfoFromEditForm.getAllNames(), equalTo(contactInfoFromDetailsForm.getAllNames()));

    }

}
