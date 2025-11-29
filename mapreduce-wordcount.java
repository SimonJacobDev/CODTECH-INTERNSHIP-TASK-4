Here is **the same content again exactly as you gave**, with **no reduction and no changes**:

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
‘/home/msu/Desktop/Wordcountprogram/WordCount.java’

Step 9: Creation of jar files
jar -cvf <JAR_FILE_NAME> -C <CLASSES_FOLDER>/ .
..Desktop/Wordcountprogram$ jar -cvf firsttutorial.jar -C tutclasses/ .

Step 10: Run the jar files
hadoop jar <CLASS_NAME> <HDFS_INPUT_DIRECTORY> <HDFS_OUTPUT_DIRECTORY>
..Desktop/Wordcountprogram$ hadoop jar
‘/home/msu/Desktop/Wordcountprogram/firsttutorial.jar’ WordCount /wct/Input /wct/Output

Step 11: Output
hadoop dfs -cat <HDFS_OUTPUT_DIRECTORY>/*
..Desktop/Wordcountprogram$ hadoop dfs -cat /wct/Output/*

PROGRAM:

```java
//WordCount.java
import java.io.IOException; 
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration; 
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job; 
import org.apache.hadoop.mapreduce.Mapper; 
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat; 
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat; 

public class WordCount {
    public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> { 
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString()); 
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
            }
        }
    }

    public static class IntSumReducer extends Reducer<Text,IntWritable,Text,IntWritable> {
        private IntWritable result = new IntWritable();
        
        public void reduce(Text key, Iterable<IntWritable> values, Context context) 
        throws IOException, InterruptedException {
            int sum = 0;
            for(IntWritable val : values) { 
                sum += val.get();
            }
            result.set(sum); 
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count"); 
        job.setJarByClass(WordCount.class); 
        job.setMapperClass(TokenizerMapper.class); 
        job.setCombinerClass(IntSumReducer.class); 
        job.setReducerClass(IntSumReducer.class); 
        job.setOutputKeyClass(Text.class); 
        job.setOutputValueClass(IntWritable.class); 
        FileInputFormat.addInputPath(job, new Path(args[0])); 
        FileOutputFormat.setOutputPath(job, new Path(args[1])); 
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
```

RESULT:
Thus, the Word count program successfully executed using Hadoop Map Reduce.

---

If you want **same content with bold headings / spacing / alignment**, tell me — I will format it.
