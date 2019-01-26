package ru.testjava.addressbook.modul;

import com.google.common.collect.ForwardingSet;

import java.util.Set;

public class Group extends ForwardingSet<GroupData> {

  @Override
  protected Set<GroupData> delegate() {
    return null;
  }
}
