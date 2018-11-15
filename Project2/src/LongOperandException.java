/**
 * Authors: Alvin Nguyen
 * Date: April 11, 2018
 * Purpose: Display Long Operand Exception
 */

public class LongOperandException extends NumberFormatException {
	public LongOperandException() {
		super("Operand entered exceeds integer capacity");
	}
}
