package map;

import movement.Camera;
import core.Renderer;
import core.GameConfig;

import java.awt.*;

public class MapRenderer implements Renderer {
    private final Camera camera;
    private final TileMap map;
    private final TileSet tileset;

    public MapRenderer(TileMap map, Camera camera, TileSet tileset){
        this.camera=camera;
        this.map = map;
        this.tileset = tileset;
    }

    @Override
    public void draw(Graphics2D g){
        drawMap(g);
    }


    public void drawMap(Graphics2D g){
        int playerX = camera.getX();
        int playerY = camera.getY();
        int playerScreenX= (GameConfig.SCREENWIDTH - GameConfig.TILESIZE) / 2;
        int playerScreenY= (GameConfig.SCREENHEIGHT - GameConfig.TILESIZE) / 2;
        for(int y = 0; y< GameConfig.WORLDLINENUMBER; y++){
            for(int x = 0; x<GameConfig.WORLDCOLNUMBER; x++){
                int tileNum =map.getMap()[x][y];
                int tileScreenX= (x * GameConfig.TILESIZE) - playerX +playerScreenX; //CaseX - JoueurX + JoueurX sur l'ecran
                int tileScreenY= (y * GameConfig.TILESIZE) - playerY +playerScreenY;   //CaseY - JoueurY + JoueurY sur l'ecran
                if (tileScreenX<-GameConfig.TILESIZE || tileScreenY<-GameConfig.TILESIZE || tileScreenX>GameConfig.WORLDCOLNUMBER * GameConfig.TILESIZE || tileScreenY>GameConfig.WORLDLINENUMBER *GameConfig.TILESIZE) continue;
                g.drawImage(tileset.getTile(tileNum).image, tileScreenX, tileScreenY, GameConfig.TILESIZE, GameConfig.TILESIZE, null);

            } // for y
        }//for x
    }
}
