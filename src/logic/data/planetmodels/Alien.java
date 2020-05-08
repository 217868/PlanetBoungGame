package logic.data.planetmodels;

public class Alien {

    private boolean isDestroyed;

    private int x;
    private int y;

    public Alien(int x, int y) {
        this.x = x;
        this.y = y;

        isDestroyed = false;
    }

    public void destroy() {
        isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
