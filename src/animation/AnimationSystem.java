package animation;

import entity.Entity;
import entity.EntityManager;
import entity.data.MovementState;
import interfaces.ISystem;
import movement.Direction;

import java.awt.Point;

public class AnimationSystem implements ISystem {
    private final EntityManager entityManager;

    public AnimationSystem(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void update(double deltaSeconds) {
        for (Entity entity : entityManager.getEntities()) {
            if (entity.getAnimationSet() != null && entity.hasMovementState()) {
                updateMovementAnimation(entity.getAnimationSet(), entity.getMovementState());
            }
            entity.nextFrame(deltaSeconds);
        }
    }

    private static void updateMovementAnimation(AnimationSet animationSet, MovementState movementState) {
        AnimationType currentAnimation = animationSet.getCurrentAnimation();
        if (currentAnimation != null && currentAnimation.isAttack()) return;

        Point movement = movementState.movementDelta;
        boolean isMoving = movement.x != 0 || movement.y != 0;
        AnimationType preferred = movementAnimation(movementState.direction, isMoving);
        AnimationType fallback = resolveAvailableAnimation(animationSet, preferred, isMoving);
        if (fallback != null) animationSet.setAnimation(fallback);
    }

    private static AnimationType movementAnimation(Direction direction, boolean isMoving) {
        if (direction == null) direction = Direction.DOWN;

        return switch (direction) {
            case UP -> isMoving ? AnimationType.WALK_UP : AnimationType.IDLE_UP;
            case DOWN -> isMoving ? AnimationType.WALK_DOWN : AnimationType.IDLE_DOWN;
            case LEFT -> isMoving ? AnimationType.WALK_LEFT : AnimationType.IDLE_LEFT;
            case RIGHT -> isMoving ? AnimationType.WALK_RIGHT : AnimationType.IDLE_RIGHT;
            case UP_LEFT -> isMoving ? AnimationType.WALK_UP_LEFT : AnimationType.IDLE_UP_LEFT;
            case UP_RIGHT -> isMoving ? AnimationType.WALK_UP_RIGHT : AnimationType.IDLE_UP_RIGHT;
            case DOWN_LEFT -> isMoving ? AnimationType.WALK_DOWN_LEFT : AnimationType.IDLE_DOWN_LEFT;
            case DOWN_RIGHT -> isMoving ? AnimationType.WALK_DOWN_RIGHT : AnimationType.IDLE_DOWN_RIGHT;
            case NO_DIRECTION -> isMoving ? AnimationType.WALK_DOWN : AnimationType.IDLE_DOWN;
        };
    }

    private static AnimationType resolveAvailableAnimation(AnimationSet animationSet,
                                                           AnimationType preferred,
                                                           boolean isMoving) {
        if (animationSet.hasAnimation(preferred)) return preferred;

        AnimationType categoryFallback = isMoving ? AnimationType.WALK_DOWN : AnimationType.IDLE_DOWN;
        if (animationSet.hasAnimation(categoryFallback)) return categoryFallback;

        AnimationType oppositeCategory = isMoving ? AnimationType.IDLE_DOWN : AnimationType.WALK_DOWN;
        if (animationSet.hasAnimation(oppositeCategory)) return oppositeCategory;

        return null;
    }
}
