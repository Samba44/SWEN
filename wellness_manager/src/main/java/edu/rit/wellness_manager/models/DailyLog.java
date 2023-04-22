package edu.rit.wellness_manager.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DailyLog implements Log{

    private final LocalDate date;
    private double calLimit;
    private double weightLimit;
    private List<Edible>edibles;
    private List<Exercise>exercises;

    //constructor
    public DailyLog(LocalDate date, double calLimit, double weightLimit) {
        this.date = date;
        this.calLimit = calLimit;
        this.weightLimit = weightLimit;
        this.edibles = new ArrayList<>();
        this.exercises = new ArrayList<>();
    }

    public void addEdible(Edible entry) {
        edibles.add(entry);
    }

    public void removeEdible(Edible entry) {
        edibles.remove(entry);
    }

    public void addExercise(Exercise entry) {
        exercises.add(entry);
    }

    public void removeExercise(Exercise entry) {
        exercises.remove(entry);
    }

    public void setCalLimit(double limit) {
        calLimit = limit;
    }

    public void setWeightLimit(double limit) {
        weightLimit = limit;
    }

    public double getCalLimit() {
        return calLimit;
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    public List<Edible> getEdibles() {
        return edibles;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public double getTotalCal() {
        double totalCalories = 0;
        for (Edible e : edibles){
            totalCalories += e.getCalories();
        }
        for (Exercise e : exercises){
            totalCalories -= e.getCalories();
        }
        return totalCalories;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d,%d,%d,w,%.2f\n", date.getYear(), date.getMonthValue(), date.getDayOfMonth(), weightLimit));
        sb.append(String.format("%d,%d,%d,c,%.2f\n", date.getYear(), date.getMonthValue(), date.getDayOfMonth(), calLimit));
        for (Edible var : edibles) {
            sb.append(String.format("%d,%d,%d,f,%s,%.2f\n", date.getYear(), date.getMonthValue(), date.getDayOfMonth(), var.getName(), var.getQuantity()));
        }
        return sb.toString();
    }

}
