package ru.job4j.tracker.tracker;
import org.junit.Test;
import ru.job4j.tracker.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {
    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenTrueInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenMultiTrueInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"2", "0", "3", "4"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int[] expected = {2, 0, 3, 4};
        int[] selected = new int[4];
        selected[0] = input.askInt("Enter menu:");
        selected[1] = input.askInt("Enter menu:");
        selected[2] = input.askInt("Enter menu:");
        selected[3] = input.askInt("Enter menu:");
        assertThat(selected, is(expected));
    }

    @Test
    public void whenNegativeInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"-6"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(-6));
    }
}
