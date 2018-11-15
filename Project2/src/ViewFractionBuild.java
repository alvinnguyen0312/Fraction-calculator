/**
 * Authors: Alvin Nguyen
 * Date: April 11, 2018
 * Purpose: View Fraction Build
 */

import javax.swing.*;
import java.awt.*;

public class ViewFractionBuild extends ViewFraction {

    private JTextArea txtaBuild;

    public ViewFractionBuild() {
        super("Here is your fraction: ");
    }

    @Override
    protected void createContentView() {
        this.txtaBuild = new JTextArea(15, 15);
        this.txtaBuild.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        this.txtaBuild.setBackground(Color.WHITE);
        this.txtaBuild.setEditable(false);

        this.panelContent.add(this.txtaBuild);
    }

    public void displayResult(String s) {
        this.txtaBuild.setText(s);
    }
}
