package test;

import java.util.Scanner;
import java.util.Random;

public class Game {

    private int score;
    private int height;
    private int width;
    private Snake s;
    private Apple a;
    private Random rand = new Random();
    private Frame frame;

    public Game(Frame frame) {
        score = 0;
        height = 33;
        width = 80;
        int[][] temp = { { 9, 4 }, { 9, 5 }, { 9, 6 }, { 9, 7 }, { 9, 8 } };
        int[] temp1 = { 0, -1 };

        s = new Snake(temp, temp1);

        int randA, randB;
        boolean f = true;

        do {
            randA = rand.nextInt(width);
            if (!s.check_x(randA)) {
                f = false;
            }
        } while (f);

        f = true;

        do {
            randB = rand.nextInt(height);
            if (!s.check_y(randB)) {
                f = false;
            }
        } while (f);

        a = new Apple(randA, randB);
        // frame = new Frame();
        this.frame = frame;
    }

    public boolean tick() {
        s.take_step(width, height);
        if (!s.check_bod_coll())
            return false;
        check_app_coll();
        render();
        return true;
    }

    public void set_direction(int[] new_dir) {
        s.set_direction(new_dir);
    }

    public boolean check_valid_input(char key) {
        int[] curr_dir = s.get_dir();

        switch (key) {
        case 'w':
            if (curr_dir[1] == 1) {
                return false;
            } else {
                return true;
            }
        case 'a':
            if (curr_dir[0] == 1) {
                return false;
            } else {
                return true;
            }

        case 's':
            if (curr_dir[1] == -1) {
                return false;
            } else {
                return true;
            }
        case 'd':
            if (curr_dir[0] == -1) {
                return false;
            } else {
                return true;
            }
        default:
            return false;
        }
    }

    public void check_app_coll() {
        if (s.get_head_x() == a.get_x() && s.get_head_y() == a.get_y()) {
            score++;
            int new_x, new_y;
            boolean f = true;

            do {
                new_x = rand.nextInt(width);
                if (!s.check_x(new_x)) {
                    f = false;
                }
            } while (f);

            f = true;

            do {
                new_y = rand.nextInt(height);
                if (!s.check_y(new_y)) {
                    f = false;
                }
            } while (f);

            a.set_x(new_x);
            a.set_y(new_y);
            s.add_body();
        }
    }

    public void render() {
        frame.gameArea.setText(null);
        System.out.print("+");
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.print("+");
        System.out.println("\t\tScore: " + score);

        for (int i = 0; i < height; i++) {
            System.out.print("|");

            for (int j = 0; j < width; j++) {
                if (s.check_head(j, i)) {
                    System.out.print("x");
                } else if (s.check_yx(j, i)) {
                    System.out.print("o");
                } else if (a.get_x() == j && a.get_y() == i) {
                    System.out.print("A");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("|");
        }

        System.out.print("+");
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println("+");

    }

    // public static void main(String[] args) {
    // Game g = new Game();

    // System.out.println("GAME OVER!!!!!!!!!!!");
    // }
}