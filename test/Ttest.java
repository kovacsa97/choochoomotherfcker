package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Tesztelest megvalosito osztalyt.
 */
public class Ttest {

	public static void main(String[] args) {
		
		//scanners for reading input and expected input
		Scanner actualInputScanner = null;
		Scanner expectedInputScanner = null;
		
		//starting word of last read line
		String previousStartWord = null;
		
		//array list for a block of same type elements 
		ArrayList<String> actualInputBlock = new ArrayList<>();
		ArrayList<String> expectedInputBlock = new ArrayList<>();
		
		try {
			
			//actual input from stdin
			actualInputScanner = new Scanner(System.in);
			//expected input from file
			expectedInputScanner = new Scanner(new File(args[0]));
			
			//reading actual input to EOF
			while(actualInputScanner.hasNextLine()){
				if(!expectedInputScanner.hasNextLine()){
					System.out.println("input longer than expected");
					return;
				}
				
				//tokenizing current line
				String[] actualLineTokens = actualInputScanner.nextLine().trim().split(" ");
				String[] expectedLineTokens = expectedInputScanner.nextLine().trim().split(" ");
				
				//checking line starts
				if(!actualLineTokens[0].equals(expectedLineTokens[0])){
					System.out.println("input does not match expected");
					return;
				}
				
				String currentStartWord = actualLineTokens[0];
				
				//preprocessing line
				switch (currentStartWord) {
				case "switch":		//validating number of attributes
					if(actualLineTokens.length != 5){
						System.out.println("input illformed");
						return;
					}
					if(expectedLineTokens.length != 5){
						System.out.println("input file illformed");
						return;
					}
					break;
					
				case "station":
					if(actualLineTokens.length != 6){
						System.out.println("input illformed");
						return;
					}
					if(expectedLineTokens.length != 6){
						System.out.println("input file illformed");
						return;
					}
					//intended fallthrough
				case "tunnelopp":	
					//intended fallthrough
				case "rail":
					//to prevent index out of bound
					if(actualLineTokens.length < 4){
						System.out.println("input illformed");
						return;
					}
					if(expectedLineTokens.length < 4){
						System.out.println("input file illformed");
						return;
					}
					
					//ordering lexicographically inId and outId
					if(actualLineTokens[2].compareTo(actualLineTokens[3]) > 0){
						String tmp = actualLineTokens[2];
						actualLineTokens[2] = actualLineTokens[3];
						actualLineTokens[3] = tmp;
					}
					if(expectedLineTokens[2].compareTo(expectedLineTokens[3]) > 0){
						String tmp = expectedLineTokens[2];
						expectedLineTokens[2] = expectedLineTokens[3];
						expectedLineTokens[3] = tmp;
					}
					break;
				
				case "train":
					if(actualLineTokens.length != 5){
						System.out.println("input illformed");
						return;
					}
					if(expectedLineTokens.length != 5){
						System.out.println("input file illformed");
						return;
					}
					break;
					
				case "takeoff":
					if(actualLineTokens.length != 3){
						System.out.println("input illformed");
						return;
					}
					if(expectedLineTokens.length != 3){
						System.out.println("input file illformed");
						return;
					}
					break;
					
				case "crash!!!":	//removing trainId from end of line
					actualLineTokens = new String[]{actualLineTokens[0]};
					expectedLineTokens = new String[]{expectedLineTokens[0]};
					break;
					
				default:
					System.out.println("input line not recognized");
					return;
				}
				
				//creating string builders to build comparable strings
				StringBuilder actualInputLineBuilder = new StringBuilder();
				StringBuilder expectedInputLineBuilder = new StringBuilder();

				//puting line tokens in string builders
				actualInputLineBuilder.append(actualLineTokens[0]);
				for(int i = 1; i < actualLineTokens.length; i++){
					actualInputLineBuilder.append(" " + actualLineTokens[i]);
				}
				expectedInputLineBuilder.append(expectedLineTokens[0]);
				for(int i = 1; i < expectedLineTokens.length; i++){
					expectedInputLineBuilder.append(" " + expectedLineTokens[i]);
				}
				
				//if train was read reading the rest of the train
				if (currentStartWord.equals("train")) {
					for (int i = 0; i < Integer.parseInt(actualLineTokens[4]); i++) {
						if (!actualInputScanner.hasNext()) {
							System.out.println("input illformed");
							return;
						}
						actualInputLineBuilder.append(" " + actualInputScanner.nextLine().trim());
					}
					for (int i = 0; i < Integer.parseInt(expectedLineTokens[4]); i++) {
						if (!expectedInputScanner.hasNext()) {
							System.out.println("input illformed");
							return;
						}
						expectedInputLineBuilder.append(" " + expectedInputScanner.nextLine().trim());
					} 
				}
				
				//stringifying lines
				String actualInputLine = actualInputLineBuilder.toString();
				String expectedInputLine = expectedInputLineBuilder.toString();
				
				//deciding if at end of block
				if(previousStartWord != null && !previousStartWord.equals(currentStartWord)){
					if(!actualInputBlock.isEmpty()){
						//sorting lists
						Collections.sort(actualInputBlock);
						Collections.sort(expectedInputBlock);
						//checking match line by line
						for(int i = 0; i < actualInputBlock.size(); i++){
							if(!actualInputBlock.get(i).equals(expectedInputBlock.get(i))){
								System.out.println("input does not match expected");
								return;
							}
						}
					}
					actualInputBlock.clear();
					expectedInputBlock.clear();
				}
				
				//puting read lines in block
				actualInputBlock.add(actualInputLine);
				expectedInputBlock.add(expectedInputLine);
				//update last start word
				previousStartWord = currentStartWord;
				
			}
			
			if(expectedInputScanner.hasNextLine()){
				System.out.println("input shorter than expected");
				return;
			}
			
			if(!actualInputBlock.isEmpty()){
				//sorting lists
				Collections.sort(actualInputBlock);
				Collections.sort(expectedInputBlock);
				//checking match line by line
				for(int i = 0; i < actualInputBlock.size(); i++){
					if(!actualInputBlock.get(i).equals(expectedInputBlock.get(i))){
						System.out.println("input does not match expected");
						return;
					}
				}
			}
			
			System.out.println("input matches expected");
			
		} catch (FileNotFoundException e) {
			System.out.println("could not open " + args[0]);
			e.printStackTrace();
		}
		finally {
			if(expectedInputScanner != null)
				expectedInputScanner.close();
			if(actualInputScanner != null)
				actualInputScanner.close();
		}
	}

}
