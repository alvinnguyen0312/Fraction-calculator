/**
 * Authors: Alvin Nguyen
 * Date: April 11, 2018
 * Purpose: View Fraction
 */

import javax.swing.*;
import java.awt.*;

public abstract class ViewFraction {
    protected JPanel panelRoot;
    protected JPanel panelContent;
    protected JLabel labelRoot;
    
    public ViewFraction(String labelRoot) {
        this.panelRoot = new JPanel();
        this.panelContent = new JPanel();
        this.panelContent.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        this.labelRoot = new JLabel(labelRoot, SwingConstants.CENTER);
        this.createContentView();
        this.createRootView();
    }

    private void createRootView() {
        this.panelRoot.setLayout(new BorderLayout());
        this.panelRoot.add(this.labelRoot, BorderLayout.NORTH);
        this.panelRoot.add(this.panelContent, BorderLayout.CENTER);
    }

    protected abstract void createContentView();

    public JPanel getPanelRoot() {
        return panelRoot;
    }
}
