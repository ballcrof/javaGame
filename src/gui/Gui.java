package username;

import java.io.File;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import java.util.ArrayList;


public class Gui<toReturn> extends Application {
    /* Even if it is a GUI it is useful to have instance variables
    so that you can break the processing up into smaller methods that have
    one responsibility.
     */
    private Controller theController;
    private BorderPane root;  //the root element of this GUI
    private Popup descriptionPane;
    private Stage primaryStage;  //The stage that is passed in on initialization

    /*a call to start replaces a call to the constructor for a JavaFX GUI*/
    @Override
    public void start(Stage assignedStage) {
        /*Initializing instance variables */
        theController = new Controller(this);
        primaryStage = assignedStage;
        /*Border Panes have  top, left, right, center and bottom sections */
        root = setUpRoot();
        descriptionPane = createPopUp(400, 400, "Example Description of something");
        Scene scene = new Scene(root, 1000, 500);
        primaryStage.setTitle("D&D Level Generator");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private BorderPane setUpRoot() {
        BorderPane temp = new BorderPane();

        /*Node top = setTopButtonPanel();
        temp.setTop(top);*/

        Node left = setLeftButtonPanel();  //separate method for the left section
        temp.setLeft(left);

        //TilePane room = createTilePanel();
        GridPane room = new ChamberView(1,1);
        temp.setCenter(room);

        ArrayList<Door> tempMap = new ArrayList<>();
        Node right = setRightButtonPanel(tempMap);
        temp.setRight(right);

        Node centre = setCentreDescription("");
        temp.setCenter(centre);

        Node bottom = setBottomDescription(0);
        temp.setBottom(bottom);

        return temp;
    }

    private Node setBottomDescription(int q){
      HBox temp = new HBox();
      Button button = new Button("Edit");
      if(q != 0){
        button.setOnAction(e -> {
          Chamber returnVal = popupWindow(q);
          theController.changedChambercontents(returnVal, q);
        });
      }
      temp.getChildren().add(button);
      return temp;
    }

    private Node setBottomPassage(int chamber, int passage){
      HBox temp = new HBox();
      Button button = new Button("Edit");
      button.setOnAction(e -> {
        Passage returnVal = popupWindowPassage(chamber, passage);
        theController.changedPassagecontents(returnVal, chamber, passage);
      });
      temp.getChildren().add(button);
      return temp;
    }

    public Passage popupWindowPassage(int chamber, int passage){
      Stage window = new Stage();
      HBox grid = new HBox();

      Button addM = new Button("Add Monster");
      addM.setOnAction(e -> {
        theController.changeMonsterP(chamber, passage -1 );
        root.setCenter(setCentreDescription(theController.getPassageDescription(passage, chamber)));
        window.close();
      });
      grid.getChildren().add(addM);

      Button removeM = new Button("Remove Monsters");
      removeM.setOnAction(e -> {
        theController.removeMonsterP(chamber, passage-1);
        root.setCenter(setCentreDescription(theController.getPassageDescription(passage, chamber)));
        window.close();
      });
      grid.getChildren().add(removeM);

      Button addT = new Button("Add Treasure");
      addT.setOnAction(e -> {
        theController.changeTreasureP(chamber, passage-1);
        root.setCenter(setCentreDescription(theController.getPassageDescription(passage , chamber)));
        window.close();
      });
      grid.getChildren().add(addT);

      Button removeT = new Button("Remove Treasure");
      removeT.setOnAction(e -> {
        theController.removeTreasureP(chamber, passage-1);
        root.setCenter(setCentreDescription(theController.getPassageDescription(passage, chamber)));
        window.close();
      });
      grid.getChildren().add(removeT);

      Button close = new Button("Close Window");
      close.setOnAction(e ->{
        window.close();
      });
      grid.getChildren().add(close);

      Scene scene2 = new Scene(grid);
      window.setScene(scene2);
      window.showAndWait();

      return theController.returnPass(chamber, passage);
    }

    public Chamber popupWindow(int q){
      Stage window = new Stage();
      HBox grid = new HBox();

      Button addM = new Button("Add Monster");
      addM.setOnAction(e -> {
        theController.changeMonster(q);
        root.setCenter(setCentreDescription(theController.getNewDescription(q)));
        window.close();
      });
      grid.getChildren().add(addM);

      Button removeM = new Button("Remove Monsters");
      removeM.setOnAction(e -> {
        theController.removeMonster(q);
        root.setCenter(setCentreDescription(theController.getNewDescription(q)));
        window.close();
      });
      grid.getChildren().add(removeM);

      Button addT = new Button("Add Treasure");
      addT.setOnAction(e -> {
        theController.changeTreasure(q);
        root.setCenter(setCentreDescription(theController.getNewDescription(q)));
        window.close();
      });
      grid.getChildren().add(addT);

      Button removeT = new Button("Remove Treasure");
      removeT.setOnAction(e -> {
        theController.removeTreasure(q);
        root.setCenter(setCentreDescription(theController.getNewDescription(q)));
        window.close();
      });
      grid.getChildren().add(removeT);

      Button close = new Button("Close Window");
      close.setOnAction(e ->{
        window.close();
      });
      grid.getChildren().add(close);

      Scene scene2 = new Scene(grid);
      window.setScene(scene2);
      window.showAndWait();

      return theController.returnCham(q);
    }

    /*private Node setTopButtonPanel(){
      HBox temp = new HBox();
      FileChooser fileChooser = new FileChooser();
      Button button = new Button("Save File");
      button.setOnAction(e -> {
          File selectedFile = fileChooser.showOpenDialog(primaryStage);
      });
      temp.getChildren().add(button);

      FileChooser filesaver = new FileChooser();
      Button button1 = new Button("Load File");
      button.setOnAction(e -> {
          File selectedFile = filesaver.showSaveDialog(primaryStage);
      });
      temp.getChildren().add(button1);

      return temp;
    }*/

    private Node setCentreDescription(String description){
      TextArea textA = new TextArea();
      textA.setStyle("-fx-padding: 10;" +
              "-fx-border-style: solid inside;" +
              "-fx-border-width: 5;" +
              "-fx-border-insets: 5;" +
              "-fx-border-radius: 5;" +
              "-fx-border-color: green;");
      textA.setText(description);
      return textA;
    }

    private Node setRightButtonPanel(ArrayList<Door> doorMap) {
        /*this method should be broken down into even smaller methods, maybe one per button*/
        int val;
        ChoiceBox<String> temp = new ChoiceBox<>();
        temp.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: red;");

        for(int i = 0; i < doorMap.size(); i++){
          temp.getItems().add("Door "+(i + 1));
        }

        temp.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
          if(newValue.contains("1")){
            descriptionPane = createPopUp(400, 400, doorConnection(doorMap.get(0)));
          }
          else if(newValue.contains("2")){
            descriptionPane = createPopUp(400, 400, doorConnection(doorMap.get(1)));
          }
          else if(newValue.contains("3")){
            descriptionPane = createPopUp(400, 400, doorConnection(doorMap.get(2)));
          }
          else if(newValue.contains("4")){
            descriptionPane = createPopUp(400, 400, doorConnection(doorMap.get(3)));
          }
          else if(newValue.contains("5")){
            descriptionPane = createPopUp(400, 400, doorConnection(doorMap.get(4)));
          }
          descriptionPane.show(primaryStage);
        });
        return temp;
    }

    protected String doorConnection(Door doorFromExit){
      return doorFromExit.getDescription();
    }

    private Node setLeftButtonPanel() {
        /*this method should be broken down into even smaller methods, maybe one per button*/
        VBox temp = new VBox();
        int t;
        temp.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
        /*This button listener is an example of a button changing something
        in the controller but nothing happening in the view */

        ArrayList<Door> tempMap = new ArrayList<>();

        Button showButton1 = createButton("Chamber 1", "-fx-background-color: #FFFFFF; ");
        showButton1.setOnAction((ActionEvent event) -> {
            root.setCenter(setCentreDescription(theController.getNewDescription(1)));
            root.setRight(setRightButtonPanel(theController.getDoorsFromChamber(1)));
            root.setBottom(setBottomDescription(1));
        });
        temp.getChildren().add(showButton1);

        for(int i = 0; i < theController.getDoorsFromChamber(1).size(); i++){
        Button button = createButton("Passage " +(i+1), "-fx-background-color: #FFFFFF; ");
          int k = i + 1;
          button.setOnAction((ActionEvent event) -> {
              root.setCenter(setCentreDescription(theController.getPassageDescription(k, 1)));
              root.setBottom(setBottomPassage(1, k));
          });
          temp.getChildren().add(button);
        }

        Button showButton2 = createButton("Chamber 2", "-fx-background-color: #FFFFFF; ");
        showButton2.setOnAction((ActionEvent event) -> {
            root.setCenter(setCentreDescription(theController.getNewDescription(2)));
            root.setRight(setRightButtonPanel(theController.getDoorsFromChamber(2)));
            root.setBottom(setBottomDescription(2));
        });
        temp.getChildren().add(showButton2);

        for(int i = 0; i < theController.getDoorsFromChamber(2).size(); i++){
          Button button = createButton("Passage " +(i+1), "-fx-background-color: #FFFFFF; ");
          int k = i + 1;
          button.setOnAction((ActionEvent event) -> {
              root.setCenter(setCentreDescription(theController.getPassageDescription(k, 2)));
              root.setBottom(setBottomPassage(2, k));
          });
          temp.getChildren().add(button);
        }

        Button showButton3 = createButton("Chamber 3", "-fx-background-color: #FFFFFF; ");
        showButton3.setOnAction((ActionEvent event) -> {
            root.setCenter(setCentreDescription(theController.getNewDescription(3)));
            root.setRight(setRightButtonPanel(theController.getDoorsFromChamber(3)));
            root.setBottom(setBottomDescription(3));
        });
        temp.getChildren().add(showButton3);

        for(int i = 0; i < theController.getDoorsFromChamber(3).size(); i++){
          Button button = createButton("Passage " +(i+1), "-fx-background-color: #FFFFFF; ");
          int k = i + 1;
          button.setOnAction((ActionEvent event) -> {
              root.setCenter(setCentreDescription(theController.getPassageDescription(k, 3)));
              root.setBottom(setBottomPassage(3, k));
          });
          temp.getChildren().add(button);
        }

        Button showButton4 = createButton("Chamber 4", "-fx-background-color: #FFFFFF; ");
        showButton4.setOnAction((ActionEvent event) -> {
            root.setCenter(setCentreDescription(theController.getNewDescription(4)));
            root.setRight(setRightButtonPanel(theController.getDoorsFromChamber(4)));
            root.setBottom(setBottomDescription(4));
        });
        temp.getChildren().add(showButton4);

        for(int i = 0; i < theController.getDoorsFromChamber(4).size(); i++){
          Button button = createButton("Passage " +(i+1), "-fx-background-color: #FFFFFF; ");
          int k = i + 1;
          button.setOnAction((ActionEvent event) -> {
              root.setCenter(setCentreDescription(theController.getPassageDescription(k, 4)));
              root.setBottom(setBottomPassage(4, k));
          });
          temp.getChildren().add(button);
        }

        Button showButton5 = createButton("Chamber 5", "-fx-background-color: #FFFFFF; ");
        showButton5.setOnAction((ActionEvent event) -> {
            root.setCenter(setCentreDescription(theController.getNewDescription(5)));
            root.setRight(setRightButtonPanel(theController.getDoorsFromChamber(5)));
            root.setBottom(setBottomDescription(5));
        });
        temp.getChildren().add(showButton5);

        for(int i = 0; i < theController.getDoorsFromChamber(5).size(); i++){
          Button button = createButton("Passage " +(i+1), "-fx-background-color: #FFFFFF; ");
          int k = i + 1;
          button.setOnAction((ActionEvent event) -> {
              root.setCenter(setCentreDescription(theController.getPassageDescription(k, 5)));
              root.setBottom(setBottomPassage(5, k));
          });
          temp.getChildren().add(button);
        }

        /*this button listener is an example of getting data from the controller
        this also has no real function at the moment but it might later on
        Button hideButton = createButton("Hide Description", "-fx-background-color: #FFFFFF; ");
        hideButton.setOnAction((ActionEvent event) -> {
            descriptionPane.hide();
            changeDescriptionText(theController.getNewDescription(1));
        });
        temp.getChildren().add(hideButton);*/
        return temp;

    }

    /* an example of a popup area that can be set to nearly any
    type of node
     */
    private Popup createPopUp(int x, int y, String text) {
        Popup popup = new Popup();
        popup.setX(x);
        popup.setY(y);
        TextArea textA = new TextArea(text);
        popup.getContent().addAll(textA);
        textA.setStyle(" -fx-background-color: white;");
        textA.setMinWidth(80);
        textA.setMinHeight(50);
        return popup;
    }

    /*generic button creation method ensure that all buttons will have a
    similar style and means that the style only need to be in one place
     */
    private Button createButton(String text, String format) {
        Button btn = new Button();
        btn.setText(text);
        btn.setStyle("");
        return btn;
    }

    private void changeDescriptionText(String text) {
        ObservableList<Node> list = descriptionPane.getContent();
        for (Node t : list) {
            if (t instanceof TextArea) {
                TextArea temp = (TextArea) t;
                temp.setText(text);
            }

        }

    }

