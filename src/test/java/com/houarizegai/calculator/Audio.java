import java.io.*;
import javax.sound.sampled.*;
class Audio {
     File clickWav;
     Clip clip1;
   public static void processFile(File file, Clip clip) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
     AudioInputStream audiosystem = AudioSystem.getAudioInputStream(file);
       clip = AudioSystem.getClip();
       clip.open(audiosystem);
       clip.start();
       clip.setMicrosecondPosition(10);
}

  Audio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    clickWav = new File("button2.wav"); // Wav file goes here
}
 public void clickSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException{ // here is the method that plays the first file
     processFile(clickWav, clip1);
  }
/* you can add as many sound methods here by also adding files 
 *  for example we have clickWav file and clip1 file
 *  to implement this code into your code 
 *  simply write Audio audio = new Audio()
 *  and then call the method audio.clickSound();
 *  by surrounding it with throw declarations.
 */
}
