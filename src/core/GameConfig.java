package core;

public class GameConfig {
    public static final int originalTileSize =16;
    public static final int scale =4;
    public static final int tileSize = scale * originalTileSize; //48 pixels
    public static final int maxScreenCol =16;
    public static final int maxScreenLine =12;
    public static final int screenWidth = maxScreenCol * tileSize; //768 pixels
    public static final int screenHeight = maxScreenLine * tileSize;  //576 pixels
    public static final int FPS=60;
    public static final double drawInterval = (double) 1000 /FPS;
    public static final int worldColNumber=32;
    public static final int worldLineNumber =24;
}
