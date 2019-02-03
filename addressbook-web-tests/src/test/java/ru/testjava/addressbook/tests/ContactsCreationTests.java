package ru.testjava.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testjava.addressbook.model.ContactData;
import ru.testjava.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class ContactsCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().HomePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Alex").withLastname("Jackson")
            .withAddress("My street").withMobileNum("111").withHomeNum("222").withWorkNum("333").withGroup("test2");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().
            mapToInt((c) -> c.getId()).max().getAsInt()))));

  }

}




