import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<GeneticStep> geneticCode = new ArrayList<GeneticStep>();
		geneticCode.add(GeneticStep.EAT);
		geneticCode.add(GeneticStep.MOVE);
		geneticCode.add(GeneticStep.TURN_R);
		geneticCode.add(GeneticStep.EAT);
		geneticCode.add(GeneticStep.MOVE);
		geneticCode.add(GeneticStep.TURN_R);
		geneticCode.add(GeneticStep.TURN_R);
		geneticCode.add(GeneticStep.CLONE);

		
		ArrayList<GeneticStep> geneticCode2 = new ArrayList<GeneticStep>();
		geneticCode2.add(GeneticStep.MOVE);
		geneticCode2.add(GeneticStep.CLONE);
		geneticCode2.add(GeneticStep.TURN_R);
		
		ArrayList<ArrayList<GeneticStep>> codes = new ArrayList<ArrayList<GeneticStep>>();
		codes.add(geneticCode);
		codes.add(geneticCode2);
		VirusGame game = new VirusGame(codes);
		
		while (!game.gameOver()) {
			printMap(game);
			System.out.println("Tour : " + game.time() + " Score t0 : " + game.score(0) + " Score t1 : " + game.score(1));
			System.out.println("Team 0 : " + game.teamScore(0) + " Team 1 : " + game.teamScore(1));
			System.out.println("");
			game.play();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void printMap(VirusGame game) {
		for (int i = game.mapMinY() ; i != game.mapMaxY() + 1 ; i++) {
			for (int j = game.mapMinX() ; j != game.mapMaxX() + 1 ; j++) {
				Coordinates c = new Coordinates(j, i);
				if (game.emptyCell(c))
					System.out.print(" ");
				else
					System.out.print(game.cellPlayer(c));
			}
			System.out.println("");
		}
	}
}
