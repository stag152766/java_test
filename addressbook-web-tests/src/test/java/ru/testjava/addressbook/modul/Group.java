package ru.testjava.addressbook.modul;

import com.google.common.collect.ForwardingSet;

import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {

  private Set<GroupData> delegateO;

  @Override
  protected Set<GroupData> delegate() {
    return delegateO;
  }

  public Groups withAdded(GroupData group) { //добавили свой метод

  }
}
