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
        return NO_DIRECTION;    }

//    public boolean isDiagonal(){
//        return dx!=0 && dy!=0;
//    }
}