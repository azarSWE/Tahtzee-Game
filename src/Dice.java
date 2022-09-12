
/**
 * This class represents a single six-sided die (dice)
 * @author azararya
 */
public class Dice {
	
	/* Instance Variable: */
    /* amount of value die shows */
	private int value;
	
	/** Constructor: Creates a new instance of class Dice and initialize the value variable  
     */
	public Dice() {
		value = -1;
	}
	
	/** Constructor: Creates a new instance of class Dice 
     * @param value initialize the value variable of class Dice 
     */
	public Dice(int value) {
		this.value = value;
	}
	
	/** Method to use the provided class RandomNumber to generate a number between 1 and 6 (inclusive) 
	 * and set the value to that number 
     */
	public void roll() {
		value = RandomNumber.getRandomNumber(1, 6);
	}
	
	/** Method to access the value of die 
     * @return value returns the value of die
     */
	public int getValue () {
		return value;
	}
	
}
