package gameEOTIH;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

public class AudioPlayer implements LineListener {
	
	private AudioInputStream inputStream;
	private AudioFormat format;
	private Clip clip;
	private boolean isComplete;
	
	private DataLine.Info setInfo(File file) {
		try {
			inputStream = AudioSystem.getAudioInputStream(file);
			format= inputStream.getFormat();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Set info erro:"+e.getMessage());
		}
		return new DataLine.Info(Clip.class, format);
		
	}
	
	public void setFile(String fileLocation) {
		File file = new File(fileLocation);
		try {
			DataLine.Info info= setInfo(file);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(inputStream);
			clip.addLineListener(this);
		} 
		catch(IOException | LineUnavailableException e)
		{e.printStackTrace();}
	}
	
	public void play() {
		try {
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			do {
				Thread.sleep(25);
			}while(!isComplete);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			clip.close();
		}
	}
	
	

	@Override
	public void update(LineEvent event) {
		// TODO Auto-generated method stub
		LineEvent.Type type = event.getType();
		//audio file is no longer playing 
		if(type == LineEvent.Type.STOP) {
			isComplete = true;
		}
	}
}


class AudioPlayerThread implements Runnable{
	String fileLocation;
	AudioPlayer player;
	long delay;
	
	AudioPlayerThread(String location) {
		// TODO Auto-generated constructor stub
		this.fileLocation= location;
	}
	AudioPlayerThread(String location, long d) {
		// TODO Auto-generated constructor stub
		this.fileLocation=location;
		this.delay= d;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(delay);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		player= new AudioPlayer();
		player.setFile(fileLocation);
		player.play();
	}
	
}