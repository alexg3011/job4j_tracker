package ru.job4j.tracker.stream;

import org.junit.Test;
import ru.job4j.stream.Student;
import ru.job4j.stream.StudentMap;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StudentMapTest {
    @Test
    public void whenListToMap() {
        List<Student> students = List.of(
                new Student(10, "Surname1"),
                new Student(40, "Surname4"),
                new Student(50, "Surname1"),
                new Student(70, "Surname7"),
                new Student(90, "Surname1")
        );
        StudentMap studentMap = new StudentMap();
        Map<String, Student> rsl = studentMap.studentToMap(students);
        Map<String, Student> expected = new HashMap<>();
        expected.put("Surname1", new Student(10, "Surname1"));
        expected.put("Surname4", new Student(40, "Surname4"));
        expected.put("Surname7", new Student(70, "Surname7"));
        assertThat(rsl, is(expected));
    }
}
