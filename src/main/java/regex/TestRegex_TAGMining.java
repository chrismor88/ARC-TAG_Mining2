package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex_TAGMining{

	

	public static void main(String[] args) {



		/* REGEX */

		final String REGEX_ORD = "((\\d+(st|nd|rd|th)) | (\\d+°))";
		final String REGEX_NUM = "((\\+|-)?\\d+((\\.|,)\\d+)?(\\^\\d+)?((\\s*\\*\\s*)10\\^\\d+)?(\\s*E\\^(\\+|-)?\\d+)?)";
		

		//for format time hh:mm:ss, hh:mm, hh:mm am, hh:mm:ss pm ...........
		final String time1 ="(([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])(:[0-5][0-9])?(\\s*(am|AM|pm|PM))?)";

		//for format time xx h yy m  ,  xx h yy min  , xx h  yy m zz s, xx m yy s ............
		final String time2 = "((\\d{1,2}\\s*h)?\\s*\\d{1,2}\\s*m(in)?\\s*(\\d{1,2}\\s*(s(ec)?)))";


		final String REGEX_TIME = time1+"|"+time2;


		
		final String LENGTH_MEASURE = "((\\s*(k|c|d|m)?m(s?))|(\\s*(kilo|deci|centi|milli)?meter)|\\s*(mi|ft|yd|yard|naut mi|hand|span|mile(s?)|in(ches)?|NM|nm))";
		final String AREA_MEASURE = "((\\s*(k|c|d|m)?m\\^2)|\\s*(mi\\^2|ha|a|ca)|\\s+((in|yd|ft|NM|nm)\\^2)|\\s+(ro|acro))";
		final String SPEED_MEASURE = "\\s*((m/s)||(m/min)|(m/h)|(km/h)||(in/s)|(ft/s)|(ft/m)|(ft/h)|(km|m|miles)ph|mps|knot|kn|rpm|(rad/s)|(rad/min))\\s*";
		final String WEIGHT_MEASURE = "\\s*(t|kg|hg|g|dg|cg|mg|µg|carat|lb|cwt|ton|(milli|kilo|centi|deci|deka|hecto)?gram(s?)|once(s)?|pound(s?)|grain(s?))\\s*";
		final String VOLUME_MEASURE = "\\s*((m|d|k|c)?m\\^3|(d|c|m)?l|in\\^3|ft\\^3|yd\\^3|gal|bbl|pt|(dry|fluid)\\spt)\\s*";
		final String TEMPERATURE_MEASURE = "\\s*((c|C)elsius|°C|(k|K)elvin|°K|(f|F)ahrenheit|°F)\\s*";
		final String PRESSURE_MEASURE = "\\s*(bar|Pa(scal)?|at(m?)|Torr|psi)\\s*";
		final String DATA_MEASURE = "\\s*((giga|Giga||mega|Mega|kilo|Kilo|Tera|tera|peta|Peta|Exa|exa|Yotta|yotta)?byte(s?)|bit|(G|M|K|T|E|Y)B)\\s*";
		
		
		
		
		
		final String DATA_RATE_MEASURE = "\\s*(Kbps|Mbps|Gbps|(Kb/s(ec)?)|(Mb/s(ec)?)|(Gb/s(ec?))|(KB/s(ec)?)|(MB/s(ec)?)|(GB/s(ec)?))\\s*";

		
		final String REGEX_DISTANCE = REGEX_NUM+LENGTH_MEASURE;
		final String REGEX_AREA = REGEX_NUM+AREA_MEASURE+")";
		final String REGEX_SPEED = REGEX_NUM+SPEED_MEASURE+")";
		final String REGEX_WEIGHT = REGEX_NUM+WEIGHT_MEASURE+")";
		final String REGEX_VOLUME = REGEX_NUM+VOLUME_MEASURE+")";
		final String REGEX_TEMPERATURE = REGEX_NUM+TEMPERATURE_MEASURE+")";
		final String REGEX_PRESSURE = REGEX_NUM+PRESSURE_MEASURE+")";
		final String REGEX_DATA_RATE = REGEX_NUM+DATA_RATE_MEASURE;

		
		final String CURRENCY = "(USD|usd|EUR|euro|dollar(s)?|pound(s)?|GBP|yen|JPY|CHF|AUD|AFN|ALL|DZD|AOA|ARS|AMD|AWG|"+
								"AZN|BSD|BHD|BDT|BBD|BYR|BZD|BMD|BTN|BOB|BAM|BWP|BRL|BND|BGN|BIF|XOF|XAF|XPF|KHR|CAD|CVE|KYD|"+
								"CLP|CNY|COP|KMF|CDF|CRC|HRK|CUC|CUP|CYP|€|£|$)";
		
		final String REGEX_MONEY = "(\\d+((\\.|,)?\\d+)?\\s*"+CURRENCY+")|("+CURRENCY+"\\s*\\d+((\\.|,)?\\d+)?)";
				


		final String MONTH = "(\\s*(January|january|jan(\\.)|Jan(\\.)?|JAN(\\.)?|February|february|Feb(\\.)?|FEB(\\.)?|feb(\\.)?|March|march|Mar(\\.)?|MAR(\\.)?|mar(\\.)?|April|april|Apr(\\.)?|APR(\\.)?|"+
				"May|MAY|may|June|JUNE|june|July|JULY|july|August|august|Aug(\\.)?|AUG(\\.)?|aug(\\.)?"+
				"September|september|Sept(\\.)?|SEPT(\\.)?|sept(\\.)?|October|october|Oct(\\.)?|OCT(\\.)?|oct(\\.)?|November|november|Nov(\\.)?|NOV(\\.)?|nov(\\.)?|December|december|Dec(\\.)?|DEC(\\.)?|dec(\\.)?)\\s*)";

		//for format dd/mm/yyyy, dd/mm/yy, dd.mm.yyyy, dd.mm.yy, dd-mm-yyyy, dd-mm-yy, dd mm yyyy, dd mm yy
		final String date1 = "(([0]?[1-9]|[1|2][0-9]|[3][0|1])(\\.|/|-|\\s+)([0]?[1-9]|[1][0-2])(\\.|/|-|\\s+)([0-9]{4}|[0-9]{2}))";


		//for format mm/dd/yyyy, mm-dd-yyyy, mm dd yyyy, mm.dd.yyyy
		final String date2 = "(([0]?[1-9]|[1][0-2])(\\.|/|-|\\s+)([0]?[1-9]|[1|2][0-9]|[3][0|1])(\\.|/|-|\\s+)([0-9]{4}))";

		//for format dd Month yyyy, dd Month yy,ddMonthyyyy
		final String date3 = "([0]?[1-9]|[1|2][0-9]|[3][0|1])"+MONTH+"([0-9]{4}|[0-9]{2})";

		//for format Month dd,    Month dd, yyyy, Month ddth, yyyy.........
		final String date4 = "("+MONTH+"\\d+"+"(st|nd|rd|th)?(,\\s+([1|2]\\d{3})?)?)";

		//for format ddth Month   ddth Month yyyy
		final String date5 = "("+REGEX_ORD+MONTH+"(\\d{4})?)";
	
		//for format yyyy-mm-dd, yyyy/mm/dd, yyyy mm dd
		final String date6 = "([1|2]\\d{3}(\\.|-|/|\\s+)([0]?[1-9]|[1][0-2])(\\.|-|/|\\s+)(\\d{1,2}))";


		final String REGEX_DATE = date1+"|"+date2+"|"+date3+"|"+date4+"|"+date5+"|"+date6;




		final String REGEX_URL = "<a href=\"http://(.)*>(.)*</a>";








		/* TAG STRING*/
		final String TAG_DATE = " #DATE ";
		final String TAG_TIME = " #TIME ";
		final String TAG_ORD = " #ORD ";
		final String TAG_NUM = " #NUM ";
		final String TAG_DISTANCE = " #DISTANCE ";
		final String TAG_AREA = " #AREA ";
		final String TAG_SPEED = " #SPEED ";
		final String TAG_URL = " #URL ";
		final String TAG_WEIGHT = " #WEIGHT ";
		final String TAG_VOLUME = " #VOLUME ";
		final String TAG_DATA_RATE = " #DATA_RATE ";
		final String TAG_MONEY = " #MONEY ";



		String text1 = " 12/03/1988  , 24.08.12, 01/02/2010, 1/1/11, 24-04-12 ";


		String text2 = "Dante Alighieri, or simply Dante ( May 14/ June 13, 1265 – September 14, 1321), was an Italian poet from Florence. His central work, the Commedia (Divine Comedy), is considered the greatest literary work composed in the Italian language and a masterpiece of world literature. In Italian he is known as \"the Supreme Poet\" (il Sommo Poeta). Dante and the Divine Comedy have been a source of inspiration for artists for almost seven centuries. Dante, with Petrarch and Boccaccio are also known as \"the three fountains\". Dante is also called \"the Father of the Italian language\". The first biography written on him was by his contemporary Giovanni Villani. The most famous section in La Divina Commedia is the first third of it, the first 34 cantos of the poem, called Inferno, which is Dante's vision of hell.";
		String text3 ="I was born in 12th March 1988";

		String text4 ="1990-12-01   2000/03/11   2000 04 07";
		String text5 = "11/12/2015, 11/12/15, 11.12.2015, 11.12.15, 12-03-1988, 12-03-88, 12 02 2000, 12 02 00";
		String text6 = "12 Mar 1988      12 March 1988     24 Dec. 2015";
		String text7 = "12th March       12th Mar. 1988";
		String text8 = "1:02:34      1h 30min   1h 21m   1h 21m 38s  1 h 21 m 38 s 1:05 am   7:45 pm    22m 10sec   21m 14sec   ";
		String text9 = " 1st    2nd  100th      1°   12°   ";
		String text10 = "1km   1,30232 m  321.039120 ms";
		String text11 = "15GB/s 10Mb/s     fdsjkfsdkjf eqweqwe 7 Kbps   ";
		
		

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
		
		Pattern patternDataRate = Pattern.compile(REGEX_DATA_RATE);
		Matcher matcherDataRate = patternDataRate.matcher(text11);
		String result7 = matcherDataRate.replaceAll(TAG_DATA_RATE);

		
	
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

		System.out.println(text10);
		System.out.println(result6);
		System.out.println();
		
		
		
		System.out.println(text11);
		System.out.println(result7);
		System.out.println();

		
		

	}

}











