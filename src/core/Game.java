package core;

import world.Camera;
import world.MapLoader;
import world.MapRenderer;
import world.TileMap;

import java.awt.*;

public class Game {
    private MapRenderer mapRenderer;
    private Camera camera;


    public void update(){

    }
    public void render(Graphics2D g2){
        mapRenderer.drawMap(g2);
    }

    public void initialize() {
        camera = new Camera();

        MapLoader mapLoader = new MapLoader();
        TileMap map = mapLoader.initializeMap();
        mapRenderer = new MapRenderer(map,camera);
    }

}
