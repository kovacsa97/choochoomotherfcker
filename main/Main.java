package main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import boardelements.*;
import color.Color;
import update.Timer;


public class Main {
	
	public static void main(String args[]) throws Exception {		
		
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("vonat.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();

	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
		
		Timer t = new Timer();
		
		EntryPoint ep = BoardCreater.createBoard(t);
		t.registerElement(ep);
		t.start();
	}
}
