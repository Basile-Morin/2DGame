package animation;

import entity.Entity;
import entity.Player;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AnimationManager {

    private final Map<
            Class<? extends Entity>,
            EnumMap<AnimationType, BufferedImage[]>
            > entityAnimationDataMap;
    private static final int FRAME_DURATION = 30;

    public AnimationManager() {
        entityAnimationDataMap = new HashMap<>();
        initialize();
    }

    private void initialize() {
        try {
            EnumMap<AnimationType, BufferedImage[]> playerMap =
                    new EnumMap<>(AnimationType.class);

            // WALK DOWN
            BufferedImage down0 = load("/UI/Image/boy/boyDown0.png");
            BufferedImage down1 = load("/UI/Image/boy/boyDown1.png");
            BufferedImage down2 = load("/UI/Image/boy/boyDown2.png");

            playerMap.put(AnimationType.IDLE_DOWN, new BufferedImage[]{down0});
            playerMap.put(AnimationType.WALK_DOWN, new BufferedImage[]{down1, down2});

            // WALK UP
            BufferedImage up0 = load("/UI/Image/boy/boyUp0.png");
            BufferedImage up1 = load("/UI/Image/boy/boyUp1.png");
            BufferedImage up2 = load("/UI/Image/boy/boyUp2.png");

            playerMap.put(AnimationType.IDLE_UP, new BufferedImage[]{up0});
            playerMap.put(AnimationType.WALK_UP, new BufferedImage[]{up1, up2});

            // WALK LEFT
            BufferedImage left0 = load("/UI/Image/boy/boyLeft0.png");
            BufferedImage left1 = load("/UI/Image/boy/boyLeft1.png");
            BufferedImage left2 = load("/UI/Image/boy/boyLeft2.png");

            playerMap.put(AnimationType.IDLE_LEFT, new BufferedImage[]{left0});
            playerMap.put(AnimationType.WALK_LEFT, new BufferedImage[]{left1, left2});

            // WALK RIGHT
            BufferedImage right0 = load("/UI/Image/boy/boyRight0.png");
            BufferedImage right1 = load("/UI/Image/boy/boyRight1.png");
            BufferedImage right2 = load("/UI/Image/boy/boyRight2.png");

            playerMap.put(AnimationType.IDLE_RIGHT, new BufferedImage[]{right0});
            playerMap.put(AnimationType.WALK_RIGHT, new BufferedImage[]{right1, right2});

            // UP RIGHT
            BufferedImage upright0 = load("/UI/Image/boy/boyUpRight0.png");
            BufferedImage upright1 = load("/UI/Image/boy/boyUpRight1.png");
            BufferedImage upright2 = load("/UI/Image/boy/boyUpRight2.png");

            playerMap.put(AnimationType.IDLE_UP_RIGHT, new BufferedImage[]{upright0});
            playerMap.put(AnimationType.WALK_UP_RIGHT, new BufferedImage[]{upright1, upright2});

            BufferedImage downright0 = load("/UI/Image/boy/boyDownRight0.png");
            BufferedImage downright1 = load("/UI/Image/boy/boyDownRight1.png");
            BufferedImage downright2 = load("/UI/Image/boy/boyDownRight2.png");

            playerMap.put(AnimationType.IDLE_DOWN_RIGHT, new BufferedImage[]{downright0});
            playerMap.put(AnimationType.WALK_DOWN_RIGHT, new BufferedImage[]{downright1, downright2});

            BufferedImage upleft0 = load("/UI/Image/boy/boyUpLeft0.png");
            BufferedImage upleft1 = load("/UI/Image/boy/boyUpLeft1.png");
            BufferedImage upleft2 = load("/UI/Image/boy/boyUpLeft2.png");

            playerMap.put(AnimationType.IDLE_UP_LEFT, new BufferedImage[]{upleft0});
            playerMap.put(AnimationType.WALK_UP_LEFT, new BufferedImage[]{upleft1, upleft2});

            BufferedImage downleft0 = load("/UI/Image/boy/boyDownLeft0.png");
            BufferedImage downleft1 = load("/UI/Image/boy/boyDownLeft1.png");
            BufferedImage downleft2 = load("/UI/Image/boy/boyDownLeft2.png");

            playerMap.put(AnimationType.IDLE_DOWN_LEFT, new BufferedImage[]{downleft0});
            playerMap.put(AnimationType.WALK_DOWN_LEFT, new BufferedImage[]{downleft1, downleft2});

            entityAnimationDataMap.put(Player.class,playerMap);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private BufferedImage load(String path) throws IOException {
        return javax.imageio.ImageIO.read(
                Objects.requireNonNull(getClass().getResourceAsStream(path))
        );
    }

    public AnimationSet createAnimationSet(Class<? extends Entity> clazz) {
        EnumMap<AnimationType, BufferedImage[]> data = entityAnimationDataMap.get(clazz);

        if (data == null) throw new IllegalArgumentException("Aucune animation enregistrée pour : " + clazz.getName());

        EnumMap<AnimationType, Animation> animations = new EnumMap<>(AnimationType.class);

        for (Map.Entry<AnimationType, BufferedImage[]> entry : data.entrySet()) {
            animations.put(
                    entry.getKey(),
                    new Animation(entry.getValue(), FRAME_DURATION, true)
            );
        }

        return new AnimationSet(animations);
    }
}