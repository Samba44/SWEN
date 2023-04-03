package edu.rit.wellness_manager.view;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.time.*;

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
   
   // Main
   public static void main(String[] args) {
      launch(args);
   }
   
   // Event Listener
   public void handle(ActionEvent ae) {
      String selection = ((MenuItem)ae.getSource()).getText();
      switch(selection) {
         case "Save":
            // Method
            break;
         case "New Food":
            // Method
            break;
         case "New Recipe":
            // Method
            break;
         case "New Log":
            // Method
            break;
         case "Set Calorie Limit":
            // Method
            break;
          case "Set Weight Limit":
            // Method
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
      menu.getItems().addAll(mi1, mi2, mi3, mi4, mi5, mi6, mi7);
      menuBar.getMenus().add(menu);
      
      mi1.setOnAction(this);
      mi2.setOnAction(this);
      mi3.setOnAction(this);
      mi4.setOnAction(this);
      mi5.setOnAction(this);
      mi6.setOnAction(this);
      mi7.setOnAction(this);
      
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
      
      root.getChildren().addAll(menuBar, gpMain);
      
      scene = new Scene(root, 500, 600);
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();  
   }
}