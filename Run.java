Here is the **same content again, exactly as you asked — simple, full, and unchanged**:

---

# **How to Run Hadoop wordcount MapReduce on Windows 10**

## **Prepare**

Download **MapReduceClient.jar**
(Link: [https://github.com/MuhammadBilalYar/HADOOP-INSTALLATION-ON-WINDOW-10/blob/master/MapReduceClient.jar](https://github.com/MuhammadBilalYar/HADOOP-INSTALLATION-ON-WINDOW-10/blob/master/MapReduceClient.jar))

Download **Input_file.txt**
(Link: [https://github.com/MuhammadBilalYar/HADOOP-INSTALLATION-ON-WINDOW-10/blob/master/input_file.txt](https://github.com/MuhammadBilalYar/HADOOP-INSTALLATION-ON-WINDOW-10/blob/master/input_file.txt))

Place both files in **“C:/”**

---

## **Hadoop Operation**

### **1. Open CMD in Administrative mode**

Move to:

```
C:/Hadoop-2.8.0/sbin
```

Start cluster:

```
Start-all.cmd
start all
```

---

### **2. Create an input directory in HDFS**

```
hadoop fs -mkdir /input_dir
```

---

### **3. Copy the input text file input_file.txt to HDFS**

```
hadoop fs -put C:/input_file.txt /input_dir
```

---

### **4. Verify input_file.txt in HDFS**

```
hadoop fs -ls /input_dir/
```

Output:
Input_file

---

### **5. Verify content of copied file**

```
hadoop dfs -cat /input_dir/input_file.txt
```

Output:
Content

---

### **6. Run MapReduceClient.jar (Wordcount program)**

```
hadoop jar C:/MapReduceClient.jar wordcount /input_dir /output_dir
```

Output:
Success

---

### **7. Verify generated output**

```
hadoop dfs -cat /output_dir/*
```

Output:
out

---

## **Some Other Useful Commands**

### **Leave Safe Mode**

```
hadoop dfsadmin –safemode leave
```

### **Delete file from HDFS**

```
hadoop fs -rm -r /iutput_dir/input_file.txt
```

### **Delete directory from HDFS**

```
hadoop fs -rm -r /iutput_dir
```

---

If you want, I can also give a **simple WordCount program**, **explain steps**, or **fix errors like SafeMode, path issues, output_dir already exists**, etc.



  https://github.com/MuhammadBilalYar/Hadoop-On-Window?tab=readme-ov-file

https://muhammadbilalyar.github.io/blogs/How-to-install-Hadoop-on-Window-10/

https://muhammadbilalyar.github.io/blogs/How-to-Run-Hadoop-wordcount-MapReduce-Example-on-Windows-10/
