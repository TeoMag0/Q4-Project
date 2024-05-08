import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

/*
 * purpose: maintains and draws the background and walls I guess
 * to update: drawme must have cases for all tilepics
 */

public class TileMap extends Startable implements DrawableObject{
    public static final TileMap Singleton = new TileMap(.5f);
    private MyHashTable<Vector2, Tile> tileMap;//key is <column, row>
    private float tileSize;

    public TileMap(float tileSize){
        tileMap = new MyHashTable<>(1000);
        this.tileSize = tileSize;
    }
    public void start(){
        Pair<Vector2, Tile>[] map = TileMapSaveLoad.loadMapFromText();

        for (int i = 0; i < map.length; i++) {
            tileMap.put(map[i].key, map[i].val);
            tileMap.get(map[i].key).setUp();
        }
    }

    public void drawMe(Graphics g){
        for(Vector2 each : tileMap.keySet().toDLList()){
            Tile tile = tileMap.get(each);
            
            Vector2 tilePixelCoords = Screen.getScreenCoords(rcToCoords(each));
            Vector2 tileBR = Vector2.sum(tilePixelCoords, new Vector2(Screen.toPixels(tileSize), Screen.toPixels(tileSize)));
            if(tilePixelCoords.getX() <= Screen.screenPixelDimensions.getX() && tilePixelCoords.getY() <= Screen.screenPixelDimensions.getY() && tileBR.getX() >= 0 && tileBR.getY() >= 0){
                g.drawImage(tile.pic().pic(), tilePixelCoords.intX(), tilePixelCoords.intY(), Screen.toPixels(tileSize), Screen.toPixels(tileSize), null);
            }
        }
    }

    public Vector2 rcToCoords(Vector2 rc){
        return Vector2.multiply(rc, tileSize);
    }
    public Vector2 coordsToRC(Vector2 coords){
        Vector2 condensed = Vector2.multiply(coords , 1f/tileSize);
        return new Vector2((int)Math.floor(condensed.getX()), (int)Math.ceil(condensed.getY()));
    }

    public void addTile(Vector2 coords, TilePic pic, boolean isWall){
        Vector2 rc = coordsToRC(coords);
        if(tileMap.get(rc) != null){
            removeTile(coords);
        }
        tileMap.put(rc, new Tile(rc, pic, isWall, tileSize));
    }
    public void addTileRC(Vector2 rc, TilePic pic, boolean isWall){
        if (tileMap.get(rc) != null) {
            removeTile(rcToCoords(rc));
        }
        tileMap.put(rc, new Tile(rc, pic, isWall, tileSize));
    }
    public void removeTile(Vector2 coords){
        Vector2 rc = coordsToRC(coords);
        if(tileMap.get(rc) != null){
            tileMap.remove(rc).deleteCollider();
        }
    }
    public float tileSize(){
        return tileSize;
    }

    @SuppressWarnings("unchecked")
    public void saveMap(){
        Pair<Vector2, Tile>[] array = new Pair[tileMap.keySet().toDLList().size()];

        int i=0;
        for(Vector2 each : tileMap.keySet().toDLList()){
            array[i] = new Pair<Vector2, Tile>(each, tileMap.get(each));
            i++;
        }
        TileMapSaveLoad.saveMap(array);
    }
    
    @SuppressWarnings("unchecked")
    public void saveToTextFile(){
        Pair<Vector2, Tile>[] array = new Pair[tileMap.keySet().toDLList().size()];

        int i = 0;
        for (Vector2 each : tileMap.keySet().toDLList()) {
            array[i] = new Pair<Vector2, Tile>(each, tileMap.get(each));
            i++;
        }
        TileMapSaveLoad.saveMapToText(array);
    }
}

enum TilePic {
    STONE_BRICK_FLOOR("FloorTile.png"),
    STONE_WALL("wall1.png"),
    JAIL_BARS("JailBars.png");
    ;
    private BufferedImage pic;
    private TilePic(String s){
        try{
            pic = ImageIO.read(new File(s));
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public BufferedImage pic(){
        return pic;
    }
}
