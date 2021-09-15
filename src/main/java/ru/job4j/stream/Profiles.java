package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    public List<Address> collect(List<Profile> profiles) {
        Comparator<Address> sorted = Comparator.comparing(Address::getCity);

        return profiles.stream()
                .map(profile -> new Address(
                                profile.getAddress().getCity(),
                                profile.getAddress().getStreet(),
                                profile.getAddress().getHome(),
                                profile.getAddress().getApartment()
                        )
                )
                .sorted(sorted)
                .distinct()
                .collect(Collectors.toList());
    }

}
