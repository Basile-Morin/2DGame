package core.input;

public class InputState {
    private final boolean[] inputs = new boolean[600];
    private final Object lock = new Object();

    public boolean[] getInputs() {
        synchronized (lock) {
            return inputs.clone();
        }
    }

    public boolean getInput(int code) {
        synchronized (lock) {
            return inputs[code];
        }
    }

    public void setInput(boolean input, int code) {
        synchronized (lock) {
            inputs[code] = input;
        }
    }
}