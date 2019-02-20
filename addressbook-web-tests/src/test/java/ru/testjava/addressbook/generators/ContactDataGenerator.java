package ru.testjava.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.thoughtworks.xstream.XStream;
import ru.testjava.addressbook.model.ContactData;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;


  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    jCommander.parse(args);
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> groups = generatorContacts(count);
    saveAsXml(groups, new File(file));

  }

  private List<ContactData> generatorContacts(int count) {
      List<ContactData> contact = new ArrayList<>();
      for (int i=0; i< count; i++) {
      contact.add(new ContactData().withFirstname(String.format("firstname%s", i)).
              withLastname(String.format("lastname%s", i)).
              withWork(String.format("111-%s", i)).
              withAddress(String.format("address%s", i)).
              withEmail(String.format("email%s", i)));
    }
      return contact;
  }


  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.alias("contact",ContactData.class);
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

}