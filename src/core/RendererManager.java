package core;

import java.awt.*;
import java.util.ArrayList;

public class RendererManager {
    private final ArrayList<Renderer> renderers;

    public RendererManager() {
        this.renderers = new ArrayList<>();
    }

    public void addRenderer(Renderer renderer){
        renderers.add(renderer);
    }

//    public void removeRenderer(Renderer renderer){
//        renderers.remove(renderer);
//    }
//
//    public ArrayList<Renderer> getRenderers(){
//        return renderers;
//    }

    public void render(Graphics2D g2){
        for (Renderer renderer : renderers) renderer.draw(g2);
    }
}
