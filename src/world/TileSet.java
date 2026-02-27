package world;

import javax.imageio.ImageIO;
import java.util.Objects;

public class TileSet {
    private Tile[] tile;

    public TileSet(){
        this.initializeTileSprite();
    }

    public void initializeTileSprite(){
        try {
            tile = new Tile[2];

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Image/tiles/grass.png")));
            tile[0].collision=false;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Image/tiles/water.png")));
            tile[1].collision=true;



        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public Tile getTile(int index){
        if(index>=tile.length || index < 0) return null;
        return tile[index];
    }
}
