package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex_TAGMining{

	public static void main(String[] args) {



		/* REGEX */

		final String REGEX_ORD = "((\\d+(st|nd|rd|th)) | (\\d+°))";
		final String REGEX_NUM = "\\d+";
		

		//for format time hh:mm:ss, hh:mm, hh:mm am, hh:mm:ss pm ...........
		final String time1 ="(([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])(:[0-5][0-9])?(\\s*(am|AM|pm|PM))?)";

		//for format time xx h yy m  ,  xx h yy min  , xx h  yy m zz s ...........
		final String time2 = "(\\d{1,2}\\s*h\\s+\\d{1,2}\\s*(m|min)\\s+(\\d{1,2}\\s*(s|sec))?)";


		final String REGEX_TIME = time1+"|"+time2;


		
		final String LENGTH_MEASURE = "((\\s*(k|c|d|m)?m(s?))|(\\s*(kilo|deci|centi|milli)?meter)|\\s*(mi|ft|yd|mile(s?)|in(ches)?|NM|nm))";
		final String AREA_MEASURE = "((\\s*(k|c|d|m)?m\\^2)|\\s*(mi\\^2|ha|a|ca)|\\s+((in|yd|ft|NM|nm)\\^2)|\\s+(ro|acro))";
		final String SPEED_MEASURE = "(\\s*((m/s)|(km/h)(ft/s)|mph|knot))";
		
		final String REGEX_DISTANCE = "(\\d+(\\.|,)?(\\d+)?\\s*"+LENGTH_MEASURE+")";
		final String REGEX_AREA = "(\\d+(\\.|,)?(\\d+)?\\s*"+AREA_MEASURE+")";
		final String REGEX_SPEED = "(\\d+(\\.|,)?(\\d+)?\\s*"+SPEED_MEASURE+")";
		
		



		final String MONTH = "(January|january|jan(\\.)|Jan(\\.)?|JAN(\\.)?|February|Feb(\\.)?|FEB(\\.)?|March|Mar(\\.)?|MAR(\\.)?|April|Apr(\\.)?|APR(\\.)?|"+
				"May|MAY|June|JUNE|July|JULY|August|Aug(\\.)?|AUG(\\.)?|"+
				"September|Sept(\\.)?|SEPT(\\.)?|October|Oct(\\.)?|OCT(\\.)?|November|Nov(\\.)?|NOV(\\.)?|December|Dec(\\.)?|DEC(\\.)?)";

		//for format dd/mm/yyyy, dd/mm/yy, dd.mm.yyyy, dd.mm.yy, dd-mm-yyyy, dd-mm-yy, dd mm yyyy, dd mm yy
		final String date1 = "(([0]?[1-9]|[1|2][0-9]|[3][0|1])(\\.|/|-|\\s+)([0]?[1-9]|[1][0-2])(\\.|/|-|\\s+)([0-9]{4}|[0-9]{2}))";


		//for format mm/dd/yyyy, mm-dd-yyyy, mm dd yyyy, mm.dd.yyyy
		final String date2 = "(([0]?[1-9]|[1][0-2])(\\.|/|-|\\s+)([0]?[1-9]|[1|2][0-9]|[3][0|1])(\\.|/|-|\\s+)([0-9]{4}))";

		//for format dd Month yyyy, dd Month yy,
		final String date3 = "([0]?[1-9]|[1|2][0-9]|[3][0|1])\\s+"+MONTH+"\\s+([0-9]{4}|[0-9]{2})";

		//for format Month dd,    Month dd, yyyy, Month ddth, yyyy.........
		final String date4 = "("+MONTH+"\\s+\\d+"+"(st|nd|rd|th)?(,\\s+([1|2]\\d{3}))?)";

		//for format ddth Month   ddth Month yyyy
		final String date5 = "("+REGEX_ORD+"\\s+"+MONTH+"(\\s+\\d{4})?)";


		//for format yyyy-mm-dd, yyyy/mm/dd, yyyy mm dd
		final String date6 = "([1|2]\\d{3}(\\.|-|/|\\s+)([0]?[1-9]|[1][0-2])(\\.|-|/|\\s+)(\\d{1,2}))";



		final String REGEX_DATE = date1+"|"+date2+"|"+date3+"|"+date4+"|"+date5+"|"+date6;












		/* TAG STRING*/
		final String TAG_DATE = " #DATE ";
		final String TAG_TIME = " #TIME ";
		final String TAG_ORD = " #ORD ";
		final String TAG_NUM = " #NUM ";
		final String TAG_DISTANCE = " #DISTANCE ";
		final String TAG_AREA = " #AREA ";
		final String TAG_SPEED = " #SPEED ";





		String text1 = " 12/03/1988  , 24.08.12, 01/02/2010, 1/1/11, 24-04-12 ";


		String text2 = "Dante Alighieri, or simply Dante ( May 14/ June 13, 1265 – September 14, 1321), was an Italian poet from Florence. His central work, the Commedia (Divine Comedy), is considered the greatest literary work composed in the Italian language and a masterpiece of world literature. In Italian he is known as \"the Supreme Poet\" (il Sommo Poeta). Dante and the Divine Comedy have been a source of inspiration for artists for almost seven centuries. Dante, with Petrarch and Boccaccio are also known as \"the three fountains\". Dante is also called \"the Father of the Italian language\". The first biography written on him was by his contemporary Giovanni Villani. The most famous section in La Divina Commedia is the first third of it, the first 34 cantos of the poem, called Inferno, which is Dante's vision of hell.";
		String text3 ="I was born in 12th March 1988";

		String text4 ="1990-12-01   2000/03/11   2000 04 07";
		String text5 = "11/12/2015, 11/12/15, 11.12.2015, 11.12.15, 12-03-1988, 12-03-88, 12 02 2000, 12 02 00";
		String text6 = "12 Mar 1988      12 March 1988     24 Dec. 2015";
		String text7 = "12th March       12th Mar. 1988";
		String text8 = "1:02:34      1h 30min   1h 21m   1h 21m 38s  1 h 21 m 38 s 1:05 am   7:45 pm ";
		String text9 = " 1st    2nd  100th      1°   12°   ";
		String text10 = "1km   1,30232 m  321.039120 ms";
		
		
		

		Pattern patternTime = Pattern.compile(REGEX_TIME);
		Matcher matcherTime = patternTime.matcher(text8);
		String result1 = matcherTime.replaceAll(TAG_TIME);

		Pattern patternDate = Pattern.compile(REGEX_DATE);
		Matcher matcherDate = patternDate.matcher(text1);
		String result2 = matcherDate.replaceAll(TAG_DATE);

		patternDate = Pattern.compile(REGEX_DATE);
		matcherDate = patternDate.matcher(text2);
		String result3 = matcherDate.replaceAll(TAG_DATE);

		patternDate = Pattern.compile(REGEX_DATE);
		matcherDate = patternDate.matcher(text3);
		String result4 = matcherDate.replaceAll(TAG_DATE);

		Pattern patternOrd = Pattern.compile(REGEX_ORD);
		Matcher matcherOrd = patternOrd.matcher(text9);
		String result5 = matcherOrd.replaceAll(TAG_ORD);

		Pattern patternDistance = Pattern.compile(REGEX_DISTANCE);
		Matcher matcherDistance = patternDistance.matcher(text10);
		String result6 = matcherDistance.replaceAll(TAG_DISTANCE);
		
		

		/*

		System.out.println(text8);
		System.out.println(result1);
		System.out.println();

		System.out.println(text1);
		System.out.println(result2);
		System.out.println();

		System.out.println(text2);
		System.out.println(result3);
		System.out.println();


		System.out.println(text3);
		System.out.println(result4);
		System.out.println();

		System.out.println(text9);
		System.out.println(result5);
		System.out.println();
		*/
		System.out.println(text10);
		System.out.println(result6);
		System.out.println();


	}

}