//    private GridPane createGridPanel() {
//        GridPane t = new GridPane();
//        /*t.setStyle(
//                "-fx-border-style: solid inside;" +
//                        "-fx-border-width: 2;" +
//                        "-fx-border-insets: 1;" +
//                        "-fx-border-radius: 9;" +
//                        "-fx-border-color: black;");*/
//        Node[] tiles = makeTiles();  //this should be a roomview object
//        t.add(tiles[0],0,0,1,1);
//        t.add(tiles[1],0,1,1,1);
//        t.add(tiles[2],0,2,1,1);
//        t.add(tiles[3],0,3,1,1);
//        t.add(tiles[4],1,0,1,1);
//        t.add(tiles[5],1,1,1,1);
//        t.add(tiles[6],1,2,1,1);
//        t.add(tiles[7],1,3,1,1);
//        t.add(tiles[8],2,0,1,1);
//        t.add(tiles[9],2,1,1,1);
//        t.add(tiles[10],2,2,1,1);
//        t.add(tiles[11],2,3,1,1);
//        t.add(tiles[12],3,0,1,1);
//        t.add(tiles[13],3,1,1,1);
//        t.add(tiles[14],3,2,1,1);
//        t.add(tiles[15],3,3,1,1);
//        //t.setHgap(0);
//        //t.setVgap(0);
//          return t;
//    }

