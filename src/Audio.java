import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {

    private final String filePath;

    Audio (String filePath) {
        this.filePath = filePath;
    }

    void play () {
        System.out.println("\n\n**ALARM SOUND**");
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(getFilePath())){
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        }
        catch (LineUnavailableException e) {
            System.out.println("File couldn't be played.");
        }
        catch (UnsupportedAudioFileException e) {
            System.out.println("Format not supported.");
        }
        catch (IOException e) {
            System.out.println("Something went wrong" + "\n" + "Error: " + e);
        }
    }

    File getFilePath () {
        return new File(this.filePath);
    }

}