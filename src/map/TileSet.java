package map;

import javax.imageio.ImageIO;
import java.util.Objects;

public class TileSet {

    private static TileSet instance; // unique instance

    private final Tile[] tile;

    private TileSet() {
        tile = new Tile[2];
        initializeTileSprite();
    }

    public static TileSet getInstance() {
        if (instance == null) {
            instance = new TileSet();
        }
        return instance;
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