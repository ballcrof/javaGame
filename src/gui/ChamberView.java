package username;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ChamberView extends GridPane {
    private String floor;
    private String treasure;
    private int length;
    private int width;


    public ChamberView(int len, int wid){
        floor = "/res/floor.png";
       treasure = "/res/tres.png";
       length = len;
       width = wid; //user these values to decide the size of the view and how many tiles


        Node[] tiles = makeTiles();
        int pos = 0;
        //should definitely be a loop and possibly a method
        for(int i = 0; i < length; i++){
          for(int k = 0; k < width; k++){
            add(tiles[pos],i,k,1,1);
            pos++;
          }
        }
    }


    private Node[] makeTiles() {  //should have a parameter and a loop
      Node[] toReturn = new Node[length * width];
        for(int i = 0; i < (length * width); i++){
          toReturn[i] = floorFactory(floor);
          }
        return toReturn;
    }


    public Node floorFactory(String img) {
        Image floor = new Image(getClass().getResourceAsStream(img));
        Label toReturn = new Label();
        ImageView imageView = new ImageView(floor);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        toReturn.setGraphic(imageView);
        return toReturn;
    }


}
