package entity;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntityManager {
    private final Map<Integer,Entity> entityMap;
    private int nextId;

    public EntityManager() {
        this.entityMap = new HashMap<>();
        this.nextId =0;
    }
    public int generateId(){
        return nextId++;
    }

    public void addEntity(Entity entity){
        entityMap.put(entity.getId(),entity);
    }
    public void removeEntity(int id){
        entityMap.remove(id);
    }

    public Entity getEntity(int id){
        return entityMap.get(id);
    }

    public Collection<Entity> getEntities(){
        return Collections.unmodifiableCollection(entityMap.values());
    }

    public Collection<Player> getPlayers(){
        return entityMap.values().stream()
                .filter(e-> e.getType()==EntityType.PLAYER)
                .map(entity -> (Player) entity)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Collection<Entity> getEntitiesByType(EntityType type){
        return entityMap.values().stream()
                .filter(e->e.getType()==type)
                .toList();
    }

    public void clear(){
        entityMap.clear();
    }
}

/*class entity.EntityManager {
- entities : Map<Integer, Entity>
- nextId : int
+ EntityManager()
+ generateId() : int
+ addEntity(entity:Entity) : void
+ removeEntity(id:int) : void
+ getEntity(id:int) : Entity
+ getEntities() : Collection<Entity>
+ getPlayers() : Collection<Player>
+ getEntitiesByType(type:EntityType) : List<Entity>
+ clear() : void
}
 */