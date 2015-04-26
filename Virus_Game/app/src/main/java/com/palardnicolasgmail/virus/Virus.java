
public class Virus {
	
	public Virus(VirusGame game, int player, Coordinates coordinates, Direction direction) {
		this.player = player;
		this.coordinates = coordinates.clone();
		this.direction = direction;
		this.game = game;
		this.waitTime = 1;
		this.step = 0;
	}
	
	public Coordinates frontCell() {
		Coordinates dest = this.coordinates().clone();
		switch (this.direction()) {
			case RIGHT:
				dest.setX(dest.x() + 1);
				break;
			case UP:
				dest.setY(dest.y() - 1);
				break;
			case LEFT:
				dest.setX(dest.x() - 1);
				break;
			case DOWN:
				dest.setY(dest.y() + 1);
				break;
		}
		return dest;
	}
	
	public int player() { return player; }
	
	public boolean waiting() { return waitTime > 0; }
	
	public Coordinates coordinates() { return this.coordinates; }
	
	public int step() { return step; }

	public Direction direction() { return this.direction; }
	
	public void waitXTurns(int waitTime) { this.waitTime += waitTime; }
	
	public void setCoordinates(Coordinates c) {
		this.coordinates = c.clone();
	}
	
	public void turnR() {
		switch (direction) {
		case RIGHT:
			direction = Direction.DOWN;
			break;
		case UP:
			direction = Direction.RIGHT;
			break;
		case LEFT:
			direction = Direction.UP;
			break;
		case DOWN:
			direction = Direction.LEFT;
			break;
	}
	}
	
	public void turnL() {
		switch (direction) {
			case RIGHT:
				direction = Direction.UP;
				break;
			case UP:
				direction = Direction.LEFT;
				break;
			case LEFT:
				direction = Direction.DOWN;
				break;
			case DOWN:
				direction = Direction.RIGHT;
				break;
		}
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
