package entity;

import animation.AnimationManager;
import core.GameConfig;

public class EntityFactory {
    private final EntityManager entityManager;
    private final AnimationManager animationManager;

    public EntityFactory(EntityManager entityManager, AnimationManager animationManager) {
        this.entityManager = entityManager;
        this.animationManager = animationManager;
    }

    public Player createPlayer(int tileX, int tileY) {
        Player player = new Player(entityManager.generateId());
        initialize(player, Player.class, tileX, tileY);
        return player;
    }

    public Enemy createEnemy(int tileX, int tileY) {
        Enemy enemy = new Enemy(entityManager.generateId());
        initialize(enemy, Enemy.class, tileX, tileY);
        return enemy;
    }

    private void initialize(Entity entity, Class<? extends Entity> animationType, int tileX, int tileY) {
        entity.getPosition().setLocation(
                tileX * GameConfig.TILESIZE,
                tileY * GameConfig.TILESIZE
        );
        entity.setAnimationSet(animationManager.createAnimationSet(animationType));
    }
}