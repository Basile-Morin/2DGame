package world;

import core.GameConfig;

import java.awt.*;

public class MapRenderer {
    private final Camera camera;
    private TileMap map;
    private final TileSet tileset;

    public MapRenderer(TileMap map,Camera camera){
        this.camera=camera;
        this.tileset= new TileSet();
        this.map = map;
    }


    public void drawMap(Graphics2D g){
        int playerX = camera.getX();
        int playerY = camera.getY();
        int playerScreenX= (GameConfig.screenWidth - GameConfig.tileSize) / 2;
        int playerScreenY= (GameConfig.screenHeight - GameConfig.tileSize) / 2;
        for(int y = 0; y< GameConfig.worldLineNumber; y++){
            for(int x=0;x<GameConfig.worldColNumber;x++){
                int tileNum =map.getMap()[x][y];
                int tileScreenX= (x * GameConfig.tileSize) - playerX +playerScreenX; //CaseX - JoueurX + JoueurX sur l'ecran
                int tileScreenY= (y * GameConfig.tileSize) - playerY +playerScreenY;   //CaseY - JoueurY + JoueurY sur l'ecran
                if (tileScreenX<-GameConfig.tileSize || tileScreenY<-GameConfig.tileSize || tileScreenX>GameConfig.worldColNumber* GameConfig.tileSize || tileScreenY>GameConfig.worldLineNumber*GameConfig.tileSize) continue;
                g.drawImage(tileset.getTile(tileNum).image, tileScreenX, tileScreenY, GameConfig.tileSize, GameConfig.tileSize, null);

            } // for y
        }//for x
    }
}
