package ru.testjava.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testjava.addressbook.appmanager.ContactHelper;
import ru.testjava.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.testjava.addressbook.appmanager.ContactHelper.*;

public class ContactDetailsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all2().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("test1")
              .withLastname("test2")
              .withAddress("111,\n" + "test\n" + "123456")
              .withHome("11-11-11")
              .withMobile("+7 (398)")
              .withWork("33 33 33")
              .withEmail("test_.test@t.com")
              .withEmail3("@33"), true);

    }

  }


  @Test
  public void testContactDetails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all2().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    app.goTo().homePage();
    ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);
    assertThat(cleaned(contactInfoFromDetailsForm.getAll()),
            equalTo(cleaned(mergeAll(contactInfoFromEditForm))));

  }


  private String mergeNames(ContactData contact) {
    return Arrays.asList(contact.getFirstname(), contact.getLastname())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining());
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(
            isHomePresent(contact),
            isMobilePresent(contact),
            isWorkPresent(contact))
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining());
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactHelper::cleaned)
            .collect(Collectors.joining());

  }

  private String mergeAll(ContactData contact) {
    return Arrays.asList(mergeNames(contact), contact.getAddress(), mergePhones(contact),
            mergeEmails(contact)).stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining());
  }

  private String isHomePresent(ContactData contact) {
    if (contact.getHome().equals("")) {
      return contact.getHome();
    }
    return "H:" + contact.getHome();
  }

  private String isMobilePresent(ContactData contact) {
    if (contact.getMobile().equals("")) {
      return contact.getMobile();
    }
    return "M:" + contact.getMobile();
  }

  private String isWorkPresent(ContactData contact) {
    if (contact.getWork().equals("")) {
      return contact.getWork();
    }
    return "W:" + contact.getWork();
  }



}
