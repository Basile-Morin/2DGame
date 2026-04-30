package entity;

import animation.AnimationSet;
import animation.EntityAnimationRenderer;
import entity.data.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected final int id;
    protected boolean active;
    protected MovementState movementState;
    protected CombatStats combatStats;
    protected InteractionData interactionData;
    protected CollisionData collisionData;
    protected final EntityType type;
    protected AnimationSet animationSet;
    protected Point position;


    public Entity(int id,EntityType type){
        this.id=id;
        this.type=type;
        position = new Point(0,0);
        this.active=true;
    }

    public void translate(int dX, int dY){
        position.x+=dX;
        position.y+=dY;
    }
    public boolean hasCombatStats(){
        return combatStats != null;
    }

    public boolean hasMovementState(){
        return movementState != null;
    }

    public boolean hasInteractionData(){
        return interactionData != null;
    }

    public boolean isActive(){
        return active;
    }
    public void deactivate(){
        active=false;
    }
    public void activate(){
        active=true;
    }
    public void setAnimationSet(AnimationSet animationSet){
        this.animationSet=animationSet;
    }
    public void nextFrame(){
        if (animationSet==null) return;
        animationSet.update();
    }

    public AnimationSet getAnimationSet(){
        return animationSet;
    }

    public BufferedImage getFrame(){
        if (animationSet==null) return null;
        return animationSet.getFrame();
    }

    public MovementState getMovementState() {
        return movementState;
    }

    public CombatStats getCombatStats() {
        return combatStats;
    }

    public InteractionData getInteractionData() {
        return interactionData;
    }

    public CollisionData getCollisionData(){
        return collisionData;
    }

    public int getId() {
        return id;
    }

    public EntityType getType() {
        return type;
    }

    public Point getPosition() {
        return position;
    }


}



/*
class entity.Entity {
- id : int
- active : boolean
- movementState : MovementState
- combatStats : CombatStats
- interactionData : InteractionData
- entityType : EntityType
- animationSet
- position : Point
+ translate(int dX, int dY) : void
+ hasCombatStats() : boolean
+ hasMovementState() : boolean
+ hasInteractionData() : boolean
+ isActive() : boolean
+ deactivate() : void
+ update() : void
+ setAnimationSet(animationSet : AnimationSet) : void
+ nextFrame() : void
+ getFrame() : BufferedImage
}
 */