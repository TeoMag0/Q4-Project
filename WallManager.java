import java.awt.*;

//keeps track of all walls

public class WallManager implements DrawableObject{
    public static final WallManager Singleton = new WallManager();
    private MyArrayList<Wall> walls;

    public WallManager(){
        walls = Wall.allWalls;

        //create walls here
        new Wall(new Vector2(-2,0), new Vector2(1,1));
    }

    public void drawMe(Graphics g){
        for(Wall each : walls){
            each.drawMe(g);
        }
    }
}
