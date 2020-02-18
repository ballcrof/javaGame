import java.util.Scanner;

public class main{
  public static void main(String[] args){
    Player player = new Player();
    Inventory inventory = new Inventory();

    inventory.setUp(player);

    System.out.println("Input your name:");
    Scanner input = new Scanner(System.in);
    String name = input.nextLine();
    player.Name(name);

    /*System.out.println("Do you want to see your name?");
    //Scanner question = new Scanner(System.in);
    String answer = input.nextLine();
    if (answer.equalsIgnoreCase("yes")){
      inventory.display_name();
    }*/

    System.out.println("\n\npay attention "+ player.Name() +"\n\nyour actions for this game will be: \n\"talk\" + the discriptor for an NPC to talk to them if you're able\n\n\"use\" + the item you want to use if you are able\n\n\"look\" to observe your surroundings \nor \"look\" + object to get a more detailed description\n\n\"pick up\" + an object you want to pick up\n\n\"inventory\" to see what items you currently posses\n\nYou will use the cardinal directions to move throughout this world\n\nand finally just type \"quit to quit the game\"\n\nEnjoy!\n");

    String toDo;

    do {
      System.out.print("what will you do: ");
      toDo = input.nextLine();
    } while (toDo.contains("quit") != true);

  }
}
/* to compile: /Users/brendanallcroft/apache-ant-1.10.7/bin/ant compile*/
/* to run: java -cp build/classes main*/
