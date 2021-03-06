package ru.testjava.addressbook.tests;

import org.testng.annotations.Test;
import ru.testjava.addressbook.model.GroupData;
import ru.testjava.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

  @Test
  public void testDbConnection() {
      Connection conn = null;
      try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
        //создаем объект для извлечения данных из БД
        Statement st = conn.createStatement();
        // извлекаем сет строк таблицы
        ResultSet rs = st.executeQuery
                ("select group_id, group_name, group_header, group_footer  from group_list ");
        Groups groups = new Groups();
        // пробегаем по множеству результатов, каждый шаг resultSet - это указатель на одну строку таблицы
        while (rs.next()) {
        groups.add(new GroupData().withId(rs.getInt("group_id"))
                .withName(rs.getString("group_name"))
                .withFooter(rs.getString("group_footer"))
                .withHeader(rs.getString("group_footer")));
        }
        rs.close();
        st.close();
        conn.close();
        System.out.println(groups);

      } catch (SQLException ex) {

        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
      }

    }
}
