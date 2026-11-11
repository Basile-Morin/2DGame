package entity;

import core.GameConfig;

public class EntityFactory {
    private final EntityManager entityManager;
    public EntityFactory(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Player createPlayer(int x, int y){
        Player p = new Player(entityManager.generateId());
        p.getPosition().x=x;
        p.getPosition().y=y;
        return p;
    }
    public Enemy createEnemy(int x, int y){
        Enemy e = new Enemy(entityManager.generateId());
        e.getPosition().x=x*GameConfig.TILESIZE;
        e.getPosition().y=y*GameConfig.TILESIZE;
        return e;
    }

}

/*
-entityManager : EntityManager
+ EntityFactory(entityManager:EntityManager)
+ createPlayer(x:int, y:int) : Player
+ createEnemy(x:int, y:int, enemyId:int ) : Enemy
+ createNpc(x:int, y:int, npcId:int) : Npc
+ createWorldObject(x:int, y:int, objectId:int) : WorldObject
 */