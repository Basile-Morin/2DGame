package animation;

import java.awt.image.BufferedImage;
import java.util.EnumMap;

public class AnimationSet {

    private final EnumMap<AnimationType, Animation> animations;
    private AnimationType currentAnimation;

    public AnimationSet(EnumMap<AnimationType, Animation> animations) {
        this.animations = animations;
        this.currentAnimation = AnimationType.IDLE_DOWN;
    }

    public void update() {
        Animation anim = animations.get(currentAnimation);
        if (anim != null) {
            anim.update();
        }
    }

    public BufferedImage getFrame() {
        Animation anim = animations.get(currentAnimation);
        return anim != null ? anim.getFrame() : null;
    }

    public void setAnimation(AnimationType type) {
        if (type == null || type == currentAnimation) {
            return;
        }

        currentAnimation = type;

        Animation anim = animations.get(type);
        if (anim != null) {
            anim.reset();
        }
    }

    public AnimationType getCurrentAnimation() {
        return currentAnimation;
    }
}