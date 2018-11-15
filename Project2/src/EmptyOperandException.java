/**
 * Authors: Alvin Nguyen
 * Date: April 11, 2018
 * Purpose: Create Empty Operand Exception
 */

public class EmptyOperandException extends Exception {

	public EmptyOperandException() {
		super("Numerator and denominator cannot be empty");
	}
}
