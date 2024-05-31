import java.awt.Graphics;

public class BalloonManager {
    private static MyArrayList<Vector2> locs = new MyArrayList<>();
    private static MyArrayList<Balloon> balloons;

    public static void setUpBalloons(){
        balloons = new MyArrayList<>();
        locs = BalloonSaveLoad.load();
        for(Vector2 each : locs){
            balloons.add(new Balloon(each, getRandomColor()));
        }
    }
    public static void drawAll(Graphics g){
        for(Balloon each : balloons){
            each.drawMe(g);
        }
    }

    public static void addBalloon(Vector2 loc){
        locs.add(loc);
        BalloonSaveLoad.save(locs);
        setUpBalloons();
    }

    private static String getRandomColor(){
        int c = (int)(Math.random()*4);
        switch(c){
            case 0:
                return "R";
            case 1:
                return "G";
            case 2:
                return "Y";
            case 3:
                return "B";
        }
        return null;
    }
}
