package Asset;

import java.awt.image.BufferedImage;

public class Animation {
    private final BufferedImage[] frames;
    private final float frameDuration; // secondes par frame
    private float timer = 0f;
    private int index = 0;

    public Animation(BufferedImage[] frames, float frameDuration) {
        this.frames = frames;
        this.frameDuration = frameDuration;
    }

    public void update(float dt) {
        timer += dt;
        while (timer >= frameDuration) {
            timer -= frameDuration;
            index = (index + 1) % frames.length;
        }
    }

    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
}