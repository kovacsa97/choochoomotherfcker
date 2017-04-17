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
import parser.ChooChooParser;
import update.TestingTimer;
import update.Timer;


public class Main {
	
	/**
	 * Main függvény, implementálja a menüt
	 * @param args bemeneti paraméterek, nincs használatban
	 */
	public static void main(String args[]) {
		Board b=null;
		TestingTimer t=new TestingTimer();
		boolean exit=false;
		PrintStream os=System.out;
		
		Scanner sc=new Scanner(System.in);
		while (!exit&&sc.hasNext()) {
			String cmd=sc.nextLine();
			String[] cmdargs=cmd.split(" ");
			switch (cmdargs[0]) {
			case "load-file":
				b=new ChooChooParser().parse(cmdargs[1]);
				t=new TestingTimer();
				if (b!=null)
					b.setAllTimers(t);
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
				if (b!=null)
					if (cmdargs[1].equals("train"))
						t.listTrains(os);
					else b.list(os, cmdargs[1]);
				break;
			case "step":
				if (b!=null)
				try {
					t.step(Integer.parseInt(cmdargs[1]));
				} catch (EndGameException e) {
					os.println("crash!!!");
				}
				break;
			case "enable-randomness":
				if (b!=null)
				b.setRandomness(Boolean.getBoolean(cmdargs[1]));
				break;
			case "generate-next-train":
				if (b!=null)
				try {
					b.getNextTrain();
				} catch (EndGameException e) {
					os.println("crash!!! "+e.tid);
				}
				break;
			case "set-driving-force":
				t.setDrivingForce(cmdargs[1], Integer.parseInt(cmdargs[2]));
				break;
			case "build-tunnel":
				if (b!=null)
				b.buildTunnel(cmdargs[1], cmdargs[2]);
				break;
			case "destroy-tunnel":
				if (b!=null)
				b.destroyTunnel();
				break;
			case "set-passenger-get-on":
				if (b!=null) 
					b.setPassengerGetOn(Boolean.getBoolean(cmdargs[1]));
				break;
			case "switch":
				if (b!=null)
					b.changeSwitch(cmdargs[1]);
				break;
			case "quit":
				exit=true;
				break;
			default:
				os.println("invalid command");	
			}
			
		}	
	}
}
