public class Player{
  String playerName = new String();
  int health;

  public Player(){
    health = 20;
  }

  public void Name(String name){
    playerName = name;
  }

  public String Name(){
    return playerName;
  }

  public void addHealth(int heal){
    health += heal;
  }

  public void damage(int damage){
    health -= damage;
  }
}
