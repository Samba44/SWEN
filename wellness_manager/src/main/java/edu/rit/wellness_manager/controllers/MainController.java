package edu.rit.wellness_manager.controllers;

import edu.rit.wellness_manager.models.DailyLog;
import edu.rit.wellness_manager.models.Edible;
import edu.rit.wellness_manager.models.Exercise;
import edu.rit.wellness_manager.models.Log;

import java.time.LocalDate;
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

    private Map<LocalDate, Log> dailyLogs;

    private List<Exercise> exercises;

    //constructor
    private MainController(){
        edibles = new ArrayList<>();
        dailyLogs = new TreeMap<>();
        exercises = new ArrayList<>();
    }

    public void loadProgram() {
        LoadController loadController = new LoadController();
        loadController.loadExercise(this.exercises);
        loadController.loadFood(this.edibles);
        loadController.loadLog(this.dailyLogs);
    }

    public void addEdible(Edible edible){
        edibles.add(edible);
    }
    public void removeEdible(Edible edible){
        edibles.remove(edible);
    }

    public void addExercise(Exercise exercise){
        exercises.add(exercise);
    }
    public void removeExercise(Exercise exercise){
        exercises.remove(exercise);
    }

    public void addLog(Log log,LocalDate date){
        dailyLogs.put(date,log);
    }
    public void setCalLimit(LocalDate date, double calLimit){
        if (dailyLogs.containsKey(date)){
            dailyLogs.get(date).setCalLimit(calLimit);
        }else{
            DailyLog dailyLog = new DailyLog(date,calLimit,Log.DEFAULT_WEIGHT);
            dailyLogs.put(date,dailyLog);
        }
    }
    public void setWeightLimit(LocalDate date, double weightLimit){
        if (dailyLogs.containsKey(date)){
            dailyLogs.get(date).setWeightLimit(weightLimit);
        }else{
            DailyLog dailyLog = new DailyLog(date,Log.DEFAULT_CALORIES,weightLimit);
            dailyLogs.put(date,dailyLog);
        }

    }
    public double getCalLimit(LocalDate date){
        return dailyLogs.get(date).getCalLimit();
    }
    public double getWeightLimit(LocalDate date){
        return dailyLogs.get(date).getWeightLimit();
    }
    public void addEdibleEntry(LocalDate date,Edible edible, int quantity){
        edible.setQuantity(quantity);
        dailyLogs.get(date).addEdible(edible);
    }
    public void removeEdibleEntry(LocalDate date,Edible edible){
        dailyLogs.get(date).removeEdible(edible);
    }
    public List<Edible> getAllEdibles(){
        return edibles;
    }
    public Log getLogOnDate(LocalDate date){
        if (!dailyLogs.containsKey(date)) {
            dailyLogs.put(date, new DailyLog(date, Log.DEFAULT_CALORIES, Log.DEFAULT_WEIGHT));
        }

        return dailyLogs.get(date);
    }

    public List<Exercise> getAllExercises() {
        return exercises;
    }

    public Map<LocalDate, Log> getAllLogs() {
        return dailyLogs;
    }
}
