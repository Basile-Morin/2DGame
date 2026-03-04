package entity;

import Asset.Animation;

public class Entity {

    protected int x,y;





    public void move(int dx, int dy){
        x+=dx;
        y+=dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
