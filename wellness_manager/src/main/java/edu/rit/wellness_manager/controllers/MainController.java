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
}
