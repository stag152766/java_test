package ru.testjava.addressbook.generators;

import com.thoughtworks.xstream.XStream;
import ru.testjava.addressbook.model.ContactData;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


public class ContactDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);


    List<ContactData> groups = generatorContacts(count);
    saveAsXml(groups, file);

  }

  private static List<ContactData> generatorContacts(int count) {
      List<ContactData> contact = new ArrayList<>();
      for (int i=0; i< count; i++) {
      contact.add(new ContactData().withFirstname(String.format("firstname%s", i)).
              withLastname(String.format("lastname%s", i)).
              withWork(String.format("%s%s%s%", i)).
              withAddress(String.format("address%s", i)).
              withEmail(String.format("%s%s%s@", i)));
    }
      return contact;
  }


  private static void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

}