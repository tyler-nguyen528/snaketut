package snake.src.test;

import java.util.Random;

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

class Apple extends Object {
    private Random rand = new Random();
    private int randA, randB;
    private Snake s;

    public Apple(Snake s) {
        this.s = s;
        set_Pos();
    }

    public void set_Pos() {
        boolean f = true;

        do {
            randA = rand.nextInt(80);
            if (!s.check_x(randA)) {
                f = false;
            }
        } while (f);

        f = true;

        do {
            randB = rand.nextInt(33);
            if (!s.check_y(randB)) {
                f = false;
            }
        } while (f);
    }
}