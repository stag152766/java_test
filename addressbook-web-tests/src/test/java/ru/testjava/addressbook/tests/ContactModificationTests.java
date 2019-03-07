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
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
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
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("mod_1")
            .withLastname("mod2")
            .withAddress("mod3")
            .withGroup(null)
            .withEmail("mod4")
            .withEmail2("mod5")
            .withEmail3("mod6")
            .withHome("mod7")
            .withWork("mod8")
            .withMobile("mod9");
    app.goTo().homePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    Contacts beforeModify = before.without(modifiedContact).withAdded(contact);
    assertThat(after, equalTo(beforeModify));
  }

}
