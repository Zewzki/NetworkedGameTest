package game;

public class Projectile {

    private static final int MAX_BOUNCE = 5;

    private float xVel;
    private float yVel;
    private int xPos;
    private int yPos;
    private int bounceCount;

    public Projectile(int x , int y, float xVel, float yVel) {
        xPos = x;
        yPos = y;
        this.xVel = xVel;
        this.yVel = yVel;
    }

    public float getxVel() {
        return xVel;
    }

    public float getyVel() {
        return yVel;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getBounceCount() {
        return bounceCount;
    }

    public int getMaxBounce() {
        return MAX_BOUNCE;
    }

}
