package ReadingClueWeb09;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import phrases.*;
import regex.TAGComponent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import clean.CleanerPhrase;



public class ReadingClueWeb {
	public static void main(String[] args) throws IOException {
		long startInMillis = System.currentTimeMillis();
		System.out.println("AVVIO ELABORAZIONE ED ESTRAZIONE FRASI DA WARC CLUE WEB");


		// Set up a local compressed WARC file for reading 
		String inputWarcFile="util/00.warc.gz";
		
		
		// open our gzip input stream
		GZIPInputStream gzInputStream=new GZIPInputStream(new FileInputStream(inputWarcFile));

		// cast to a data input stream...
		DataInputStream inStream=new DataInputStream(gzInputStream);

		// iterate through our stream
		WarcRecord thisWarcRecord;
		
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
				

				String HTMLContent = htmlRecord.getRawRecord().getContentUTF8();
				String HTMLContent2="";
				if (HTMLContent.indexOf("<")!=-1){
					HTMLContent2 = HTMLContent.substring(HTMLContent.indexOf("<"));
				}

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

			}
		}
	
		inStream.close();
		System.out.println("CONCLUSO");
		long endTimeInMillis = System.currentTimeMillis();
		double totalTime = (endTimeInMillis - startInMillis) / 1000 ;
		double totalTime2 = (endTimeInMillis - startInMillis) ;
		System.out.println("TEMPO IMPIEGATO: "+totalTime+" sec");

	}


	private static String getHTMLBody(String HTMLContent){
		String aux="";
		try {

			Element bodyHTML = Jsoup.parse(HTMLContent, "UTF-8").body();
			Elements pTags = bodyHTML.getElementsByTag("p");

			for(Element p:pTags){
				String[] parole = p.text().split(" ");
				if(parole.length > 4){		
					aux = aux+" "+p.text();
				}
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return aux;
	}

}
