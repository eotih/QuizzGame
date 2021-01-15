package gameEOTIH;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class WinMusic extends Thread
{
	public WinMusic() throws LineUnavailableException, IOException, UnsupportedAudioFileException
	{		
		File file = new File("G:/JAVA/background/W.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.start();

	}
}
