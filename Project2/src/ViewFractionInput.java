/**
 * Authors: Alvin Nguyen
 * Date: April 11, 2018
 * Purpose: View Fraction Input
 */

import javax.swing.*;
import java.awt.*;

public class ViewFractionInput extends ViewFraction {

    private JLabel labelNum;

    private JLabel labelDen;

    private JTextField txtNum;

    private JTextField txtDen;

    private JButton btnBuildFraction;

    private JButton btnStartOver;

    public ViewFractionInput() {
        super("Enter a fraction:");
    }

    @Override
    protected void createContentView() {
        // initialize components
        this.panelRoot = new JPanel();
        this.panelContent = new JPanel();
        this.labelNum = new JLabel("Numerator", SwingConstants.CENTER);
        this.labelDen = new JLabel("Denominator", SwingConstants.CENTER);
        this.txtNum = new JTextField();
        this.txtDen = new JTextField();
        this.btnBuildFraction = new JButton("Build Fraction");
        this.btnStartOver = new JButton("Start Over!");

        // add components into panel
        this.panelContent.setLayout(new GridLayout(6,1));
        this.panelContent.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        this.panelContent.add(this.labelNum);
        this.panelContent.add(this.txtNum);
        this.panelContent.add(this.labelDen);
        this.panelContent.add(this.txtDen);
        this.panelContent.add(this.btnBuildFraction);
        this.panelContent.add(this.btnStartOver);
    }

    public JLabel getLabelNum() {
        return labelNum;
    }

    public void setLabelNum(JLabel labelNum) {
        this.labelNum = labelNum;
    }

    public JLabel getLabelDen() {
        return labelDen;
    }

    public void setLabelDen(JLabel labelDen) {
        this.labelDen = labelDen;
    }

    public JTextField getTxtNum() {
        return txtNum;
    }

    public void setTxtNum(JTextField txtNum) {
        this.txtNum = txtNum;
    }

    public JTextField getTxtDen() {
        return txtDen;
    }

    public void setTxtDen(JTextField txtDen) {
        this.txtDen = txtDen;
    }

    public JButton getBtnBuildFraction() {
        return btnBuildFraction;
    }

    public void setBtnBuildFraction(JButton btnBuildFraction) {
        this.btnBuildFraction = btnBuildFraction;
    }

    public JButton getBtnStartOver() {
        return btnStartOver;
    }

    public void setBtnStartOver(JButton btnStartOver) {
        this.btnStartOver = btnStartOver;
    }
}
