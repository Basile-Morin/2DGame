package animation;

public enum AnimationType {
    IDLE_DOWN(AnimationCategory.IDLE),
    IDLE_UP(AnimationCategory.IDLE),
    IDLE_LEFT(AnimationCategory.IDLE),
    IDLE_RIGHT(AnimationCategory.IDLE),
    IDLE_DOWN_LEFT(AnimationCategory.IDLE),
    IDLE_DOWN_RIGHT(AnimationCategory.IDLE),
    IDLE_UP_LEFT(AnimationCategory.IDLE),
    IDLE_UP_RIGHT(AnimationCategory.IDLE),

    WALK_DOWN(AnimationCategory.WALK),
    WALK_UP(AnimationCategory.WALK),
    WALK_LEFT(AnimationCategory.WALK),
    WALK_RIGHT(AnimationCategory.WALK),
    WALK_DOWN_LEFT(AnimationCategory.WALK),
    WALK_DOWN_RIGHT(AnimationCategory.WALK),
    WALK_UP_LEFT(AnimationCategory.WALK),
    WALK_UP_RIGHT(AnimationCategory.WALK),

    ATTACK_DOWN(AnimationCategory.ATTACK),
    ATTACK_UP(AnimationCategory.ATTACK),
    ATTACK_LEFT(AnimationCategory.ATTACK),
    ATTACK_RIGHT(AnimationCategory.ATTACK),
    ATTACK_DOWN_LEFT(AnimationCategory.ATTACK),
    ATTACK_DOWN_RIGHT(AnimationCategory.ATTACK),
    ATTACK_UP_LEFT(AnimationCategory.ATTACK),
    ATTACK_UP_RIGHT(AnimationCategory.ATTACK);

    private final AnimationCategory category;

    AnimationType(AnimationCategory category) {
        this.category = category;
    }

    public AnimationCategory getCategory() {
        return category;
    }

    // Helpers pratiques
    public boolean isIdle() {
        return category == AnimationCategory.IDLE;
    }

    public boolean isWalk() {
        return category == AnimationCategory.WALK;
    }

    public boolean isAttack() {
        return category == AnimationCategory.ATTACK;
    }
}