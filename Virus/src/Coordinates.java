
public class Coordinates {
	
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int x() { return x; }
	
	public void setX(int x) { this.x = x; }
	
	public int y() { return y; }
	
	public void setY(int y) { this.y = y; }
	
	public boolean isInRect(Coordinates minCorner, Coordinates maxCorner) {
		return this.x >= minCorner.x() &&
				this.x <= maxCorner.x() &&
				this.y >= minCorner.y() &&
				this.y <= maxCorner.y();
	}
	
	@Override
	public Coordinates clone() {
		return new Coordinates(this.x, this.y);
	}
	
	@Override
	public int hashCode() {
		return this.x + 100* this.y;
	}

	@Override
	public boolean equals(Object o) { // TODO ERROR
		Coordinates c = (Coordinates) o;
		return c.x() == this.x && c.y() == this.y;
	}
	
	public String toString() {
		return new String("(" + this.x + "," + this.y + ")");
	}
	
	private int x;
	private int y;

}
