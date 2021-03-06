package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import writer_text.TAGMiningFileWriter;

public class TAGComponent {

	/* REGEX */

	private final static String TLDs = "\\.(com|us|it|en|org|edu|net|fr|es|gov)";
	
	//for format time hh:mm:ss, hh:mm, hh:mm am, hh:mm:ss pm ...........
	private final static String time1 ="(([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])(:[0-5][0-9])?\\s?(am|AM|pm|PM)?)";

	//for format time xx h yy m  ,  xx h yy min  , xx h  yy m zz s, xx m yy s ............
	private final static String time2 = "\\s\\d{1,2}(\\s)?h\\s\\d{2}(\\s)?m(in)?\\s(\\d{2}\\s?s(ec)?)?\\s?(am|AM|pm|PM)?";
	
	

	private final static String CURRENCY = "(USD|usd|EUR|euro|dollar(s)?|pound(s)?|GBP|yen|JPY|CHF|AUD|AFN|ALL|DZD|AOA|ARS|AMD|AWG|"+
			"AZN|BSD|BHD|BDT|BBD|BYR|BZD|BMD|BTN|BOB|BAM|BWP|BRL|BND|BGN|BIF|XOF|XAF|XPF|KHR|CAD|CVE|KYD|"+
			"CLP|CNY|COP|KMF|CDF|CRC|HRK|CUC|CUP|CYP|RUB|€|£|\\$)";


	private final static String REGEX_ORD = "(([1-9]\\d*(st|nd|rd|th)) | ([1-9]\\d*°))";


	private final static String MONTH = "(January|january|jan(\\.)|Jan(\\.)?|JAN(\\.)?|February|february|Feb(\\.)?|FEB(\\.)?|feb(\\.)?|March|march|Mar(\\.)?|MAR(\\.)?|mar(\\.)?|April|april|Apr(\\.)?|APR(\\.)?|"+
			"May|MAY|may|June|JUNE|june|Jul(y)?|JUL(Y)?|jul(y)?|August|august|Aug(\\.)?|AUG(\\.)?|aug(\\.)?|"+
			"September|september|Sept(\\.)?|SEPT(\\.)?|sept(\\.)?|October|october|Oct(\\.)?|OCT(\\.)?|oct(\\.)?|November|november|Nov(\\.)?|NOV(\\.)?|nov(\\.)?|December|december|Dec(\\.)?|DEC(\\.)?|dec(\\.)?)";
		
	//for format dd/mm/yyyy o d/m/yyyy
	private final static String date1 = "\\d{1,2}\\/\\d{1,2}\\/(19|20)\\d{2}";
	
	
	//for format dd-mm-yyyy o d-m-yyyy
	private final static String date2 = "\\d{1,2}-\\d{1,2}-(19|20)\\d{2}";
	
	
	//for format dd mm yyyy o   d m yyyy
	private final static String date3 = "\\d{1,2}\\s\\d{1,2}\\s(19|20)\\d{2}";
	
	
	//for format yyyy mm dd o yyyy m d
	private final static String date4 = "(19|20)\\d{2}\\s\\d{1,2}\\s\\d{1,2}";
	
	
	//for format dth mm yyyy o  dth m yyyyy
	private final static String date5 = "\\s"+REGEX_ORD+"\\s\\d{1,2}\\s(19|20)\\d{2}\\s";
	
	
	//for format dth MONTH yyyy
	private final static String date6 = "\\s"+REGEX_ORD+"\\s(of\\s)?"+MONTH+"\\s(19|20)\\d{2}\\s";
	
	
	//for format MONTH dd yyyy o MONTH dd,yyyy o MONTH dd, yyyy
	private final static String date7 = "\\s"+MONTH+"\\s\\d{1,2}(,\\s*)?\\s*(19|20)\\d{2}\\s";
	
	
	//for format MONTH ddth yyyy o MONTH ddth, yyyy
	private final static String date8 = "\\s"+MONTH+"\\s"+REGEX_ORD+"(,?\\s*)?\\s*(19|20)\\d{2}\\s";
	
