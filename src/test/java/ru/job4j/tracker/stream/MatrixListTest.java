package ru.job4j.tracker.stream;

import org.junit.Test;
import ru.job4j.stream.MatrixList;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixListTest {
    @Test
    public void whenMatrixToList() {
        Integer[][] matrix = new Integer[][] {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9}
        };
        MatrixList matrixList = new MatrixList();
        List<Integer> rsl = matrixList.convert(matrix);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(rsl, is(expected));
    }
}
