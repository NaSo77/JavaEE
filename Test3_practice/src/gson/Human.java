package gson;

import java.util.ArrayList;

public class Human {
    private String name;
    private int age;
    private boolean isАwesome;
    private ArrayList<String> words;

    public Human(String name, int age, boolean isАwesome, ArrayList<String> words) {
	this.name = name;
	this.age = age;
	this.isАwesome = isАwesome;
	this.words = words;
    }

}
