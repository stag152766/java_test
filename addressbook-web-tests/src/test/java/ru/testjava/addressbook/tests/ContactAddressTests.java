package ru.testjava.addressbook.tests;

import org.testng.annotations.Test;
import ru.testjava.addressbook.appmanager.ContactHelper;
import ru.testjava.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{


  @Test
  public void testContactAddress() {
    app.goTo().HomePage();
    ContactData contact = app.contact().all2().iterator().next();
    ContactData contactInfoEditForm = app.contact().infoFromEditForm(contact);
    assertThat(ContactHelper.cleaned(contact.getAddress()),
            equalTo(ContactHelper.cleaned(contactInfoEditForm.getAddress())));
  }
}
