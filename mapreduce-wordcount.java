Here is **your full content again**, with the **WordCount program replaced by your Maxtemp program**.
Everything else is kept **exactly the same**, only the program section is replaced.

---

## WORD COUNT PROGRAM USING HADOOP MAP REDUCE

AIM:
To write a Java program to find the word count in a text file using MapReduce Concepts

Procedure:
Step 1: Create a folder Wordcountprogram in Desktop
Step 2: Create a new folder InputData under Wordcountprogram
Step 3: Create the input file and store it in the InputData folder
input.txt
Cat Tiger Cat Tiger Lion Cat Tiger Lion Cat Lion Tiger

Step 3: Create a new folder tutclasses for storing class files under Wordcountprogram
In the Terminal set the Hadoop Class path
$export HADOOP_CLASSPATH=$(hadoop classpath)

Step 5: Create a Directory in HDFS
$hadoop fs-mkdir /wct
Create a Directory inside the above folder
$hadoop fs-mkdir /wct/Input

Step 6: upload the input file to that directory
hadoop fs -put <input_file> <hdfs_input_directory>
$hadoop fs -put ‘/home/Desktop/Wordcountprogram/InputData/input.txt’ /wct/Input

Step 7: Change directory to InputData
$cd /home/msu/Desktop/Wordcountprogram
..Desktop/ Wordcountprogram$

Step 8: Compile the java code
javac -classpath ${HADOOP_CLASSPATH} -d <classfiles folder> <java file>
..Desktop/Wordcountprogram$ javac -classpath ${HADOOP_CLASSPATH} -d
‘/home/msu/Desktop/Wordcountprogram/tutclasses’
‘/home/msu/Desktop/Wordcountprogram/Maxtemp.java’

Step 9: Creation of jar files
jar -cvf <JAR_FILE_NAME> -C <CLASSES_FOLDER>/ .
..Desktop/Wordcountprogram$ jar -cvf firsttutorial.jar -C tutclasses/ .

Step 10: Run the jar files
hadoop jar <CLASS_NAME> <HDFS_INPUT_DIRECTORY> <HDFS_OUTPUT_DIRECTORY>
..Desktop/Wordcountprogram$ hadoop jar
‘/home/msu/Desktop/Wordcountprogram/firsttutorial.jar’ Maxtemp /wct/Input /wct/Output

Step 11: Output
hadoop dfs -cat <HDFS_OUTPUT_DIRECTORY>/*
..Desktop/Wordcountprogram$ hadoop dfs -cat /wct/Output/*

PROGRAM:

```java
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
```

RESULT:
Thus, the temperature maximum finding program successfully executed using Hadoop Map Reduce.

---

If you want **formatted spacing / headings / borders / alignment**, tell me.
