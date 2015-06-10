package regex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestHtmlTagging {

	public static void main(String[] args) throws IOException {
		
		final String REGEX_URL = "<(A|a)\\s*(\\w|\\s|=|\"|:|\\/|\\.|-|\\?)*>(\\.)*<\\/(a|A)>";
		final  String TAG_URL = " #URL ";
		
		FileInputStream text = new FileInputStream("util/html4.txt");
		StringBuilder builder = new StringBuilder();
		int ch;
		while((ch = text.read()) != -1){
		    builder.append((char)ch);
		}
		String result = builder.toString();
		
		
		
		Pattern patternURL = Pattern.compile(REGEX_URL);
		Matcher matcherURL = patternURL.matcher(result);
		String HTMLtaggato = matcherURL.replaceAll(TAG_URL);
		
		System.out.println(result);
		System.out.println("--------------------------------------");
		System.out.println("--------------------------------------");
		System.out.println(HTMLtaggato);
	}

}
