package map;

import javax.imageio.ImageIO;
import java.util.Objects;

// Singleton
public class TileSet {


    private final Tile[] tile;

    public TileSet() {
        tile = new Tile[2];
        initializeTileSprite();
    }


    private void initializeTileSprite() {
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(
                    Objects.requireNonNull(
                            getClass().getResourceAsStream("/UI/Image/tiles/grass.png")
                    )
            );
            tile[0].collision = false;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(
                    Objects.requireNonNull(
                            getClass().getResourceAsStream("/UI/Image/tiles/water.png")
                    )
            );
            tile[1].collision = true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Tile getTile(int index) {
        if (index >= tile.length || index < 0) return null;
        return tile[index];
    }
}