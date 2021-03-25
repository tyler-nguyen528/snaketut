package test;

//import java.lang.*;

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
    Controller con;
    Game g;

    JFrame frame = new JFrame("Snake");
    JButton restart_Butt = new JButton();
    JButton twoPlay_Butt = new JButton();
    JButton hardMode_Butt = new JButton();
    JButton normMode_Butt = new JButton();
    JPanel buttonsPanel = new JPanel();
    JTextArea gameArea = new JTextArea();
    JPanel gamePanel = new JPanel();
    PrintStream printStream = new PrintStream(new CustomOutputStream(gameArea));

    public Frame() {
        con = new Controller(this);

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

        twoPlay_Butt.setEnabled(false);
        hardMode_Butt.setEnabled(false);
        normMode_Butt.setEnabled(false);

        buttonsPanel.add(restart_Butt);
        buttonsPanel.add(twoPlay_Butt);
        buttonsPanel.add(hardMode_Butt);
        buttonsPanel.add(normMode_Butt);

        restart_Butt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restart_Butt.setText("Restart");
                restart_Butt.setEnabled(false);
                con.restartPressed();

            }
        });
        gameArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    con.w_Press();
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    con.a_Press();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    con.s_Press();
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    con.d_Press();
                }
            }
        });
        frame.add(buttonsPanel);

        gameArea.setLayout(null);
        gameArea.setBounds(2, 25, 780, 600);
        gameArea.setBackground(new java.awt.Color(255, 255, 255));
        gameArea.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        gameArea.setEditable(false);
        gameArea.setFont(new Font("monospaced", Font.PLAIN, 12));

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

class Threadtest implements Runnable {
    Game g;

    public Threadtest(Game g) {
        this.g = g;
    }

    public void run() {
        while (true) {
            if (!g.tick())
                break;
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("GAME OVER!!!!!!!!");
    }
}

class Controller {
    private Game g;
    Thread t;

    public Controller(Frame f) {
        g = new Game(f);
    }

    public void restartPressed() {
        t = new Thread(new Threadtest(g));
        t.start();

    }

    public void w_Press() {
        int[] temp = { 0, -1 };
        if (g.check_valid_input('w'))
            g.set_direction(temp);
    }

    public void a_Press() {
        int[] temp = { -1, 0 };
        if (g.check_valid_input('a'))
            g.set_direction(temp);
    }

    public void s_Press() {
        int[] temp = { 0, 1 };
        if (g.check_valid_input('s'))
            g.set_direction(temp);
    }

    public void d_Press() {
        int[] temp = { 1, 0 };
        if (g.check_valid_input('d'))
            g.set_direction(temp);
    }
}

public class Window {
    public static void main(String[] args) {
        Frame f = new Frame();
    }
}
