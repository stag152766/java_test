package ru.testjava.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testjava.addressbook.model.ContactData;
import ru.testjava.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname(app.properties.getProperty("web.firstname"))
              .withLastname(app.properties.getProperty("web.lastname"))
              .withAddress(app.properties.getProperty("web.address"))
              .withHome(app.properties.getProperty("web.home"))
              .withMobile(app.properties.getProperty("web.mobile"))
              .withWork(app.properties.getProperty("web.work"))
              .withEmail(app.properties.getProperty("web.email"))
              .withEmail3(app.properties.getProperty("web.email3")), true);

    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("test1")
            .withLastname("test2").withAddress("test3").withGroup(null);
    app.contact().modify(contact);
    assertEquals(app.contact().count(), before.size());
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

  }

}
