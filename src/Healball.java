import java.awt.*;

public class Healball {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;//a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;

    public Healball(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =5;
        dy =5;
        width = 150;
        height = 150;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);


    } // constructor
    public void move() {
        if(xpos >= 1000-width){ //Right wall
            dx = -dx;
        }

        if(xpos < 0){ // Left wall
            dx = -dx;
        }

        if(ypos < 0){ // Top wall
            dy=-dy;
        }
        if (ypos >= 700-height ){ // Bottom wall
            dy = -dy;
        }


        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos, ypos, width, height);

    }
}
