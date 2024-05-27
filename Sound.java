import javax.sound.sampled.*;
import java.io.*;

public class Sound {

    private static final MyHashTable<String, Clip> clips = new MyHashTable<>(500);

    public static void playSound(String fileName){
        checkClip(fileName);

        Clip clip = clips.get(fileName);
        clip.start();
    }

    private static void checkClip(String fileName){
        if(clips.get(fileName) == null){
            try{
                AudioSystem.getAudioInputStream(new File(fileName));
                clips.put(fileName, AudioSystem.getClip());
            }catch(LineUnavailableException e){
                e.printStackTrace();
            }catch(UnsupportedAudioFileException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
