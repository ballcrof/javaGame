public class Inventory{

  static Player player = new Player();

  public Inventory(){

  }

  public void WeaponInit(){
  //  Weapon main_weapon = new Weapon("none");
  }

  public static void setUp(Player yourPlayer){
    player = yourPlayer;
  }

  public void display_name(){
    System.out.println("yo whats up its ya boi "+ player.Name() + "\n");
  }

}
