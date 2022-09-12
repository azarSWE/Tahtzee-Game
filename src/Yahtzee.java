
/**
 * This class represents the Yahtzee game in which there are 5 rolled dice and the scoring is performed
 * @author azararya
 */
public class Yahtzee{
	
	/* Instance Variables: */
    /* an array of Dice class */
	private Dice[] dice;
	
	/** Constructor: Creates a new instance of Yahtzee class, 
     * and initialize the dice array and roll them for random values
     */
	public Yahtzee () {
		
		Dice d1 = new Dice(2);
		Dice d2 = new Dice(1);
		Dice d3 = new Dice(6);
		Dice d4 = new Dice(5);
		Dice d5 = new Dice(1);
		
		d1.roll();
		d2.roll();
		d3.roll();
		d4.roll();
		d5.roll();
		
		dice = new Dice[] {d1, d2, d3, d4, d5};
		
	}
	
	/**
	 * Constructor: Creates a new instance of Yahtzee class, 
	 * @param dice initialize the instance variable 
	 */
	public Yahtzee (Dice[] dice) {
		this.dice = dice;
	}
	
	
	/**
	 * This method count how many dice shows each of the possible values from 1-6
	 * @return diceValueArray is an array with 6 elements each represents the number 
	 * of times values 1-6 repeated among 5 dice.
	 */
	public int[] getValueCount () {
		int[] diceValueCountArray = new int[6]; 
		int counter = 0;
		for (int i = 1; i <= 6; i++) {
			for (int j = 0; j < 5; j++) {
				if (dice[j].getValue() == i)
					counter ++;
			} 
			diceValueCountArray[i - 1] = counter;	
			counter = 0;	
			}	
		return diceValueCountArray;
	}
	
	
	/**
	 * This method computes sum of values on top of the 5 dice
	 * @return sumDice an integer value which is summation of values represented by 5 dice
	 */
	private int sumOfDiceValues() {
		int sumDice = 0;
		for (int i = 0; i < 5; i++) {
			sumDice = sumDice + dice[i].getValue();
		}
		return sumDice;
	}
	
	
	/**
	 * This method records all the possible scores for the dice in the instance variable.
	 * @return scoreArray is an array with 13 elements each represents a score option
	 */
	public int[] getScoreOptions () {
		int[] diceScoreOptionsArray = new int[13];
		
		// initialization of scoreArray as all score options equal zero
		for (int i = 0; i < 13; i++) {
			diceScoreOptionsArray[i] = 0;
		}
		
		int[] diceValueCountArray = getValueCount();
		
		// the first six scoring options
		for (int i = 0; i < 6; i++) {
			diceScoreOptionsArray[i] = diceValueCountArray[i] * (i + 1); 
		}
		
		// three of a kind, four of a kind, and Yahtzee
		for (int i = 0; i < 6; i++) {
			if (diceValueCountArray[i] == 5) {
				diceScoreOptionsArray[6] = sumOfDiceValues();
				diceScoreOptionsArray[7] = sumOfDiceValues();
				diceScoreOptionsArray[11] = 50;
				break;
			} else if (diceValueCountArray[i] == 4) {
				diceScoreOptionsArray[6] = sumOfDiceValues();
				diceScoreOptionsArray[7] = sumOfDiceValues();
				diceScoreOptionsArray[11] = 0;
				break;
			} else if (diceValueCountArray[i] == 3) {
				diceScoreOptionsArray[6] = sumOfDiceValues();
				diceScoreOptionsArray[11] = 0;
				break;
			}
		}
		
		// full house
		for (int i = 0; i < 6; i++) {
			if (diceValueCountArray[i] == 3) {
				for (int j = 0; j < 6; j++) {
					if (diceValueCountArray[j] == 2) 
						diceScoreOptionsArray[8] = 25;
				}
				
			}	
		}

		// small straight and large straight 
		for (int i = 0; i < 2; i++) {
			if (diceValueCountArray[i] == 1 && diceValueCountArray[i + 1] == 1 && diceValueCountArray[i + 2] == 1 && diceValueCountArray[i + 3] == 1 && diceValueCountArray[i + 4] == 1) {
				diceScoreOptionsArray[10] = 40;
				diceScoreOptionsArray[9] = 30;
				break;
			} else {
				for (int j = 0; j < 3; j++) {
					if (diceValueCountArray[j] >= 1 && diceValueCountArray[j + 1] >= 1 && diceValueCountArray[j + 2] >= 1 && diceValueCountArray[j + 3] >= 1) {
						diceScoreOptionsArray[10] = 0;
						diceScoreOptionsArray[9] = 30;
						break;
					}

				}
			}
		}
		
		// chance
		diceScoreOptionsArray[12] = sumOfDiceValues();
		
		return diceScoreOptionsArray;
			
	}
	
	
	/**
	 * This method returns an array that contains the maximum value from the array of possible scores and the smallest index at which the maximum is found
	 * @return maxScore is an array contains the maximum value and the smallest index at which the maximum is found
	 */
	public int[] score () {
		int[] diceScoreOptionsArray = getScoreOptions();
		int maxScore = 0;
		int index = 0;
		for (int i = 0; i < diceScoreOptionsArray.length; i++) {
			if (diceScoreOptionsArray[i] > maxScore) {
				maxScore = diceScoreOptionsArray[i];
				index = i;
			}
		}
		int[] maxScoreIndex = new int[2];
		maxScoreIndex[0] = maxScore;
		maxScoreIndex[1] = index;
		return maxScoreIndex;			
	}
	
	
	/**
	 * Method to compare the given Yahtzee object from the argument with the "this" object irrespective of order to see if they are equal.
	 * @param otherType a Yahtzeee object is compared with this "object"
	 * @return true or false
	 */
	public boolean equals(Yahtzee otherType) {
		for (int i = 0; i < 5; i++) {
			int j;
			for (j = 0; j < 5; j++) {
				if (this.dice[i].getValue() == otherType.dice[j].getValue())
					break;
				if (j == 4) return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Method to represent Dice array as a String for display purposes
	 * @return String representation of Dice array
	 */
	public String toString () {
		String s = "Dice: {";
		for (int i = 0; i < 5; i++) {
			s += this.dice[i].getValue();
			if (i < dice.length -1)
				s += ", ";
		}
		s += "}";
		return s;
	}
}
