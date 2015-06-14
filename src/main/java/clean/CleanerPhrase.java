package clean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanerPhrase {

	
private final static String REGEX_CLEAN_TEXT = "<[a-z]+|(\\/)+\\w*(\")?>|\\s@\\s|\\^[a-zA-Z]+|<<|>>|\\w+=\"(\\w)*(:)?(\\/\\/)?(\\w+|\\.|\\/|-|=|&)*(\")?>?|&amp;|\\w+=\\w+\"|\">|\\.\\w{2,6}\"|~";
	
	
	
	public CleanerPhrase() {
		// TODO Auto-generated constructor stub
	}

	public static String deleteWastedHTML(String text){
		Pattern patternPHONE_NUMBER = Pattern.compile(REGEX_CLEAN_TEXT);
		Matcher matcherPHONE_NUMBER = patternPHONE_NUMBER.matcher(text);		
		String cleanedText = matcherPHONE_NUMBER.replaceAll("");
		
		return cleanedText;	
	}
	
	public static void main(String[] args) {
		
		File file = new File("/Users/Christian/Desktop/testo da pulire.txt");
	
			String line = null;
			String cleanedText = "";
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
					 cleanedText = cleanedText +"\n"+deleteWastedHTML(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("TESTO PULITO\n\n");
			System.out.println(cleanedText);
	}
	
	
	}
	
	

