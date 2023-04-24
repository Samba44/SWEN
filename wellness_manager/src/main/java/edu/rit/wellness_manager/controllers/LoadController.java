package edu.rit.wellness_manager.controllers;

import edu.rit.wellness_manager.models.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LoadController implements FileController{

    public void loadFood(List<Edible>edibles){
        try (Scanner scan = new Scanner(new FileInputStream(foodFileName))) {
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                if (s.isEmpty()) {
                    continue;
                }
                String[] split = s.split(",");
                if (split[0].equals("b")) {
                    Food food = new Food(split[1], Double.parseDouble(split[2]), Double.parseDouble(split[3]), Double.parseDouble(split[4]), Double.parseDouble(split[5]));
                    edibles.add(food);
                } else {
                    String name = split[1];
                    List<Edible> list = new ArrayList<>();
                    for (int i = 2; i < split.length; i += 2) {
                        for (Edible var : edibles) {
                            if (var.getName().equals(split[i])) {
                                Edible child = null;
                                try {
                                    child = var.clone();
                                    child.setQuantity(Double.parseDouble(split[i + 1]));
                                    list.add(child);
                                    break;
                                } catch (CloneNotSupportedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    edibles.add(new Recipe(name, list, 1));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadLog(Map<LocalDate, Log> logs){
        try (Scanner scan = new Scanner(new FileInputStream(logFileName))) {
            // TODO loadLog
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                if (s.isEmpty()) {
                    continue;
                }
                String[] log = s.split(",");
                int year = Integer.parseInt(log[0]);
                int month = Integer.parseInt(log[1]);
                int day = Integer.parseInt(log[2]);
                LocalDate date = LocalDate.of(year, month, day);
                String type = log[3];
                if (type.equalsIgnoreCase("c")) {
                    double calLimit = Double.parseDouble(log[4]);
                    if (logs.containsKey(date)) {
                        logs.get(date).setCalLimit(calLimit);
                    } else {
                        Log newLog = new DailyLog(date, calLimit, Log.DEFAULT_WEIGHT);
                        logs.put(date, newLog);
                    }
                } else if (type.equalsIgnoreCase("w")) {
                    double weightLimit = Double.parseDouble(log[4]);
                    if (logs.containsKey(date)) {
                        logs.get(date).setWeightLimit(weightLimit);
                    } else {
                        Log newLog = new DailyLog(date, Log.DEFAULT_CALORIES, weightLimit);
                        logs.put(date, newLog);
                    }
                } else if (type.equalsIgnoreCase("f")) {
                    String edibleName = log[4];
                    double quantity = Double.parseDouble(log[5]);

                    if (!logs.containsKey(date)) {
                        Log newLog = new DailyLog(date, Log.DEFAULT_CALORIES, Log.DEFAULT_WEIGHT);
                        logs.put(date, newLog);
                    }

                    for (Edible edible : MainController.instance.getAllEdibles()) {
                        if (edible.getName().equalsIgnoreCase(edibleName.trim())) {
                            Edible toAdd = edible.clone();
                            toAdd.setQuantity(quantity);
                            logs.get(date).addEdible(toAdd);
                        }
                    }
                } else if (type.equalsIgnoreCase("e")) {
                    String exerciseName = log[4];
                    double time = Double.parseDouble(log[5]);

                    if (!logs.containsKey(date)) {
                        Log newLog = new DailyLog(date, Log.DEFAULT_CALORIES, Log.DEFAULT_WEIGHT);
                        logs.put(date, newLog);
                    }

                    for (Exercise exercise : MainController.instance.getAllExercises()) {
                        if (exercise.getName().equalsIgnoreCase(exerciseName.trim())) {
                            logs.get(date).addExercise(exercise);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    //loadExercise
    public void loadExercise(List<Exercise>exercises){
        try (Scanner scan = new Scanner(new FileInputStream(exerciseFileName))) {
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                if (s.isEmpty()) {
                    continue;
                }
                String[] split = s.split(",");
                Exercise exercise = new Exercise(split[1], Double.parseDouble(split[2]));
                exercises.add(exercise);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
