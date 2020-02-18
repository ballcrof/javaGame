package hydra;
import java.util.ArrayList;

public class Hydra{
	private ArrayList<Head> heads;
	private static int hydraCount =0;
	private final static int MAX_HEADS = 4;
	private int idNum;
	private int headCount;

public Hydra(){
	heads = new ArrayList<>();
	setIdNum();
	setNumHeads();
	growHeads();
}
private void setIdNum(){
	idNum = Hydra.hydraCount;
	Hydra.hydraCount++;	
}
public void growHeads(){
	for(int i=0; i<headCount; i++){
		associateHead(new Head(i));
	}
}

void associateHead(Head addMe){
	heads.add(addMe);
	addMe.joinHydra(this);
}

public int getNumHeads(){
	return heads.size();
}
private void setNumHeads(){
	headCount = (int)(Math.random()*MAX_HEADS) + 1;
}

public Head getLeastBusyHead(){
	Head leastBusy= heads.get(0);
	for(int i=1; i<heads.size(); i++){ //starting at 1 because we already have 0
		if(heads.get(i).getNumTargets()<leastBusy.getNumTargets()){
			leastBusy = heads.get(i);
		}
	}
	return leastBusy;
}

public ArrayList<Head> getHeads(){
	return heads;
	
}
public String toString(){
	return "Hydra "+ new Integer(idNum).toString();
}
}