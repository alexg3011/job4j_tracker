package ru.job4j.tracker.collection;

import org.junit.Test;
import ru.job4j.collection.Departments;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DepartmentsTest {
    @Test
    public void whenMissed() {
        List<String> input = List.of("k1/sk1");
        List<String> expect = Arrays.asList("k1", "k1/sk1");
        List<String> result = Departments.fillGaps(input);
        assertThat(result, is(expect));
    }

    @Test
    public void whenNonChange() {
        List<String> input = Arrays.asList("k1", "k1/sk1");
        List<String> expect = Arrays.asList("k1", "k1/sk1");
        List<String> result = Departments.fillGaps(input);
        assertThat(result, is(expect));
    }

    @Test
    public void naturalCompTest() {
        List<String> input = Arrays.asList("k1", "k1/sk2", "k1/sk1");
        List<String> expect = Arrays.asList("k1", "k1/sk1", "k1/sk2");
        Departments.sortAsc(input);
        assertThat(input, is(expect));
    }
}
