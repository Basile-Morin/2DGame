package animation;

import java.awt.image.BufferedImage;

class Animation {
    private final BufferedImage[] frames;
    private final boolean looping;
    private boolean finished;
    private int currentFrame;
    private double elapsedSeconds;
    private final double frameDurationSeconds;

    public Animation(BufferedImage[] frames, double frameDurationSeconds, boolean looping) {
        this.frames = frames;
        this.frameDurationSeconds = frameDurationSeconds;
        this.looping = looping;
        this.finished=false;
    }

    public void update(double deltaSeconds) {
        if (finished) return;
        elapsedSeconds += deltaSeconds;

        while (elapsedSeconds >= frameDurationSeconds && !finished) {
            elapsedSeconds -= frameDurationSeconds;
            if (looping) {
                currentFrame = (currentFrame + 1) % frames.length;
            } else if (currentFrame < frames.length - 1) {
                currentFrame++;
            } else {
                finished = true;
                elapsedSeconds = 0.0;
            }
        }
    }

    public void reset() {
        elapsedSeconds = 0.0;
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
