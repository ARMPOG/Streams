package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Person> people = getPeople();

        // Imperative approach - means we define every single step for what we want to achieve
/*

        List<Person> females = new ArrayList<>();
        for (Person person : people) {
            if (person.getGender().equals(Gender.FEMALE)){
                females.add(person);
            }
        }
        females.forEach(System.out::println);
*/

        //Declarative approach - streams allow us just to tell what we want and it will do all necessary actions for us.

        // Filter
        System.out.println("Filtered ........");
        List<Person> females = people.stream().filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());
        females.forEach(System.out::println);
        System.out.println("");

        //Sort
        System.out.println("Sorted ..........");
        List<Person> sortedByAge = people.stream().sorted(Comparator.comparing(Person::getAge)
                .thenComparing(Person::getGender).reversed())
                .collect(Collectors.toList());
        sortedByAge.forEach(System.out::println);
        System.out.println("");

        //All match
        System.out.println("All match ..........");
        boolean matchByAge = people.stream().allMatch(person -> person.getAge() > 13);
        System.out.println(matchByAge);
        System.out.println("");

        //Any match
        System.out.println("Any match ...........");
        boolean anyMatchByAge = people.stream().anyMatch(person -> person.getAge() > 39);
        System.out.println(anyMatchByAge);
        System.out.println("");

        //None Match
        System.out.println("None match ........");
        boolean noneMatchName = people.stream().noneMatch(person -> person.getName().equals("Antony"));
        System.out.println(noneMatchName);
        System.out.println("");

        //Max
        System.out.println("Max .........");
        people.stream().max(Comparator.comparing(Person::getAge)).ifPresent(System.out::println);
        System.out.println("");

        //Min
        System.out.println("Min ........");
        people.stream().min(Comparator.comparing(Person::getAge)).ifPresent(System.out::println);
        System.out.println("");

        //Group
        System.out.println("Group ..........");
        Map<Gender, List<Person>> groupByGender = people.stream().collect(Collectors.
                groupingBy(Person::getGender));

        groupByGender.forEach((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
            System.out.println("");
        });

        System.out.println("The oldest female is.....");
        Optional<String> oldestFemaleAge = people.stream().filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        oldestFemaleAge.ifPresent(System.out::println);
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Armen", 36, Gender.MALE),
                new Person("Karen", 26, Gender.MALE),
                new Person("Nare", 16, Gender.FEMALE),
                new Person("Mery", 40, Gender.FEMALE),
                new Person("Benji", 34, Gender.MALE),
                new Person("Linda", 14, Gender.FEMALE)
        );
    }
}
