import java.io.*;

/*
 * purpose: manage file saving and loading for the map
 */

public class TileMapSaveLoad{

    private static final String saveFile = "virus.exe";

    public static void saveMap(Pair<Vector2, Tile>[] tiles){
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(saveFile));
            output.writeObject(tiles);
            output.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    @SuppressWarnings("unchecked")
    public static Pair<Vector2, Tile>[] loadMap(){
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(saveFile));
            Pair<Vector2, Tile>[] list = (Pair<Vector2, Tile>[])input.readObject();
            input.close();
            return list;
        } catch (IOException e) {
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return null;
    }
}