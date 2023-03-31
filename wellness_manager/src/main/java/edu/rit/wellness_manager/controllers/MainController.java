package edu.rit.wellness_manager.controllers;

import edu.rit.wellness_manager.models.Edible;
import edu.rit.wellness_manager.models.Log;

import java.util.*;

public class MainController {
    public static MainController instance = null;
    public static MainController getInstance(){
        if (instance == null){
            instance = new MainController();
        }
        return instance;
    }
    private List<Edible> edibles;

    private Map<Date, Log> dailyLogs;

    //constructor
    private MainController(){
        edibles = new ArrayList<>();
        dailyLogs = new TreeMap<>();
    }
    public void addEdible(Edible edible){
        edibles.add(edible);
    }
    public void removeEdible(Edible edible){
        edibles.remove(edible);
    }
    public void addLog(Log log,Date date){
        dailyLogs.put(date,log);
    }
    public void setCalLimit(Date date, double calLimit){
        dailyLogs.get(date).setCalLimit(calLimit);
    }
    public void setWeightLimit(Date date, double weightLimit){
        dailyLogs.get(date).setWeightLimit(weightLimit);
    }
    public double getCalLimit(Date date){
        return dailyLogs.get(date).getCalLimit();
    }
    public double getWeightLimit(Date date){
        return dailyLogs.get(date).getWeightLimit();
    }
    public void addEdibleEntry(Date date,Edible edible, int quantity){
        edible.setQuantity(quantity);
        dailyLogs.get(date).addEdible(edible);
    }
    public void removeEdibleEntry(Date date,Edible edible){
        dailyLogs.get(date).removeEdible(edible);
    }
    public List<Edible> getAllEdibles(){
        return edibles;
    }
    public Log getLogOnDate(Date date){
        return dailyLogs.get(date);
    }
}
