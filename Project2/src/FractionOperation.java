/**
 * Authors: Alvin Nguyen
 * Date: April 11, 2018
 * Purpose: Create Fraction Operation
 */

public class FractionOperation {

    private FractionOperationType type;

    private String label;

    private int mnemonic;

    private int accelerator;

    public FractionOperation(FractionOperationType type, String label, int key) {
        this.type = type;
        this.label = label;
        this.mnemonic = key;
        this.accelerator = key;
    }

    public FractionOperation(FractionOperationType type, String label, int mnemonic, int accelerator) {
        this.type = type;
        this.label = label;
        this.mnemonic = mnemonic;
        this.accelerator = accelerator;
    }

    public FractionOperationType getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public int getMnemonic() {
        return mnemonic;
    }

    public int getAccelerator() {
        return accelerator;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
