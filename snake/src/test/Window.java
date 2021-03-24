package test;

import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.border.Border;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import java.util.*;

class CustomOutputStream extends OutputStream {
    private JTextArea textArea;

    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.append(String.valueOf((char) b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}

class Frame extends JFrame {
    // Controller con;

    JFrame frame = new JFrame("Snake");
    JButton restart_Butt = new JButton();
    JButton twoPlay_Butt = new JButton();
    JButton hardMode_Butt = new JButton();
    JButton normMode_Butt = new JButton();
    JPanel buttonsPanel = new JPanel();
    JTextArea gameArea = new JTextArea();
    JPanel gamePanel = new JPanel();

    public Frame() {
        // con = new Controller(this);

        restart_Butt.setText("Start");
        twoPlay_Butt.setText("Two Players");
        hardMode_Butt.setText("Hard Mode");
        normMode_Butt.setText("Normal Mode");

        restart_Butt.setBounds(125, 0, 120, 20);
        twoPlay_Butt.setBounds(250, 0, 120, 20);
        hardMode_Butt.setBounds(375, 0, 120, 20);
        normMode_Butt.setBounds(500, 0, 120, 20);

        buttonsPanel.setLayout(null);
        buttonsPanel.setBounds(0, 0, 785, 20);

        buttonsPanel.add(restart_Butt);
        buttonsPanel.add(twoPlay_Butt);
        buttonsPanel.add(hardMode_Butt);
        buttonsPanel.add(normMode_Butt);

        // restart_Butt.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // restart_Butt.setText("Restart");
        // con.restartPressed();
        // }
        // });

        frame.add(buttonsPanel);

        gameArea.setLayout(null);
        gameArea.setBounds(2, 25, 780, 600);
        gameArea.setBackground(new java.awt.Color(255, 255, 255));
        gameArea.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        gameArea.setEditable(false);
        gameArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        PrintStream printStream = new PrintStream(new CustomOutputStream(gameArea));
        System.setOut(printStream);
        gamePanel.add(gameArea);
        gamePanel.setLayout(null);
        gamePanel.setBounds(0, 25, 785, 600);
        frame.add(gamePanel);

        frame.setSize(800, 666);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class Controller {
    private Game g;
    Frame f;

    public Controller(Frame f) {
        // this.f = f;
        // g = new Game(f);
    }

    // public void restartPressed() {
    // f.clearPanel();
    // g.render();
    // }
}
/*
 * public class Window { public static void main(String[] args) { Frame f = new
 * Frame(); }
 * 
 * }
 */