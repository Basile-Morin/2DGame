package movement;

import animation.AnimationSet;
import animation.AnimationType;
import core.GameConfig;
import entity.Player;
import interfaces.ISystem;
import core.input.InputState;
import map.TileMap;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

public class PlayerMovementSystem implements ISystem {
    private final Player player;
    private final InputState inputState;
    private final TileMap tileMap;
    private double fractionalMovementX;
    private double fractionalMovementY;

    public PlayerMovementSystem(Player player, InputState inputState, TileMap tileMap) {
        this.player = player;
        this.inputState = inputState;
        this.tileMap = tileMap;
    }

    @Override
    public void update(double deltaSeconds) {

        Point2D.Double wantedMovements = findWantedMovements(inputState, deltaSeconds);
        double exactX = wantedMovements.x + fractionalMovementX;
        double exactY = wantedMovements.y + fractionalMovementY;
        int wantedX = (int) exactX;
        int wantedY = (int) exactY;
        fractionalMovementX = exactX - wantedX;
        fractionalMovementY = exactY - wantedY;

        Point mouvements = EntityCollisionCalculator.handleWalls(
                player,
                tileMap,
                wantedX,
                wantedY
        );

        if (mouvements.x != wantedX) fractionalMovementX = 0.0;
        if (mouvements.y != wantedY) fractionalMovementY = 0.0;

        animationFinder(player, mouvements.x, mouvements.y);

        player.translate(mouvements.x, mouvements.y);
    }

    public Point2D.Double findWantedMovements(InputState inputState, double deltaSeconds){
        int directionX = 0;
        int directionY = 0;
        boolean[] inputs = inputState.getInputs();

        if (inputs[KeyEvent.VK_Z] && !inputs[KeyEvent.VK_S]) {
            directionY = -1;
        } else if (inputs[KeyEvent.VK_S] && !inputs[KeyEvent.VK_Z]) {
            directionY = 1;
        }

        if (inputs[KeyEvent.VK_Q] && !inputs[KeyEvent.VK_D]) {
            directionX = -1;
        } else if (inputs[KeyEvent.VK_D] && !inputs[KeyEvent.VK_Q]) {
            directionX = 1;
        }

        if (directionX == 0) fractionalMovementX = 0.0;
        if (directionY == 0) fractionalMovementY = 0.0;

        if (directionX == 0 && directionY == 0) {
            return new Point2D.Double();
        }

        double directionLength = Math.hypot(directionX, directionY);
        double distance = GameConfig.ENTITY_BASE_SPEED_PX_PER_SECOND * deltaSeconds;
        return new Point2D.Double(
                directionX / directionLength * distance,
                directionY / directionLength * distance
        );
    }

    public static void animationFinder(Player player, int dX, int dY){
        AnimationType oldAnimation = player.getAnimationSet().getCurrentAnimation();
        AnimationType newAnimation = findDirection(dX,dY);
        AnimationSet animationSet = player.getAnimationSet();

        if (oldAnimation.isAttack()) return; //on gère pas ça ici
        if (oldAnimation==newAnimation || (oldAnimation.isIdle() && newAnimation==null)) return; //on change pas l'animation si c'est pareil
        if (newAnimation!=null) player.getAnimationSet().setAnimation(newAnimation);
        else if(oldAnimation.isWalk()){
            switch (oldAnimation){
                case WALK_DOWN -> animationSet.setAnimation(AnimationType.IDLE_DOWN);
                case WALK_UP -> animationSet.setAnimation(AnimationType.IDLE_UP);
                case WALK_LEFT -> animationSet.setAnimation(AnimationType.IDLE_LEFT);
                case WALK_RIGHT -> animationSet.setAnimation(AnimationType.IDLE_RIGHT);
                case WALK_DOWN_LEFT -> animationSet.setAnimation(AnimationType.IDLE_DOWN_LEFT);
                case WALK_DOWN_RIGHT -> animationSet.setAnimation(AnimationType.IDLE_DOWN_RIGHT);
                case WALK_UP_LEFT -> animationSet.setAnimation(AnimationType.IDLE_UP_LEFT);
                case WALK_UP_RIGHT -> animationSet.setAnimation(AnimationType.IDLE_UP_RIGHT);
            }
        }

    }

    public static AnimationType findDirection(int dX, int dY){

        AnimationType animationType =null;
        double dxRatio,dyRatio,tolerance=0.3;
        int absDX = (int) Math.abs(dX);
        int absDY = (int) Math.abs(dY);
        int total = absDX + absDY;



        if (total!=0) {
            dxRatio = (double) absDX / total;
            dyRatio = (double) absDY / total;
        } else {
            dxRatio = 0;
            dyRatio = 0;
        }

        if (dxRatio >= tolerance && dyRatio >= tolerance) {
            if (dX > 0 && dY < 0) {
                animationType = AnimationType.WALK_UP_RIGHT;
            } else if (dX > 0 && dY > 0) {
                animationType = AnimationType.WALK_DOWN_RIGHT;
            } else if (dX < 0 && dY < 0) {
                animationType = AnimationType.WALK_UP_LEFT;
            } else if (dX < 0 && dY > 0) {
                animationType = AnimationType.WALK_DOWN_LEFT;
            }
        } else if (dxRatio <= tolerance) {
            if (dY < 0) {
                animationType = AnimationType.WALK_UP;
            } else if (dY > 0) {
                animationType = AnimationType.WALK_DOWN;
            }

        } else //if (dyRatio <= tolerance)
        {
            if (dX < 0) {
                animationType = AnimationType.WALK_LEFT;
            } else if (dX > 0) {
                animationType = AnimationType.WALK_RIGHT;
            }
        }
        return animationType;
    }
}
