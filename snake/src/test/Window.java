package snake.src.test;

//import java.lang.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

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

class P1_Control extends KeyAdapter {
    private Controller con;

    public P1_Control(Controller con) {
        this.con = con;
    }

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
}

class P2_Control extends KeyAdapter {
    private Controller con;

    public P2_Control(Controller con) {
        this.con = con;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            con.up_Press();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            con.left_Press();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            con.down_Press();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            con.right_Press();
        }
    }
}

class Frame extends JFrame {
    Controller con;

    P1_Control wasd;
    P2_Control arrows;

    JFrame frame = new JFrame("Snake");
    JButton start_Butt = new JButton();
    JButton oneNorm_Butt = new JButton();
    JButton twoNorm_Butt = new JButton();
    JButton oneHard_Butt = new JButton();
    JButton twoHard_Butt = new JButton();
    JPanel buttonsPanel = new JPanel();
    JTextArea gameArea = new JTextArea();
    JPanel gamePanel = new JPanel();
    PrintStream printStream = new PrintStream(new CustomOutputStream(gameArea));

    public Frame() {
        con = new Controller(this);

        start_Butt.setText("Start");
        oneNorm_Butt.setText("1P Normal");
        twoNorm_Butt.setText("2P Normal");
        oneHard_Butt.setText("1P Hard");
        twoHard_Butt.setText("2P Hard");

        start_Butt.setBounds(25, 0, 120, 20);
        oneNorm_Butt.setBounds(180, 0, 120, 20);
        twoNorm_Butt.setBounds(335, 0, 120, 20);
        oneHard_Butt.setBounds(490, 0, 120, 20);
        twoHard_Butt.setBounds(645, 0, 120, 20);

        start_Butt.setEnabled(false);

        buttonsPanel.setLayout(null);
        buttonsPanel.setBounds(0, 0, 800, 20);

        buttonsPanel.add(start_Butt);
        buttonsPanel.add(oneNorm_Butt);
        buttonsPanel.add(twoNorm_Butt);
        buttonsPanel.add(oneHard_Butt);
        buttonsPanel.add(twoHard_Butt);

        start_Butt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start_Butt.setEnabled(false);
                oneNorm_Butt.setEnabled(false);
                twoNorm_Butt.setEnabled(false);
                oneHard_Butt.setEnabled(false);
                twoHard_Butt.setEnabled(false);
                // start();
                con.startPressed();
            }
        });

        oneNorm_Butt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameArea.setText(null);
                start_Butt.setEnabled(true);
                oneNorm_Butt.setEnabled(false);
                oneHard_Butt.setEnabled(true);
                twoNorm_Butt.setEnabled(true);
                twoHard_Butt.setEnabled(true);
                System.out.println("One Player on Normal Mode Selected. Press Start!!");
                con.one_Norm_Pressed();
            }
        });

        oneHard_Butt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameArea.setText(null);
                start_Butt.setEnabled(true);
                oneNorm_Butt.setEnabled(true);
                oneHard_Butt.setEnabled(false);
                twoNorm_Butt.setEnabled(true);
                twoHard_Butt.setEnabled(true);
                System.out.println("One Player on Hard Mode Selected. Press Start!!");
                con.one_Hard_Pressed();
            }
        });

        twoNorm_Butt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameArea.setText(null);
                start_Butt.setEnabled(true);
                oneNorm_Butt.setEnabled(true);
                oneHard_Butt.setEnabled(true);
                twoNorm_Butt.setEnabled(false);
                twoHard_Butt.setEnabled(true);
                System.out.println(
                        "Two Players on Normal Mode Selected. \nTry to make the other player hit you! Press Start!!");
                con.two_Norm_Pressed();
            }
        });

        twoHard_Butt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameArea.setText(null);
                start_Butt.setEnabled(true);
                oneNorm_Butt.setEnabled(true);
                oneHard_Butt.setEnabled(true);
                twoNorm_Butt.setEnabled(true);
                twoHard_Butt.setEnabled(false);
                System.out.println(
                        "Two Players on Hard Mode Selected. \nTry to make the other player hit you! Press Start!!");
                con.two_Hard_Pressed();
            }
        });

        wasd = new P1_Control(con);
        arrows = new P2_Control(con);

        frame.add(buttonsPanel);

        gameArea.setLayout(null);
        gameArea.setBounds(2, 25, 780, 600);
        gameArea.setBackground(new java.awt.Color(255, 255, 255));
        gameArea.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        gameArea.setEditable(false);
        gameArea.setFont(new Font("monospaced", Font.BOLD, 25));

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

    public void end() {
        gameArea.removeKeyListener(wasd);
        start_Butt.setEnabled(false);
        oneNorm_Butt.setEnabled(true);
        twoNorm_Butt.setEnabled(true);
        oneHard_Butt.setEnabled(true);
        twoHard_Butt.setEnabled(true);
    }

    public void end2() {
        gameArea.removeKeyListener(wasd);
        gameArea.removeKeyListener(arrows);
        start_Butt.setEnabled(false);
        oneNorm_Butt.setEnabled(true);
        twoNorm_Butt.setEnabled(true);
        oneHard_Butt.setEnabled(true);
        twoHard_Butt.setEnabled(true);
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
        g.print_Over();
    }
}

