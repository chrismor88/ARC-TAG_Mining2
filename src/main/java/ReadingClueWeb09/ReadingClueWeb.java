package ReadingClueWeb09;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import phrases.*;
import regex.TAGComponent;
import writer_text.TAGMiningFileWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import clean.CleanerPhrase;



public class ReadingClueWeb {
	public static void main(String[] args) throws IOException {





		// Set up a local compressed WARC file for reading 
		String inputWarcFile="/Volumes/DATA/workspace/warc_dir/00.warc.gz";
		// open our gzip input stream
		GZIPInputStream gzInputStream=new GZIPInputStream(new FileInputStream(inputWarcFile));

		// cast to a data input stream...
		DataInputStream inStream=new DataInputStream(gzInputStream);

		// iterate through our stream
		WarcRecord thisWarcRecord;
		//PrintWriter pw = new PrintWriter("/home/roberto/Scrivania/bodyURL.txt");
		//creo array di stringhe per memorizzare le frasi
		String[] phrases;
		while ((thisWarcRecord=WarcRecord.readNextWarcRecord(inStream))!=null) {
			// see if it's a response record
			if (thisWarcRecord.getHeaderRecordType().equals("response")) {
				// it is - create a WarcHTML record
				WarcHTMLResponseRecord htmlRecord=new WarcHTMLResponseRecord(thisWarcRecord);
				// get our TREC ID and target URI
				String trecID=htmlRecord.getTargetTrecID();
				String thisTargetURI=htmlRecord.getTargetURI();
				// print our data


				//System.out.println(trecID + " : " + thisTargetURI);





				String HTMLContent = htmlRecord.getRawRecord().getContentUTF8();
				String HTMLContent2="";
				if (HTMLContent.indexOf("<")!=-1){
					HTMLContent2 = HTMLContent.substring(HTMLContent.indexOf("<"));
				}





				//String textBody = getHTMLBody(HTMLContent2);


				//PARTE CHRIS
				String textBody = getHTMLBody(HTMLContent2);




				phrases = SentenceDetector.sentenceDetect(textBody);

				for(String phrase : phrases){
					String cleanedPhrase = CleanerPhrase.deleteWastedHTML(phrase);
					//prende in considerazione frasi di lunghezza compresa tra 4 e 39 parole
					String[] temp = cleanedPhrase.split(" ");	
					if(temp.length>3 && temp.length<40){
						TAGComponent.tagPhrase(trecID, cleanedPhrase);
					}
				}

				/*
				System.out.println(body);
                System.out.println("====================================");
                pw.println(HTMLContent2);
                pw.println("============================");
                pw.println("estrazione testo jsoup");
                pw.println("============================");
                pw.println(body);
                pw.println("============================");
				 */
			}
		}
		//pw.close();
		inStream.close();
		System.out.println("CONCLUSO");

	}
	private static String getHTMLBody(String HTMLContent){
		String aux="";
		try {
		
			
			
			/*
			 * DA UTILIZZARE SOLO SE FOSSE NECESSARIO ESTRATTE I TAG <a href> DAL FILE HTML PER POI TAGGARLI CON #URL
			 * POTREBBE NON FUNZIONARE CON MAP REDUCE!
			 * 
			Elements links = Jsoup.parse(HTMLContent, "UTF-8").getElementsByTag("a");
			for(Element link :links){
				TAGComponent.tagLink(link.toString());
			}
			*/
			
			aux= Jsoup.parse(HTMLContent).body().text();


		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return aux;
	}

}
