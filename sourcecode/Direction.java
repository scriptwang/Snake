import java.util.Random;

public enum Direction {
	Left,Up,Right,Down;
	
	static Direction randomDirection(){
		int pick = new Random().nextInt(Direction.values().length);
		return Direction.values()[pick];
	}
}
