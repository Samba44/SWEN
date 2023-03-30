package edu.rit.wellness_manager.models;

public interface Log {
    double DEFAULT_WEIGHT = 150.0;
    double DEFAULT_CALORIES = 2000.0;
    void addEdible(Edible entry);
    void removeEdible(Edible entry);
    void setCalLimit(double limit);
    void setWeightLimit(double limit);
    double getCalLimit();
    double getWeightLimit();
    double getTotalCal();
}
