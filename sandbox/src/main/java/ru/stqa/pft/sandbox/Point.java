package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance (Point p1) {
    return Math.sqrt((p1.x- x) * (p1.x- x) + (p1.y- y ) * (p1.y- y));
  }
}

