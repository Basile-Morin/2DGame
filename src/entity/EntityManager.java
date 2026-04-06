package entity;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    private final List<Entity> entities;

    public EntityManager() {
        entities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public Player getPlayer() {
        if (entities.isEmpty()) return null;
        return (Player) entities.getFirst();
    }
}