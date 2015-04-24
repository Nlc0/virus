
public enum GeneticStep {
	MOVE (0),
	TURN_R (0),
	TURN_L (0),
	CLONE (9);
	
	private final int waitTime;
	
	GeneticStep(int waitTime) {
		this.waitTime = waitTime;
	}
	
	public int waitTime() { return this.waitTime; }
}
