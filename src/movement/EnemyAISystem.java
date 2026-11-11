package movement;

import entity.Entity;
import entity.EntityManager;
import entity.Player;
import entity.data.MovementState;
import interfaces.ISystem;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnemyAISystem implements ISystem {
    private final EntityManager entityManager;

    public EnemyAISystem(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void update(double deltaSeconds) {
        List<Point> playerPositions = entityManager.getPlayers().stream()
                .map(Player::getPosition)
                .collect(Collectors.toCollection(ArrayList::new));

        for (Entity enemy : entityManager.getEntitiesByType(entity.EntityType.ENEMY)) {
            if (!enemy.hasMovementState()) continue;

            MovementState movementState = enemy.getMovementState();
            Point enemyPosition = enemy.getPosition();
            Point closestPlayerPosition = findClosestPlayerPosition(enemyPosition, playerPositions);

            if (closestPlayerPosition == null) {
                movementState.desiredMovementX = 0.0;
                movementState.desiredMovementY = 0.0;
                continue;
            }

            double dx = closestPlayerPosition.x - enemyPosition.x;
            double dy = closestPlayerPosition.y - enemyPosition.y;
            double distance = Math.hypot(dx, dy);

            if (distance == 0.0) {
                movementState.desiredMovementX = 0.0;
                movementState.desiredMovementY = 0.0;
            } else {
                movementState.desiredMovementX = dx / distance;
                movementState.desiredMovementY = dy / distance;
            }
        }
    }

    private static Point findClosestPlayerPosition(Point entityPosition, List<Point> playerPositions) {
        Point closestPosition = null;
        double closestDistanceSquared = Double.POSITIVE_INFINITY;

        for (Point playerPosition : playerPositions) {
            double dx = playerPosition.x - entityPosition.x;
            double dy = playerPosition.y - entityPosition.y;
            double distanceSquared = dx * dx + dy * dy;

            if (distanceSquared < closestDistanceSquared) {
                closestDistanceSquared = distanceSquared;
                closestPosition = playerPosition;
            }
        }

        return closestPosition;
    }
}
