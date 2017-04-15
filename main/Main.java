package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import board.Board;
import boardelements.*;
import color.Color;
import update.TestingTimer;
import update.Timer;


public class Main {	
	public static void main(String args[]) {
		Board b=null;
		TestingTimer t=new TestingTimer();
		boolean exit=false;
		PrintStream os=System.out;
		
		Scanner sc=new Scanner(System.in);
		while (!exit) {
			String cmd=sc.nextLine();
			String[] cmdargs=cmd.split(" ");
			switch (cmdargs[0]) {
			case "load-file":
				// b=loadFile(cmdargs[1], t);
				t=new TestingTimer();
				break;
			case "set-output":
				if (cmdargs[1].equals("cls")) os=System.out;
				else
					try {
						// Scary!
						os=new PrintStream(new FileOutputStream(cmdargs[1]));
					} catch (FileNotFoundException e) {
						os.println("File not found!");
					}
				break;
			case "list":
				b.list(os, cmdargs[1]);
				break;
			case "step":
				t.step(Integer.parseInt(cmdargs[1]));
				break;
			case "enable-randomness":
				b.setRandomness(Boolean.getBoolean(cmdargs[1]));
				break;
			case "generate-next-train":
				try {
					b.getNextTrain();
				} catch (EndGameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "set-driving-force":
				// TODO
				break;
			case "build-tunnel":
				b.buildTunnel(cmdargs[1], cmdargs[2]);
				break;
			case "destroy-tunnel":
				b.destroyTunnel();
				break;
			case "set-passenger-get-on":
				// TODO
				break;
			case "quit":
				exit=true;
				break;
			}
			
		}	
	}
}
