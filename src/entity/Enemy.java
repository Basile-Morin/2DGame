package entity;

import core.GameConfig;
import entity.data.CollisionData;
import entity.data.CombatStats;
import entity.data.MovementState;
import movement.Direction;

import java.awt.*;

public class Enemy extends Entity {
    //private int score;
    //private int experience;
    //private int level;

    public Enemy(int id)
    {
        super(id, EntityType.ENEMY);
        combatStats = new CombatStats();
        combatStats.damage= GameConfig.ENEMYBASEDAMAGE;

        movementState = new MovementState();
        movementState.direction= Direction.DOWN;
        movementState.speed=GameConfig.ENTITY_BASE_SPEED_PX_PER_SECOND;

        collisionData = new CollisionData();
        collisionData.area=new Rectangle(0,0,GameConfig.ENTITYWIDTH,GameConfig.ENTITYHEIGHT);
        collisionData.solidArea = GameConfig.ENTITYBASEHITBOX;

    }

}