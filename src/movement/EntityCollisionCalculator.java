package movement;

import core.GameConfig;
import entity.Entity;
import map.TileMap;
import map.TileSet;

import java.awt.*;

public class EntityCollisionCalculator {


    public static Point handleWalls(Entity entity, TileMap map, int dX, int dY){
        int[][] mapData = map.getMap();
        TileSet tileSet = map.getTileSet();
        Point position = entity.getPosition();

        int movedX = moveAxis(entity, map, mapData, tileSet, position.x, position.y, dX, true);
        int movedY = moveAxis(entity, map, mapData, tileSet, position.x + movedX, position.y, dY, false);
        return new Point(movedX, movedY);
    }

    private static int moveAxis(Entity entity, TileMap map, int[][] mapData, TileSet tileSet,
                                int startX, int startY, int distance, boolean horizontal){
        int direction = Integer.signum(distance);
        int moved = 0;

        for (int i = 0; i < Math.abs(distance); i++) {
            int nextX = startX + (horizontal ? moved + direction : 0);
            int nextY = startY + (horizontal ? 0 : moved + direction);
            if (!canOccupy(entity, map, mapData, tileSet, nextX, nextY)) break;
            moved += direction;
        }

        return moved;
    }

    private static boolean canOccupy(Entity entity, TileMap map, int[][] mapData, TileSet tileSet,
                                     int entityX, int entityY){
        Rectangle solidArea = entity.getCollisionData().solidArea;
        int leftCol = Math.floorDiv(entityX + solidArea.x, GameConfig.TILESIZE);
        int rightCol = Math.floorDiv(entityX + solidArea.x + solidArea.width - 1, GameConfig.TILESIZE);
        int topRow = Math.floorDiv(entityY + solidArea.y, GameConfig.TILESIZE);
        int bottomRow = Math.floorDiv(entityY + solidArea.y + solidArea.height - 1, GameConfig.TILESIZE);

        for (int col = leftCol; col <= rightCol; col++) {
            for (int row = topRow; row <= bottomRow; row++) {
                if (col < 0 || col >= map.getCols() || row < 0 || row >= map.getRows()) return false;
                if (tileSet.getTile(mapData[col][row]).collision) return false;
            }
        }

        return true;
    }
}
