package movement;

import entity.EntityManager;
import interfaces.ISystem;

public class EnemyAISystem implements ISystem {
    private final EntityManager entityManager;

    public EnemyAISystem(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void update(double deltaSeconds) {
        // TODO: implement enemy AI
    }
}
