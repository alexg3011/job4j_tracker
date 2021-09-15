package ru.job4j.tracker.stream;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.stream.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProfilesTest {
    @Test
    public void whenCollectAddresses() {
        List<Profile> profiles = List.of(
                new Profile(new Address("Moscow", "Arbat", 1, 2)),
                new Profile(new Address("St. Petersburg", "Nevskij pr-t", 3, 4)),
                new Profile(new Address("Sochi", "Polyana", 5, 6))
        );
        Profiles profiles1 = new Profiles();
        List<Address> rsl = profiles1.collect(profiles);
        List<Address> expected = Arrays.asList(
                new Address("Moscow", "Arbat", 1, 2),
                new Address("St. Petersburg", "Nevskij pr-t", 3, 4),
                new Address("Sochi", "Polyana", 5, 6));
        assertThat(rsl, is(expected));

    }
}
