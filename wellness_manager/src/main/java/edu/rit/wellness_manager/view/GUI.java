package edu.rit.wellness_manager.view;

import edu.rit.wellness_manager.controllers.MainController;
import edu.rit.wellness_manager.controllers.SaveController;
import edu.rit.wellness_manager.models.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

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
   private Label tfCalorieLimit = new Label();
   
   private Label lblWeightLimit = new Label("Weight Limit:");
   private Label tfWeightLimit = new Label();
   
   private Label lblTotalCalories = new Label("Total Calories:");
   private Label tfTotalCalories = new Label();
   
   private Label lblFat = new Label("Fat:");
   private Label tfFat = new Label();
   
   private Label lblCarbs = new Label("Carbs:");
   private Label tfCarbs = new Label();
   
   private Label lblProtein = new Label("Protein:");
   private Label tfProtein = new Label();
   
   // List Text Area
   private TextArea taList = new TextArea();
   
   // Date Picker
   private DatePicker datePicker = new DatePicker();

   private ListView<Edible> edibleListView;
   private ListView<Exercise> exerciseListView;

   //text inputs
   TextInputDialog tidNewFood = new TextInputDialog("Enter new food.");
   TextInputDialog tidNewExercise = new TextInputDialog("Enter new exercise.");
   TextInputDialog tidNewCalLimit = new TextInputDialog("Enter calorie limit.");
   TextInputDialog tidNewWeightLimit = new TextInputDialog("Enter weight limit.");
   TextInputDialog tidAddNewLog = new TextInputDialog("Enter new log.");
   TextInputDialog tidNewRecipe = new TextInputDialog("Enter new recipe.");

   // Main
   public static void main(String[] args) {
      launch(args);
   }

   private void changeList() {
      LocalDate date = datePicker.getValue();
      Log log = MainController.getInstance().getLogOnDate(date);
      edibleListView.setItems(FXCollections.observableList(log.getEdibles()));
      exerciseListView.setItems(FXCollections.observableList(log.getExercises()));
   }

   private void updateNutrients() {
      LocalDate date = datePicker.getValue();
      Log dailyLog = MainController.getInstance().getLogOnDate(date);
      tfTotalCalories.setText(String.format("%.2f", dailyLog.getTotalCal()));
      double totalFat = 0;
      double totalCarb = 0;
      double totalProtein = 0;

      for (Edible edible : dailyLog.getEdibles()) {
         totalFat += edible.getFat();
         totalCarb += edible.getCarb();
         totalProtein += edible.getProtein();
      }

      double nutrients = totalCarb + totalFat + totalProtein;
      if (nutrients != 0) {
         tfCarbs.setText(String.format("%.2f", totalCarb / nutrients));
         tfFat.setText(String.format("%.2f", totalFat / nutrients));
         tfProtein.setText(String.format("%.2f", totalProtein / nutrients));
      } else {
         tfCarbs.setText(String.format("%.2f", 0.00));
         tfFat.setText(String.format("%.2f", 0.00));
         tfProtein.setText(String.format("%.2f", 0.00));
      }

      tfWeightLimit.setText(String.format("%.2f", dailyLog.getWeightLimit()));
      tfCalorieLimit.setText(String.format("%.2f", dailyLog.getCalLimit()));
   }
   
   // Event Listener
   public void handle(ActionEvent ae) {
      String selection = ((MenuItem) ae.getSource()).getText();
      MainController mainController = MainController.getInstance();
      switch (selection) {
         case "Save":
            SaveController saveController = new SaveController();
            saveController.saveEdible(mainController.getAllEdibles());
            saveController.saveExercise(mainController.getAllExercises());
            saveController.saveLog(mainController.getAllLogs());
            break;
         case "New Food":
            Optional<String> s2 = tidNewFood.showAndWait();
            if (s2.isPresent()) { // Optional is present if user clicks OK
               String foodString = tidNewFood.getEditor().getText();
               String[] split = foodString.split(",");
               Food food = new Food(split[0], Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), Double.parseDouble(split[4]));
               mainController.addEdible(food);
            }
            break;
         case "New Recipe":
            Optional<String> s5 = tidNewRecipe.showAndWait();
            if (s5.isPresent()) {
               String recipeString = tidNewRecipe.getEditor().getText();
               String[] splitRecipe = recipeString.split(",");
               String recipeName = splitRecipe[0];
               Recipe recipe = new Recipe(recipeName);
               for (int i = 1; i < splitRecipe.length; i+=2) {
                  String edibleName = splitRecipe[i];
                  double edibleQuantity = Double.parseDouble(splitRecipe[i + 1]);

                  for (Edible edible: mainController.getAllEdibles()) {
                     if (edible.getName().equalsIgnoreCase(edibleName.trim())) {
                        try {
                           recipe.addEdible(edible.clone(), edibleQuantity);
                        } catch (CloneNotSupportedException e) {
                           throw new RuntimeException(e);
                        }
                     }
                  }
               }
            }
            break;
         case "New Exercise":
            Optional<String> s3 = tidNewExercise.showAndWait();
            if (s3.isPresent()) {
               String exerciseString = tidNewExercise.getEditor().getText();
               String[] split2 = exerciseString.split(",");
               Exercise exercise = new Exercise(split2[0], Double.parseDouble(split2[1]));
               mainController.addExercise(exercise);
            }
            break;
         case "New Log":
            Optional<String> s4 = tidAddNewLog.showAndWait();
            if (s4.isPresent()) {
               String newLog = tidAddNewLog.getEditor().getText();
               String[] split3 = newLog.split(",");
               LocalDate logDate = datePicker.getValue();
               boolean notFound = true;
               for (Edible edible : mainController.getAllEdibles()) {
                  if (edible.getName().equalsIgnoreCase(split3[0].trim())) {
                     try {
                        Edible toAdd = edible.clone();
                        toAdd.setQuantity(Double.parseDouble(split3[1]));
                        mainController.getLogOnDate(logDate).addEdible(toAdd);
                        notFound = false;
                        changeList();
                     } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                     }
                  }
               }

               for (Exercise exercise: mainController.getAllExercises()) {
                  if (exercise.getName().equalsIgnoreCase(split3[0].trim())){
                     // TODO exercise time
                     mainController.getLogOnDate(logDate).addExercise(exercise);
                     notFound = false;
                  }
               }

               if(notFound){
                  Alert notFoundAlert = new Alert(Alert.AlertType.ERROR);
                  notFoundAlert.setHeaderText("Food or exercise does not exist, please try again.");
                  notFoundAlert.showAndWait();
               }

               Platform.runLater(new Runnable() {
                  @Override
                  public void run() {
                     updateNutrients();
                     changeList();
                  }
               });
            }
            break;
         case "Set Calorie Limit":
            LocalDate date = datePicker.getValue();
            Optional<String> s = tidNewCalLimit.showAndWait();
            if (s.isPresent()) {
               String newCalLimit = tidNewCalLimit.getEditor().getText();
               mainController.setCalLimit(date, Double.parseDouble(newCalLimit));
               Platform.runLater(new Runnable() {
                  @Override
                  public void run() {
                     tfCalorieLimit.setText(newCalLimit);
                  }
               });
            }
            break;
         case "Set Weight Limit":
            LocalDate date2 = datePicker.getValue();
            Optional<String> s1 = tidNewWeightLimit.showAndWait();
            if (s1.isPresent()) {
               String newWeightLimit = tidNewWeightLimit.getEditor().getText();
               mainController.setWeightLimit(date2, Double.parseDouble(newWeightLimit));
               Platform.runLater(new Runnable() {
                  @Override
                  public void run() {
                     tfWeightLimit.setText(newWeightLimit);
                  }
               });

            }
            break;
         case "Exit":
            System.exit(0);
            break;
      }
   }

   // Start
   public void start(Stage _stage) {
      MainController.getInstance().loadProgram();

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

      GridPane gpList = new GridPane();
      gpList.setMinSize(200, 300);
      gpList.setPadding(new Insets(10, 10, 10, 10));
      gpList.setHgap(5);
      gpList.setVgap(5);
      gpList.setAlignment(Pos.CENTER);

      datePicker.setValue(LocalDate.now());
      gpList.add(datePicker, 0, 0);
      edibleListView = new ListView<>(FXCollections.observableList(MainController.getInstance().getLogOnDate(datePicker.getValue()).getEdibles()));
      exerciseListView = new ListView<>(FXCollections.observableList(MainController.getInstance().getLogOnDate(datePicker.getValue()).getExercises()));

      edibleListView.setCellFactory(
              new Callback<ListView<Edible>, ListCell<Edible>>() {
                 @Override
                 public ListCell<Edible> call(ListView<Edible> listView) {
                    return new EdibleListCell();
                 }
              });
      edibleListView.setPrefSize(300, 400);

      exerciseListView.setCellFactory(new Callback<ListView<Exercise>, ListCell<Exercise>>() {
         public ListCell<Exercise> call(ListView<Exercise> listView) {
            return new ExerciseListCell();
         }
      });

      gpList.add(edibleListView,1,0);
      gpList.add(exerciseListView,2,0);

      datePicker.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent actionEvent) {
            Platform.runLater(new Runnable() {
               @Override
               public void run() {
                  changeList();
                  updateNutrients();
               }
            });
         }
      });

      Platform.runLater(new Runnable() {
         @Override
         public void run() {
            updateNutrients();
         }
      });
      
      // Set up food/recipe Information
      GridPane gpInfo = new GridPane();
      gpInfo.setPadding(new Insets(10, 10, 10, 10));
      gpInfo.setHgap(5);
      gpInfo.setVgap(5);
      gpInfo.setAlignment(Pos.CENTER);
      
      gpInfo.add(lblCalorieLimit, 0, 0);
      gpInfo.add(tfCalorieLimit, 1, 0);
      
      gpInfo.add(lblWeightLimit, 0, 1);
      gpInfo.add(tfWeightLimit, 1, 1);
      
      gpInfo.add(lblTotalCalories, 0, 2);
      gpInfo.add(tfTotalCalories, 1, 2);
      
      gpInfo.add(lblFat, 0, 3);
      gpInfo.add(tfFat, 1, 3);
      
      gpInfo.add(lblCarbs, 0, 4);
      gpInfo.add(tfCarbs, 1, 4);
      
      gpInfo.add(lblProtein, 0, 5);
      gpInfo.add(tfProtein, 1, 5);
      
      // Setup Root
      GridPane gpMain = new GridPane();
      gpMain.add(gpList, 0, 0);
      gpMain.add(gpInfo, 0, 1);

      tidNewFood.setHeaderText("Enter new food in format:\n name,calories,fat,carb,protein");
      tidNewFood.setContentText("New food: ");

      tidNewExercise.setHeaderText("Enter new exercise in format:\n name,calories");
      tidNewExercise.setContentText("New exercise: ");

      tidNewCalLimit.setContentText("New Calorie Limit: ");

      tidNewWeightLimit.setContentText("New Weight Limit: ");

      tidAddNewLog.setHeaderText("Enter new log, for food or exercise and amount");
      tidNewExercise.setContentText("New log: ");

      tidNewRecipe.setHeaderText("Enter new recipe");
      tidNewRecipe.setContentText("New recipe: ");

      root.getChildren().addAll(menuBar, gpMain);
      
      scene = new Scene(root, 700, 600);
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();  
   }

   // Code from https://stackoverflow.com/questions/27438629/listview-with-custom-content-in-javafx
   private class EdibleListCell extends ListCell<Edible> {
      private HBox content;
      private Text name;
      private Text quantity;

      public EdibleListCell() {
         super();
         name = new Text();
         quantity = new Text();
         content = new HBox(name, quantity);
         content.setSpacing(10);
      }

      @Override
      protected void updateItem(Edible edible, boolean empty) {
         super.updateItem(edible, empty);
         if (edible != null && !empty) {
            name.setText(edible.getName());
            quantity.setText(String.format("%.2f", edible.getQuantity()));
            setGraphic(content);
         } else {
            setGraphic(null);
         }
      }
   }

   private class ExerciseListCell extends ListCell<Exercise> {
      private HBox content;
      private Text name;
      private Text quantity;

      public ExerciseListCell() {
         super();
         name = new Text();
         quantity = new Text();
         content = new HBox(name, quantity);
         content.setSpacing(10);
      }

      @Override
      protected void updateItem(Exercise exercise, boolean empty) {
         super.updateItem(exercise, empty);
         if (exercise != null && !empty) {
            name.setText(exercise.getName());
            // quantity.setText(String.format("%.2f", exercise.getQuantity()));
            setGraphic(content);
         } else {
            setGraphic(null);
         }
      }
   }
}