package snake.src.test;

import java.util.Random;
import java.util.ArrayList;

public abstract class Object {
    protected int x;
    protected int y;

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public void setx(int x) {
        this.x = x;
    }

    public void sety(int y) {
        this.y = y;
    }
}

class snake_Bod extends Object {
    public snake_Bod(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

abstract class Apple extends Object {
    protected Random rand = new Random();
    protected int randA, randB, max_x, max_y;

    abstract void set_Pos();
}

class Apple_P1 extends Apple {
    private Snake s;

    public Apple_P1(Snake s, int max_x, int max_y) {
        this.s = s;
        this.max_x = max_x;
        this.max_y = max_y;
        set_Pos();
    }

    public void set_Pos() {
        boolean f = true;

        do {
            randA = rand.nextInt(max_x);
            if (!s.check_x(randA)) {
                f = false;
            }
        } while (f);

        f = true;

        do {
            randB = rand.nextInt(max_y);
            if (!s.check_y(randB)) {
                f = false;
            }
        } while (f);

        x = randA;
        y = randB;
    }
}

class Apple_P2 extends Apple {
    private Snake s1, s2;

    public Apple_P2(Snake s1, Snake s2, int max_x, int max_y) {
        this.s1 = s1;
        this.s2 = s2;

        this.max_x = max_x;
        this.max_y = max_y;
        set_Pos();
    }

    public void set_Pos() {
        boolean f = true;

        do {
            randA = rand.nextInt(max_x);
            if (!s1.check_x(randA) && !s2.check_x(randA)) {
                f = false;
            }
        } while (f);

        f = true;

        do {
            randB = rand.nextInt(max_y);
            if (!s1.check_x(randB) && !s2.check_x(randB)) {
                f = false;
            }
        } while (f);

        x = randA;
        y = randB;
    }
}

class body_Comp extends snake_Bod {

    ArrayList<snake_Bod> coll;

    public body_Comp() {
        super(0, 0);
        coll = new ArrayList<snake_Bod>();
    }

    public snake_Bod get(int x) {
        return coll.get(x);
    }

    public int size() {
        return coll.size();
    }

    public void add(snake_Bod new_bod) {
        coll.add(coll.size(), new_bod);
    }

    public void move(snake_Bod new_bod) {
        coll.add(0, new_bod);
    }

    public void remove() {
        coll.remove(coll.size() - 1);
    }
}