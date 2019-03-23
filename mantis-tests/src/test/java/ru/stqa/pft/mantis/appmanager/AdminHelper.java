package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

public class AdminHelper extends HelperBase {


  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public void login() {
    wd.get(app.getProperty("web.baseUrl") + "/login.php");
    type(By.name("username"), app.getProperty("web.adminLogin"));
    type(By.name("password"), app.getProperty("web.adminPassword"));
    click(By.cssSelector("input[value='Login']"));
  }


  public void manageUsers(String username) {
    wd.get(app.getProperty("web.baseUrl") + "/manage_overview_page.php");
    click(By.cssSelector("a[href*='manage_user_page']"));
    type(By.name("username"), username);
    click(By.cssSelector("input[value='Manage User']"));
  }

  public void resetPassword() {
    click(By.cssSelector("input[value='Reset Password']"));
  }



  public String findConformationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  public void finish(String conformationLink, String newpassword) {
    wd.get(conformationLink);
    type(By.name("password"), newpassword);
    type(By.name("password_confirm"), newpassword);
    click(By.cssSelector("input[value='Update User']"));
  }
}
