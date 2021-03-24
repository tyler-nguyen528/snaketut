package test;

import java.util.Scanner;
import java.util.Random;

public class Game {

    private int score;
    private int height;
    private int width;
    private Snake s;
    private Apple a;
    private boolean flag = true;
    private Random rand = new Random();

    public Game(int height, int width) {
        score = 0;
        this.height = height;
        this.width = width;
        int[][] temp = { { 9, 4 }, { 9, 5 }, { 9, 6 }, { 9, 7 } };
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
        start();
    }

    public void start() {
        Scanner input = new Scanner(System.in);
        int[] direction = new int[2];
        char key;
        render();

        do {
            key = input.next().charAt(0);
            input.nextLine();
            switch (key) {
            case 'w': // up
                direction[0] = 0;
                direction[1] = -1;
                break;
            case 'a': // left
                direction[0] = -1;
                direction[1] = 0;
                break;
            case 's': // down
                direction[0] = 0;
                direction[1] = 1;
                break;
            case 'd': // right
                direction[0] = 1;
                direction[1] = 0;
                break;
            case 'f':
                flag = false;
                break;
            default:
                break;
            }

            if (check_valid_input(key)) {
                s.set_direction(direction);
            }

            s.take_step(width, height);

            if (!s.check_bod_coll())
                break;

            check_app_coll();

            render();

        } while (flag);
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

    public int[][] board_matrix() {
        int[][] mat = { {} };

        return mat;
    }

    public void render() {
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

    public static void main(String[] args) {
        Game g = new Game(10, 20);

        System.out.println("GAME OVER!!!!!!!!!!!");
    }
}
