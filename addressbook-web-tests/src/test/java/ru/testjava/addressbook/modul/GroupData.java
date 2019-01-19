package ru.testjava.addressbook.modul;

//вспомогательный класс, который описывает объект типа группа

import java.util.Objects;

public class GroupData {
  private int id;
  private final String name;
  private final String header;
  private final String footer;

  public GroupData(String name, String header, String footer) {
    this.id = Integer.MAX_VALUE; //дефолтное значение для индентификатора
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public GroupData(int id, String name, String header, String footer) {
    this.id = id;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  @Override
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

}
