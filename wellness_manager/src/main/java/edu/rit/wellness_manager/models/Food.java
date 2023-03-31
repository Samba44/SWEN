package edu.rit.wellness_manager.models;

import java.util.List;

public class Food implements Edible {
    private String name;
    private double calories;
    private double fat;
    private double carb;
    private double protein;
    private double quantity;

    //constructor
    public Food(String name, double calories, double fat, double carb, double protein) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carb = carb;
        this.protein = protein;
        quantity = 1;
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
        return quantity;
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

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    //because of composable interface this can be unimplemented
    public Edible getEdible(int i) {
        return null;
    }

    public void addEdible(Edible child, double quantity) {

    }

    public void removeEdible(Edible child) {

    }

    public List<Edible> getEdibles() {
        return null;
    }

    @Override
    public String toString() {
        return "b," + name + "," + calories + "," + fat + "," + carb + "," + protein;
    }

    @Override
    public Edible clone() throws CloneNotSupportedException {
        Food clone = (Food) super.clone();
        clone.setName(this.name);
        clone.setCalories(this.calories);
        clone.setQuantity(this.quantity);
        clone.setCarb(this.carb);
        clone.setFat(this.fat);
        clone.setProtein(this.protein);
        return clone;
    }
}