	//for format dd.mm.yyyy o d.m.yyyy o yyyy.mm.yy o yyyy.m.y o dd.mm.yy o mm.dd.yy
	private final static String date9 = "(\\d{1,2}(\\.)\\d{1,2}(\\.)(19|20)\\d{2})|((19|20)\\d{2}(\\.)\\d{1,2}(\\.)\\d{1,2})|(\\d{1,2}\\.\\d{1,2}\\.\\d{2})";
	
	
	//for format yyyy-mm-dd o yyyy-m-d
	private final static String date10 = "(19|20)\\d{2}-\\d{1,2}-\\d{1,2}";
	
	//for format yyyy-MONTH-dd o dd-MONTH-yyyy
	private final static String date11 = "(19|20)\\d{2}-"+MONTH+"-\\d{1,2}\\s*|\\s*\\d{1,2}-"+MONTH+"-(19|20)\\d{2}";
	
	//for format MONTH yyyy o dd MONTH yyyy
	private final static String date12 = "((\\s\\d{1,2})?\\s"+MONTH+"\\s(19|20)\\d{2}\\s)";
	
	

	

	private final static String LENGTH_MEASURE = "(((k|c|d|m)?m)|((kilo|deci|centi|milli)?meter)|(mi|ft|yd|yard|naut mi|hand|span|mile(s?)|inch(es)?|NM|nm))[\\s,;]";
	private final static String AREA_MEASURE = "(((k|c|d|m)?m\\^2)|(mi\\^2)|((in|inch(es)?|yd|ft|NM|nm)\\^2)|(ro|acro))[\\s,;]";
	private final static String SPEED_MEASURE = "((m\\/s)|(m\\/min)|(m\\/h)|(km\\/h)|(in\\/s)|(ft\\/s)|(ft\\/m)|(ft\\/h)|(km|m|miles)ph|mps|knot|kn|rpm|(rad\\/s)|(rad\\/min))[\\s,;]";
	private final static String WEIGHT_MEASURE = "(t|kg|hg|g|dg|cg|mg|µg|carat|lb|cwt|ton|(milli|kilo|centi|deci|deka|hecto)?gram(s?)|once(s)?|pound(s?)|grain(s?))[\\s,;]";
	private final static String VOLUME_MEASURE = "((m|d|k|c)?m\\^3|(d|c|m)?l|in\\^3|ft\\^3|yd\\^3|gal|bbl|pt|(dry|fluid)\\spt)[\\s,;]";
	private final static String TEMPERATURE_MEASURE = "((c|C)elsius|°C|(k|K)elvin|°K|(f|F)ahrenheit|°F)";
	private final static String DATA_MEASURE = "((giga|Giga||mega|Mega|kilo|Kilo|Tera|tera|peta|Peta|Exa|exa|Yotta|yotta)?byte(s?)|bit|(G|M|K|T|E|Y)B)";
	private final static String DATA_RATE_MEASURE = "(Kbps|Mbps|Gbps|(Kb\\/s(ec)?)|(Mb\\/s(ec)?)|(Gb\\/s(ec?))|(KB\\/s(ec)?)|(MB\\/s(ec)?)|(GB\\/s(ec)?))";
	
	
	
