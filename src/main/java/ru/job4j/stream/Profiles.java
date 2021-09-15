package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    public List<Address> collect(List<Profile> profiles) {

        return profiles.stream()
                .map(profile -> new Address(
                                profile.getAddress().getCity(),
                                profile.getAddress().getStreet(),
                                profile.getAddress().getHome(),
                                profile.getAddress().getApartment()
                        )
                )
                .collect(Collectors.toList());
    }
}
