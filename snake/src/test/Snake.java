package snake.src.test;

public class Snake {

    private body_Comp body;
    private int dirx, diry;

    public Snake(int x, int[] body_Array) {
        body = new body_Comp();
        for (int i = 0; i < body_Array.length; i++) {
            body.add(new snake_Bod(x, body_Array[i]));
        }

        dirx = 0;
        diry = -1; // up
    }

    public int size() {
        return body.size();
    }

    public boolean check_yx(int x, int y) {
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).getx() == x && body.get(i).gety() == y) {
                return true;
            }
        }
        return false;
    }

    public boolean check_x(int x) {
        for (int i = 0; i < body.size(); i++) {
            if (x == body.get(i).getx())
                return true;
        }
        return false;
    }

    public boolean check_y(int y) {
        for (int i = 0; i < body.size(); i++) {
            if (y == body.get(i).gety())
                return true;
        }
        return false;
    }

    public void take_step(int max_x, int max_y) {
        int newx, newy;

        newx = body.get(0).getx() + dirx;
        newy = body.get(0).gety() + diry;

        if (newx <= 0)
            newx += max_x;
        if (newx >= max_x)
            newx -= max_x;
        if (newy <= 0)
            newy += max_y;
        if (newy >= max_y)
            newy -= max_y;

        body.move(new snake_Bod(newx, newy));
        body.remove();
    }

    public boolean check_bod_coll() {
        for (int i = 1; i < body.size(); i++) {
            if (body.get(0).getx() == body.get(i).getx() && body.get(0).gety() == body.get(i).gety()) {
                return false;
            }
        }
        return true;
    }

    public void set_direction(int newx, int newy) {
        dirx = newx;
        diry = newy;
    }

    public boolean check_head(int x, int y) {
        if (body.get(0).getx() == x && body.get(0).gety() == y) {
            return true;
        } else
            return false;
    }

    public int get_head_x() {
        return body.get(0).getx();
    }

    public int get_head_y() {
        return body.get(0).gety();
    }

    public void add_body(int max_x, int max_y) {
        snake_Bod tail = body.get(body.size() - 1);
        int newx = tail.getx() - dirx;
        int newy = tail.gety() - diry;

        if (newx <= 0)
            newx += max_x;
        if (newx >= max_x)
            newx -= max_x;
        if (newy <= 0)
            newy += max_y;
        if (newy >= max_y)
            newy -= max_y;

        body.add(new snake_Bod(newx, newy));
    }

    public int get_dirx() {
        return dirx;
    }

    public int get_diry() {
        return diry;
    }
}
