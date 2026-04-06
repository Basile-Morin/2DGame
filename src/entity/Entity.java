package entity;


import java.awt.*;

public abstract class Entity {
    protected Point wantedMovements;
    protected final Point coordinates;

    protected Entity() {
        coordinates = new Point(0,0);
    }


    public void move(int dx, int dy){
        coordinates.x+=dx;
        coordinates.y+=dy;
    }

    public int getX() {
        return coordinates.x;
    }

    public int getY() {
        return coordinates.y;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public Point getWantedMovements() {return wantedMovements;}

    public void setWantedMovements(Point wantedMovements) {this.wantedMovements = wantedMovements;}
}
