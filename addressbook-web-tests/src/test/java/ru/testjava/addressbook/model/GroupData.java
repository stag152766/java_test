package ru.testjava.addressbook.model;

//вспомогательный класс, который описывает объект типа группа

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("group")
public class GroupData {
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  private String name;
  private String header;
  private String footer;

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (id != groupData.id) return false;
    return name != null ? name.equals(groupData.name) : groupData.name == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  public GroupData withId(int id) {
    this.id = id;
    return this; //это ссылка на самого себя внутри экземпляра класса
                 //метод вернет экземляр класса GroupData, вызвавший его
  }

  public GroupData withName(String name) {
    this.name = name;
    return this; //
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
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
