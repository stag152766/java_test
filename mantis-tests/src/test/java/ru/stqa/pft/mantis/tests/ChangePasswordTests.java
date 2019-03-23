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
    String username = "user1553302824487";
    String newpassword = "222222";
    String email = String.format("%s@localhost.localdomain", username);
    app.admin().login();
    app.admin().manageUsers(username);
    app.admin().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String conformationLink = app.admin().findConformationLink(mailMessages, email);
    app.admin().finish(conformationLink, newpassword);
    HttpSession session = app.newSession();
    assertTrue(session.login(username, newpassword));
    assertTrue(session.isLoggedAs(username));
  }


  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
