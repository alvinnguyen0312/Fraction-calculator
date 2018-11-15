/**
 * Authors: Alvin Nguyen
 * Date: April 11, 2018
 * Purpose: Simulate a Fraction Calculator
 */


public class Fraction implements Comparable<Fraction> {

	private int num;
	private int den;

    /**Purpose: constructor to set den and num to 1
     *@param: none
     *@return: none
     */
	public Fraction() {
		this.num = 1;
		this.den = 1;
	}

    /*
     * @param int num
     * @param int den
     * @return: none
     */
	public Fraction(int num, int den) {
		this.num = num;
		this.den = den;
	}

    /**
     * Purpose: to convert to decimal
     * @param: none
     * @return: double
     */
	public double convertToDecimal() {
		return this.num*1.0/this.den;
	}

    /**
     * Purpose: to convert to reciprocal
     * @param: none
     * @return: object Fraction
     */
	public Fraction convertToReciprocal() {
		return new Fraction(this.den, this.num);
	}

    /**
     * Purpose: add two fractions
     * @param: an object Fraction 
     * @return: an object 
     */
	public Fraction add(Fraction f) {
		Fraction result = new Fraction(
				this.num*f.den + this.den*f.num,
				this.den*f.den
		);
		
		return result.lowestTerms();
	}
	/**
     * Purpose: multiply two fractions
     * @param: an object Fraction 
     * @return: an object 
     */

	public Fraction multiply(Fraction f) {
		Fraction result = new Fraction(this.num*f.num, this.den*f.den);

		return result.lowestTerms();
	}

    /**
     * Purpose: to check if two fractions are equal
     * @param object Fraction
     * @return boolean
     */
	public boolean equals(Fraction f) {
		return this.num*f.den == f.num*this.den;
	}

    /**
     * Purpose: check if this Fraction is greater than the other one
     * @param object Fraction
     * @return boolean
     */
	public boolean greaterThan(Fraction f) {
		return this.num*f.den > f.num*this.den;
	}

    /**
     *@param: none
     * @return: an object Fraction
     */
	public Fraction lowestTerms() {
		int min = this.num > this.den ? this.den : this.num;
		int lowestNum = this.num;
		int lowestDen = this.den;
		
		for(int i = min; i > 0; i--) {
			if(lowestNum%i == 0 && lowestDen%i == 0) {
				lowestNum /= i;
				lowestDen /= i;
				break;
			}
		}
		
		return new Fraction(lowestNum, lowestDen);
	}

	/**getter method
	 * @return an int num
	 * @param none
	 */
	public int getNum() {
		return num;
	}

	/**setter method
	 * @param num the num to set
	 * @return none
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**getter method
	 * @return the den (an int)
	 * @param none
	 */
	public int getDen() {
		return den;
	}

	/**setter method
	 * @param den the den to set
	 * @return none
	 */
	public void setDen(int den) {
		this.den = den;
	}

    @Override
	public int compareTo(Fraction o) {

	    if(this.convertToDecimal() == o.convertToDecimal()) {
	        return 0;
        } else if (this.convertToDecimal() > o.convertToDecimal()) {
	        return 1;
        }

		return -1;
	}

	@Override
	public String toString() {
		return String.format("%d/%d", this.num, this.den);
	}
}
