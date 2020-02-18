package hydra;
import java.util.ArrayList;

public class Head{
	private int idNum;
	private Hydra hydra;
	private int numAttackTargets;

public Head(int id){

	numAttackTargets = 0;
	idNum = id;
}



void joinHydra(Hydra addMe){
	hydra = addMe;
}

public Hydra getHydra(){
	return hydra;
	
}


public void addAttackTarget(){
	numAttackTargets++;
}



public int getNumTargets(){
	return numAttackTargets;
}

public String getHydraName(){
	return hydra.toString();
}

public String toString(){
	return "Head "+  new Integer(idNum).toString();
}
}