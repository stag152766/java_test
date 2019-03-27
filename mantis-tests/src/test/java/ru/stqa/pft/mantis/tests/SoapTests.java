package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class SoapTests {

  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    //получение списка проектов
    MantisConnectPortType mc = new MantisConnectLocator()
            .getMantisConnectPort(new URL("http://localhost/mantisbt-1.2.19/api/soap/mantisconnect.php"));
    //получаем массив проектов, к которым указанный пользователь имеет доступ
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
    //вывод на консоль количества проектов
    System.out.println(projects.length);
    //названия проектов
    for (ProjectData project : projects){
      System.out.println(project.getName());
    }
  }

}
