package animation;

import core.GameConfig;
import entity.Entity;
import entity.EntityManager;
import interfaces.Renderer;
import movement.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityAnimationRenderer implements Renderer {
    private final Camera camera;
    private final EntityManager entityManager;

    public EntityAnimationRenderer(EntityManager entityManager, Camera camera) {
        this.camera = camera;
        this.entityManager = entityManager;
    }

    @Override
    public void draw(Graphics2D g) {
        int cameraX = camera.getX();
        int cameraY = camera.getY();
        int screenCenterX = (GameConfig.SCREENWIDTH - GameConfig.TILESIZE) / 2;
        int screenCenterY = (GameConfig.SCREENHEIGHT - GameConfig.TILESIZE) / 2;

        for (Entity entity : entityManager.getEntities()) {
            BufferedImage currentFrame = entity.getFrame();
            if (currentFrame == null) continue;

            Point currentPosition = entity.getPosition();
            int entityWidth = entity.getCollisionData().area.width;
            int entityHeight = entity.getCollisionData().area.height;

            int screenX = currentPosition.x - cameraX + screenCenterX;
            int screenY = currentPosition.y - cameraY + screenCenterY;

            if (screenX + entityWidth < 0 ||
                    screenX > GameConfig.SCREENWIDTH ||
                    screenY + entityHeight < 0 ||
                    screenY > GameConfig.SCREENHEIGHT) {
                continue;
            }

            g.drawImage(currentFrame, screenX, screenY, entityWidth, entityHeight, null);
        }
    }
}