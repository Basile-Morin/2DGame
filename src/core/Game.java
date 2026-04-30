package core;


import animation.AnimationManager;
import animation.EntityAnimationRenderer;
import animation.AnimationSystem;
import core.input.InputState;
import entity.EntityFactory;
import movement.Camera;
import movement.PlayerMovementSystem;
import entity.EntityManager;
import entity.Player;
import map.MapLoader;
import map.MapRenderer;
import map.TileMap;
import map.TileSet;

import java.awt.*;

public class Game {
    private final Object gameStateLock = new Object();

    private final RendererManager rendererManager;
    private final SystemManager systemManager;
    private final InputState inputState;

    public Game(InputState inputState){
        this.inputState = inputState;
        this.rendererManager = new RendererManager();
        this.systemManager = new SystemManager();
    }

    public void update(){
        synchronized (gameStateLock) {
            systemManager.update();
        }
    }

    public void render(Graphics2D g2){
        synchronized (gameStateLock) {
            rendererManager.render(g2);
        }
    }

    public void initialize() {
        EntityManager entityManager = new EntityManager();
        EntityFactory entityFactory= new EntityFactory(entityManager);
        AnimationManager animationManager = new AnimationManager();

        //Création du joueur
        Player player = entityFactory.createPlayer(0,0);
        player.setAnimationSet(animationManager.createAnimationSet(Player.class));
        entityManager.addEntity(player);

        //Création camera
        Camera camera = new Camera(player);

        //Chargement de la carte et de son renderer
        TileSet tileSet = new TileSet();
        TileMap map = MapLoader.initializeMap("/map/map.txt");
        MapRenderer mapRenderer = new MapRenderer(map,camera);
        rendererManager.addRenderer(mapRenderer);

        //Système de mouvement
        PlayerMovementSystem movementSystem = new PlayerMovementSystem(player,inputState,map);
        systemManager.addSystem(movementSystem);

        //Animation Render
        EntityAnimationRenderer animationRenderer = new EntityAnimationRenderer(entityManager,camera);
        rendererManager.addRenderer(animationRenderer);

        //Système animation
        AnimationSystem animationSystem = new AnimationSystem(entityManager);
        systemManager.addSystem(animationSystem);


    }
}
