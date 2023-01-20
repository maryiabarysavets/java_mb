package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testDistance() {
    Point point_1 = new Point(5, 5);
    Point point_2 = new Point(1, 1);

    Assert.assertEquals(point_1.distance(point_2), 5.656854249492381);
  }

  @Test
  public void testDistance1() {
    Point point_1 = new Point(3.122, 3.122);
    Point point_2 = new Point(1, 1);

    Assert.assertEquals(point_1.distance(point_2), 3.000961179355708);

  }
}


