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
                if (split[0].equals("f")) {
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
        } catch (FileNotFoundException e) {
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
