package ru.testjava.addressbook.tests;

import org.testng.annotations.Test;
import ru.testjava.addressbook.model.Contacts;
import ru.testjava.addressbook.model.ContactData;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactsCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().HomePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Alex").withLastname("Jackson")
            .withAddress("My street").withMobile("+79135467841").withGroup("test1");
    app.contact().create(contact, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().
            mapToInt((c) -> c.getId()).max().getAsInt()))));

  }
}




