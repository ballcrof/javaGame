package hydra;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Battle{
	
private ArrayList<Hydra> battleLineup;
private HashMap<Head, Hydra> attacking;
private HashMap<Head, Head> matches;
private HashMap<Head, ArrayList<Hydra>> finalLineup;


public Battle(){

	battleLineup = new ArrayList<>();
	attacking = new HashMap<>();
	matches =  new HashMap<>();
	finalLineup = new HashMap<>();


}
public ArrayList<Hydra> getBattleLineup(){
	return battleLineup;
}

public void createBattleLineup(int count){
	for(int i=0; i<count; i++){
		battleLineup.add(new Hydra());
	}
	
}



private void assignAttackTargets(){
/* This method assigns a hydra to be the attack target
for each Head.  At the end of this method the instance variable
attacking will be filled */
	ArrayList<Hydra> toBeAttacked; 
	ArrayList<Head> curHydraHeads;

	
}

private ArrayList<Hydra> getPossibleTargets(Hydra attacker){
	ArrayList<Hydra> targets = new ArrayList<>();
/* this method gets a list of possible Hydras to be attacked
that excludes the current Hydra because we don't want to attack
ourselves */

return null;
}

private void assignDefenders(){
	Hydra target;
	Head opponent;
	/*this method finds an opponent Head to defend the attack.  For
	every attacker in the attackers hashmap, a defender is found from
	the attacked Hydra. The end result of this method is that the matches
	instance variable is complete */

	
	
}

private void makeFinalLineup()
{
	/*This method evaluates the pairs in matches and creates the final
	data structure that represents each Head and an ArrayList of Hydras
	that are being attacked.  The ArrayList should contain Hydras that are 
	being actively attacked as well as those being defended against.  At the
	end of this method the instance variable finalLineup is populated */
}


public void showPreliminaryPlans(){
	/* this method prints out the attacking instance variable in
	a human readable form */
	
	
	
}

public void showFinalPlans(){
	/*this method should print the finalLineup instance variable
	in human readable form
	*/


}

public static void main(String[] args){
	Battle thePlan = new Battle();
	thePlan.createBattleLineup(5);
	thePlan.assignAttackTargets();
	thePlan.showPreliminaryPlans();
	thePlan.assignDefenders();
	thePlan.makeFinalLineup();
	thePlan.showFinalPlans();


}

}