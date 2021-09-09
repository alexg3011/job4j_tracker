package ru.job4j.collection;
import org.junit.Test;
import java.util.Comparator;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class JobTest {
    @Test
    public void whenCompatorByNameDesc() {
        Comparator<Job> cmpName = new JobDescByName();
        int rsl = cmpName.compare(
                new Job("b", 0),
                new Job("a", 0)
        );
        assertThat(rsl, lessThan(0));
    }
    @Test
    public void whenCompatorByNameEqual() {
        Comparator<Job> cmpName = new JobDescByName();
        int rsl = cmpName.compare(
                new Job("b", 0),
                new Job("b", 0)
        );
        assertThat(rsl, equalTo(0));
    }
    @Test
    public void whenCompatorByNameAsc() {
        Comparator<Job> cmpName = new JobAscByName();
        int rsl = cmpName.compare(
                new Job("b", 0),
                new Job("a", 0)
        );
        assertThat(rsl, greaterThan(0));
    }
    @Test
    public void whenCompatorByPriorityDesc() {
        Comparator<Job> cmpPriority = new JobDescByPriority();
        int rsl = cmpPriority.compare(
                new Job("a", 4),
                new Job("a", 2)
        );
        assertThat(rsl, lessThan(0));
    }
    @Test
    public void whenCompatorByPriorityAsc() {
        Comparator<Job> cmpPriority = new JobAscByPriority();
        int rsl = cmpPriority.compare(
                new Job("a", 2),
                new Job("a", 4)
        );
        assertThat(rsl, lessThan(0));
    }
    @Test
    public void whenCompatorByPriorityEqual() {
        Comparator<Job> cmpPriority = new JobDescByPriority();
        int rsl = cmpPriority.compare(
                new Job("a", 0),
                new Job("a", 0)
        );
        assertThat(rsl, equalTo(0));
    }
    @Test
    public void whenCompatorByNameAndPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByAscNameAndAscPriority() {
        Comparator<Job> cmpNamePriority = new JobAscByName().thenComparing(new JobAscByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Fix bug", 1),
                new Job("Impl task", 0)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByDescNameAndAscPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobAscByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByAscNameAndDescPriority() {
        Comparator<Job> cmpNamePriority = new JobAscByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Fix bug", 1),
                new Job("Impl task", 0)
        );
        assertThat(rsl, lessThan(0));
    }
}
