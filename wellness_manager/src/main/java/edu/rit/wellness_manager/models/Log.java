package edu.rit.wellness_manager.models;

import java.util.List;

public interface Log {
    double DEFAULT_WEIGHT = 150.0;
    double DEFAULT_CALORIES = 2000.0;
    void addEdible(Edible entry);
    void removeEdible(Edible entry);
    void addExercise(Exercise entry);
    void removeExercise(Exercise entry);
    void setCalLimit(double limit);
    void setWeightLimit(double limit);
    double getCalLimit();
    double getWeightLimit();
    double getTotalCal();

    List<Edible> getEdibles();
    List<Exercise> getExercises();
}
