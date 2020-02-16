import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class testingIdeas{

  static Tool butt = new Tool();

  public static void main(String[] args){

    testingIdeas(butt);
    return;
  }

  public static void testingIdeas(Tool butt){
    System.out.println("i will generate a random number between 1 and 100:");
    Random rand = new Random();

    //waits to output for 1 second
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch(InterruptedException ex) {

    }//

    System.out.println(rand.nextInt(100) + 1 +"\nname a tool: ");

    butt.proof();

    Scanner tools = new Scanner(System.in);
    String toolType = tools.nextLine();
    //System.out.println(butt.tool(toolType));

    butt.isIt(butt.tool(toolType));

  }
}
