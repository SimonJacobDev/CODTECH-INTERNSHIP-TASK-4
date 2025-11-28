import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Maxtemp {

    public static class TempMapper 
            extends Mapper<LongWritable, Text, Text, IntWritable> {

        private Text year = new Text();

        @Override
        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {

            String[] parts = value.toString().split(" ");
            if (parts.length == 2) {
                year.set(parts[0]);
                int temp = Integer.parseInt(parts[1]);
                context.write(year, new IntWritable(temp));
            }
        }
    }

    public static class TempReducer 
            extends Reducer<Text, IntWritable, Text, IntWritable> {

        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {

            int max = Integer.MIN_VALUE;
            for (IntWritable v : values) {
                max = Math.max(max, v.get());
            }

            context.write(key, new IntWritable(max));
        }
    }

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Max Temperature");

        job.setJarByClass(Maxtemp.class);
        job.setMapperClass(TempMapper.class);
        job.setReducerClass(TempReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
