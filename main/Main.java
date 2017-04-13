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
import update.Timer;


public class Main {	
	public static void main(String args[]) {
		Board b=null;
		Timer t=new Timer();
		boolean exit=false;
		PrintStream os=System.out;
		
		Scanner sc=new Scanner(System.in);
		while (!exit) {
			String cmd=sc.nextLine();
			String[] cmdargs=cmd.split(" ");
			switch (cmdargs[0]) {
			case "load-file":
				// b=loadFile(cmdargs[1], t);
				t=new Timer();
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
				// TODO
				break;
			case "step":
				// TODO
				break;
			case "enable-randomness":
				// TODO
				break;
			case "generate-next-train":
				// TODO
				break;
			case "set-driving-force":
				
				break;
			case "build-tunnel":
				// TODO
				break;
			case "destroy-tunnel":
				// TODO
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
