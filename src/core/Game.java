package core;


import animation.AnimationManager;
import animation.EntityAnimationRenderer;
import animation.AnimationSystem;
import core.input.InputState;
import entity.Enemy;
import entity.EntityFactory;
import movement.EnemyAISystem;
import movement.MovementSystem;
import movement.Camera;
import movement.PlayerInputSystem;
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

    public void update(double deltaSeconds){
        synchronized (gameStateLock) {
            systemManager.update(deltaSeconds);
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

        Enemy enemy = entityFactory.createEnemy(10,10);
        enemy.setAnimationSet(animationManager.createAnimationSet(Player.class));
        entityManager.addEntity(enemy);

        //Création camera
        Camera camera = new Camera(player);

        //Chargement de la carte et de son renderer
        TileMap map = MapLoader.initializeMap("/map/map.txt");
        MapRenderer mapRenderer = new MapRenderer(map,camera);
        rendererManager.addRenderer(mapRenderer);

        // L'entrée du joueur définit une intention ; le système commun applique le mouvement.
        systemManager.addSystem(new EnemyAISystem(entityManager));
        systemManager.addSystem(new PlayerInputSystem(player, inputState));
        systemManager.addSystem(new MovementSystem(entityManager, map));

        //Animation Render
        EntityAnimationRenderer animationRenderer = new EntityAnimationRenderer(entityManager,camera);
        rendererManager.addRenderer(animationRenderer);

        //Système animation
        AnimationSystem animationSystem = new AnimationSystem(entityManager);
        systemManager.addSystem(animationSystem);


    }
}
