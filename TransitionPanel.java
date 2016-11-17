package com.TOC.Project.DFA;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * Created by SHAAN on 08-11-16.
 */

public class TransitionPanel extends JPanel {

    private int i, j;
    private int noOfStates, startState, finalState, noOfInputSymbols;
    private char[] inputSymbols;
    private String str;

    private JLabel[] info;
    private JLabel[][] inputMessage;
    private JLabel[] statesLabels;
    private JTextField[][] trans;
    private JLabel lInputString;
    private JTextField tfInputString;
    private JButton check;

    private int[][] transMatrix;
    private String finalMessage;

    //Constructor
    public TransitionPanel(int noOfStates, int startState, int finalState, char[] inputSymbols, int noOfInputSymbols) {

        setEnabled(true);
        setLayout(new GridBagLayout());
        //setFont(new Font("timesnewroman",Font.BOLD,16));
        setVisible(true);
        setBackground(new Color(255, 241, 211));
        setSize(new Dimension(800,600));

        // assigning parameters to private variables
        this.noOfStates = noOfStates;
        this.startState = startState;
        this.finalState = finalState;
        this.noOfInputSymbols = noOfInputSymbols;
        this.inputSymbols = new char[noOfInputSymbols];
        for(i = 0; i < noOfInputSymbols; i++) {
            this.inputSymbols[i] = inputSymbols[i];
        }

        // initializing the variables
        transMatrix = new int[noOfStates][noOfInputSymbols];
        info = new JLabel[4];
        trans = new JTextField[noOfStates][noOfInputSymbols];
        inputMessage = new JLabel[noOfStates][noOfInputSymbols];
        statesLabels = new JLabel[noOfStates];
        lInputString = new JLabel("Enter the string to be checked : ");
        tfInputString = new JTextField(10);
        check = new JButton("Check");
        createStateLabels();
        createInputLabels();

        // creating the transition matrix
        for(i = 0; i < noOfStates; i++) {
            for(j = 0; j < noOfInputSymbols; j++) {
                trans[i][j] = new JTextField(1);
            }
        }

        // Generating the information message
        String  message[] = {   "No of States :         " + noOfStates,
                                "Start State :           " + startState,
                                "Final State :           " + finalState,
                                "Input Symbols :     { " };
        for(i = 0; i <noOfInputSymbols; i++) {
            message[3] += "'" + (char)inputSymbols[i] +  "' ";
        }
        message[3] += "}";

        // putting the messages into JLabels
        for(i = 0; i < 4; i++) {
            info[i] = new JLabel(message[i]);
        }

        customizeElements();
        configureTransitionPanel();
        addListener();

    }

    private void customizeElements() {
        for (i = 0; i < 4; i++) {
            info[i].setFont(new Font("Lucida Fax", Font.BOLD, 16));
            info[i].setForeground(new Color(0, 194, 110));
        }
        for (i = 0; i < noOfStates; i++) {
            statesLabels[i].setFont(new Font("Lucida Fax", Font.BOLD, 14));
            statesLabels[i].setForeground(new Color(203, 0, 255));
        }
        for(i = 0; i < noOfStates; i++) {
            for (j = 0; j < noOfInputSymbols; j++) {
                inputMessage[i][j].setFont(new Font("Lucida Fax", Font.BOLD, 12));
                trans[i][j].setFont(new Font("Lucida Fax", Font.BOLD, 12));
            }
        }

        lInputString.setFont(new Font("Lucida Fax", Font.BOLD, 14));
        lInputString.setForeground(new Color(255, 67,0));
        tfInputString.setFont(new Font("Lucida Fax", Font.BOLD, 14));
        check.setFont(new Font("Lucida Fax", Font.BOLD, 14));
        check.setBackground(new Color(29, 255, 163));
    }

