package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException {
    //админ инициирует смену пароля пользователю
    String username = "user1";
    String email = String.format("user1s@localhost.localdomain");
    app.admin().login();
    app.admin().manageUsers(username);
    app.admin().resetPassword();

    //пользователь устанавливает новый пароль

    String newpassword = "111111";
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String conformationLink = app.admin().findConformationLink(mailMessages, email);
    app.admin().finish(conformationLink, newpassword);

    //проверка логина с новым паролем
    HttpSession session = app.newSession();
    assertTrue(session.login(username, newpassword));
    assertTrue(session.isLoggedAs(username));

  }


  @AfterMethod
  public void stopMailServer() {
    app.mail().stop();
  }
}
