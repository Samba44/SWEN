package edu.rit.wellness_manager.controllers;

import edu.rit.wellness_manager.models.Edible;
import edu.rit.wellness_manager.models.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SaveController {

    private final String foodFileName ="foods.csv";
    private final String logFileName ="log.csv";

    public void saveEdible(List<Edible>ediblesToSave){
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(foodFileName),true);
            for (Edible e : ediblesToSave){
                pw.println(e.toString());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveLog(Map<Date, Log>logs){
        Set<Date> dates = logs.keySet();
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(logFileName),true);
            for (Date date : dates){
                pw.println(logs.get(date).toString());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
