package ru.testjava.addressbook.modul;

import com.google.common.collect.ForwardingSet;
import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {

  private Set<GroupData> delegate;

  public Groups(Groups groups) {
    this.delegate = new HashSet<>(groups.delegate); //берем множество из существующего объекта, который передан в качестве параментра
  }

  public Groups() {
    this.delegate = new HashSet<>();
  }

  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }

  public Groups withAdded(GroupData group) { //добавили свой метод
    Groups groups = new Groups(this); //создали копию
    groups.add(group); //в копию добавлили объект, который передан в качестве параментра
    return groups;
  }

  public Groups without(GroupData group) {
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;

  }


}
