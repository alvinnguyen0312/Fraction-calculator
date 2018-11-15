/**
 * Authors: Alvin Nguyen
 * Date: April 11, 2018
 * Purpose: Create Fraction Operation Action
 */

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

public class FractionOperationAction {

    ArrayList<Fraction> fractionList;

    ViewFractionResult viewFractionResult;

    public FractionOperationAction(ViewFractionResult viewFractionResult) {
        this.fractionList = new ArrayList<>();
        this.viewFractionResult = viewFractionResult;
    }

    /**
     * @param type
     */
    public void doOperation(FractionOperationType type) {
        // if fraction list empty, show warning
        if (this.fractionList.size() == 0) {
            this.showDialog("No fraction has been entered yet!");
            return;
        }

        // do operation base on operation choice
        switch (type) {
            case DECIMAL:
            case RECIPROCAL:
                this.doUnaryOperation(type);
                break;
            case ADD:
            case MULTIPLY:
            case IS_EQUAL:
            case IS_GREATER:
                this.doBinaryOperation(type);
                break;
            case LOWEST_TERM:
            case SORT:
                this.doSetOperation(type);
                break;
        }
    }

    /**
     * @param type
     */
    private void doUnaryOperation(FractionOperationType type) {
        switch (type) {
            case DECIMAL:
                this.viewFractionResult.displayResult(
                        String.format("%.2f", this.fractionList.get(this.fractionList.size() - 1).convertToDecimal())
                );
                break;
            case RECIPROCAL:
                this.viewFractionResult.displayResult(
                        this.fractionList.get(this.fractionList.size() - 1).convertToReciprocal().toString()
                );
                break;
        }
    }

    /**
     * @param type
     */
    private void doBinaryOperation(FractionOperationType type) {
        if (this.fractionList.size() < 2) {
            this.showDialog("Insufficient amount of fractions.");
        } else {
            Fraction a = this.fractionList.get(this.fractionList.size() - 2);
            Fraction b = this.fractionList.get(this.fractionList.size() - 1);
            switch (type) {
                case ADD:
                    this.viewFractionResult.displayResult(a.add(b).toString());
                    break;
                case MULTIPLY:
                    this.viewFractionResult.displayResult(a.multiply(b).toString());
                    break;
                case IS_EQUAL:
                    this.viewFractionResult.displayResult(
                            String.format("%s is %sequal to %s ", a.toString(), a.equals(b) ? "" : "not ", b.toString())
                    );
                    break;
                case IS_GREATER:
                    this.viewFractionResult.displayResult(
                            String.format("%s is %sgreater than %s ", a.toString(), a.greaterThan(b) ? "" : "not ", b.toString())
                    );
                    break;
            }
        }
    }

    /**
     * @param type
     */
    private void doSetOperation(FractionOperationType type) {
        String result = "";
        ArrayList<Fraction> cpFractionList = new ArrayList<>(fractionList);
        switch (type) {
            case LOWEST_TERM:
                for (Fraction f : cpFractionList) {
                    result += (f.lowestTerms().toString() + " ");
                }
                this.viewFractionResult.displayResult(result);
                break;
            case SORT:
                Collections.sort(cpFractionList);
                for (Fraction f : cpFractionList) {
                    result += (f.toString() + " ");
                }
                this.viewFractionResult.displayResult(result);
                break;
        }
    }


    /**
     * @param message
     */
    private void showDialog(String message) {
        final JPanel warningPanel = new JPanel();
        JOptionPane.showMessageDialog(warningPanel, "Warning: " + message, "Warning", JOptionPane.WARNING_MESSAGE);
    }


    public static ArrayList<FractionOperation> getOperationList() {
        ArrayList<FractionOperation> operationList = new ArrayList<>();
        operationList.add(new FractionOperation(FractionOperationType.DECIMAL, "Decimal", KeyEvent.VK_D));
        operationList.add(new FractionOperation(FractionOperationType.RECIPROCAL, "Reciprocal", KeyEvent.VK_R));
        operationList.add(new FractionOperation(FractionOperationType.ADD, "Fraction 1 + Fraction 2", KeyEvent.VK_A));
        operationList.add(new FractionOperation(FractionOperationType.MULTIPLY, "Fraction 1 x Fraction 2", KeyEvent.VK_M));
        operationList.add(new FractionOperation(FractionOperationType.IS_EQUAL, "Is Fraction 1 = Fraction 2", KeyEvent.VK_E));
        operationList.add(new FractionOperation(FractionOperationType.IS_GREATER, "Is Fraction 1 > Fraction 2", KeyEvent.VK_G));
        operationList.add(new FractionOperation(FractionOperationType.LOWEST_TERM, "Lowest Terms", KeyEvent.VK_L));
        operationList.add(new FractionOperation(FractionOperationType.SORT, "Sort List", KeyEvent.VK_S));

        return operationList;
    }

    public ArrayList<Fraction> getFractionList() {
        return fractionList;
    }

    public void setFractionList(ArrayList<Fraction> fractionList) {
        this.fractionList = fractionList;
    }
}
