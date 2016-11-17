package com.TOC.Project.DFA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by SHAAN on 07-11-16.
 */

public class InputPanel extends JPanel {

    private static JLabel lNoOfStates, lStartState, lFinalState, lInputSymbols;
    private static JTextField tfNoOfStates, tfStartState, tfFinalState, tfInputSymbols;
    private int noOfStates, startState, finalState, noOfInputSymbols = 0;
    private char[] inputSymbols = new char[10];
    private static JButton submit;
    private JLabel heading;

    GridBagConstraints g = new GridBagConstraints();
    private boolean isFocused = false;

    public InputPanel() {
        setBackground(new Color(255, 241, 211));
        init();
        customizeElements();
        configureLayout();
    }

    private void customizeElements() {
        heading.setFont(new Font("Forte", Font.BOLD, 84));
        heading.setForeground(new Color(235, 0, 30));
        lNoOfStates.setFont(new Font("Lucida Fax", Font.BOLD, 14));
        lFinalState.setFont(new Font("Lucida Fax", Font.BOLD, 14));
        lStartState.setFont(new Font("Lucida Fax", Font.BOLD, 14));
        lInputSymbols.setFont(new Font("Lucida Fax", Font.BOLD, 14));
        tfNoOfStates.setFont(new Font("Lucida Fax", Font.BOLD, 14));
        tfFinalState.setFont(new Font("Lucida Fax", Font.BOLD, 14));
        tfStartState.setFont(new Font("Lucida Fax", Font.BOLD, 14));
        tfInputSymbols.setFont(new Font("Lucida Fax", Font.BOLD, 14));
        submit.setFont(new Font("Lucida Fax", Font.BOLD, 14));
        submit.setBackground(new Color(16,255, 189));
    }

    private void init() {
        heading = new JLabel("DFA");
        lNoOfStates = new JLabel("Number of States : ");
        lStartState = new JLabel("Starting State : ");
        lFinalState = new JLabel("Final State : ");
        lInputSymbols = new JLabel("Input Symbols : ");
        tfNoOfStates = new JTextField(15);
        tfStartState = new JTextField(15);
        tfFinalState = new JTextField(15);
        tfInputSymbols = new JTextField(16);
        submit = new JButton("Submit");

        submit.addActionListener(new ActionListener() {
            JPanel tPanel;

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isFocused)
                    tfInputSymbols.setText("");
                getData();
                String message = "No of States : " + noOfStates + "\nStart State : " + startState + "\nFinal State : " + finalState + "\nInput Symbols : { ";
                int i;
                for(i = 0; i <noOfInputSymbols; i++) {
                    message += "'" + (char)inputSymbols[i] +  "' ";
                }
                message += "}\n\nPlease Click OK to enter the transitions";
                JOptionPane.showMessageDialog(null, message, "Information Entered Correctly !!!", JOptionPane.INFORMATION_MESSAGE);

                removeAll();
                revalidate();
                repaint();
                setLayout(new BorderLayout());
                JScrollPane scrollPane = new JScrollPane(new TransitionPanel(noOfStates, startState, finalState, inputSymbols, noOfInputSymbols));
                add(scrollPane, BorderLayout.CENTER);
            }
        });
    }

    private void getData() {
        try {
            noOfStates = Integer.parseInt(tfNoOfStates.getText());
            startState = Integer.parseInt(tfStartState.getText());
            finalState = Integer.parseInt(tfFinalState.getText());
            boolean checkStartState = (startState < 0 || startState >= noOfStates);
            if(checkStartState) {
                JOptionPane.showMessageDialog(null, "Start state should be between 0 and " + (noOfStates - 1), "ERROR!!!", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            boolean checkFinalState = (finalState < 0 || finalState >= noOfStates);
            if(checkFinalState) {
                JOptionPane.showMessageDialog(null, "Final state should be between 0 and " + (noOfStates - 1), "ERROR!!!", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            boolean checkInputSymbols = checkInputSym();
            System.out.println(checkInputSymbols);
            if(!checkInputSymbols) {
                JOptionPane.showMessageDialog(null, "Input Symbol should be entered in the following format: a b c ... and so on", "ERROR!!!", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR!!!",JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        getInputSymbols(tfInputSymbols.getText());

    }

    private boolean checkInputSym() {
        int i;
        String str = tfInputSymbols.getText().toString();
        System.out.println(str + str.length());
        for (i = 1; i < str.length(); i += 2) {
            System.out.println("he" + str.charAt(i) + "llo");
            if(str.charAt(i) != ' ') {
                System.out.println("false");
                return false;
            }
        }
        return true;
    }

    private void getInputSymbols(String str) {
        int i, j;
        for(i = 0, j=0; i<str.length(); i += 2, j++) {
            inputSymbols[j] = str.charAt(i);
            noOfInputSymbols ++;
        }
    }

    private void configureLayout() {
        setLayout(new GridBagLayout());

        //heading
        g.insets = new Insets(20,0,10,0);
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        g.anchor = GridBagConstraints.CENTER;
        add(heading,g);

        //No of states
        g.insets = new Insets(20,0,10,20);
        g.gridy ++;
        g.gridwidth = 1;
        g.anchor = GridBagConstraints.LINE_START;
        add(lNoOfStates,g);

        g.insets = new Insets(30,0,10,0);
        g.gridx ++;
        g.anchor = GridBagConstraints.LINE_START;
        add(tfNoOfStates,g);

        //start state
        g.insets = new Insets(10,0,10,20);
        g.gridx --;
        g.gridy ++;
        g.anchor = GridBagConstraints.LINE_START;
        add(lStartState,g);

        g.insets = new Insets(10,0,10,20);
        g.gridx ++;
        g.anchor = GridBagConstraints.LINE_START;
        add(tfStartState,g);

        //final state
        g.insets = new Insets(10,0,10,20);
        g.gridx --;
        g.gridy ++;
        g.anchor = GridBagConstraints.LINE_START;
        add(lFinalState,g);

        g.insets = new Insets(10,0,10,20);
        g.gridx ++;
        g.anchor = GridBagConstraints.LINE_START;
        add(tfFinalState,g);

        //input symbols
        g.insets = new Insets(10,0,10,20);
        g.gridx --;
        g.gridy ++;
        g.anchor = GridBagConstraints.LINE_START;
        add(lInputSymbols,g);

        g.insets = new Insets(10,0,10,20);
        g.gridx ++;
        g.anchor = GridBagConstraints.LINE_START;

        tfInputSymbols.setForeground(Color.lightGray);
        tfInputSymbols.setFont(new Font("Lucida Fax", Font.ITALIC, 14));
        tfInputSymbols.setText("Example: a b c ... and so on");
        tfInputSymbols.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tfInputSymbols.setText("");
                tfInputSymbols.setForeground(Color.BLACK);
                tfInputSymbols.setFont(new Font("Lucida Fax", Font.BOLD, 14));
                isFocused = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                //nothing
            }
        });
        add(tfInputSymbols,g);

        //submit button
        g.insets = new Insets(30, 0, 30, 0);
        g.anchor = GridBagConstraints.CENTER;
        g.gridx --;
        g.gridy ++;
        g.gridwidth = 2;
        add(submit,g);
    }

}
