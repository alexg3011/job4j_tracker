package ru.job4j.tracker.lambda;

import org.junit.Test;
import ru.job4j.lambda.LinearFunction;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LinearFunctionTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        LinearFunction function = new LinearFunction();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        LinearFunction function = new LinearFunction();
        List<Double> result = function.diapason(5, 8, x -> x * x + 2 * x + 1);
        List<Double> expected = Arrays.asList(36D, 49D, 64D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenExpFunctionThenExpResults() {
        LinearFunction function = new LinearFunction();
        List<Double> result = function.diapason(5, 8, x -> Math.pow(5, x));
        List<Double> expected = Arrays.asList(3125D, 15625D, 78125D);
        assertThat(result, is(expected));
    }
}
