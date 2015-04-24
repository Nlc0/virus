
public class Coordinates {
	
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int x() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int y() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public Coordinates clone() {
		return new Coordinates(this.x, this.y);
	}
	
	public int hashCode() {
		return this.x + 100* this.y;
	}
	public boolean equals(Coordinates c) {
		return c.x() == this.x || c.y() == this.y;
	}
	
	private int x;
	private int y;

}
