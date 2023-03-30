package edu.rit.wellness_manager.models;

import java.util.List;

public class Recipe implements Edible{
    private String name;
    private List<Edible> ingredients;

    public String getName() {
        return name;
    }

    public double getCalories() {
        double sumCalories = 0.0;
        for(Edible ingredient : ingredients){
            sumCalories += ingredient.getCalories();
        }
        return sumCalories;
    }

    public double getFat() {
        double sumFat = 0.0;
        for(Edible ingredient : ingredients){
            sumFat += ingredient.getFat();
        }
        return sumFat;
    }

    public double getProtein() {
        double sumProtein = 0.0;
        for(Edible ingredient : ingredients){
            sumProtein += ingredient.getProtein();
        }
        return sumProtein;
    }

    public double getCarb() {
        double sumCarbs = 0.0;
        for(Edible ingredient : ingredients){
            sumCarbs += ingredient.getCarb();
        }
        return sumCarbs;
    }

    public double getQuantity() {
        return 0;
    }
    @Override
    public String toString() {
        return "r," + name + "," + getCalories()+ "," + getFat() + "," + getCarb() + "," + getProtein();
    }
}
