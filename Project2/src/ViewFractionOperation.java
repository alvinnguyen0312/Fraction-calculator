/**
 * Authors: Alvin Nguyen
 * Date: April 11, 2018
 * Purpose: View Fraction Operation
 */

import javax.swing.*;

public class ViewFractionOperation extends ViewFraction {

    private JComboBox comboOperations;

    public ViewFractionOperation() {
        super("Select an operation:");
    }

    @Override
    protected void createContentView() {
        this.comboOperations = new JComboBox(FractionOperationAction.getOperationList().toArray());
        this.panelContent.add(this.comboOperations);
    }
    
    public JComboBox getComboOperations() {
        return comboOperations;
    }
}
