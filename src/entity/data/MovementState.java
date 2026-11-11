package entity.data;

import movement.Direction;

import java.awt.*;

public class MovementState {
    /** Dernière direction demandée, conservée pour orienter l'animation à l'arrêt. */
    public Direction direction = Direction.DOWN;
    /** Vecteur voulu pour le tick courant, fourni par l'entrée ou l'IA. */
    public double desiredMovementX;
    public double desiredMovementY;
    /** Déplacement réellement effectué au dernier tick, après collision. */
    public Point movementDelta = new Point();
    public double speed;
    /** Restes sous-pixel conservés par le système de mouvement. */
    public double fractionalX;
    public double fractionalY;
}
