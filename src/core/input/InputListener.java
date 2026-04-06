package core.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class InputListener implements KeyListener {
    private final InputState inputState;
    public InputListener() {
        this.inputState = new InputState();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println(e.getKeyCode());
        int code = e.getKeyCode();
        inputState.setInput(true,code);


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        inputState.setInput(false,code);
    }

    public InputState getInputState() {
        return inputState;
    }
}


