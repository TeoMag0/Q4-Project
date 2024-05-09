
/*
 * controls boss room entry
 */

public class Entry {
    private static final Vector2[] entryTiles = new Vector2[] {
        new Vector2(-2, -8),
        new Vector2(-1, -8),
        new Vector2(0, -8),
        new Vector2(1, -8),
    };

    public static void closeEntry(){
        for(Vector2 each : entryTiles){
            TileMap.Singleton.addTileRC(each, TilePic.STONE_WALL, true);
        }
    }
    public static void openEntry(){
        for (Vector2 each : entryTiles) {
            TileMap.Singleton.addTileRC(each, TilePic.STONE_BRICK_FLOOR, false);
        }
    }
}
