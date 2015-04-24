
public class Virus {
	
	public Virus(VirusGame game, int player, Coordinates coordinates, Direction direction) {
		this.player = player;
		this.coordinates = coordinates.clone();
		this.direction = direction;
		this.game = game;
		this.waitTime = 0;
		this.step = 0;
	}
	
	public int player() {
		return player;
	}
	
	public boolean waiting() {
		return waitTime > 0;
	}
	
	public int step() { 
		return step;
	}

	public void move(int n) {
		if (Direction.RIGHT.equals(direction)) {
			if (this.coordinates.x() == game.mapMaxX())
				this.coordinates.setX(game.mapMinX());
			else if (game.emptyCell(new Coordinates(this.coordinates.x() + 1, this.coordinates.y())))
				this.coordinates.setX(coordinates.x() + 1);
		}
		else if (Direction.UP.equals(direction)) {
			if (this.coordinates.y() == game.mapMinY())
				this.coordinates.setY(game.mapMaxY());
			else if (game.emptyCell(new Coordinates(this.coordinates.x(), this.coordinates.y() - 1)))
				this.coordinates.setY(coordinates.y() - 1);
		}
		else if (Direction.LEFT.equals(direction)) {
			if (this.coordinates.x() == game.mapMinX())
				this.coordinates.setX(game.mapMaxX());
			else if (game.emptyCell(new Coordinates(this.coordinates.x() - 1, this.coordinates.y())))
				this.coordinates.setX(coordinates.x() - 1);
		}
		else if (Direction.DOWN.equals(direction)) {
			if (this.coordinates.y() == game.mapMaxY())
				this.coordinates.setY(game.mapMinY());
			else if (game.emptyCell(new Coordinates(this.coordinates.x(), this.coordinates.y() + 1)))
				this.coordinates.setY(coordinates.y() + 1);
		}
	}
	
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
