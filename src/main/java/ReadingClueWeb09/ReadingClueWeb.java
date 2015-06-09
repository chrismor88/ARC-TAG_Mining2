package ReadingClueWeb09;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;



public class ReadingClueWeb {
	public static void main(String[] args) throws IOException {
		// Set up a local compressed WARC file for reading 
        String inputWarcFile="/home/roberto/Scaricati/00.warc.gz";
            // open our gzip input stream
            GZIPInputStream gzInputStream=new GZIPInputStream(new FileInputStream(inputWarcFile));
            
            // cast to a data input stream
            DataInputStream inStream=new DataInputStream(gzInputStream);
            
            // iterate through our stream
            WarcRecord thisWarcRecord;
            while ((thisWarcRecord=WarcRecord.readNextWarcRecord(inStream))!=null) {
              // see if it's a response record
              if (thisWarcRecord.getHeaderRecordType().equals("response")) {
                // it is - create a WarcHTML record
                WarcHTMLResponseRecord htmlRecord=new WarcHTMLResponseRecord(thisWarcRecord);
                // get our TREC ID and target URI
                String thisTRECID=htmlRecord.getTargetTrecID();
                String thisTargetURI=htmlRecord.getTargetURI();
                // print our data
                System.out.println(thisTRECID + " : " + thisTargetURI);
                
                String HTMLContent = htmlRecord.getRawRecord().getContentUTF8();
				String HTMLContent2="";
				if (HTMLContent.indexOf("<")!=-1){
					HTMLContent2 = HTMLContent.substring(HTMLContent.indexOf("<"));
				}
				System.out.println(HTMLContent2);
                System.out.println("====================================");
              }
            }
            
            inStream.close();
          }
	
}
