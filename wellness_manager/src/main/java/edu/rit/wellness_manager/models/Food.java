package edu.rit.wellness_manager.models;

public class Food implements Edible {
    private String name;
    private double calories;
    private double fat;
    private double carb;
    private double protein;

    //constructor
    public Food(String name, double calories, double fat, double carb, double protein) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carb = carb;
        this.protein = protein;
    }
    //getters
    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public double getFat() {
        return fat;
    }

    public double getCarb() {
        return carb;
    }

    public double getQuantity() {
        return 0;
    }

    public double getProtein() {
        return protein;
    }

    //setter
    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setCarb(double carb) {
        this.carb = carb;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    @Override
    public String toString() {
        return "b," + name + "," + calories + "," + fat + "," + carb + "," + protein;
    }
}
