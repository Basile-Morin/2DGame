package movement;

import core.GameConfig;
import core.ISystem;
import core.input.InputState;
import entity.EntityManager;
import map.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MovementSystem implements ISystem {
    private final EntityManager entityManager;
    private final InputState inputState;
    private final TileMap tileMap;

    public MovementSystem(EntityManager entityManager, InputState inputState, TileMap tileMap) {
        this.entityManager = entityManager;
        this.inputState = inputState;
        this.tileMap = tileMap;
    }

    @Override
    public void update() {
        //player
        Point wantedMovements = findWantedMovements(inputState);
        Point mouvements = EntityCollisionCalculator.handleWalls(entityManager.getPlayer(),tileMap,wantedMovements.x, wantedMovements.y);

        //temporaire
        entityManager.getPlayer().move(mouvements.x, mouvements.y);
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
}
