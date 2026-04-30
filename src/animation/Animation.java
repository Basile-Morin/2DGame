package animation;

import java.awt.image.BufferedImage;

class Animation {
    private final BufferedImage[] frames;
    private final boolean looping;
    private boolean finished;
    private int currentFrame;
    private int counter;
    private final int frameMaxNumber;

    public Animation(BufferedImage[] frames, int animationLength, boolean looping) {
        this.frames = frames;
        this.frameMaxNumber = animationLength;
        this.looping = looping;
        this.finished=false;
    }

    public void update() {
        counter++;
        if (counter >= frameMaxNumber) {
            counter = 0;

            if (looping) {
                currentFrame = (currentFrame + 1) % frames.length;
            } else if (currentFrame < frames.length - 1) {
                currentFrame++;
            } else if (currentFrame >= frames.length -1){
                finished = true;
            }
        }
    }

    public void reset() {
        counter = 0;
        currentFrame = 0;
        finished=false;
    }

    public BufferedImage getFrame() {
        return frames[currentFrame];
    }

    public boolean isFinished() {
        return finished;
    }
}