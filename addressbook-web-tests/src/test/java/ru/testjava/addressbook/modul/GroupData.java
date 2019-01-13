package ru.testjava.addressbook.modul;

//вспомогательный класс, который описывает объект типа группа

public class GroupData {
  private final String name;
  private final String header;
  private final String footer;

  public GroupData(String name, String header, String footer) {
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "name='" + name + '\'' +
            '}';
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }
}
