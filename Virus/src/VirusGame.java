import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;


public class VirusGame {
	public static final int TIME_OUT = 100;
	
	
	
	public void get();
	
	// Retourne 0 si on est encore en train de jouer
	// 1 si le joueur 1 a gagné
	// 2 si le joueur 2 a gagné
	// X si le joueur X a gagné
	// Gérer le cas d'égalité
	public int GameState() {
		if (timer < TIME_OUT)
			return 0;
		Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
		for (Virus v : bank) {
			scores.put(v.team(), scores.get(v.team()) + 1);
		}
		return max(scores) + 1;
	}
	
	public void play() {
		for (Virus v : bank ) {
			switch (program1.)
		}
	}
	
	private Hashtable<Coordinates, Virus> map;
	private LinkedList<Virus> bank;
	private ArrayList<Integer> program1;
	private ArrayList<Integer> program2;
	
	private int timer;
}
