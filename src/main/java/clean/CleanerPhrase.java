package clean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanerPhrase {


	private final static String REGEX_CLEAN_TEXT = "<[a-z]+|(\\/)+\\w*(\")?>|\\s@\\s|\\^[a-zA-Z]+|<<|>>|\\w+=\"(\\w)*(:)?(\\/\\/)?(\\w+|\\.|\\/|-|=|&)*(\")?>?|&amp;|\\w+=\\w+\"|\">|\\.\\w{2,6}\"|~";



	public CleanerPhrase() {

	}

	public static String deleteWastedHTML(String text){
		Pattern patternPHONE_NUMBER = Pattern.compile(REGEX_CLEAN_TEXT);
		Matcher matcherPHONE_NUMBER = patternPHONE_NUMBER.matcher(text);		
		String cleanedText = matcherPHONE_NUMBER.replaceAll("");

		return cleanedText;	
	}



}



