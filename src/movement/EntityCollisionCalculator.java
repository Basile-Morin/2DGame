package movement;

import core.GameConfig;
import entity.Entity;
import map.TileMap;
import map.TileSet;

import java.awt.*;

import static java.lang.Math.signum;

public class EntityCollisionCalculator {


    public static Point handleWalls(Entity entity, TileMap map,double dX, double dY){
        final double originaldX=dX,originaldY=dY;
        final int entitySpeed = entity.getMovementState().speed;

        Direction blockedDirection=checkTileCollision(entity,map);
        if (blockedDirection.dy==-1 && dY<0) {
            dY=0;
            dX = entitySpeed*signum(originaldX)+(dX-originaldX);
        }
        else if (blockedDirection.dy==1 && dY>0) {
            dY=0;
            dX = entitySpeed*signum(originaldX)+(dX-originaldX);
        }
        if (blockedDirection.dx==-1 && dX<0) {
            dX=0;
            dY = entitySpeed*signum(originaldY)+(dY-originaldY);
        }
        else if (blockedDirection.dx==1 && dX>0) {
            dX=0;
            dY = entitySpeed*signum(originaldY)+(dY-originaldY);
        }
        return new Point((int) Math.round(dX),(int) Math.round(dY));
    }


    static public Direction checkTileCollision(Entity entity,TileMap map){
        int dX = 0,dY = 0;
        final int entitySpeed = entity.getMovementState().speed;
        final Point entityCoordinates = entity.getPosition();
        final Rectangle entitySolidArea = entity.getCollisionData().solidArea;

        boolean verticalCollision,horizontalCollision;
        int entityCollisionLeftWorldX = entityCoordinates.x+ entitySolidArea.x;
        int entityCollisionTopWorldY = entityCoordinates.y+ entitySolidArea.y;
        int entityCollisionRightWorldX = entityCoordinates.x+ entitySolidArea.x+ entitySolidArea.width;
        int entityCollisionBottomWorldY = entityCoordinates.y+ entitySolidArea.y+ entitySolidArea.height;

        int entityCollisionLeftCol = entityCollisionLeftWorldX/GameConfig.TILESIZE;
        int entityCollisionTopRow = entityCollisionTopWorldY/GameConfig.TILESIZE;
        int entityCollisionRightCol = entityCollisionRightWorldX/GameConfig.TILESIZE;
        int entityCollisionBottomRow = entityCollisionBottomWorldY/GameConfig.TILESIZE;

        int newEntityCollisionRightCol,newEntityCollisionBottomRow,newEntityCollisionLeftCol,newEntityCollisionTopRow;

        newEntityCollisionTopRow = Math.floorDiv(entityCollisionTopWorldY - entitySpeed,GameConfig.TILESIZE);
        verticalCollision = checkUpDown(newEntityCollisionTopRow,entityCollisionLeftCol,entityCollisionRightCol, map);
        if(verticalCollision) dY = -1;


        newEntityCollisionBottomRow = (entityCollisionBottomWorldY + entitySpeed)/GameConfig.TILESIZE;
        verticalCollision = checkUpDown(newEntityCollisionBottomRow,entityCollisionLeftCol,entityCollisionRightCol, map);
        if (verticalCollision) dY = 1;


        newEntityCollisionLeftCol = Math.floorDiv(entityCollisionLeftWorldX - entitySpeed,GameConfig.TILESIZE);
        horizontalCollision = checkLeftRight(newEntityCollisionLeftCol,entityCollisionTopRow,entityCollisionBottomRow, map);
        if (horizontalCollision) dX = -1;

        newEntityCollisionRightCol = (entityCollisionRightWorldX + entitySpeed)/GameConfig.TILESIZE;
        horizontalCollision = checkLeftRight(newEntityCollisionRightCol,entityCollisionTopRow,entityCollisionBottomRow, map);
        if (horizontalCollision) dX = 1;

        return Direction.fromValues(dX,dY);
    }

    static private boolean checkLeftRight(int col,int topRow,int bottomRow,TileMap map){
        int tileNum1,tileNum2;
        TileSet tileSet = map.getTileSet();
        if (col<0 || col > map.getCols()-1){return true;}
        tileNum1 = map.getMap()[col][topRow];
        tileNum2 = map.getMap()[col][bottomRow];
        return (tileSet.getTile(tileNum1).collision || tileSet.getTile(tileNum2).collision);
    }

    static private boolean checkUpDown(int row,int leftCol,int rightCol,TileMap map){
        int tileNum1,tileNum2;
        TileSet tileSet = map.getTileSet();
        if (row<0 || row>map.getRows()-1){return true;}
        tileNum1 = map.getMap()[leftCol][row];
        tileNum2 = map.getMap()[rightCol][row];
        return (tileSet.getTile(tileNum1).collision || tileSet.getTile(tileNum2).collision);
    }
}
