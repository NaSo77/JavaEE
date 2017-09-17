package objects;


public class Car {
    @Override
    public String toString() {
	return "Car [model=" + model + ", year=" + year + ", color=" + color + "]";
    }

    private String model;
    private int year;
    private String color;

    public String getModel() {
	return model;
    }

    public int getYear() {
	return year;
    }

    public String getColor() {
	return color;
    }

    public Car(String model, int year, String color) {
	this.model = model;
	this.year = year;
	this.color = color;
    }


    public Car() {
	// TODO Auto-generated constructor stub
    }

}
