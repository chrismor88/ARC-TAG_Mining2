package count;

import java.io.IOException;
import java.util.StringTokenizer;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;




public class TagCountMapper extends
		Mapper<LongWritable, Text, Text, IntWritable> {


	private static final IntWritable one = new IntWritable(1);
	private Text word = new Text();


	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {


		String line = value.toString();
		
		StringTokenizer tokenizer = new StringTokenizer(line);

		while (tokenizer.hasMoreTokens()) {
			word.set(tokenizer.nextToken());
			if(word.toString().charAt(0)=='#'){
			context.write(word, one);
			}
		}


	}


}
