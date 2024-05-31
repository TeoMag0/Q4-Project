import java.io.*;
import java.util.Scanner;


public class BalloonSaveLoad {

    private static final String saveFile = "ThisIsWhereTheBalloonLocationsAreStoredOK.txt";

    public static void save(MyArrayList<Vector2> locs) {
        try {
            PrintWriter out = new PrintWriter(saveFile);

            for (Vector2 each : locs) {
                out.println(each);
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static MyArrayList<Vector2> load() {
        try {
            Scanner scan = new Scanner(new File(saveFile));

            MyArrayList<Vector2> list = new MyArrayList<>();
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                float x = Float.parseFloat(s.substring(1, s.indexOf(",")));
                float y = Float.parseFloat(s.substring(s.indexOf(",")+1, s.indexOf(">")));
                list.add(new Vector2(x, y));
            }
            scan.close();
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}