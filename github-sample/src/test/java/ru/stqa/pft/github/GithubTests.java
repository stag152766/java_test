package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {


  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("630e7e729a7c7a871cf2806583e080a6f75b72dd");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("stag152766", "java_test")).commits();
    for(RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){ //нужно передать набор пар которые описывают фильтр
      System.out.println(new RepoCommit.Smart(commit).message());

    }
  }
}
