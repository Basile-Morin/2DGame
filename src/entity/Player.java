package entity;

import core.GameConfig;
import entity.data.CollisionData;
import entity.data.CombatStats;
import entity.data.MovementState;
import movement.Direction;

import java.awt.*;

public class Player extends Entity {
    //private int score;
    //private int experience;
    //private int level;

    public Player(int id)
    {
        super(id, EntityType.PLAYER);
        combatStats = new CombatStats();
        combatStats.damage= GameConfig.PLAYERBASEDAMAGE;

        movementState = new MovementState();
        movementState.direction= Direction.DOWN;
        movementState.speed=GameConfig.ENTITYBASESPEED;
        movementState.velocity=new Point(0,0);

        collisionData = new CollisionData();
        collisionData.area=new Rectangle(0,0,GameConfig.ENTITYWIDTH,GameConfig.ENTITYHEIGHT);
        collisionData.solidArea = GameConfig.ENTITYBASEHITBOX;

    }

}

/*
class entity.Player {
- score : int
- experience : int
- level : int
}
 */