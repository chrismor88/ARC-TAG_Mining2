package phrases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.InvalidFormatException;

public class SentenceDetector {
	public static void SentenceDetect() throws InvalidFormatException,IOException {
		String paragraph = "The Apache OpenNLP library is a machine learning based toolkit for processing of natural language text. It includes a sentence detector, a tokenizer, a name finder, a parts-of-speech (POS) tagger, a chunker, and a parser. It has very good APIs that can be easily integrated with a Java program. However, the documentation contains unupdated information.In this tutorial, I will show you how to use Apache OpenNLP through a set of simple examples. ";
		InputStream is = new FileInputStream("util/en-sent.bin");
		FileInputStream text = new FileInputStream("util/testoProva");
		StringBuilder builder = new StringBuilder();
		int ch;
		while((ch = text.read()) != -1){
		    builder.append((char)ch);
		}
		String result = builder.toString();
		SentenceModel model = new SentenceModel(is);
		SentenceDetectorME sdetector = new SentenceDetectorME(model);
		String sentences[] = sdetector.sentDetect(result);
		for(String s : sentences){
			System.out.println(s);
		}
		is.close();
	}
}
