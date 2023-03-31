package edu.rit.wellness_manager.models;

import java.util.List;

public interface Edible extends Cloneable{
    String getName();

    double getCalories();

    double getFat();

    double getProtein();

    double getCarb();

    double getQuantity();

    void setQuantity(double quantity);

    Edible getEdible(int i);

    void addEdible(Edible child, double quantity);

    void removeEdible(Edible child);

    List<Edible> getEdibles();

    public Edible clone() throws CloneNotSupportedException;
}
