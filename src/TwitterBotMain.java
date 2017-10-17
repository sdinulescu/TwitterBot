//Stejara Dinulescu
//Main class to run operation


import java.io.*; //read and write functionality

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TwitterBotMain {
	private static TwitterInteraction tweet;
	private static ArrayList<String> tokens;
	private static String generatedText = "";
	private static char[] charArray = new char[140];

	public static void main(String[] args) throws InterruptedException {	
		int index = 0; //infinite while loop
		while (index != 1) { //Tweets on schedule (every 5 minutes if left to run)
			System.out.println("1st Enter");
			runProgram();
			TimeUnit.MINUTES.sleep(5);
			System.out.println("Enters 2nd While Loop");
		}
		
	}
	
	public static void runProgram() {
		for (int i = 0; i < 10; i++) {
			tweet = new TwitterInteraction();
			TokenizeText text = new TokenizeText();
			
			String t = (String) tweet.searchForTweets("yesterday").get(0);
//			System.out.println("t: " + t);
			
			text.setString(t);
			
			/*
			 * SO I KNOW I SHOULD BE TRAINING THE LINES SEPARATELY AND USING CODE LIKE THIS BUT FOR SOME REASON EVERYTHING IS NULL.
			ArrayList<String> motivesToAdd = text.parse();
			try {
				tokens.addAll(motivesToAdd);
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Error reading file");
			}
			*/
			
			tokens = text.parse();
//			System.out.println(tokens);
			
			
			//MARKOV CHAIN
			generateWithMarkovChain();
		}
		
		removeAt(); 		//remove @ chars
//		System.out.println(generatedText);
		
		changeToCharArray(); //converts to char array to check character length termination
//		System.out.println(charArray);
		String generate = "";
		
		for (int i = 0; i < charArray.length; i++) { //convert back to a string
			generate = generate + charArray[i];
		}
		System.out.println(generate);
		
		//Post to Twitter
		//infinite while loop to post on schedule

		PostToTwitter twitter = new PostToTwitter();
		twitter.updateTwitter(generate);
	}
		
	public static void generateWithMarkovChain() { //generate tweets with Markov chain order 1
		MarkovGenerator<String> gen = new MarkovGenerator<String>();
		gen.markovCalcs(tokens);
		String seed = gen.findSeed();
		//System.out.println("Seed: " + seed); 
		gen.generateMarkov(seed);
		if (!generatedText.contains(gen.generatedString)) {
			generatedText = generatedText +  gen.generatedString + ". \n";
		} else {}
		
	}
	
	
	static void removeAt() { //removes @ symbols
		for (int i = 0; i < generatedText.length(); i++) {
			if (generatedText.contains("@")) {
				//System.out.println("ELIMINATE @");
				generatedText=generatedText.replaceAll("@", "");
			}
		}

	}
	
	static void changeToCharArray() { //converts to char array for character limit
		char arr[] = generatedText.toCharArray();
		
		if (charArray.length > arr.length) {
			for (int i = 0; i < arr.length; i++) {
				charArray[i] = arr[i];
			}
			for (int i = arr.length; i < charArray.length; i++) {
				charArray[i] = ' ';
			}
		} else {
			for (int i = 0; i < charArray.length; i++) {
				charArray[i] = arr[i];
			}
		}
	}
	
//	static void loadText(ArrayList<String> t) {
////		tokens = new ArrayList<String>();
//		
//		try { //tries this
//			for (int i = 0; i < t.size(); i++) {
////				TokenizeText tokenize = new TokenizeText();
////				ArrayList<String> tok = tokenize.parse();
//				tokens.addAll(t);
//			}
//			
//		} catch (Exception e) { //catches the exception
//			e.printStackTrace();
//			System.out.println("Error reading file");
//		}
//	}
//
//	private String getPath(String p) {
//		// TODO Auto-generated method stub
//		return p;
//	}
}
