import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;


// TODO Gérer les teams
public class VirusGame {
	private static final int TIME_OUT = 10;
	private static final int MAP_WIDTH = 10;
	private static final int MAP_HEIGHT = 10;
	
	private static final int START_PLAYER_0_X = 1;
	private static final int START_PLAYER_0_Y = 1;
	private static final Direction START_PLAYER_0_D = Direction.UP;
	
	private static final int START_PLAYER_1_X = 8;
	private static final int START_PLAYER_1_Y = 8;
	private static final Direction START_PLAYER_1_D = Direction.DOWN;
	
	public VirusGame (ArrayList<ArrayList<GeneticStep>> geneticCodes) {
		timer = 0;
		nbPlayers = geneticCodes.size();
		
		this.bank = new LinkedList<Virus>();
		this.bank.add(new Virus(this, 0, new Coordinates(START_PLAYER_0_X, START_PLAYER_0_Y), START_PLAYER_0_D));
		this.bank.add(new Virus(this, 0, new Coordinates(START_PLAYER_1_X, START_PLAYER_1_Y), START_PLAYER_1_D));
		
		System.out.println(this.bank.get(0));
		
		this.map = new Hashtable<Coordinates, Virus>();
		this.map.put(new Coordinates(START_PLAYER_0_X, START_PLAYER_0_Y), this.bank.get(0));
		this.map.put(new Coordinates(START_PLAYER_1_X, START_PLAYER_1_Y), this.bank.get(1));
		
		this.scores = new ArrayList<Integer>();
		
		this.geneticCodes = new ArrayList<ArrayList<GeneticStep>>();
		for (int i = 0 ; i != geneticCodes.size() ; i++) {
			this.geneticCodes.add(new ArrayList<GeneticStep>());
			ArrayList<GeneticStep> code = geneticCodes.get(i);
			for (int j = 0 ; j != code.size() ; j++) {
				this.geneticCodes.get(i).add(code.get(j));
			}
		}
	}
	
	public boolean emptyCell(Coordinates c) {
		return !map.containsKey(c);
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
	
	public int nbSteps(int player) {
		return this.geneticCodes.get(player).size();
	}
	
	
	// Ces quatres fonctions sont utilisées par Virus
	public int mapMinX() { return 0; }
	
	public int mapMaxX() { return MAP_WIDTH - 1; }
	
	public int mapMinY() { return 0; }
	
	public int mapMaxY() { return MAP_HEIGHT - 1; }
	
	// Requires !gameOver()
	public void play() {
		for (Virus v : bank ) {
			if (!v.waiting()) {
				GeneticStep s = geneticCodes.get(v.player()).get(v.step());
				if (GeneticStep.MOVE.equals(s)) 
					this.move(v);
				else if (GeneticStep.TURN_R.equals(s))
					v.turnR();
				else if (GeneticStep.TURN_L.equals(s))
					v.turnL();
			}
			v.progress();
		}
		timer++;
	}
	
	private void move(Virus v) {
		if (Direction.RIGHT.equals(v.direction())) {
			if (v.coordinates().x() != this.mapMaxX() && 
					this.emptyCell(new Coordinates(v.coordinates().x() + 1, v.coordinates().y()))) {
				this.map.remove(v.coordinates());
				v.coordinates().setX(v.coordinates().x() + 1);
				this.map.put(v.coordinates(), v);
			}
		}
		else if (Direction.UP.equals(v.direction())) {
			if (v.coordinates().y() != this.mapMinY() && 
					this.emptyCell(new Coordinates(v.coordinates().x(), v.coordinates().y() - 1))) {
				this.map.remove(v.coordinates());
				v.coordinates().setY(v.coordinates().y() - 1);
				this.map.put(v.coordinates(), v);
			}
		}
		else if (Direction.LEFT.equals(v.direction())) {
			if (v.coordinates().x() != this.mapMinX() && 
					this.emptyCell(new Coordinates(v.coordinates().x() - 1, v.coordinates().y()))) {
				this.map.remove(v.coordinates());
				v.coordinates().setX(v.coordinates().x() - 1);
				this.map.put(v.coordinates(), v);
			}
		}
		else if (Direction.DOWN.equals(v.direction())) {
			if (v.coordinates().y() != this.mapMaxY() && 
					this.emptyCell(new Coordinates(v.coordinates().x(), v.coordinates().y() + 1))) {
				this.map.remove(v.coordinates());
				v.coordinates().setY(v.coordinates().y() + 1);
				this.map.put(v.coordinates(), v);
			}
		}
	}
	
	public boolean gameOver() {
		return timer >= TIME_OUT;
	}

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
