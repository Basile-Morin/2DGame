package core;


import movement.input.InputState;
import movement.Camera;
import movement.MovementSystem;
import entity.EntityManager;
import entity.Player;
import map.MapLoader;
import map.MapRenderer;
import map.TileMap;
import map.TileSet;

import java.awt.*;
import java.util.ArrayList;

public class Game {
    private final ArrayList<Renderer> renderers;
    private final InputState inputState;
    private final ArrayList<ISystem> systems;

    public Game(InputState inputState){
        this.inputState = inputState;
        renderers = new ArrayList<>();
        systems = new ArrayList<>();
    }

    public void update(){
        for (ISystem system : systems) system.update();

    }

    public void render(Graphics2D g2){ for (Renderer renderer : renderers) renderer.draw(g2);}

    public void initialize() {
        //Création du joueur
        Player player = new Player();
        EntityManager entityManager = new EntityManager();
        entityManager.addEntity(player); //ENTITIES[0] EST LE JOUEUR
        Camera camera = new Camera(player);

        //Chargement de la carte et de son renderer
        TileMap map = MapLoader.initializeMap();
        TileSet tileSet = new TileSet();
        MapRenderer mapRenderer = new MapRenderer(map,camera,tileSet);
        renderers.add(mapRenderer);

        //Système de mouvement
        MovementSystem movementSystem = new MovementSystem(entityManager,inputState);
        systems.add(movementSystem);


    }
}
