package com.TOC.Project.DFA;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;


/**
 * Created by SHAAN on 07-11-16.
 */
public class Main {

    private static JFrame frame;
    private static JPanel panel;
    private static JScrollPane scrollPane;

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {}
        init();

        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800,600));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private static void init() {
        frame = new JFrame("DFA Minimization");
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Lucida Fax", Font.BOLD, 12)));
        scrollPane = new JScrollPane(new InputPanel());
        panel = new JPanel();
    }

}
