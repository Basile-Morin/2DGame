package core;

import UI.Renderer;
import entity.Player;
import world.Camera;
import world.MapLoader;
import world.MapRenderer;
import world.TileMap;

import java.awt.*;
import java.util.ArrayList;

public class Game {
    private final ArrayList<Renderer> renderers;
    private final ArrayList<System> systems;
    private Camera camera;

    public Game(){
        renderers = new ArrayList<>();
        systems = new ArrayList<>();
    }

    public void update(){

    }

    public void render(Graphics2D g2){
        for (Renderer renderer : renderers) renderer.draw(g2);
    }

    public void initialize() {
        camera = new Camera();

        //Chargement de la carte et de son renderer
        MapLoader mapLoader = new MapLoader();
        TileMap map = mapLoader.initializeMap();
        MapRenderer mapRenderer = new MapRenderer(map,camera);
        renderers.add(mapRenderer);

        //Création du joueur
        Player player = new Player();


    }

}
