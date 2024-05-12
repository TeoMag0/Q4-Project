import java.io.*;
import java.util.Scanner;

/*
 * purpose: manage file saving and loading for the map
 */

public class TileMapSaveLoad{

    private static final String saveFile = "virus.exe";
    private static final String textSaveFile = "AAAAAA.txt";

    public static void saveMap(Pair<Vector2, Tile>[] tiles){
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(saveFile));
            output.writeObject(tiles);
            output.close();
        }catch(IOException e){
            e.printStackTrace();
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
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void saveMapToText(Pair<Vector2, Tile>[] tiles){
        try{
            PrintWriter out = new PrintWriter(textSaveFile);

            for(Pair<Vector2, Tile> each : tiles){
                out.println(String.format("%s|%s|%s",each.key, each.val.pic(),each.val.isWall()));
            }
            out.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    public static Pair<Vector2, Tile>[] loadMapFromText(){
        try{
            Scanner scan = new Scanner(new File(textSaveFile));

            MyArrayList<String> tileList = new MyArrayList<>();
            while (scan.hasNextLine()) {
                tileList.add(scan.nextLine());
            }
            Pair<Vector2, Tile>[] array = new Pair[tileList.size()];
            for (int i = 0; i < array.length; i++) {
                String s = tileList.get(i);

                float vX = Float.parseFloat(s.substring(s.indexOf("<") + 1, s.indexOf(",")));
                float vY = Float.parseFloat(s.substring(s.indexOf(",") + 1, s.indexOf(">")));

                String picString = s.substring(s.indexOf("|") + 1, s.lastIndexOf("|"));
                TilePic pic = TilePic.STONE_BRICK_FLOOR;
                if (picString.equals("STONE_WALL")) {
                    pic = TilePic.STONE_WALL;
                } else if (picString.equals("JAIL_BARS")) {
                    pic = TilePic.JAIL_BARS;
                }

                boolean isWall = Boolean.parseBoolean(s.substring(s.lastIndexOf("|") + 1));

                Vector2 vector = new Vector2(vX, vY);
                array[i] = new Pair<>(vector, new Tile(vector, pic, isWall, TileMap.Singleton.tileSize()));
            }
            scan.close();
            return array;
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}