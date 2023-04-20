package edu.rit.wellness_manager.view;

import edu.rit.wellness_manager.controllers.MainController;
import edu.rit.wellness_manager.controllers.SaveController;
import edu.rit.wellness_manager.models.Exercise;
import edu.rit.wellness_manager.models.Food;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.time.LocalDate;
import java.util.Optional;

public class GUI extends Application implements EventHandler<ActionEvent> {
   // Window
   private Stage stage;
   private Scene scene;
   private VBox root;
   
   // Menu, Menu Items and Menu Bar
   private Menu menu = new Menu("Menu");
   
   private MenuItem mi1 = new MenuItem("Save");
   private MenuItem mi2 = new MenuItem("New Food");
   private MenuItem mi3 = new MenuItem("New Recipe");
   private MenuItem mi4 = new MenuItem("New Log");
   private MenuItem mi8 = new MenuItem("New Exercise");
   private MenuItem mi5 = new MenuItem("Set Calorie Limit");
   private MenuItem mi6 = new MenuItem("Set Weight Limit");
   private MenuItem mi7 = new MenuItem("Exit");
   
   private MenuBar menuBar = new MenuBar();
   
   // Info Labels and Text Boxes
   private Label lblCalorieLimit = new Label("Calorie Limit:");
   private TextField tfCalorieLimit = new TextField();
   
   private Label lblWeightLimit = new Label("Weight Limit:");
   private TextField tfWeightLimit = new TextField();
   
   private Label lblTotalCalories = new Label("Total Calories:");
   private TextField tfTotalCalories = new TextField();
   
   private Label lblFat = new Label("Fat:");
   private TextField tfFat = new TextField();
   
   private Label lblCarbs = new Label("Carbs:");
   private TextField tfCarbs = new TextField();
   
   private Label lblProtein = new Label("Protein:");
   private TextField tfProtein = new TextField();
   
   // List Text Area
   private TextArea taList = new TextArea();
   
   // Date Picker
   private DatePicker datePicker = new DatePicker();

   //text inputs
   TextInputDialog tidNewFood = new TextInputDialog("Enter new food.");
   TextInputDialog tidNewExercise = new TextInputDialog("Enter new exercise.");
   TextInputDialog tidNewCalLimit = new TextInputDialog("Enter calorie limit.");
   TextInputDialog tidNewWeightLimit = new TextInputDialog("Enter weight limit.");

   // Main
   public static void main(String[] args) {
      launch(args);
   }
   
