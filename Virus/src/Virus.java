
public class Virus {
	
	public Virus(VirusGame game, int player, Coordinates coordinates, Direction direction) {
		this.player = player;
		this.coordinates = coordinates.clone();
		this.direction = direction;
		this.game = game;
		this.waitTime = 0;
		this.step = 0;
	}
	
	public int player() { return player; }
	
	public boolean waiting() { return waitTime > 0; }
	
	public Coordinates coordinates() { return this.coordinates; }
	
	public int step() { return step; }

	public Direction direction() { return this.direction; }
	
	public void turnR() {
		if (Direction.RIGHT.equals(direction))
			direction = Direction.DOWN;
		else if (Direction.UP.equals(direction))
			direction = Direction.RIGHT;
		else if (Direction.LEFT.equals(direction))
			direction = Direction.UP;
		else if (Direction.DOWN.equals(direction))
			direction = Direction.LEFT;
	}
	
	public void turnL() {
		if (Direction.RIGHT.equals(direction))
			direction = Direction.UP;
		else if (Direction.UP.equals(direction))
			direction = Direction.LEFT;
		else if (Direction.LEFT.equals(direction))
			direction = Direction.DOWN;
		else if (Direction.DOWN.equals(direction))
			direction = Direction.RIGHT;
	}
	
	public void progress() { 
		if (waitTime > 0) 
			waitTime--;
		else {
			if (step == game.nbSteps(player) - 1)
				step = 0;
			else
				step++;
		}
	}
	
	private int player;
	private int waitTime;
	private Coordinates coordinates;
	private Direction direction;
	private int step;
	
	VirusGame game;
}
