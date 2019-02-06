package ru.testjava.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testjava.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Alex").
              withLastname("Jackson").withAddress("My street").
              withMobile("111").withHome("222").withWork("333").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactPhones(){
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getHome(), equalTo(contactInfoFromEditForm.getHome()));
    assertThat(contact.getMobile(), equalTo(contactInfoFromEditForm.getMobile()));
    assertThat(contact.getWork(), equalTo(contactInfoFromEditForm.getWork()));


  }


}
