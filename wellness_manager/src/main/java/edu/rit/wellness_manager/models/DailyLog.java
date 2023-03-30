package edu.rit.wellness_manager.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyLog implements Log{

    private final Date date;
    private double calLimit;
    private double weightLimit;
    private List<Edible>edibles;

    //constructor
    public DailyLog(Date date, double calLimit, double weightLimit) {
        this.date = date;
        this.calLimit = calLimit;
        this.weightLimit = weightLimit;
        this.edibles = new ArrayList<>();
    }

    public void addEdible(Edible entry) {
        edibles.add(entry);
    }

    public void removeEdible(Edible entry) {
        edibles.remove(entry);
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

    public double getTotalCal() {
        double totalCalories = 0;
        for (Edible e : edibles){
            totalCalories += e.getCalories();
        }
        return totalCalories;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d,%d,%d,w,%.2f\n", date.getYear(), date.getMonth(), date.getDay(), weightLimit));
        sb.append(String.format("%d,%d,%d,c,%.2f\n", date.getYear(), date.getMonth(), date.getDay(), calLimit));
        for (Edible var : edibles) {
            sb.append(String.format("%d,%d,%d,f,%s,%.2f\n", date.getYear(), date.getMonth(), date.getDay(), var.getName(), var.getQuantity()));
        }
        return sb.toString();
    }

}
