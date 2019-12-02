# pdf-cutter
Step1:
input a pdf file (e.g: C:\eclip\pdfs\test.pdf) with index or bookmarks
enter the output directory (empty means current file)
change the output name

Step2:
The program will show all the index in gui as a "checkbox tree (not original code)"
Check the index(s) you want, and press "run" will create a new pdf file only contain the pages in the chapters u choose
p.s.: some error may comes while running java file in eclipse, but the program can run normally

Step3:
Close the project or press back to open another pdf file

Notice:
This is a project with maven dependency:

Group id: org.apache.pdfbox
Artifact id: pdfbox
Version: 2.0.13

Group id: com.lowagie
Artifact id: itext
Version: 4.2.2
