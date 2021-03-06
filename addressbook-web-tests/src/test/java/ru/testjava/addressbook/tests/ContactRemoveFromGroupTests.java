package ru.testjava.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testjava.addressbook.model.ContactData;
import ru.testjava.addressbook.model.Contacts;
import ru.testjava.addressbook.model.GroupData;
import ru.testjava.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("web.firstname").withLastname("web.lastname")
              .withAddress("web.address").withHome("web.home").withMobile("web.mobile").withWork("web.work")
              .withEmail("web.email").withEmail3("web.email3"), true);
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("newGroup"));
    }
  }


  @Test
  public void testContactRemoveFromGroup() {
    Contacts contacts = app.db().contacts();
    ContactData contact = contacts.iterator().next();
    Groups before = contact.getGroups();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    if (before.size() == 0) {
      app.goTo().homePage();
      app.contact().addTo(contact, group);
    }
    app.goTo().homePage();
    app.contact().remove(contact, group);
    Groups after = app.db().contacts().iterator().next().getGroups();
    assertThat(after, equalTo(before.without(group)));
  }
}