    private void addListener() {
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    getData();
                    finalMessage = isAccepted();
                    JOptionPane.showMessageDialog(null,finalMessage,"RESULTS", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(1);
                }catch (NumberFormatException err) {
                    JOptionPane.showMessageDialog(null,err.getMessage(),"ERROR!!!", JOptionPane.ERROR_MESSAGE );
                }
            }
        });
    }

    private String isAccepted() {

        int i, j, curr = startState, d = 0;
        String message = "String to be checked : " + str + "\n";
        message += "        Starting State : " + startState + "\n";
        message += "        Current State : " + curr + "\n";
        for(i = 0; i < str.length(); i++) {
            message += "On reading '" + str.charAt(i) + "'\n";
            d = indexOf(inputSymbols,str.charAt(i));
            curr = transMatrix[curr][d];
            message += "        Current State : " + curr + "\n";
        }
        if(curr == finalState)
            message += "\nThe String entered is ACCEPTED!!!";
        else
            message += "\nThe String entered is REJECTED!!!";
        return message;
    }

    private int indexOf(char[] arr, char ch) {
        int index = -1;
        for (int i = 0; (i < arr.length) && (index == -1); i++) {
            if (arr[i] == ch) {
                index = i;
            }
        }
        return index;
    }

    private void getData() throws NumberFormatException{
        int i, j;

        // Generating the transition matrix
        //System.out.println();
        for(i = 0; i < noOfStates; i++) {
            for(j = 0; j < noOfInputSymbols; j++) {
                try {
                    transMatrix[i][j] = Integer.parseInt(trans[i][j].getText().toString());
                    if(transMatrix[i][j] < 0 || transMatrix[i][j] >= noOfStates)
                        JOptionPane.showMessageDialog(null,"State not Present!!!","ERROR!!!", JOptionPane.ERROR_MESSAGE );
                } catch (NumberFormatException e) {
                    throw e;
                }
                //System.out.print(transMatrix[i][j] + " " );
            }
            //System.out.println();
        }

        //Taking the input string to be checked
        str = tfInputString.getText().toString();
        //System.out.println(str);
    }

    private void configureTransitionPanel() {
        int i, j;
        GridBagConstraints tg = new GridBagConstraints();

        tg.gridx = 0;
        tg.gridy = 0;
        tg.insets = new Insets(50, 250, 3, 3);
        tg.anchor = GridBagConstraints.LINE_START;
        add(info[0], tg);
        tg.insets = new Insets(3, 250, 3, 3);
        for(i = 1; i <=3; i++) {
            tg.gridy++;
            add(info[i], tg);
        }

        for(i = 0; i < noOfStates; i++) {
            tg.insets = new Insets(30,150,10,3);
            tg.gridy ++;
            add(statesLabels[i], tg);
            for (j = 0; j < noOfInputSymbols; j++) {
                tg.insets = new Insets(3,200,3,3);
                tg.gridy ++;
                add(inputMessage[i][j],tg);
                tg.insets = new Insets(3,20,3,3);
                tg.gridx ++;
                trans[i][j].setColumns(2);
                add(trans[i][j],tg);
                tg.gridx --;
            }
        }

        tg.insets = new Insets(20,100,10,0);
        tg.gridy ++;
        add(lInputString,tg);

        tg.insets = new Insets(20,0,10,100);
        tg.gridx ++;
        add(tfInputString,tg);

        tg.insets = new Insets(10,3,10,3);
        tg.gridy ++;
        tg.gridx --;
        tg.gridwidth = 2;
        tg.anchor = GridBagConstraints.CENTER;
        add(check,tg);

    }

    private void createInputLabels() {
        int i, j;
        for (i = 0; i < noOfStates; i++) {
            for(j = 0; j< noOfInputSymbols; j++) {
                inputMessage[i][j] = new JLabel("On reading '" + (char)inputSymbols[j] + "' goes to State : ");
            }
        }
    }
    private void createStateLabels() {
        int i;
        for(i = 0; i<noOfStates; i++) {
            statesLabels[i] = new JLabel("Transitions for State " + i + " : ");
        }
    }

}
