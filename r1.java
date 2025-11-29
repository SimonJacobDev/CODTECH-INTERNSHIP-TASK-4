Here is **the same content again exactly as you asked**, with no changes:

---

# **EXPERIMENT: To Draw Basic Charts Using R**

## **AIM**

To create basic data visualizations in R using pie charts, bar charts, stacked charts, and histograms.

---

# **THEORY**

## **R Data Visualization**

Data visualization is an efficient technique for understanding large datasets through visual representation. It helps identify hidden patterns and trends that might not be visible through raw data.

### **Popular Visualization Packages in R**

* **plotly** – Creates interactive, web-based visualizations.
* **ggplot2** – Grammar-of-graphics based library for elegant statistical plots.
* **tidyquant** – Used for financial and quantitative analysis.
* **taucharts** – Declarative interface for rapidly mapping data to visuals.
* **ggiraph** – Makes ggplot graphs interactive.
* **googleVis** – Interface to Google Charts for browser-based visuals.
* **RColorBrewer** – Provides attractive color schemes.
* **shiny** – Builds interactive web applications.

---

# **R GRAPHICS**

---

# **1. PIE CHART**

### **Basic Syntax**

```
pie(x, labels, radius, main, col, clockwise)
```

### **Simple Pie Chart**

```R
x <- c(20, 65, 15, 50)
labels <- c("India", "America", "Shri Lanka", "Nepal")

png(file = "Country.png")
pie(x, labels)
dev.off()
```

---

### **Pie Chart with Title & Colors**

```R
x <- c(20, 65, 15, 50)
labels <- c("India", "America", "Shri Lanka", "Nepal")

png(file = "title_color.png")
pie(x, labels, main = "Country Pie Chart", col = rainbow(length(x)))
legend("topright", c("India","America","Shri Lanka","Nepal"),
       cex = 0.8, fill = rainbow(length(x)))
dev.off()
```

---

# **2. 3D PIE CHART**

Requires: `plotrix`

```R
library(plotrix)
data <- c(19, 21, 54, 12, 36, 12)

png(file = "temp.png")
pie3D(data)
dev.off()
```

### **Setting Height & Radius**

```R
pie3D(data, radius = 0.75, height = 0.2)
```

### **Border Color**

```R
pie3D(data, border = "white")
```

### **Explode Effect**

```R
pie3D(data, explode = 0.2)
```

---

# **3. BAR CHART**

```R
H <- c(12, 35, 54, 3, 41)
M <- c("Feb", "Mar", "Apr", "May", "Jun")

png(file = "bar_properties.png")
barplot(H, names.arg = M, xlab = "Month", ylab = "Revenue",
        col = "Green", main = "Revenue Bar Chart", border = "red")
dev.off()
```

---

# **4. STACKED BAR CHART**

```R
library(RColorBrewer)

months <- c("Jan", "Feb", "Mar", "Apr", "May")
regions <- c("West", "North", "South")

Values <- matrix(c(21,32,33,14,95,
                   46,67,78,39,11,
                   22,23,94,15,16),
                 nrow = 3, ncol = 5, byrow = TRUE)

png(file = "stacked_chart.png")
barplot(Values, main = "Total Revenue", names.arg = months,
        xlab = "Month", ylab = "Revenue",
        col = c("cadetblue3","deeppink2","goldenrod1"))

legend("topleft", regions, cex = 1.3,
       fill = c("cadetblue3","deeppink2","goldenrod1"))
dev.off()
```

---

# **5. HISTOGRAM**

### **Basic Histogram**

```R
v <- c(12,24,16,38,21,13,55,17,39,10,60)

png(file = "histogram_chart.png")
hist(v, xlab = "Weight", ylab = "Frequency",
     col = "green", border = "red")
dev.off()
```

---

### **Histogram with xlim, ylim & breaks**

```R
v <- c(12,24,16,38,21,13,55,17,39,10,60)

png(file = "histogram_chart_lim.png")
hist(v, xlab = "Weight", ylab = "Frequency",
     col = "green", border = "red",
     xlim = c(0,40), ylim = c(0,3), breaks = 5)
dev.off()
```

---

# **RESULT**

Thus, various charts including pie charts, 3D pie charts, bar charts, stacked charts, and histograms were successfully created using R.

---

If you want this **as PDF / DOCX / PPT**, tell me — I’ll generate it **without removing any content**.
