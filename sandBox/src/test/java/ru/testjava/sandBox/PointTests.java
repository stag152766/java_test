package ru.testjava.sandBox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {


  @Test
  public void testPoint() {
    Distance d = new Distance(2, 1, 5, 7);
    Assert.assertEquals(d.distance(), 2.23606797749979);
      }

}

