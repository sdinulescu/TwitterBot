//Stejara Dinulescu
//Tokenizes/parses text (scraped from Twitter)

//import java.util.StringTokenizer;
import java.util.*;

public class TokenizeText {
	private String inputString = "";
	private String delimiters;
	private StringTokenizer Tokenizer = new StringTokenizer(inputString, delimiters, true); //Parameters: input string, delimiters, areDelimitersTokens?
	
	private String fSearchText; //do I need this?
	private static final Set<String> fCOMMON_WORDS = new LinkedHashSet<>();
	private static final String fWHITESPACE_AND_QUOTES_PUNCTUATION = " \t\r\n\",.!?;:()?\\";
	private static final String fWHITESPACE = " \t\r\n";
	private static final String fQUOTES_ONLY = "\"";
	private static final String fHASHES = "#@";
	
	public ArrayList<String> parse() { //parses text for tokens
		ArrayList<String> result = new ArrayList<>(); //contains all tokens
		
		boolean returnTokens = true; //returns tokens
		String currentDelims = fWHITESPACE_AND_QUOTES_PUNCTUATION;
		StringTokenizer parser = new StringTokenizer(inputString, currentDelims, true);
		
		fCOMMON_WORDS.add("https");
		fCOMMON_WORDS.add("@");
		fCOMMON_WORDS.add("\"");
		fCOMMON_WORDS.add("/");
		fCOMMON_WORDS.add("//t");
		
		String token = null;
		while (parser.hasMoreTokens()) {
			token = parser.nextToken(currentDelims);
			if (!isWhitespace(token) 
				&& !fQUOTES_ONLY.contains(token) 
				&& !fHASHES.contains(token)
				&& !fWHITESPACE_AND_QUOTES_PUNCTUATION.contains(token) 
				&& !fCOMMON_WORDS.contains(token)
				) {
				
				addNonTrivialWordToResult(token, result);
			}
		}
		return result;
	}

	private void addNonTrivialWordToResult(String token, ArrayList<String> result) {
		result.add(token);
	}

	private boolean isWhitespace(String token) {
		if (token.contains(" ")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	//getters and setters
	public void setString(String inputString) {
		this.inputString = inputString;
	}
	
	public String getString() {
		return inputString;
	}
	
}
