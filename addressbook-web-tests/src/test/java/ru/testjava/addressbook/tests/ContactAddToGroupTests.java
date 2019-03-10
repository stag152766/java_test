package ru.testjava.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testjava.addressbook.model.ContactData;
import ru.testjava.addressbook.model.Contacts;
import ru.testjava.addressbook.model.GroupData;
import ru.testjava.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {

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
      app.group().create(new GroupData().withName("test1"));
    }
  }


  @Test
  public void testContactAddToGroup() {
    Contacts contacts = app.db().contacts();
    ContactData contact = contacts.iterator().next();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    Groups before = contact.getGroups();

    if (before.size() == 0) {
      app.goTo().homePage();
      app.contact().addTo(contact, group);
    }
    else {
      List<GroupData> c = new ArrayList<GroupData>(groups.size());
      c.addAll(groups);
      c.removeAll(before);
      app.goTo().homePage();
      app.contact().addTo(contact, c.iterator().next());
    }
    Groups after = app.db().contacts().iterator().next().getGroups();
    assertThat(after, equalTo(before.withAdded(group)));
  }

}