	private final static String REGEX_NUM = "(([\\+-]?[1-9]\\d*)|([\\+-]?\\d[\\.,]\\d+))";
	private final static String REGEX_URL = "(http(s?):\\/\\/)?([a-zA-Z0-9]([a-zA-Z0-9-\\.]{0,61}[a-zA-Z0-9])?"+TLDs+"(\\/\\w+|\\?\\w+|=\\w+|&\\w+)*)(\\.html)?";
	private final static String REGEX_DISTANCE = "\\d+([\\.,]\\d+)?"+"\\s?"+LENGTH_MEASURE;
	private final static String REGEX_AREA = "\\d+([\\.,]\\d+)?"+"\\s?"+AREA_MEASURE;
	private final static String REGEX_VOLUME = "\\d+([\\.,]\\d+)?"+"\\s?"+VOLUME_MEASURE;
	private final static String REGEX_SPEED = "\\d+([\\.,]\\d+)?"+"\\s?"+SPEED_MEASURE;
	private final static String REGEX_WEIGHT = "\\d+([\\.,]\\d+)?"+"\\s?"+WEIGHT_MEASURE;
	private final static String REGEX_TEMPERATURE = REGEX_NUM+"\\s?"+TEMPERATURE_MEASURE;
	private final static String REGEX_DATA_RATE = "\\d+([\\.,]\\d+)?"+"\\s*"+DATA_RATE_MEASURE;
	private final static String REGEX_TIME = time1+"|"+time2;
	private final static String REGEX_MONEY = CURRENCY+"\\s?\\d+(\\.|,)\\d+|\\d+(\\.|,)\\d+\\s?"+CURRENCY+"|"+CURRENCY+"\\s?\\d+"+"|"+"\\s\\d+\\s?"+CURRENCY;
	private final static String REGEX_DATA = "\\d+([\\.,]\\d+)?"+"\\s?"+DATA_MEASURE;
	private final static String REGEX_DATE_RANGE = "(1\\d{3}[\\/-]1\\d{3})|(1\\d{3}[\\/-]2\\d{3})|(2\\d{3}[\\/-]2\\d{3})|([12]\\d{3}(\\/)\\d{2})";
	private final static String REGEX_YEAR = "(19|20)\\d{2}";

	private final static String REGEX_PHONE = "(\\d{3}-\\d{4,5})|(\\(\\d{3}\\)\\s(\\/\\s)?\\d{3}-\\d{4,5})|((\\d{2}-|\\+)\\d{2}-\\d{2}-\\d{3}-\\d{5})|((\\+)?\\d-\\d{3}-\\d{3}-\\d{4})|"+
			"(\\d{3}-\\d{3}-\\d{3}-\\d{4})|((\\d{3}\\s)?\\d{3}\\s\\d{3}\\s\\d{4})|((\\+\\d\\s)?\\d{3}\\s\\d{3,4}\\s\\d{4})|(\\+\\d{2}\\s\\d{2}\\s\\d{4}\\s\\d{4})|"+
			"(\\(\\d{3}\\)\\s?\\d{3}-\\d{4})|(\\d{3}-\\d{3}-\\d{4})|(\\+\\d{2}-\\d{2}-\\d{3}-\\d{5})|(\\(\\+\\d{2}\\)-\\d{3}-\\d{3}-\\d{4})|(\\(\\+\\d{3}\\)\\s\\d{3}-\\d{4})";
	
	private final static String REGEX_DATE = date1+"|"+date2+"|"+date3+"|"+date4+"|"+date5+"|"+date6+"|"+date7+"|"+date8+"|"+date9+"|"+date10+"|"+date11+"|"+date12;
	private final static String REGEX_EMAIL = "\\w+(\\.)*\\w+@\\w+(-)*\\w+"+TLDs;


	
	
	
	
	/* TAG STRING*/
	final static String TAG_DATE = " #DATE ";
	final static String TAG_TIME = " #TIME ";
	final static String TAG_ORD = " #ORD ";
	final static String TAG_NUM = " #NUM ";
	final static String TAG_DISTANCE = " #DISTANCE ";
	final static String TAG_AREA = " #AREA ";
	final static String TAG_SPEED = " #SPEED ";
	final static String TAG_URL = " #URL ";
	final static String TAG_WEIGHT = " #WEIGHT ";
	final static String TAG_VOLUME = " #VOLUME ";
	final static String TAG_DATA_RATE = " #DATA_RATE ";
	final static String TAG_MONEY = " #MONEY ";
	final static String TAG_PHONE = " #PHONE ";
	final static String TAG_EMAIL = " #EMAIL ";
	final static String TAG_TEMPERATURE = " #TEMPERATURE ";
	final static String TAG_DATA = " #DATA ";
	final static String TAG_DATE_RANGE = " #DATE_RANGE ";
	final static String TAG_YEAR = " #YEAR ";