class Controller {
    private Game g;
    private Game_State Gs;
    Frame f;
    Thread t;

    public Controller(Frame f) {
        this.f = f;
    }

    public void startPressed() {
        g = Gs.execute(f);
        t = new Thread(new Threadtest(g));
        t.start();
    }

    public void one_Norm_Pressed() {
        Gs = new Normal_1P_State();
    }

    public void two_Norm_Pressed() {
        Gs = new Normal_2P_State();
    }

    public void one_Hard_Pressed() {
        Gs = new Hard_1P_State();
    }

    public void two_Hard_Pressed() {
        Gs = new Hard_2P_State();
    }

    public void w_Press() {
        int[] temp = { 0, -1 };
        g.check_valid_input(1, temp);
    }

    public void a_Press() {
        int[] temp = { -1, 0 };
        g.check_valid_input(1, temp);
    }

    public void s_Press() {
        int[] temp = { 0, 1 };
        g.check_valid_input(1, temp);
    }

    public void d_Press() {
        int[] temp = { 1, 0 };
        g.check_valid_input(1, temp);
    }

    public void up_Press() {
        int[] temp = { 0, -1 };
        g.check_valid_input(2, temp);
    }

    public void left_Press() {
        int[] temp = { -1, 0 };
        g.check_valid_input(2, temp);
    }

    public void down_Press() {
        int[] temp = { 0, 1 };
        g.check_valid_input(2, temp);
    }

    public void right_Press() {
        int[] temp = { 1, 0 };
        g.check_valid_input(2, temp);
    }
}

interface Game_State {
    public Game execute(Frame f);
}

class Normal_1P_State implements Game_State {
    public Game execute(Frame f) {
        int[] body_Array = { 4, 5, 6, 7, 8 };
        Game g = new Game_1P(f, body_Array);
        f.gameArea.addKeyListener(f.wasd);
        return g;
    }
}

class Hard_1P_State implements Game_State {
    public Game execute(Frame f) {
        int[] body_Array = { 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        Game g = new Game_1P(f, body_Array);
        f.gameArea.addKeyListener(f.wasd);
        return g;
    }
}

class Normal_2P_State implements Game_State {
    public Game execute(Frame f) {
        int[] body_Array = { 4, 5, 6, 7, 8 };
        Game g = new Game_2P(f, body_Array);
        f.gameArea.addKeyListener(f.wasd);
        f.gameArea.addKeyListener(f.arrows);
        return g;
    }
}

class Hard_2P_State implements Game_State {
    public Game execute(Frame f) {
        int[] body_Array = { 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        Game g = new Game_2P(f, body_Array);
        f.gameArea.addKeyListener(f.wasd);
        f.gameArea.addKeyListener(f.arrows);
        return g;
    }
}

public class Window {
    public static void main(String[] args) {
        Frame f = new Frame();
    }
}
