package movement;

import core.GameConfig;
import core.ISystem;
import core.input.InputState;
import entity.EntityManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MovementSystem implements ISystem {
    private final EntityManager entityManager;
    private final InputState inputState;

    public MovementSystem(EntityManager entityManager, InputState inputState) {
        this.entityManager = entityManager;
        this.inputState = inputState;
    }

    @Override
    public void update() {
        Point wantedMovements = findWantedMovements(inputState);
        entityManager.getPlayer().setWantedMovements(wantedMovements);

        //temporaire
        entityManager.getPlayer().move(wantedMovements.x, wantedMovements.y);

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
