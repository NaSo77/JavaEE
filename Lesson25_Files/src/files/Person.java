package files;

import java.util.ArrayList;

public class Person {
    private String name;
    private int age;
    private ArrayList<Person> friends;

    public Person(String name, int age) {
	this.name = name;
	this.age = age;
	this.friends = new ArrayList<>();
	this.friends.add(new Person("Pesho"));
	this.friends.add(new Person("Gosho"));
    }

    public Person(String name) {
	this.name = name;
    }
}
