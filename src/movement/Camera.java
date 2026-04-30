package movement;

import entity.Player;

import java.awt.*;

public class Camera {
    private final Point coordinates;

    public Camera(Player player) {
        this.coordinates = player.getPosition();
    }

    public int getX(){return coordinates.x;}
    public int getY(){return coordinates.y;}
}
