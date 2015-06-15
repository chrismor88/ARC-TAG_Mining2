package aux;

import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestJSoup {


	public static void main(String[] args) {

		String aux = "";
		//File fileHTML = new File("/Volumes/DATA/workspace/stadio_roma.html");
		File fileHTML = new File("/Volumes/DATA/workspace/sito_con_table.txt");

		Document doc;
		String stringa = "mammeta <table><tr><td>kebab</td><td>ciao</td></tr></table>e a soreta";
		Element node;
		try {
			node = Jsoup.parse(fileHTML, "UTF-8");
			Elements elements = node.getElementsByTag("p");

			for(Element el:elements){

				String[] parole = el.text().split(" ");

				if(parole.length > 4){
					System.out.println(el.text());
					aux += el.text();
				}
			}

			System.out.println("=========== TAG LI ===========================");

			Elements elements2 = node.getElementsByTag("li");

			for(Element el:elements2){

				String[] parole = el.text().split(" ");

				if(parole.length > 4){
					System.out.println(el.text());
					aux += el.text();
				}
			}






			//			node.select("table").remove();
			//			aux = node.text();
			//			System.out.println(aux);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}






	}

}
