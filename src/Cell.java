import java.awt.*;

public class Cell {
    private final int x;
    private final int y;
    private boolean alive;

    public Cell(int x, int y, boolean alive){
        this.x = x;
        this.y = y;
        this.alive = alive;
        System.out.println("Created cell at: " + x + ", " + y);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean isAlive(){
        return alive;
    }

    public void setAlive(boolean alive){
        this.alive = alive;
    }
}