   // Event Listener
   public void handle(ActionEvent ae) {
      String selection = ((MenuItem)ae.getSource()).getText();
      MainController mainController = MainController.getInstance();
      switch(selection) {
         case "Save":
            SaveController saveController = new SaveController();
            saveController.saveEdible(mainController.getAllEdibles());
            saveController.saveExercise(mainController.getAllExercises());
            saveController.saveLog(mainController.getAllLogs());
            //TODO dailyLog
            break;
         case "New Food":
            Optional<String> s2 = tidNewFood.showAndWait();
            if(s2.isPresent()) {
               String foodString = tidNewFood.getEditor().getText();
               String[] split = foodString.split(",");
               Food food = new Food(split[0], Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), Double.parseDouble(split[4]));
               mainController.addEdible(food);
            }
            break;
         case "New Recipe":
            // Method
            break;
         case "New Exercise":
            Optional<String> s3 = tidNewExercise.showAndWait();
            if(s3.isPresent()) {
               String exerciseString = tidNewExercise.getEditor().getText();
               String[] split2 = exerciseString.split(",");
               Exercise exercise = new Exercise(split2[0], Double.parseDouble(split2[1]));
               mainController.addExercise(exercise);
            }
            break;
         case "New Log":
            // Method
            break;
         case "Set Calorie Limit":
            LocalDate date = datePicker.getValue();
            Optional<String> s = tidNewCalLimit.showAndWait();
            if (s.isPresent()){
               String newCalLimit = tidNewCalLimit.getEditor().getText();
               mainController.setCalLimit(date,Double.parseDouble(newCalLimit));
               tfCalorieLimit.setText(newCalLimit);
            }
            break;
          case "Set Weight Limit":
             LocalDate date2 = datePicker.getValue();
             Optional<String> s1 = tidNewWeightLimit.showAndWait();
             if(s1.isPresent()) {
                String newWeightLimit = tidNewWeightLimit.getEditor().getText();
                mainController.setWeightLimit(date2, Double.parseDouble(newWeightLimit));
                tfWeightLimit.setText(newWeightLimit);
             }
            break;
          case "Exit":
            System.exit(0);
            break;
      }
   }

   // Start
   public void start(Stage _stage) {
      stage = _stage;
      stage.setTitle("Food Activity");
      stage.setOnCloseRequest(
         new EventHandler<WindowEvent>() {
            public void handle(WindowEvent evt) { System.exit(0); }
         } );
      root = new VBox();
      
      // Set up Menu
      menu.getItems().addAll(mi1, mi2, mi3, mi4, mi8, mi5, mi6, mi7);
      menuBar.getMenus().add(menu);
      
      mi1.setOnAction(this);
      mi2.setOnAction(this);
      mi3.setOnAction(this);
      mi4.setOnAction(this);
      mi5.setOnAction(this);
      mi6.setOnAction(this);
      mi7.setOnAction(this);
      mi8.setOnAction(this);
      
      // Set up List and Date Picker
      taList.setWrapText(true);
      taList.setPrefWidth(200);
      taList.setPrefHeight(500);
      
      GridPane gpList = new GridPane();
      gpList.setMinSize(200, 300);
      gpList.setPadding(new Insets(10, 10, 10, 10));
      gpList.setHgap(5);
      gpList.setVgap(5);
      gpList.setAlignment(Pos.CENTER);

      datePicker.setValue(LocalDate.now());
      gpList.add(datePicker, 0, 0);
      gpList.add(taList, 0, 1);
      
      // Set up food/recipe Information
      GridPane gpInfo = new GridPane();
      gpInfo.setPadding(new Insets(10, 10, 10, 10));
      gpInfo.setHgap(5);
      gpInfo.setVgap(5);
      gpInfo.setAlignment(Pos.CENTER);
      
      gpInfo.add(lblCalorieLimit, 0, 0);
      gpInfo.add(tfCalorieLimit, 1, 0);
      tfCalorieLimit.setDisable(true);
      
      gpInfo.add(lblWeightLimit, 0, 1);
      gpInfo.add(tfWeightLimit, 1, 1);
      tfWeightLimit.setDisable(true);
      
      gpInfo.add(lblTotalCalories, 0, 2);
      gpInfo.add(tfTotalCalories, 1, 2);
      tfTotalCalories.setDisable(true);
      
      gpInfo.add(lblFat, 0, 3);
      gpInfo.add(tfFat, 1, 3);
      tfFat.setDisable(true);
      
      gpInfo.add(lblCarbs, 0, 4);
      gpInfo.add(tfCarbs, 1, 4);
      tfCarbs.setDisable(true);
      
      gpInfo.add(lblProtein, 0, 5);
      gpInfo.add(tfProtein, 1, 5);
      tfProtein.setDisable(true);
      
      // Setup Root
      GridPane gpMain = new GridPane();
      gpMain.add(gpList, 0, 0);
      gpMain.add(gpInfo, 1, 0);

      tidNewFood.setHeaderText("Enter new food in format:\n name,calories,fat,carb,protein");
      tidNewFood.setContentText("New food: ");

      tidNewExercise.setHeaderText("Enter new exercise in format:\n name,calories");
      tidNewExercise.setContentText("New exercise: ");

      tidNewCalLimit.setContentText("New Calorie Limit: ");

      tidNewWeightLimit.setContentText("New Weight Limit: ");

      root.getChildren().addAll(menuBar, gpMain);
      
      scene = new Scene(root, 700, 600);
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();  
   }
}