import java.awt.*;

public class StevenStone {

    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;//a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;

    public StevenStone(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =5;
        dy =5;
        width = 150;
        height = 150;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);

    } // constructor



}
