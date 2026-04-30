package movement;

import animation.AnimationSet;
import animation.AnimationType;
import core.GameConfig;
import entity.Player;
import interfaces.ISystem;
import core.input.InputState;
import entity.EntityManager;
import map.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerMovementSystem implements ISystem {
    private final Player player;
    private final InputState inputState;
    private final TileMap tileMap;

    public PlayerMovementSystem(Player player, InputState inputState, TileMap tileMap) {
        this.player = player;
        this.inputState = inputState;
        this.tileMap = tileMap;
    }

    @Override
    public void update() {

        Point wantedMovements = findWantedMovements(inputState);
        Point mouvements = EntityCollisionCalculator.handleWalls(
                player,
                tileMap,
                wantedMovements.x,
                wantedMovements.y
        );

        animationFinder(player, mouvements.x, mouvements.y);

        player.translate(mouvements.x, mouvements.y);
    }

    public Point findWantedMovements(InputState inputState){
        int dX=0,dY=0;
        boolean[] inputs = inputState.getInputs();

        if (inputs[KeyEvent.VK_Z] && !inputs[KeyEvent.VK_S]) {
            dY = -1* GameConfig.ENTITYBASESPEED;
        } else if (inputs[KeyEvent.VK_S] && !inputs[KeyEvent.VK_Z]) {
            dY = GameConfig.ENTITYBASESPEED;
        }

        if (inputs[KeyEvent.VK_Q] && !inputs[KeyEvent.VK_D]) {
            dX = -1*GameConfig.ENTITYBASESPEED;
        } else if (inputs[KeyEvent.VK_D] && !inputs[KeyEvent.VK_Q]) {
            dX = GameConfig.ENTITYBASESPEED;
        }

        return new Point(dX,dY);
    }

    public void animationFinder(Player player, int dX, int dY){
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

    public AnimationType findDirection(int dX, int dY){

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
