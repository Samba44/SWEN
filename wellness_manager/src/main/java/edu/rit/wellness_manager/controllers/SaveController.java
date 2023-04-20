package edu.rit.wellness_manager.controllers;

import edu.rit.wellness_manager.models.Edible;
import edu.rit.wellness_manager.models.Exercise;
import edu.rit.wellness_manager.models.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SaveController implements FileController{


    public void saveEdible(List<Edible>ediblesToSave){
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(foodFileName,true),true);
            for (Edible e : ediblesToSave){
                pw.println(e.toString());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveLog(Map<LocalDate, Log>logs){
        Set<LocalDate> dates = logs.keySet();
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(logFileName,true),true);
            for (LocalDate date : dates){
                pw.println(logs.get(date).toString());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveExercise(List<Exercise>exerciseToSave){
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(exerciseFileName,true),true);
            for (Exercise e : exerciseToSave){
                pw.println(e.toString());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
