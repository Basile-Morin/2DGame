package movement.input;

public class InputState {
    private boolean[] inputs;
    public InputState(){
        inputs=new boolean[600];
    }


    public boolean[] getInputs() {
        return inputs;
    }

    public void setInput(boolean input, int code) {
        this.inputs[code] = input;
    }
}
