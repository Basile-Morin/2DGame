package movement;

public enum Direction {
    UP(0,-1), DOWN(0,1), LEFT(-1,0), RIGHT(1,0),
    UP_LEFT(-1,-1), UP_RIGHT(1,-1), DOWN_LEFT(-1,1), DOWN_RIGHT(1,1), NO_DIRECTION(0,0);

    public final int dx, dy;

    Direction(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction fromValues(int dx, int dy) {
        for (Direction dir : values()) {
            if (dir.dx == dx && dir.dy == dy) {
                return dir;
            }
        }
        return NO_DIRECTION;
    }

    /** Converts any movement vector to the nearest one of the eight sprite directions. */
    public static Direction fromVector(double dx, double dy) {
        if (dx == 0.0 && dy == 0.0) return NO_DIRECTION;

        int octant = Math.floorMod((int) Math.round(Math.atan2(dy, dx) / (Math.PI / 4.0)), 8);
        return switch (octant) {
            case 0 -> RIGHT;
            case 1 -> DOWN_RIGHT;
            case 2 -> DOWN;
            case 3 -> DOWN_LEFT;
            case 4 -> LEFT;
            case 5 -> UP_LEFT;
            case 6 -> UP;
            case 7 -> UP_RIGHT;
            default -> NO_DIRECTION;
        };
    }

//    public boolean isDiagonal(){
//        return dx!=0 && dy!=0;
//    }
}
