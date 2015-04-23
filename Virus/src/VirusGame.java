import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;


// TODO Gérer les teams
public class VirusGame {
	private static final int TIME_OUT = 100;
	
	public VirusGame (ArrayList<ArrayList<GeneticStep>> geneticCodes) {
		timer = 0;
		nbPlayers = geneticCodes.size();
		
		private Hashtable<Coordinates, Virus> map;
		
		private LinkedList<Virus> bank;
		
		ArrayList<Integer> scores = new ArrayList<Integer>();
		
		geneticCodes = new ArrayList<ArrayList<GeneticStep>>();
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
	
	public int cellPlayer(Coordinates c) {
		return map.get(c).player();
	}
	
	public int remainingTime() {
		return TIME_OUT - timer;
	}
	
	public int nbPlayers() {
		return nbPlayers;
	}
	
	// Requires !gameOver()
	public void play() {
		for (Virus v : bank ) {
			if (!v.waiting()) {
				GeneticStep s = geneticCodes.get(v.player()).get(v.step());
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

	// Retourne 0 si on est encore en train de jouer
	// 1 si le joueur 1 a gagné
	// 2 si le joueur 2 a gagné
	// X si le joueur X a gagné
	// Gérer le cas d'égalité
	// Requires gameOver()
	public int winner() {
		int max = 0;
		int iMax = 0;
		for (int i = 0 ; i != scores.size() ; i++){
			int s = scores.get(i);
			if (s > max) {
				max = s;
				iMax = i;
			}
		}
		return iMax;
	}	
	
	private Hashtable<Coordinates, Virus> map;
	private LinkedList<Virus> bank;
	private ArrayList<ArrayList<GeneticStep>> geneticCodes;
	private ArrayList<Integer> scores;
	
	private int timer;
	private int nbPlayers;
}
