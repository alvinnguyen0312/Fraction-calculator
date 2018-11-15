/**
 * Authors: Alvin Nguyen
 * Date: April 11, 2018
 * Purpose: Display Non Integer Exception
 */

public class NonIntegerException extends NumberFormatException {
    public NonIntegerException() {
        super("Only integer values are allow");
    }
}
