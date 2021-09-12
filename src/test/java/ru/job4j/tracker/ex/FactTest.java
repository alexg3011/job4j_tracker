package ru.job4j.tracker.ex;

import org.junit.Test;
import ru.job4j.ex.Fact;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FactTest {
    @Test(expected = IllegalArgumentException.class)
    public void whenNumberLess0() {
        Fact fact = new Fact();
        int rsl = fact.calc(-1);
        assertThat(rsl, is(1));
    }
}
