package movement;

import entity.Entity;
import entity.EntityManager;
import entity.data.MovementState;
import interfaces.ISystem;
import map.TileMap;

import java.awt.Point;

public final class MovementSystem implements ISystem {
    private final EntityManager entityManager;
    private final TileMap tileMap;

    public MovementSystem(EntityManager entityManager, TileMap tileMap) {
        this.entityManager = entityManager;
        this.tileMap = tileMap;
    }

    @Override
    public void update(double deltaSeconds) {
        for (Entity entity : entityManager.getEntities()) {
            if (!entity.hasMovementState() || entity.getCollisionData() == null) continue;
            moveEntity(entity, tileMap, deltaSeconds);
        }
    }

    private static void moveEntity(Entity entity, TileMap tileMap, double deltaSeconds) {
        MovementState state = entity.getMovementState();
        double desiredX = state.desiredMovementX;
        double desiredY = state.desiredMovementY;

        if (desiredX == 0.0 && desiredY == 0.0) {
            state.movementDelta.setLocation(0, 0);
            state.fractionalX = 0.0;
            state.fractionalY = 0.0;
            return;
        }

        state.direction = Direction.fromVector(desiredX, desiredY);
        if (desiredX == 0.0) state.fractionalX = 0.0;
        if (desiredY == 0.0) state.fractionalY = 0.0;
        double directionLength = Math.hypot(desiredX, desiredY);
        double distance = state.speed * deltaSeconds;
        double exactX = desiredX / directionLength * distance + state.fractionalX;
        double exactY = desiredY / directionLength * distance + state.fractionalY;
        int wantedX = (int) exactX;
        int wantedY = (int) exactY;
        state.fractionalX = exactX - wantedX;
        state.fractionalY = exactY - wantedY;

        Point actualMovement = EntityCollisionCalculator.handleWalls(entity, tileMap, wantedX, wantedY);
        if (actualMovement.x != wantedX) state.fractionalX = 0.0;
        if (actualMovement.y != wantedY) state.fractionalY = 0.0;

        state.movementDelta.setLocation(actualMovement);
        entity.translate(actualMovement.x, actualMovement.y);
    }
}
