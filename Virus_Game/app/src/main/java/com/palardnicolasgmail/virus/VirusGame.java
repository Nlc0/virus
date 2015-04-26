import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;


// TODO Gérer les teams
public class VirusGame {
	private static final int TIME_OUT = 1000;
	private static final int MAP_WIDTH = 30;
	private static final int MAP_HEIGHT = 30;
	
	private static final int START_PLAYER_0_X = MAP_WIDTH / 4;
	private static final int START_PLAYER_0_Y = MAP_HEIGHT / 4;
	private static final Direction START_PLAYER_0_D = Direction.DOWN;
	
	private static final int START_PLAYER_1_X = MAP_WIDTH * 3 / 4;
	private static final int START_PLAYER_1_Y = MAP_HEIGHT * 3 / 4;
	private static final Direction START_PLAYER_1_D = Direction.UP;
	
	public VirusGame (ArrayList<ArrayList<GeneticStep>> geneticCodes) {
		this(geneticCodes, linearArray(geneticCodes.size()), geneticCodes.size());
	}
	public VirusGame (ArrayList<ArrayList<GeneticStep>> geneticCodes, ArrayList<Integer> teams, int nbTeams) {
		timer = 0;
		nbPlayers = geneticCodes.size();
		
		this.scores = new ArrayList<Integer>();
		for (int i = 0 ; i != this.nbPlayers ; i++) {
			this.scores.add(1);
		}
		
		this.playerTeam = new ArrayList<Integer>();
		for (int i = 0 ; i != this.nbPlayers ; i++) {
			this.playerTeam.add(i);
		}
		
		this.teamsScores = new ArrayList<Integer>();
		for (int i = 0 ; i != nbTeams ; i++) {
			this.teamsScores.add(0);
		}
		for (int i = 0 ; i != this.nbPlayers ; i++) {
			int team = this.playerTeam.get(i);
			this.teamsScores.set(team, this.teamsScores.get(team) + 1);
		}
		
		this.geneticCodes = new ArrayList<ArrayList<GeneticStep>>();
		for (int i = 0 ; i != geneticCodes.size() ; i++) {
			this.geneticCodes.add(new ArrayList<GeneticStep>());
			ArrayList<GeneticStep> code = geneticCodes.get(i);
			for (int j = 0 ; j != code.size() ; j++) {
				this.geneticCodes.get(i).add(code.get(j));
			}
		}
		
		this.bank = new LinkedList<Virus>();
		this.bank.add(new Virus(this, teams.get(0), new Coordinates(START_PLAYER_0_X, START_PLAYER_0_Y), START_PLAYER_0_D));
		this.bank.get(0).waitXTurns(this.geneticCodes.get(0).get(0).waitTime());
		this.bank.add(new Virus(this, teams.get(1), new Coordinates(START_PLAYER_1_X, START_PLAYER_1_Y), START_PLAYER_1_D));
		this.bank.get(1).waitXTurns(this.geneticCodes.get(1).get(0).waitTime());
		
		System.out.println(this.bank.get(0));
		
		this.map = new Hashtable<Coordinates, Virus>();
		this.map.put(new Coordinates(START_PLAYER_0_X, START_PLAYER_0_Y), this.bank.get(0));
		this.map.put(new Coordinates(START_PLAYER_1_X, START_PLAYER_1_Y), this.bank.get(1));
	}
	
