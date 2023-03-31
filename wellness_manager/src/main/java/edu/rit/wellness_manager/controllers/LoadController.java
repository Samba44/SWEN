package edu.rit.wellness_manager.controllers;

import edu.rit.wellness_manager.models.Edible;
import edu.rit.wellness_manager.models.Food;
import edu.rit.wellness_manager.models.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadController implements FileController{

    public void loadFood(List<Edible>edibles){
        Scanner scan = new Scanner(foodFileName);
        while(scan.hasNextLine()){
            String s = scan.nextLine();
            String[] split = s.split(",");
            if (split[0].equals("f")){
                Food food = new Food(split[1],Double.parseDouble(split[2]),Double.parseDouble(split[3]),Double.parseDouble(split[4]),Double.parseDouble(split[5]));
                edibles.add(food);
            }else {
                String name = split[1];
                List<Edible> list = new ArrayList<>();
                for (int i = 2; i < split.length; i += 2) {
                    for (Edible var : edibles) {
                        if (var.getName().equals(split[i])) {
                            Edible child = null;
                            try {
                                child = var.clone();
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                            child.setQuantity(Double.parseDouble(split[i + 1]));
                            list.add(child);
                            break;
                        }
                    }
                }
                edibles.add(new Recipe(name, list, 1));
            }
        }
    }

    public void loadLog(){
        Scanner scan = new Scanner(logFileName);
    }
}
