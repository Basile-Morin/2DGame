package movement;

import core.input.InputState;
import entity.Player;
import entity.data.MovementState;
import interfaces.ISystem;

import java.awt.event.KeyEvent;

/** Translates player key state into a movement intent. */
public final class PlayerInputSystem implements ISystem {
    private final Player player;
    private final InputState inputState;

    public PlayerInputSystem(Player player, InputState inputState) {
        this.player = player;
        this.inputState = inputState;
    }

    @Override
    public void update(double deltaSeconds) {
        boolean[] inputs = inputState.getInputs();
        int directionX = axis(inputs[KeyEvent.VK_Q], inputs[KeyEvent.VK_D]);
        int directionY = axis(inputs[KeyEvent.VK_Z], inputs[KeyEvent.VK_S]);

        MovementState movementState = player.getMovementState();
        movementState.desiredMovementX = directionX;
        movementState.desiredMovementY = directionY;
    }

    private static int axis(boolean negativePressed, boolean positivePressed) {
        if (negativePressed == positivePressed) return 0;
        return negativePressed ? -1 : 1;
    }
}
