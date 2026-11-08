package core;


import java.awt.*;

public class GameConfig {
    public static final int ORIGINALTILESIZE =16;
    public static final int SCALE =4;
    public static final int TILESIZE = SCALE * ORIGINALTILESIZE; //64 pixels
    public static final int MAXSCREENCOL =16;
    public static final int MAXSCREENLINE =12;
    public static final int SCREENWIDTH = MAXSCREENCOL * TILESIZE; //768 pixels
    public static final int SCREENHEIGHT = MAXSCREENLINE * TILESIZE;  //576 pixels
    public static final int FPS=60;
    public static final long UPDATE_INTERVAL_NS = 1_000_000_000L / FPS;
    public static final double FIXED_DELTA_SECONDS = 1.0 / FPS;
    public static final int WORLDCOLNUMBER =32;
    public static final int WORLDLINENUMBER =24;
    public static final double ENTITY_BASE_SPEED_PX_PER_SECOND = 240.0;
    public static final int ORIGINALENTITYWIDTH=16;
    public static final int ORIGINALENTITYHEIGHT=16;
    public static final int ENTITYWIDTH = ORIGINALENTITYWIDTH * SCALE;
    public static final int ENTITYHEIGHT = ORIGINALENTITYHEIGHT* SCALE;
    public static final int PLAYERBASEDAMAGE=1;
    public static final Rectangle ENTITYBASEHITBOX = new Rectangle(ENTITYWIDTH/4,ENTITYHEIGHT/4,ENTITYWIDTH/2,ENTITYWIDTH/2);


}
