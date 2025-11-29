Here is **the same content exactly as given earlier**, no reduction, no extra lines — unchanged:

---

# **EX.NO: 8**

---

# **VISUALIZING DATA SETS USING R**

---

## **AIM:**

To visualize datasets using R.

---

## **BASIC CONCEPTS:**

A dataset is a collection of data presented in tabular form.
R provides many built-in datasets that can be used as demo data to understand how R functions work.

### **Example Data Sets in R**

* **airquality** – New York Air Quality Measurements
* **AirPassengers** – Monthly Airline Passenger Numbers (1949–1960)
* **mtcars** – Motor Trend Car Road Tests
* **iris** – Edgar Anderson’s Iris Data

---

## **IMPLEMENTATION**

### **DISPLAY R DATASETS:**

```r
print(airquality)  # to print entire data set

# use dim() to get dimension of dataset
cat("Dimension:", dim(airquality))

# use nrow() to get number of rows
cat("\nRow:", nrow(airquality))

# use ncol() to get number of columns
cat("\nColumn:", ncol(airquality))

# use names() to get name of variables of dataset
cat("\nName of Variables:", names(airquality), "\n")

# display all values of Day variable
print("Day")
print(airquality$Day)

# sort values of Temp variable
sort(airquality$Temp)
```

### **Sample Output:**

```
Ozone Solar.R Wind Temp Month Day
1   41     190  7.4   67    5    1
2   36     118  8.0   72    5    2
3   12     149 12.6   74    5    3
...
153 20     223 11.5   68    9   30

Dimension: 153 6
Row: 153
Column: 6
Name of Variables: Ozone Solar.R Wind Temp Month Day

[1] "Day"
[1] 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 ...
```

---

## **STATISTICAL SUMMARY OF DATA IN R**

```r
summary(airquality$Temp)
```

### **Output**

```
Min. 1st Qu. Median  Mean 3rd Qu. Max.
56     72      79     77     85     97
```

---

## **VISUALIZING DATASET USING ggplot2**

### **Install and Load ggplot2**

```r
install.packages("ggplot2")
library(ggplot2)
```

### **Basic Scatter Plot**

```r
qplot(Sepal.Length, Sepal.Width, data = iris)
```

### **Scatter Plot with Color**

```r
qplot(Sepal.Length, Sepal.Width, data = iris, color = Species)
```

### **Adding Size**

```r
qplot(Sepal.Length, Sepal.Width, data = iris, 
      color = Species, size = Sepal.Length)
```

### **Adding Transparency**

```r
qplot(Sepal.Length, Sepal.Width, data = iris, 
      color = Species, size = Sepal.Length, alpha = I(0.2))
```

---

## **SCATTER PLOTS USING BASE R**

### **Simple Scatter Plot**

```r
plot(iris$Petal.Length, iris$Petal.Width, main = "Iris Data")
```

### **Scatter Plot with Different pch Values**

```r
plot(iris$Petal.Length, iris$Petal.Width, main = "Iris Data",
     pch = c(21, 22, 25)[unclass(iris$Species)])
```

### **Colored Scatter Plot**

```r
plot(iris$Petal.Length, iris$Petal.Width,
     pch = 21,
     bg = c("red", "green3", "blue")[unclass(iris$Species)],
     main = "Iris Data")
```

### **Pairs Plot**

```r
pairs(iris[1:4], main = "Iris Data",
      pch = 21,
      bg = c("red", "green3", "blue")[unclass(iris$Species)])
```

---

## **RESULT:**

Thus, datasets were successfully visualized using R Programming.

---

If you want this in **PDF**, **A4 lab record format**, or **college notebook format**, tell me.
