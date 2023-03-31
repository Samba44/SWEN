package edu.rit.wellness_manager.models;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Edible{
    private String name;
    private List<Edible> edibles;
    private double quantity;

    public Recipe(String name, List<Edible> ingredients, double quantity) {
        this.name = name;
        this.edibles = ingredients;
        this.quantity = quantity;
    }

    public Recipe(String name) {
        this.name = name;
        edibles = new ArrayList<>();
        quantity = 1;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        double sumCalories = 0.0;
        for(Edible ingredient : edibles){
            sumCalories += ingredient.getCalories();
        }
        return sumCalories;
    }

    public double getFat() {
        double sumFat = 0.0;
        for(Edible ingredient : edibles){
            sumFat += ingredient.getFat();
        }
        return sumFat;
    }

    public double getProtein() {
        double sumProtein = 0.0;
        for(Edible ingredient : edibles){
            sumProtein += ingredient.getProtein();
        }
        return sumProtein;
    }

    public double getCarb() {
        double sumCarbs = 0.0;
        for(Edible ingredient : edibles){
            sumCarbs += ingredient.getCarb();
        }
        return sumCarbs;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity){
        this.quantity = quantity;
    }

    public Edible getEdible(int i) {
        return edibles.get(i);
    }

    public void addEdible(Edible edible, double quantity){
        edible.setQuantity(quantity);
        this.edibles.add(edible);

    }

    public void removeEdible(Edible edible) {
        edibles.remove(edible);
    }

    public List<Edible> getEdibles() {
        return edibles;
    }

    public Edible clone() throws CloneNotSupportedException {
        Recipe clone = (Recipe) super.clone();
        clone.setQuantity(this.quantity);
        return clone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("r,%s", getName()));
        for (Edible var : edibles) {
            sb.append(String.format(",%s,%.2f", var.getName(), var.getQuantity()));
        }
        return sb.toString();
    }
}