	// Nécessaire pour appeller le constructeur complet avec le constructeur simple
	// Crée un tableau 0 1 2 3 ... n
	public static ArrayList<Integer> linearArray(int n) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		for(int i = 0 ; i != n ; i ++) {
			a.add(i);
		}
		return a;
	}
	
	public boolean emptyCell(Coordinates c) {
		return !map.containsKey(c);
	}
	
	public int cellPlayer(Coordinates c) {
		return map.get(c).player();
	}
	
	public int cellTeam(Coordinates c) {
		return this.playerTeam.get(this.map.get(c).player());
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
	
	public int time() {
		return this.timer;
	}
	
	// Ces quatres fonctions sont utilisées par this.move(Virus) pour savoir si on a atteint le bord de la map
	public int mapMinX() { return 0; }
	
	public int mapMaxX() { return MAP_WIDTH - 1; }
	
	public int mapMinY() { return 0; }
	
	public int mapMaxY() { return MAP_HEIGHT - 1; }
	
	// Requires !gameOver()
	public void play() {
		for (int i = 0 ; i < this.bank.size() ; i++ ) {
			Virus v = this.bank.get(i);
			if (!v.waiting()) {
				GeneticStep s = geneticCodes.get(v.player()).get(v.step());
				switch(s) {
					case MOVE:
						this.move(v);
						break;
					case TURN_R:
						v.turnR();
						break;
					case TURN_L:	
						v.turnL();
						break;
					case CLONE:
						this.clone(v);
						break;
					case EAT:
						this.eat(v);
						break;
				}
				v.progress();
				v.waitXTurns(geneticCodes.get(v.player()).get(v.step()).waitTime());
			}
			else
				v.progress();
		}
		timer++;
	}
	
	public void addToScore(int player, int n) {
		this.scores.set(player,  this.scores.get(player) + n);
		int team = this.playerTeam.get(player);
		this.teamsScores.set(team, this.teamsScores.get(team) + n);
	}
	
	public int score(int player) {
		return this.scores.get(player);
	}
	
	public int teamScore(int team) {
		return this.teamsScores.get(team);
	}
	
	private void move(Virus v) {
		Coordinates dest = v.frontCell();
		if (dest.isInRect(new Coordinates(this.mapMinX(), this.mapMinY()), new Coordinates(this.mapMaxX(), this.mapMaxY()))
				&& emptyCell(dest)) {
			this.map.remove(v.coordinates());
			v.setCoordinates(dest);
			this.map.put(v.coordinates(), v);
		}
	}
	
	private void clone(Virus v) {
		Coordinates dest = v.frontCell();
		if (dest.isInRect(new Coordinates(this.mapMinX(), this.mapMinY()), new Coordinates(this.mapMaxX(), this.mapMaxY()))
				&& emptyCell(dest)) {
			Virus v2 = new Virus(this, v.player(), dest, v.direction());
			v2.waitXTurns(this.geneticCodes.get(v2.player()).get(v2.step()).waitTime());
			this.bank.add(v2);
			this.map.put(dest, v2);
			this.addToScore(v.player(), 1);
		}
	}
	
	private void eat(Virus v) {
		Coordinates dest = v.frontCell();
		if (dest.isInRect(new Coordinates(this.mapMinX(), this.mapMinY()), new Coordinates(this.mapMaxX(), this.mapMaxY()))
				&& !emptyCell(dest) && this.cellTeam(dest) != this.playerTeam.get(v.player())) {
			Virus v2 = this.map.get(dest);
			this.addToScore(v2.player(), -1);
			this.bank.remove(v2);
			this.map.remove(dest);
		}
	}
	
	public boolean gameOver() {
		if (timer >= TIME_OUT)
			return true;
		
		// Pour savoir si la partie est terminée on cherche a savoir s'il y a au moins deux survivants.
		// Le premier survivant trouvé passe le booléen à true et le second renvoie false
		boolean survivor = false;
		for (int i = 0 ; i != this.teamsScores.size() ; i ++) {
			if (this.teamsScores.get(i) != 0) {
				if (!survivor)
					survivor = true;
				else
					return false;
			}
		}
		return true;
	}

	// TODO Gérer le cas d'égalité
	// Return a team
	public int winner() {
		int max = 0;
		int iMax = 0;
		for (int i = 0 ; i != this.teamsScores.size() ; i++){
			int s = this.teamsScores.get(i);
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
	private ArrayList<Integer> teamsScores;
	private ArrayList<Integer> playerTeam;
	
	private int timer;
	private int nbPlayers;
}