//    private TilePane createTilePanel() {
//        TilePane t = new TilePane();
//        t.setStyle(
//                "-fx-border-style: solid inside;" +
//                        "-fx-border-width: 2;" +
//                        "-fx-border-insets: 1;" +
//                        "-fx-border-radius: 9;" +
//                        "-fx-border-color: black;");
//        Node[] tiles = makeTiles();  //this should be a roomview object
//        int len = tiles.length/4; //hacky way to make a 4x4
//        t.setOrientation(Orientation.HORIZONTAL);
//        t.setTileAlignment(Pos.CENTER_LEFT);
//        t.setHgap(0);
//        t.setVgap(0);
//        t.setPrefColumns(4);
//        t.setMaxWidth(4 *50);  //should be getting the size from the roomview object
//        ObservableList list = t.getChildren();
//        list.addAll(tiles);  //write a method that adds the roomview objects
//        return t;
//    }

//    private Node[] makeTiles() {
//    String floor = "/res/floor.png";
//    String treasure = "/res/tres.png";
//
//        Node[] toReturn = {
//                floorFactory(floor),
//                floorFactory(floor),
//                floorFactory(floor),
//                floorFactory(floor),
//                floorFactory(floor),
//                floorFactory(floor),
//                floorFactory(treasure),
//                floorFactory(floor),
//                floorFactory(floor),
//                floorFactory(floor),
//                floorFactory(floor),
//                floorFactory(floor),
//                floorFactory(floor),
//                floorFactory(floor),
//                floorFactory(floor),
//                floorFactory(floor)
//            };
//
//        return toReturn;
//}
//
//    public Node floorFactory(String img) {
//        Image floor = new Image(getClass().getResourceAsStream(img));
//        Label toReturn = new Label();
//        ImageView imageView = new ImageView(floor);
//        imageView.setFitWidth(50);
//        imageView.setFitHeight(50);
//        toReturn.setGraphic(imageView);
//        return toReturn;
//    }

    public static void main(String[] args) {
        launch(args);
    }

}
