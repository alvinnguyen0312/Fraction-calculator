/**
 * Authors: Alvin Nguyen
 * Date: April 11, 2018
 * Purpose: View Fraction Result
 */

import javax.swing.*;
import java.awt.*;

public class ViewFractionResult extends ViewFraction {

    private JTextArea txtaResult;
   
    public ViewFractionResult() {
        super("Here is your operation: ");
    }

    @Override
    protected void createContentView() {
        this.txtaResult = new JTextArea(15, 15);
        this.txtaResult.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        this.txtaResult.setBackground(Color.WHITE);
        this.txtaResult.setEditable(false);

        this.panelContent.add(this.txtaResult);
    }

    public void displayResult(String s) {
        this.txtaResult.setText(s);
    }
}
