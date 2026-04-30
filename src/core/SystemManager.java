package core;

import interfaces.ISystem;

import java.util.ArrayList;

public class SystemManager {
    private final ArrayList<ISystem> systems;

    public SystemManager() {
        this.systems = new ArrayList<>();
    }

    public void addSystem(ISystem system){
        systems.add(system);
    }

//    public void removeSystem(ISystem system){
//        systems.remove(system);
//    }
//
//    public ArrayList<ISystem> getSystems(){
//        return systems;
//    }

    public void update(){
        for (ISystem system : systems) system.update();
    }
}
