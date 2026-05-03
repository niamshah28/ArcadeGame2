import java.awt.*;

public class Pokeball {
    public int xpos, ypos, dx, dy, width, height;
    public boolean isAlive;
    public Rectangle hitbox;

    public Pokeball(int x, int y) {
        xpos = x;
        ypos = y;
        dx = 0;
        dy = 0;
        width = 25;
        height = 25;
        isAlive = false; // only appears when shot
        hitbox = new Rectangle(xpos, ypos, width, height);
    }

    public void shoot(int startX, int startY, int speedX, int speedY) {
        xpos = startX;
        ypos = startY;
        dx = speedX * 5;
        dy = speedY * 5;
        isAlive = true;
    }

    public void move() {
        if (!isAlive) return;

        xpos += dx;
        ypos += dy;

        if (xpos < 0 || xpos > 1000 || ypos < 0 || ypos > 700) {
            isAlive = false;
        }

        hitbox = new Rectangle(xpos, ypos, width, height);
    }
}
