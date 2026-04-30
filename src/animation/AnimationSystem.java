package animation;

import entity.Entity;
import entity.EntityManager;
import interfaces.ISystem;

public class AnimationSystem implements ISystem {
    private final EntityManager entityManager;

    public AnimationSystem(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void update() {
        for (Entity entity : entityManager.getEntities()){
            entity.nextFrame();
        }
    }
}
