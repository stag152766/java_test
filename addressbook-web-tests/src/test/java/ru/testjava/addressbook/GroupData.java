package ru.testjava.addressbook;

public class GroupData { // объект
  private final String name; //у которого три атрибута
  private final String header;
  private final String footer;

  public GroupData(String name, String header, String footer) {
    this.name = name; // в классе есть конструктор, который позволяет проинициализировать объект какими то значениями
    this.header = header;
    this.footer = footer;
  }

  public String getName() {
    return name;
  } //методы которые возвращают эти атрибуты

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }
}
