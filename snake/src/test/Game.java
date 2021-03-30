package snake.src.test;

import javax.swing.text.BadLocationException;

public abstract class Game {

    protected int score;
    protected int height;
    protected int width;
    protected Apple a;
    protected Frame frame;

    abstract boolean tick();

    public void set_direction(Snake s, int[] new_dir) {
        s.set_direction(new_dir);
    }

    public boolean test_input(Snake s, int x, int y) {
        int curr_dirx = s.get_dirx(), curr_diry = s.get_diry();

        if (x == 0 && y == -1) { // input = up
            if (curr_diry == 1) // check if current direction is going down
                return false;
            else
                return true;
        } else if (x == 0 && y == 1) { // input = down
            if (curr_diry == -1) // check if current direction is going up
                return false;
            else
                return true;
        } else if (x == 1 && y == 0) { // input = right
            if (curr_dirx == -1) // check if current direction is going left
                return false;
            else
                return true;
        } else if (x == -1 && y == 0) { // input = left
            if (curr_dirx == 1) // check if current direction is going right
                return false;
            else
                return true;
        }
        return false;
    }

    public void check_app_coll(Snake s) {
        if (s.get_head_x() == a.getx() && s.get_head_y() == a.gety()) {
            score++;

            a.set_Pos();

            s.add_body(width, height);
        }
    }

    public void print_Over() {
        // frame.gameArea.setText(null);
        System.out.println("GAME OVER!!!!!!!!");
        frame.end();
    }

    abstract void check_valid_input(int p_num, int[] dir);

    abstract void render();
}

class Game_1P extends Game {
    private Snake player;
    private int newdirx, newdiry;

    public Game_1P(Frame frame, int[] body) {
        score = 0;
        height = 15;
        width = 30;

        player = new Snake(9, body);
        this.frame = frame;
        a = new Apple_P1(player, width, height);
    }

    public void check_valid_input(int p_num, int[] dir) {
        if (test_input(player, dir[0], dir[1])) {
            newdirx = dir[0];
            newdiry = dir[1];
        }
    }

    public boolean tick() {
        if (test_input(player, newdirx, newdiry))
            player.set_direction(newdirx, newdiry);
        player.take_step(width, height);
        if (!player.check_bod_coll())
            return false;
        check_app_coll(player);
        render();
        return true;
    }

    public void render() {
        frame.gameArea.setText(null);
        System.out.print("+");
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.print("+");
        System.out.println("\tScore: " + score);

        for (int i = 0; i < height; i++) {
            System.out.print("|");

            for (int j = 0; j < width; j++) {
                if (player.check_head(j, i)) {
                    System.out.print("x");
                } else if (player.check_yx(j, i)) {
                    System.out.print("o");
                } else if (a.getx() == j && a.gety() == i) {
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
        System.out.print("+");
    }

}

class Game_2P extends Game {
    private Snake p1, p2;
    private int p1_newdirx, p1_newdiry, p2_newdirx, p2_newdiry, special_flag;

    public Game_2P(Frame frame, int[] body) {
        score = 0;
        height = 15;
        width = 30;

        p1 = new Snake(4, body);
        p2 = new Snake(11, body);
        this.frame = frame;
        a = new Apple_P2(p1, p2, width, height);
    }

    public void check_valid_input(int p_num, int[] dir) {
        if (p_num == 1)
            if (test_input(p1, dir[0], dir[1])) {
                p1_newdirx = dir[0];
                p1_newdiry = dir[1];
            }

        if (p_num == 2)
            if (test_input(p2, dir[0], dir[1])) {
                p2_newdirx = dir[0];
                p2_newdiry = dir[1];
            }
    }

    public boolean tick() {
        if (test_input(p1, p1_newdirx, p1_newdiry))
            p1.set_direction(p1_newdirx, p1_newdiry);
        p1.take_step(width, height);

        if (test_input(p2, p2_newdirx, p2_newdiry))
            p2.set_direction(p2_newdirx, p2_newdiry);
        p2.take_step(width, height);

        if (!p1.check_bod_coll()) {
            special_flag = 1;
            return false;
        }

        if (!p1.check_bod_coll()) {
            special_flag = 2;
            return false;
        }

        check_app_coll(p1);
        check_app_coll(p2);

        if (check_player_coll(p1, p2)) {
            special_flag = 3;
            return false;
        }

        if (check_player_coll(p2, p1)) {
            special_flag = 4;
            return false;
        }

        render();
        return true;
    }

    public void print_Over() {
        try {
            switch (special_flag) {
            case 1:
                frame.gameArea.replaceRange("Player 1 died, Player 2 Wins!!!", 0, frame.gameArea.getLineEndOffset(0));
                break;
            case 2:
                frame.gameArea.replaceRange("Player 2 died, Player 1 Wins!!!", 0, frame.gameArea.getLineEndOffset(0));
                break;
            case 3:
                frame.gameArea.replaceRange("Player 1 hit Player 2! Player 2 Wins!!!", 0,
                        frame.gameArea.getLineEndOffset(0));
                break;
            case 4:
                frame.gameArea.replaceRange("Player 2 hit Player 1! Player 1 Wins!!!", 0,
                        frame.gameArea.getLineEndOffset(0));
                break;
            }
            frame.end2();
        } catch (Exception e) {
        }

    }

    public boolean check_player_coll(Snake s1, Snake s2) {
        for (int i = 0; i < s2.size(); i++) {
            if (s2.check_yx(s1.get_head_x(), s1.get_head_y()))
                return true;
        }
        return false;
    }

    public void render() {
        frame.gameArea.setText(null);
        System.out.print("+");
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println("+");

        for (int i = 0; i < height; i++) {
            System.out.print("|");

            for (int j = 0; j < width; j++) {
                if (p1.check_head(j, i)) {
                    System.out.print("x");
                } else if (p2.check_head(j, i)) {
                    System.out.print("y");
                } else if (p1.check_yx(j, i)) {
                    System.out.print("o");
                } else if (p2.check_yx(j, i)) {
                    System.out.print("o");
                } else if (a.getx() == j && a.gety() == i) {
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
        System.out.print("+");
    }
}