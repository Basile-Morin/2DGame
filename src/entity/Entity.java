package entity;


import core.GameConfig;

import java.awt.*;

public abstract class Entity {
    protected final Point coordinates;
    protected final Rectangle solidArea;

    protected Entity() {
        solidArea=new Rectangle(16,16,32,32);
        coordinates = new Point(0,0);
    }

//    protected Entity(Rectangle solidArea, Point coordinates) {
//        this.solidArea= solidArea;
//        this.coordinates = coordinates;
//    }

    public void move(int dx, int dy){
        coordinates.x+=dx;
        coordinates.y+=dy;
    }

    public int getSpeed(){
        return GameConfig.ENTITYBASESPEED;
    }

    public Rectangle getSolidArea() {return solidArea;}

    public Point getCoordinates() {
        return coordinates;
    }

}
