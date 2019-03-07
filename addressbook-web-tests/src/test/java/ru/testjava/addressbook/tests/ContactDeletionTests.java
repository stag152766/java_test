package ru.testjava.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testjava.addressbook.model.ContactData;
import ru.testjava.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

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
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }

}
