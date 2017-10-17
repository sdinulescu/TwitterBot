//Stejara Dinulescu
//Markov Chain class -- order 1


import java.util.ArrayList;
import java.util.Set;

public class MarkovGenerator<E> {
	ArrayList<E> alphabet = new ArrayList<E>();
	ArrayList<Integer> instances = new ArrayList<Integer>();
	String generatedString = "";

	double prob = 0.0;
	int size = 0;
	
	float mainProbs[][] = new float[100][100];
		
	MarkovGenerator() {}
	
	void markovCalcs(ArrayList<E> set) {
		//create an alphabet with all possible notes
		for (int i = 0; i < set.size(); i++) {
			if ( alphabet.contains( set.get(i) ) ) {
				//System.out.println("Element contained in alphabet");
				instances.set(alphabet.indexOf( set.get(i) ), (instances.get( alphabet.indexOf( set.get(i) ) ) + 1));
			} else {
				//System.out.println("Element added to alphabet");
				alphabet.add( set.get(i) );
				instances.add(1);
			}
		}
		
//		System.out.println("alphabet: " + alphabet);
//		System.out.println("instances: " + instances);
		
//Create transition table
		int[][] transitionTable = new int[alphabet.size()][alphabet.size()];
		int number = 0; 
		for (int i = 0; i < alphabet.size(); i++) {
			for (int j = 0;  j < alphabet.size(); j++) {
				for (int a = 0; a < set.size() - 1; a++) {
					if (((ArrayList<String>) set).get(a) == alphabet.get(i) && ((ArrayList<String>) set).get(a+1) == alphabet.get(j)) {
						number++;
					} else {
					}
				}
				transitionTable[i][j] = number;
				number = 0;
			}
		}
//		for (int i = 0; i < alphabet.size(); i++) {
//			for (int j = 0;  j < alphabet.size(); j++) {
//				System.out.println("transition table:  " + transitionTable[i][j]);
//			}
//		}
	
		float[][] probabilities = new float[alphabet.size()][alphabet.size()];
		
		//calculate probabilities
		int lineTotal = 0; 
		for (int i = 0; i < alphabet.size(); i++) {
			for (int j = 0; j < alphabet.size(); j++) {
				lineTotal = lineTotal + transitionTable[i][j];
				//System.out.println("i = " + i + " j = " + j + " total = " + lineTotal);
			}
			for (int j = 0; j < alphabet.size(); j++) {
				if (lineTotal == 0) {
				} else {
					probabilities[i][j] = ((float)transitionTable[i][j] / lineTotal);
//					System.out.println("i = " + i + " j = " + j + " probabilities" + probabilities[i][j]);
				}
			}
			lineTotal = 0;
		}
		
		for (int i = 0; i < alphabet.size(); i++) {
			for (int j = 0; j < alphabet.size(); j++) {
				mainProbs[i][j] = probabilities[i][j];
			}
		}
		
//		for (int i = 0; i < alphabet.size(); i++) {
//			for (int j = 0; j < alphabet.size(); j++) {
//				System.out.println("mainProbs: " + mainProbs[i][j]);
//			}
//		}
	}
	
	String findSeed() {
		String mostCommonString = "";
		int commCount = 0;
		for (int i = 0; i < instances.size()-1; i++) {
			if (instances.get(i) < instances.get(i+1)) {
				commCount = instances.get(i + 1);
			}
		}
//		System.out.println(commCount);
		if (commCount == 0) { //if everything has the same instance, start from the first word
			mostCommonString  = (String) alphabet.get(0);
		} else { //else, start from the word that is the most probable to occur
			mostCommonString = (String) alphabet.get(instances.indexOf(commCount));
		}
		return mostCommonString;
	}

	E generateMarkov(String seed) {
		prob = 0;
		double numMarkov = Math.random();
		E charToAdd=null;
		if (alphabet.contains(seed)) {
//			System.out.println("Contains seed yay");
			for (int j = 0; j < alphabet.size(); j++) {
//				System.out.println("Prob: " + prob + " mainProbs: " + mainProbs);
				if (prob < numMarkov && numMarkov <= (prob + mainProbs[alphabet.indexOf(seed)][j])) {
//					System.out.println("Add to generatedMarkov");
					charToAdd = alphabet.get(j);
					generatedString = generatedString + " " + charToAdd;
				} else {
//					System.out.println("nah");
				}
				prob = prob + mainProbs[alphabet.indexOf(seed)][j];
			}
		}
		
		if (charToAdd != null) {
			generateMarkov((String) charToAdd);
		}
		return charToAdd;
	}
	
}
