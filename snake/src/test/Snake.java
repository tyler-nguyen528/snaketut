package test;

import java.util.ArrayList;

public class Snake {

    private ArrayList<int[]> body = new ArrayList<int[]>();
    private int[] dir = new int[2];

    public Snake() {
        int[][] temp = { { 4, 9 }, { 4, 10 }, { 4, 11 }, { 4, 12 } };
        for (int i = 0; i < temp.length; i++) {
            body.add(temp[i]);
        }

        dir[0] = 0;
        dir[1] = 1; // up
    }

    public Snake(int[][] init_body, int[] init_direction) {
        for (int i = 0; i < init_body.length; i++) {
            body.add(init_body[i]);
        }
        dir[0] = init_direction[0];
        dir[1] = init_direction[1];
    }

    public boolean check_yx(int x, int y) {
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i)[0] == x && body.get(i)[1] == y) {
                return true;
            }
        }
        return false;
    }

    public boolean check_x(int x) {
        for (int i = 0; i < body.size(); i++) {
            if (x == body.get(i)[0])
                return true;
        }
        return false;
    }

    public boolean check_y(int y) {
        for (int i = 0; i < body.size(); i++) {
            if (y == body.get(i)[1])
                return true;
        }
        return false;
    }

    public void take_step() {
        int[] new_position = new int[2];

        new_position[0] = body.get(0)[0] + dir[0];
        new_position[1] = body.get(0)[1] + dir[1];

        body.add(0, new_position);
        body.remove(body.size() - 1);
    }

    public void set_direction(int[] new_direction) {
        dir[0] = new_direction[0];
        dir[1] = new_direction[1];
    }

    public boolean check_head(int x, int y) {
        if (body.get(0)[0] == x && body.get(0)[1] == y) {
            return true;
        } else
            return false;
    }

    public int get_head_x() {
        return body.get(0)[0];
    }

    public int get_head_y() {
        return body.get(0)[1];
    }

}
