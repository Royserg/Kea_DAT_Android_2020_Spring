package com.example.fragments_app.storage;

import com.example.fragments_app.models.Person;

import java.util.ArrayList;
import java.util.List;

public class People {

    private static List<Person> people = new ArrayList<>();

    public static List<Person> getPeople() {
        if (People.people.isEmpty()) {
            fillList();
        }

        return people;
    }

    public static void setPeople(List<Person> people) {
        People.people = people;
    }

    public static Person getPerson(int index) {
        return people.get(index);
    }

    private static void fillList() {
        People.people.add(new Person("John", 23, "skiing"));
        People.people.add(new Person("Emily", 40, "painting"));
        People.people.add(new Person("Patrick", 24, "sports"));
        People.people.add(new Person("Sebastian", 37, "diving"));
        People.people.add(new Person("Hanna", 77, "shopping"));
    }
}
