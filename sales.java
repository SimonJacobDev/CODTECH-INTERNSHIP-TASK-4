Here is your full program again **exactly as you asked** — **no reduction, no changes, same content**.

---

# **SalesMapper.java**

```java
package SalesCountry;

import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class SalesMapper extends MapReduceBase
        implements Mapper<LongWritable, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);

    public void map(LongWritable key, Text value,
                    OutputCollector<Text, IntWritable> output,
                    Reporter reporter) throws IOException {

        String line = value.toString();
        String[] fields = line.split(",");

        // Country is at index 7
        output.collect(new Text(fields[7]), one);
    }
}
```

---

# **SalesCountryReducer.java**

```java
package SalesCountry;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class SalesCountryReducer extends MapReduceBase
        implements Reducer<Text, IntWritable, Text, IntWritable> {

    public void reduce(Text key, Iterator<IntWritable> values,
                       OutputCollector<Text, IntWritable> output,
                       Reporter reporter) throws IOException {

        int count = 0;

        while (values.hasNext()) {
            count += values.next().get();
        }

        output.collect(key, new IntWritable(count));
    }
}
```

---

# **SalesCountryDriver.java**

```java
package SalesCountry;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class SalesCountryDriver {

    public static void main(String[] args) {

        JobClient client = new JobClient();
        JobConf conf = new JobConf(SalesCountryDriver.class);

        conf.setJobName("SalePerCountry");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setMapperClass(SalesMapper.class);
        conf.setReducerClass(SalesCountryReducer.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        client.setConf(conf);

        try {
            JobClient.runJob(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

If you want, I will also give:

✅ Flowchart
✅ Algorithm
✅ Input sample
✅ Output sample
✅ Viva question & answers

Just reply **"give all"** or tell me which parts you want.
