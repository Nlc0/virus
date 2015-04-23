import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;


// TODO Faire autant de teams qu'on veut
public class VirusGame {
	private static final int TIME_OUT = 100;
	
	
	public VirusGame (ArrayList<ArrayList<GeneticStep>> geneticCodes) {
		for (int i = 0 ; i != geneticCodes.size() ; i++) {
			this.geneticCodes.add(new ArrayList<GeneticStep>());
			for (int j = 0 ; j != geneticCodes.get(i).size() ; j++) {
				this.geneticCodes.get(i).add(geneticCodes.get(i).get(j));
			}
		}
	}
	
	public boolean emptyCell(Coordinates c) {
		return !map.contains(c);
	}
	
	public int cellTeam(Coordinates c) {
		return map.get(c).team();
	}
	
	public int remainingTime() {
		return TIME_OUT - timer;
	}
	
	public int nbPlayers() {
		return geneticCodes.size();
	}
	
	// Requirement :: !gameOver()
	public void play() {
		for (Virus v : bank ) {
			if (!v.waiting()) {
				GeneticStep s = geneticCodes.get(v.team()).get(v.step());
				if (GeneticStep.MOVE.equals(s)) 
					v.move(1);
				else if (GeneticStep.TURN_R.equals(s))
					v.turnR();
				else if (GeneticStep.TURN_L.equals(s))
					v.turnL();
			}
			v.progress();
		}
		timer++;
	}
	
	public boolean gameOver() {
		return timer >= TIME_OUT;
	}
	
	public 
	
	
	// Retourne 0 si on est encore en train de jouer
	// 1 si le joueur 1 a gagné
	// 2 si le joueur 2 a gagné
	// X si le joueur X a gagné
	// Gérer le cas d'égalité
	public int gameState() {
		if (timer < TIME_OUT)
			return 0;
		Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
		for (Virus v : bank) {
			scores.put(v.team(), scores.get(v.team()) + 1);
		}
		return max(scores) + 1;
	}
	
	private Hashtable<Coordinates, Virus> map;
	private LinkedList<Virus> bank;
	private ArrayList<ArrayList<GeneticStep>> geneticCodes;
	
	private int timer;
}
