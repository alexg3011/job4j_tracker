package ru.job4j.oop;
import org.junit.Test;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

public class PointTest {
    @Test
    public void distance3d() {
        Point a = new Point(1, 2, 3);
        Point b = new Point(-7, -2, 4);
        double rsl = a.distance3d(b);
        assertThat(rsl, closeTo(9, 0.001));
    }
    @Test
    public void distance3dTwo() {
        Point a = new Point(0, 0, 0);
        Point b = new Point(0, 0, 0);
        double rsl = a.distance3d(b);
        assertThat(rsl, closeTo(0, 0.001));
    }
}
