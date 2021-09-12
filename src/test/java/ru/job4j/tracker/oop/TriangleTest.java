package ru.job4j.tracker.oop;
import org.junit.Test;
import ru.job4j.oop.Point;
import ru.job4j.oop.Triangle;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

public class TriangleTest {

    @Test
    public void area() {
        Point a = new Point(0, 0);
        Point b = new Point(4, 0);
        Point c = new Point(0, 4);
        Triangle triangle = new Triangle(a, b, c);
        double rsl = triangle.area();
        assertThat(rsl, closeTo(8, 0.001));
    }

    @Test
    public void areaTwo() {
        Point a = new Point(-2, 3);
        Point b = new Point(4, 3);
        Point c = new Point(2, 5);
        Triangle triangle = new Triangle(a, b, c);
        double rsl = triangle.area();
        assertThat(rsl, closeTo(6, 0.001));
    }

    @Test
    public void areaThree() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 0);
        Point c = new Point(0, 0);
        Triangle triangle = new Triangle(a, b, c);
        double rsl = triangle.area();
        assertThat(rsl, closeTo(-1, 0.001));
    }
}