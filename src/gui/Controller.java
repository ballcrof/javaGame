package username;


import java.util.ArrayList;
import dnd.models.Monster;
import dnd.models.Treasure;
import dnd.die.Percentile;

public class Controller {
    private Gui myGui;
    private Chamber hydraBattle;
    private static int NUM_HYDRAS = 5;
    private ArrayList<Chamber> chamberArray;
    private ArrayList<ArrayList<Passage>> passageArray;
    private ArrayList<Passage> passageArrays;

    public Controller(Gui theGui){
        myGui = theGui;
        chamberArray = new ArrayList<Chamber>();
        passageArray = new ArrayList<ArrayList<Passage>>();
        ArrayList<Integer> createPassages = new ArrayList<Integer>();
        for(int i = 0; i < 5; i++){
          hydraBattle = new Chamber();
          chamberArray.add(hydraBattle);
          createPassages.add(hydraBattle.getDoors().size());
        }
        passageArray = createPassagesFunc(createPassages);
    }

    public Chamber returnCham(int q){
      Chamber toReturn = chamberArray.get(q - 1);
      return toReturn;
    }

    public Passage returnPass(int chamber, int passage){
      Passage toReturn = passageArray.get(chamber - 1).get(passage);
      return toReturn;
    }

    public void changedChambercontents(Chamber changed, int chamber){
      chamberArray.set(chamber - 1, changed);
    }

    public void changedPassagecontents(Passage changed, int chamber, int passage){
      passageArray.get(chamber - 1).set(passage, changed);
    }

    public void changeMonster(int chamber){
      Monster monster = new Monster();
      Percentile percentile = new Percentile();
      monster.setType(percentile.roll());
      chamberArray.get(chamber - 1).addMonster(monster);
    }

    public void changeMonsterP(int chamber, int passage){
      Monster monster = new Monster();
      Percentile percentile = new Percentile();
      monster.setType(percentile.roll());
      passageArray.get(chamber - 1).get(passage).addMonster(monster);
    }

    public void removeMonster(int chamber){
      chamberArray.get(chamber - 1).removeMonsters();
    }

    public void removeMonsterP(int chamber, int passage){
      passageArray.get(chamber - 1).get(passage).removeMonsters();
    }

    public void changeTreasure(int chamber){
      Treasure monster = new Treasure();
      Percentile percentile = new Percentile();
      monster.setDescription(percentile.roll());
      chamberArray.get(chamber - 1).addTreasure(monster);
    }

    public void changeTreasureP(int chamber, int passage){
      Treasure monster = new Treasure();
      Percentile percentile = new Percentile();
      monster.setDescription(percentile.roll());
      passageArray.get(chamber - 1).get(passage).addTreasure(monster);
    }

    public void removeTreasure(int chamber){
      chamberArray.get(chamber - 1).removeTreasures();
    }

    public void removeTreasureP(int chamber, int passage){
      passageArray.get(chamber - 1).get(passage).removeTreasures();
    }

    public ArrayList<Passage> getPassages(int q){
      return passageArray.get(q - 1);
    }

    private ArrayList<ArrayList<Passage>> createPassagesFunc(ArrayList<Integer> createPassages){
      ArrayList<ArrayList<Passage>> allPassages = new ArrayList<ArrayList<Passage>>();
      ArrayList<Passage> everyPassage = new ArrayList<Passage>();
      //setup return var
      for(int l = 0; l < createPassages.size(); l++){
        ArrayList<Passage> passageArrayfunc = new ArrayList<Passage>();
        allPassages.add(passageArrayfunc);
      }

      for(int l = 0; l < createPassages.size(); l++){
        for(int i = l; i < (createPassages.get(l) +l); i++){
          i = i%createPassages.size();
          if ((i -l) == 0){
            createPassages.set(i, createPassages.get(i) + 1);          }
          else{
            if(createPassages.get(i) == 0){
              int signal = 0;
              for(int w = 0; w < createPassages.size(); w++){
                if((w-l) == 0){
                }
                else if(createPassages.get(w) == 0){
                  signal = 0;
                }
                else{
                  Passage section = new Passage();
                  PassageSection connects = new PassageSection();
                  section.addPassageSection(connects);
                  section.passageConnects(l, w);
                  createPassages.set(w, createPassages.get(w) - 1);
                  createPassages.set(l, createPassages.get(l) - 1);
                  allPassages.get(w).add(section);
                  allPassages.get(l).add(section);
                  everyPassage.add(section);
                  signal = 1;
                  break;
                }
              }
              if(signal == 0){
                Passage newPassage = new Passage();
                PassageSection deadEnd = new PassageSection("Dead End");
                newPassage.passageConnects(i, i);
                newPassage.addPassageSection(deadEnd);
                allPassages.get(l).add(newPassage);
                everyPassage.add(newPassage);
                createPassages.set(i, createPassages.get(i) - 1);
              }
            }
            else{
              Passage section = new Passage();
              PassageSection connects = new PassageSection();
              section.addPassageSection(connects);
              section.passageConnects(l, i);
              createPassages.set(i, createPassages.get(i) - 1);
              createPassages.set(l, createPassages.get(l) - 1);
              allPassages.get(i).add(section);
              allPassages.get(l).add(section);
              everyPassage.add(section);
            }
          }
        }
      }
      for(int h = 0; h<createPassages.size(); h++){
        for(int b = 0; b<createPassages.get(h); b++){
          Passage newPassage = new Passage();
          PassageSection deadEnd = new PassageSection("Dead End");
          newPassage.passageConnects(b, b);
          newPassage.addPassageSection(deadEnd);
          allPassages.get(h).add(newPassage);
          everyPassage.add(newPassage);
        }
      }
      return allPassages;
    }

    private String getNameList(int q){

        String nameList = new String();
        nameList = "Chamber " + q + "\n";
        nameList += chamberArray.get(q - 1).getDescription() + "\n";
        return nameList;
    }

    public void reactToButton(){
        System.out.println("Thanks for clicking!... Nothing has Happened :(");
    }

    public String getNewDescription(int q){
        //return "this would normally be a description pulled from the model of the Dungeon level.";
        return getNameList(q);
    }

    private String getPassageList(int q, int j){

        String nameList = new String();
        nameList = "Passage " + q + "\n";
        nameList += passageArray.get(j - 1).get(q - 1).getDescription() + "\n";
        nameList += passageArray.get(j - 1).get(q - 1).getConnection() + "\n";
        return nameList;
    }

    public String getPassageDescription(int q, int j){
      return getPassageList(q, j);
    }

    public ArrayList<Door> getDoorsFromChamber(int q){
      return chamberArray.get(q - 1).getDoors();
    }

}
