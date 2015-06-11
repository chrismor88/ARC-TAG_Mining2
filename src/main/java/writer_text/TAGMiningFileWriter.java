package writer_text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class TAGMiningFileWriter {

	public static void writeOutput1(String trackID,String stringaDaRimpiazzare, String tag) throws Exception{
		String path = "/Volumes/DATA/workspace/TAGMining_output/output1.txt";

		try {
			File file = new File(path);

			if (file.exists()){
				try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				    String line = null;
				    while ((line = reader.readLine()) != null) {
				        System.out.println(line);
				    }
				} catch (IOException x) {
				    System.err.format("IOException: %s%n", x);
				}
				
				PrintWriter pw = new PrintWriter(new FileWriter(file, true));
				pw.write("BOMBAMI\n");
				pw.flush();
				pw.close();
			}

			else{
				if (file.createNewFile()){
					PrintWriter pw = new PrintWriter(file);
					pw.write("TRACK-ID\t\tSTRINGA DA SOSTITUIRE\t\tTAG\n");
					pw.flush();
					pw.close();
				}else{

				}
				//throw new Exception("Impossibile creare il file output1!");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}




	}
	
	public static void main(String[] args) {
		try {
			TAGMiningFileWriter.writeOutput1("", "", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}








}
