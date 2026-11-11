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

    public Player spawnPlayer(int tileX, int tileY) {
        Player player = createPlayer(tileX, tileY);
        entityManager.addEntity(player);
        return player;
    }

    public Enemy spawnEnemy(int tileX, int tileY) {
        Enemy enemy = createEnemy(tileX, tileY);
        entityManager.addEntity(enemy);
        return enemy;
    }

    private Player createPlayer(int tileX, int tileY) {
        Player player = new Player(entityManager.generateId());
        initialize(player, Player.class, tileX, tileY);
        return player;
    }

    private Enemy createEnemy(int tileX, int tileY) {
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