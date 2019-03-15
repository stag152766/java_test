package ru.stqa.pft.mantis.tests;

import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.Closeable;
import java.net.http.HttpClient;
import java.nio.file.ClosedWatchServiceException;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
  private ApplicationManager app;
  private CloseableHttpClient httpclient;



  public HttpSession(ApplicationManager app){
    this.app = app;
    httpclient = HttpClient.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
  }

  public boolean login() {
    HttpPost post = new HttpPost(app.getProperty("web.baseURL") + "/login.php");
    List<NameValuePair> params = new ArrayList();
    params.add(new BasicNameValuePair("username", username));
    params.add(new BasicNameValuePair("password", password));
    params.add(new BasicNameValuePair("secure_session", "on"));
    params.add(new BasicNameValuePair("return", "index.php"));
    post.setEntity(new UrlEncodedFormEntity(params));
    CloseableHttpResponse response = httpclient.execute(post);
    String body = getTextForm(response);
    return body.contains(String.format("<span class\"italic\">%s</span>", username));
  }

  private String getTextForm(CloseableHttpResponse response) {
    try {
      return EntityUtils.toString(response.getEntity());
    } finally {
      response.close();
    }
  }

  public boolean isLoggedAs(String username) {
    HttpGet get = new HttpGet(app.getProperty("web.baseUrl" + "/index.php"));
    CloseableHttpResponse response = httpclient.execute(get);
    String body = getTextForm(response);
    return body.contains(String.format("<span class=\italic\">%s</span>", username));
  }

}
