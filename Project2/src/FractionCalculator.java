/**
 * Authors: Alvin Nguyen
 * Date: April 11, 2018
 * Purpose: Simulate a Fraction Calculator
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.util.*;

public class FractionCalculator extends JFrame {

    private ViewFractionInput viewFractionInput;
    private ViewFractionBuild viewFractionBuild;
    private ViewFractionOperation viewFractionOperation;
    private ViewFractionResult viewFractionResult;
    private FractionOperationAction operationAction;
    private JMenuBar mb;
    private boolean isStartOver = false;

 
    public FractionCalculator() {
        this.createView();
        this.operationAction = new FractionOperationAction(this.viewFractionResult);

        // listen to build button clicked
        this.viewFractionInput.getBtnBuildFraction().addActionListener(e -> {
            int num, den;

            try {
                num = getNum();
            } catch (Exception ex) {
                showDialog(ex.getMessage());
                this.viewFractionInput.getTxtNum().requestFocus();
                return;
            }

            try {
                den = getDen();
            } catch (Exception ex) {
                showDialog(ex.getMessage());
                this.viewFractionInput.getTxtDen().requestFocus();
                return;
            }

            ArrayList<Fraction> fractionList = this.operationAction.getFractionList();
            fractionList.add(new Fraction(num, den));
            this.operationAction.setFractionList(fractionList);
            viewFractionBuild.displayResult(FractionCalculator.fractionListString(fractionList));
        });

        // listen to start over button clicked
        this.viewFractionInput.getBtnStartOver().addActionListener(e -> {
            // enable start over state
            this.isStartOver = true;

            // clear all fractions added
            ArrayList<Fraction> fractionList = this.operationAction.getFractionList();
            fractionList.clear();
            this.operationAction.setFractionList(fractionList);

            // clear input and selected option
            this.viewFractionInput.getTxtNum().setText("");
            this.viewFractionInput.getTxtDen().setText("");
            this.viewFractionBuild.displayResult("");
            this.viewFractionResult.displayResult("");
            this.viewFractionOperation.getComboOperations().setSelectedIndex(0);
        });

        // listen to operation list change
        this.viewFractionOperation.getComboOperations().addActionListener(e -> {

            // if start over state enabled, do nothing but disable it
            if(this.isStartOver) {
                this.isStartOver = false;
            } else {
                JComboBox comboOperations = (JComboBox) e.getSource();
                FractionOperation operation = (FractionOperation) comboOperations.getSelectedItem();
                this.operationAction.doOperation(operation.getType());
            }

            if(this.operationAction.getFractionList().size() == 0) {
                this.viewFractionInput.getTxtNum().requestFocus();
            }
        });
    }

    private void createView() {
        // add menu bar
        this.createMenu();

        // initiate children panels
        this.viewFractionInput = new ViewFractionInput();
        this.viewFractionBuild = new ViewFractionBuild();
        this.viewFractionOperation = new ViewFractionOperation();
        this.viewFractionResult = new ViewFractionResult();

        // setting frame
        this.setTitle("Fraction Calculator - Alvin Nguyen");
        this.setSize(900, 300);
        this.setBackground(Color.GRAY);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1, 4));

        //add all panels and menu to main frame
        this.setJMenuBar(this.mb);
        this.add(this.viewFractionInput.getPanelRoot());
        this.add(this.viewFractionBuild.getPanelRoot());
        this.add(this.viewFractionOperation.getPanelRoot());
        this.add(this.viewFractionResult.getPanelRoot());

        // set frame visible
        this.setVisible(true);
    }

    private void createMenu() {
        this.mb = new JMenuBar();

        // main menu
        JMenu menu = new JMenu("Operations");

        // menu item about
        JMenuItem miAbout = new JMenuItem("About", KeyEvent.VK_U);
        miAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK));
        miAbout.addActionListener(e ->
                this.showDialog("FractionCalculator - 2018\n@author: Alvin Nguyen", false)
        );

        // menu operations item
        for(FractionOperation fo : FractionOperationAction.getOperationList()) {
            JMenuItem mi = new JMenuItem(fo.getLabel(), fo.getMnemonic());
            mi.setAccelerator(KeyStroke.getKeyStroke(fo.getAccelerator(), InputEvent.CTRL_MASK));
            mi.addActionListener(e -> this.operationAction.doOperation(fo.getType()));
            menu.add(mi);
        }

        // add separator between operation items and about
        menu.addSeparator();

        // add about
        menu.add(miAbout);

        // add menu into menu bar
        this.mb.add(menu);
    }

    /**
     *
     * @return an int
     * @throws EmptyOperandException
     * @throws LongOperandException
     */
    private int getNum() throws EmptyOperandException, LongOperandException {
        return this.parseInput(this.viewFractionInput.getTxtNum().getText());
    }

    /**
     * @return an int
     * @throws EmptyOperandException
     * @throws DenominatorOfZeroException
     * @throws LongOperandException
     */
    private int getDen() throws EmptyOperandException, DenominatorOfZeroException, LongOperandException {
        int result = this.parseInput(this.viewFractionInput.getTxtDen().getText());

        // if den is 0, throw error
        if (result == 0) {
            throw new DenominatorOfZeroException();
        }

        return result;
    }

    /**
     * @param string s
     * @return an int
     * @throws EmptyOperandException
     * @throws LongOperandException
     * @throws NonIntegerException
     */
    private int parseInput(String s) throws EmptyOperandException, LongOperandException, NonIntegerException {
        if (s.equals("")) {
            throw new EmptyOperandException();
        }

        int result;

        try {
            result = Integer.parseInt(s);
        } catch (NumberFormatException e) {

            try {
                new BigInteger(s);
            } catch (NumberFormatException ex) {
                throw new NonIntegerException();
            }

            throw new LongOperandException();
        }

        return result;

    }

    /**
     * @param message
     * @return none
     */
    private void showDialog(String message) {
        final JPanel warningPanel = new JPanel();
        JOptionPane.showMessageDialog(warningPanel, "Warning: " + message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * @param message
     * @param isWarning
     * @return none
     */
    private void showDialog(String message, boolean isWarning) {
        if(isWarning) {
            this.showDialog(message);
        } else {
            final JPanel warningPanel = new JPanel();
            JOptionPane.showMessageDialog(warningPanel, "" + message, "About FractionCalculator", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * @return string
     */
    private static String fractionListString(ArrayList<Fraction> fractionList) {
        String fractionString = "";

        for (Fraction f : fractionList) {
            fractionString += f.toString() + " ";
        }

        return fractionString;
    }

    /**
     * @param args
     * @return none
     */
    public static void main(String[] args) {
        new FractionCalculator();
    }
}
