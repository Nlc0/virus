import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<GeneticStep> geneticCode = new ArrayList<GeneticStep>();
		geneticCode.add(GeneticStep.MOVE);
		geneticCode.add(GeneticStep.TURN_R);
		
		ArrayList<ArrayList<GeneticStep>> codes = new ArrayList<ArrayList<GeneticStep>>();
		codes.add(geneticCode);
		codes.add(geneticCode);
		VirusGame game = new VirusGame(codes);
		
		Coordinates c = new Coordinates(1,1);
		
		System.out.println(game.cellPlayer(c));

		printMap(game);
		
		/*while (!game.gameOver()) {
			printMap(game);
			game.play();
		}*/
	}
	
	public static void printMap(VirusGame game) {
		for (int i = game.mapMinX() ; i != game.mapMaxX() + 1 ; i++) {
			for (int j = game.mapMinY() ; j != game.mapMaxY() + 1 ; j++) {
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
