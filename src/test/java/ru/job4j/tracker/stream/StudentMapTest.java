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
                new Student("Surname1", 10),
                new Student("Surname4", 40),
                new Student("Surname1", 50),
                new Student("Surname7", 70),
                new Student("Surname1", 90)
        );
        StudentMap studentMap = new StudentMap();
        Map<String, Student> rsl = studentMap.studentToMap(students);
        Map<String, Student> expected = new HashMap<>();
        expected.put("Surname1", new Student("Surname1", 10));
        expected.put("Surname4", new Student("Surname4", 40));
        expected.put("Surname7", new Student("Surname7", 70));
        assertThat(rsl, is(expected));
    }
}