	public static void tagPhrase(String trecID,String phrase){

		String temp = phrase;
		temp.trim();
		String firstPart = temp.substring(0,(temp.length()/2));
		String secondPart = temp.substring(temp.length()/2);

		if(firstPart.equals(secondPart)){
			phrase = firstPart+" "+secondPart;
		}



		String changedPhrase = phrase;
		changedPhrase = tagPhraseEMAIL(trecID,phrase);
		changedPhrase = tagPhraseURL(trecID,changedPhrase);
		changedPhrase = tagPhraseDATE(trecID,changedPhrase);
		changedPhrase = tagPhraseDATE_RANGE(trecID, changedPhrase);
		changedPhrase = tagPhraseTIME(trecID,changedPhrase);
		changedPhrase = tagPhrasePHONE(trecID,changedPhrase);
		changedPhrase = tagPhraseMONEY(trecID,changedPhrase);
		changedPhrase = tagPhraseAREA(trecID,changedPhrase);
		changedPhrase = tagPhraseVOLUME(trecID,changedPhrase);
		changedPhrase = tagPhraseSPEED(trecID,changedPhrase);
		changedPhrase = tagPhraseDISTANCE(trecID,changedPhrase);
		changedPhrase = tagPhraseTEMPERATURE(trecID,changedPhrase);
		changedPhrase = tagPhraseWEIGHT(trecID,changedPhrase);
		changedPhrase = tagPhraseDATA_RATE(trecID,changedPhrase);
		changedPhrase = tagPhraseDATA(trecID,changedPhrase);
		changedPhrase = tagPhraseORD(trecID,changedPhrase);
		changedPhrase = tagPhraseYEAR(trecID,changedPhrase);
		changedPhrase = tagPhraseNUM(trecID,changedPhrase);


		if(!phrase.equals(changedPhrase)){
			TAGMiningFileWriter.writeOutput2(trecID,phrase);
			TAGMiningFileWriter.writeOutput3(trecID,changedPhrase);
		}

	}




	private static String tagPhraseYEAR(String trecID, String phrase) {
		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern pattern = Pattern.compile(REGEX_YEAR);
		Matcher matcher = pattern.matcher(phrase);
		while(matcher.find()){

			startIndex = matcher.start();
			endIndex = matcher.end();
			String matchedSubString = matcher.group();

			
			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_YEAR);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_YEAR+changedPhrase.substring(endIndex);
			matcher = pattern.matcher(changedPhrase);
		}

