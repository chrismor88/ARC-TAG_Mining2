package count;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class OrderByValueMapper extends
		Mapper<LongWritable, Text, IntWritable, Text> {
	
	private Text tag = new Text();
	private IntWritable frequency = new IntWritable();

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();

		StringTokenizer tokenizer = new StringTokenizer(line);
		
		tag.set(tokenizer.nextToken());
		frequency.set(Integer.parseInt(tokenizer.nextToken()));
			
		context.write(frequency, tag);
		
	}
}
