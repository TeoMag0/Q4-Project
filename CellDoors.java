
/*
 * controls for the cell doors
 */

public class CellDoors implements Runnable{
    private static final Vector2[][] cellDoors = new Vector2[][] {
        new Vector2[] { new Vector2(-3, -16), new Vector2(-3, -17) },//cell 1
        new Vector2[] { new Vector2(2, -16), new Vector2(2, -17) },// cell 2
        new Vector2[] { new Vector2(-3, -21), new Vector2(-3, -22) },// cell 3
        new Vector2[] { new Vector2(2, -21), new Vector2(2, -22) },// cell 4
    };

    public static void openCellDoors(){
        new Thread(new CellDoors()).start();
    }
    public void run(){
        try{
            Thread.sleep(4000);
            for(Vector2[] door : cellDoors){
                for(Vector2 tile : door){
                    TileMap.Singleton.addTileRC(tile, TilePic.STONE_BRICK_FLOOR, false);
                }
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public static void closeCellDoors(){
        for (Vector2[] door : cellDoors) {
            for (Vector2 tile : door) {
                TileMap.Singleton.addTileRC(tile, TilePic.JAIL_BARS, true);
            }
        }
    }
}
