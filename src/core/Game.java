package core;


import core.input.InputState;
import movement.Camera;
import movement.MovementSystem;
import entity.EntityManager;
import entity.Player;
import map.MapLoader;
import map.MapRenderer;
import map.TileMap;
import map.TileSet;

import java.awt.*;

public class Game {
    private final RendererManager rendererManager;
    private final SystemManager systemManager;
    private final InputState inputState;

    public Game(InputState inputState){
        this.inputState = inputState;
        rendererManager = new RendererManager();
        systemManager = new SystemManager();
    }

    public void update(){
        systemManager.update();
    }

    public void render(Graphics2D g2){rendererManager.render(g2);}

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
        rendererManager.addRenderer(mapRenderer);

        //Système de mouvement
        MovementSystem movementSystem = new MovementSystem(entityManager,inputState);
        systemManager.addSystem(movementSystem);


    }
}
