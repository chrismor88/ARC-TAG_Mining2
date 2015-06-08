
package parseBeer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParseBeer {
	public static void main(String[] args) throws IOException {
		FileReader f;
	    //f=new FileReader("/media/roberto/ROB/Università (Roma Tre)/Big Data/ratebeer.txt");
		f=new FileReader("/home/roberto/Scaricati/00.warc");

	    BufferedReader b;
	    b=new BufferedReader(f);

	    String s;

	    while(true) {
	      s=b.readLine();
	      if(s==null)
	        break;
	      System.out.println(s);
	    }
	}
}