package writer_text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Formatter;

public class TAGMiningFileWriter {
	
	private final static String PATH = "/Volumes/DATA/workspace/TAGMining_output/";
	private final static String PATH1 = PATH+"output1.txt";
	private final static String PATH2 = PATH+"output2.txt";
	private final static String PATH3 = PATH+"output3.txt";
	
	private final static int MAX_LENGHT_FOR_STRING = 50;

	public static void writeOutput1(String warcTrecID,String stringaDaRimpiazzare, String tag){
		//System.out.println("TAG: "+tag);
		int diff = MAX_LENGHT_FOR_STRING - stringaDaRimpiazzare.length();
		String whiteSpaceValueColumn2 = "";
		
		String whiteSpaceHeadingColumn1 = "                     ";
		String whiteSpaceHeadingColumn2 = "                   ";
		
		
		
		for(int i=0;i<diff;i++){
			whiteSpaceValueColumn2 = whiteSpaceValueColumn2 + " ";
		}
		
		
		try {
			File file = new File(PATH1);

			if (file.exists()){
				try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
					String line = null;
					while ((line = reader.readLine()) != null) {
						////System.out.println(line);
					}
				} catch (IOException x) {
					System.err.format("IOException: %s%n", x);
				}

				PrintWriter pw = new PrintWriter(new FileWriter(file, true));
				Formatter formatter = new Formatter();
				formatter.format("%s\t\t\t%s%s%s\n", warcTrecID,stringaDaRimpiazzare,whiteSpaceValueColumn2,tag);
				pw.write(formatter.toString());
				pw.flush();
				pw.close();
			}

			else{
				
					PrintWriter pw = new PrintWriter(file);
					Formatter formatterHeading = new Formatter();
					formatterHeading.format("%s\t\t\t\t\t\t%s\t\t\t\t   %s\n\n","TREC-ID","STRINGA DA RIMPIAZZARE","TAG");
					pw.write(formatterHeading.toString());
					
					Formatter formatterRecord = new Formatter();
					formatterRecord.format("%s\t\t\t%s%s%s\n", warcTrecID,stringaDaRimpiazzare,whiteSpaceValueColumn2,tag);
					pw.write(formatterRecord.toString());
					//pw.write("TRACK-ID\t\tSTRINGA DA SOSTITUIRE\t\t\tTAG\n");
					//pw.write(warcTrecID+"\t\t"+stringaDaRimpiazzare+"\t\t\t"+tag+"\n");
					pw.flush();
					pw.close();
					
					
				
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}




	}

	public static void writeOutput2(String warcTrecID, String phrase) {
	

		try {
			File file = new File(PATH2);

			if (file.exists()){
				try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
					String line = null;
					while ((line = reader.readLine()) != null) {
						////System.out.println(line);
					}
				} catch (IOException x) {
					System.err.format("IOException: %s%n", x);
				}

				PrintWriter pw = new PrintWriter(new FileWriter(file, true));
				pw.write(warcTrecID+"\t\t"+phrase+"\n");
				pw.flush();
				pw.close();
			}

			else{
				
					PrintWriter pw = new PrintWriter(file);
					pw.write("TRACK-ID\t\tSTRINGA DA SOSTITUIRE\n");
					pw.write(warcTrecID+"\t\t"+phrase+"\n");
					pw.flush();
					pw.close();
				
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void writeOutput3(String warcTrecID, String phraseChanged) {
		

		try {
			File file = new File(PATH3);

			if (file.exists()){
				try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
					String line = null;
					while ((line = reader.readLine()) != null) {
						////System.out.println(line);
					}
				} catch (IOException x) {
					System.err.format("IOException: %s%n", x);
				}

				PrintWriter pw = new PrintWriter(new FileWriter(file, true));
				pw.write(warcTrecID+"\t\t"+phraseChanged+"\n");
				pw.flush();
				pw.close();
			}

			else{
				
					PrintWriter pw = new PrintWriter(file);
					pw.write("TRACK-ID\t\tSTRINGA DA SOSTITUIRE\n");
					pw.write(warcTrecID+"\t\t"+phraseChanged+"\n");
					pw.flush();
					pw.close();
				
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {

		String s = "\t";
		char c = ' ';
		int count = 0;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i) == c)
				count++;
			
		}
		//System.out.println(count);
		
	}

}