		return changedPhrase;
	}




	private static String tagPhraseORD(String trecID, String phrase) {
		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern pattern = Pattern.compile(REGEX_ORD);
		Matcher matcher = pattern.matcher(phrase);
		while(matcher.find()){

			startIndex = matcher.start();
			endIndex = matcher.end();
			String matchedSubString = matcher.group();

			
			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_ORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_ORD+changedPhrase.substring(endIndex);
			matcher = pattern.matcher(changedPhrase);
		}

		return changedPhrase;
	}

	
	private static String tagPhraseDATE_RANGE(String trecID, String phrase) {
		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern pattern = Pattern.compile(REGEX_DATE_RANGE);
		Matcher matcher = pattern.matcher(phrase);
		while(matcher.find()){

			startIndex = matcher.start();
			endIndex = matcher.end();
			String matchedSubString = matcher.group();

			
			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_DATE_RANGE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_DATE_RANGE+changedPhrase.substring(endIndex);
			matcher = pattern.matcher(changedPhrase);
		}

		return changedPhrase;
	}



	private static String tagPhraseWEIGHT(String trecID, String phrase) {
		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern pattern = Pattern.compile(REGEX_WEIGHT);
		Matcher matcher = pattern.matcher(phrase);
		while(matcher.find()){

			startIndex = matcher.start();
			endIndex = matcher.end();
			String matchedSubString = matcher.group();

			
			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_WEIGHT);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_WEIGHT+changedPhrase.substring(endIndex);
			matcher = pattern.matcher(changedPhrase);
		}

		return changedPhrase;
	}




	private static String tagPhraseSPEED(String trecID, String phrase) {
		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern pattern = Pattern.compile(REGEX_SPEED);
		Matcher matcher = pattern.matcher(phrase);
		while(matcher.find()){

			startIndex = matcher.start();
			endIndex = matcher.end();
			String matchedSubString = matcher.group();

			
			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_SPEED);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_SPEED+changedPhrase.substring(endIndex);
			matcher = pattern.matcher(changedPhrase);
		}

		return changedPhrase;
	}




	private static String tagPhraseDATA(String trecID, String phrase) {
		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern pattern = Pattern.compile(REGEX_DATA);
		Matcher matcher = pattern.matcher(phrase);
		while(matcher.find()){

			startIndex = matcher.start();
			endIndex = matcher.end();
			String matchedSubString = matcher.group();

			
			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_DATA);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_DATA+changedPhrase.substring(endIndex);
			matcher = pattern.matcher(changedPhrase);
		}

		return changedPhrase;
	}




	private static String tagPhraseTEMPERATURE(String trecID,
			String phrase) {
		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern pattern = Pattern.compile(REGEX_TEMPERATURE);
		Matcher matcher = pattern.matcher(phrase);
		while(matcher.find()){

			startIndex = matcher.start();
			endIndex = matcher.end();
			String matchedSubString = matcher.group();

			
			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_TEMPERATURE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_TEMPERATURE+changedPhrase.substring(endIndex);
			matcher = pattern.matcher(changedPhrase);
		}

		return changedPhrase;
	}




	private static String tagPhraseNUM(String trecID, String phrase) {
		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern pattern = Pattern.compile(REGEX_NUM);
		Matcher matcher = pattern.matcher(phrase);
		while(matcher.find()){

			startIndex = matcher.start();
			endIndex = matcher.end();
			String matchedSubString = matcher.group();

			
			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_NUM);
			} catch (Exception e) {
				e.printStackTrace();
			}
		

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_NUM+changedPhrase.substring(endIndex);
			matcher = pattern.matcher(changedPhrase);
		}

		return changedPhrase;
	}




	private static String tagPhraseDATA_RATE(String trecID, String phrase) {
		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern pattern = Pattern.compile(REGEX_DATA_RATE);
		Matcher matcher = pattern.matcher(phrase);
		while(matcher.find()){

			startIndex = matcher.start();
			endIndex = matcher.end();
			String matchedSubString = matcher.group();

			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_DATA_RATE);
			} catch (Exception e) {
				e.printStackTrace();
			}

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_DATA_RATE+changedPhrase.substring(endIndex);
			matcher = pattern.matcher(changedPhrase);
		}

		return changedPhrase;
	}




	private static String tagPhraseVOLUME(String trecID, String phrase) {
		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern pattern = Pattern.compile(REGEX_VOLUME);
		Matcher matcher = pattern.matcher(phrase);
		while(matcher.find()){

			startIndex = matcher.start();
			endIndex = matcher.end();
			String matchedSubString = matcher.group();

			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_VOLUME);
			} catch (Exception e) {
				e.printStackTrace();
			}

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_VOLUME+changedPhrase.substring(endIndex);
			matcher = pattern.matcher(changedPhrase);
		}

		return changedPhrase;
	}




	private static String tagPhraseAREA(String trecID, String phrase) {
		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern pattern = Pattern.compile(REGEX_AREA);
		Matcher matcher = pattern.matcher(phrase);
		while(matcher.find()){

			startIndex = matcher.start();
			endIndex = matcher.end();
			String matchedSubString = matcher.group();

			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_AREA);
			} catch (Exception e) {
				e.printStackTrace();
			}

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_AREA+changedPhrase.substring(endIndex);
			matcher = pattern.matcher(changedPhrase);
		}

		return changedPhrase;
	}




	private static String tagPhraseDISTANCE(String trecID, String phrase) {
		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern pattern = Pattern.compile(REGEX_DISTANCE);
		Matcher matcher = pattern.matcher(phrase);
		while(matcher.find()){

			startIndex = matcher.start();
			endIndex = matcher.end();
			String matchedSubString = matcher.group();

			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_DISTANCE);
			} catch (Exception e) {
				e.printStackTrace();
			}

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_DISTANCE+changedPhrase.substring(endIndex);
			matcher = pattern.matcher(changedPhrase);
		}

		return changedPhrase;
	}




	private static String tagPhraseMONEY(String trecID, String phrase) {
		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern pattern = Pattern.compile(REGEX_MONEY);
		Matcher matcher = pattern.matcher(phrase);
		while(matcher.find()){

			startIndex = matcher.start();
			endIndex = matcher.end();
			String matchedSubString = matcher.group();

			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_MONEY);
			} catch (Exception e) {
				e.printStackTrace();
			}

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_MONEY+changedPhrase.substring(endIndex);
			matcher = pattern.matcher(changedPhrase);
		}

		return changedPhrase;
	}




	private static String tagPhraseTIME(String trecID, String phrase) {
		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern pattern = Pattern.compile(REGEX_TIME);
		Matcher matcher = pattern.matcher(phrase);
		while(matcher.find()){

			startIndex = matcher.start();
			endIndex = matcher.end();
			String matchedSubString = matcher.group();

			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_TIME);
			} catch (Exception e) {
				e.printStackTrace();
			}

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_TIME+changedPhrase.substring(endIndex);
			matcher = pattern.matcher(changedPhrase);
		}

		return changedPhrase;
	}




	private static String tagPhraseEMAIL(String trecID, String phrase) {
		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern pattern = Pattern.compile(REGEX_EMAIL);
		Matcher matcher = pattern.matcher(phrase);
		while(matcher.find()){

			startIndex = matcher.start();
			endIndex = matcher.end();
			String matchedSubString = matcher.group();

			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_EMAIL);
			} catch (Exception e) {
				e.printStackTrace();
			}

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_EMAIL+changedPhrase.substring(endIndex);
			matcher = pattern.matcher(changedPhrase);
		}

		return changedPhrase;
	}




	private static String tagPhraseURL(String trecID, String phrase) {

		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern patternURL = Pattern.compile(REGEX_URL);
		Matcher matcherURL = patternURL.matcher(phrase);
		while(matcherURL.find()){

			startIndex = matcherURL.start();
			endIndex = matcherURL.end();
			String matchedSubString = matcherURL.group();

			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_URL);
			} catch (Exception e) {
				e.printStackTrace();
			}

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_URL+changedPhrase.substring(endIndex);
			matcherURL = patternURL.matcher(changedPhrase);
		}

		return changedPhrase;
	}

	
	private static String tagPhraseDATE(String trecID, String phrase) {

		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern patternDATE = Pattern.compile(REGEX_DATE);
		Matcher matcherDATE = patternDATE.matcher(phrase);
		while(matcherDATE.find()){

			startIndex = matcherDATE.start();
			endIndex = matcherDATE.end();
			String matchedSubString = matcherDATE.group();
			//System.out.println(phrase);

			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_DATE);
			} catch (Exception e) {
				e.printStackTrace();
			}

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_DATE+changedPhrase.substring(endIndex);
			matcherDATE = patternDATE.matcher(changedPhrase);
		}

		return changedPhrase;
	}
	
	
	private static String tagPhrasePHONE(String trecID, String phrase) {

		String changedPhrase = phrase;
		int startIndex, endIndex = 0;


		Pattern patternPHONE = Pattern.compile(REGEX_PHONE);
		Matcher matcherPHONE = patternPHONE.matcher(phrase);
		while(matcherPHONE.find()){

			startIndex = matcherPHONE.start();
			endIndex = matcherPHONE.end();
			String matchedSubString = matcherPHONE.group();

			try {
				TAGMiningFileWriter.writeOutput1(trecID,matchedSubString, TAG_PHONE);
			} catch (Exception e) {
				e.printStackTrace();
			}

			changedPhrase = changedPhrase.substring(0, startIndex)+TAG_PHONE+changedPhrase.substring(endIndex);
			matcherPHONE = patternPHONE.matcher(changedPhrase);
		}

		return changedPhrase;
	}



}
