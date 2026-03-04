package Asset;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AssetManager {
    private final Map<String, BufferedImage> cache = new HashMap<>();

    public BufferedImage getImage(String path) {
        return cache.computeIfAbsent(path, p -> {
            try {
                return ImageIO.read(Objects.requireNonNull(AssetManager.class.getResourceAsStream(p)));
            } catch (IOException | IllegalArgumentException e) {
                throw new RuntimeException("Impossible de charger l'image: " + p, e);
            }
        });
    }
}