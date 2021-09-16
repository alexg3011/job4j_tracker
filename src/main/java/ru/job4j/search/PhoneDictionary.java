package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private final ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        var name = (Predicate<Person>) value -> value.getName().equals(key);
        var surname = (Predicate<Person>) value -> value.getSurname().equals(key);
        var phone = (Predicate<Person>) value -> value.getPhone().equals(key);
        var address = (Predicate<Person>) value -> value.getAddress().equals(key);
        var combine = name.or(surname).or(phone).or(address);

        var result = new ArrayList<Person>();
        for (var person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
