import entity.EntityType;
import entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player p;


    @BeforeEach
    public void createPlayer(){
        p=new Player(0);
    }

    @Test
    public void creationTest() {

        assertNotNull(p, "Player is null");
        assertEquals(0, p.getId());
        assertEquals(EntityType.PLAYER, p.getType());
        assertNotNull(p.getPosition());
    }

    @Test
    public void playerIsCreatedAtPositionZeroZero() {

        assertEquals(0, p.getPosition().x);
        assertEquals(0, p.getPosition().y);
    }

    @Test
    public void translateShouldMovePlayer() {

        p.translate(10, 15);

        assertEquals(10, p.getPosition().x);
        assertEquals(15, p.getPosition().y);
    }

    @Test
    public void translateShouldAccumulateMovement() {

        p.translate(10, 5);
        p.translate(-3, 7);

        assertEquals(7, p.getPosition().x);
        assertEquals(12, p.getPosition().y);
    }

    @Test
    public void playerOptionalDataByDefault() {

        assertTrue(p.hasMovementState());
        assertTrue(p.hasCombatStats());
        assertFalse(p.hasInteractionData());

        assertNotNull(p.getMovementState());
        assertNotNull(p.getCombatStats());
        assertNull(p.getInteractionData());
    }

    @Test
    public void playerCanBeActivatedAndDeactivated() {

        p.activate();
        assertTrue(p.isActive());

        p.deactivate();
        assertFalse(p.isActive());
    }

    @Test
    public void playerHasNoFrameWithoutAnimationSet() {

        assertNull(p.getFrame());

        assertDoesNotThrow(p::nextFrame);
    }
}