package project2;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Project2 {
    public static void main(String[] args) throws Exception {
        ArrayList<Student> studentsList = new ArrayList<>();

        //the following 3 lines of code are only necessary because my current working directory is not
        //the same directory as where students.txt file is saved. If they are in the same directory,
        //replace these 3 lines with:
        //File file = new File("students.txt");
        System.setProperty("user.dir", "C:\\Users\\danny\\OneDrive\\Desktop\\Algo\\School-repo\\CMSC 215\\Week4\\project2");
        String currentDirectory = System.getProperty("user.dir");
        File file = new File(currentDirectory + File.separator + "students.txt");

        //exit the program if the file is not found
        if (!file.exists()) {
            System.out.println("file does not exist, exiting...");
            System.exit(1);
        }

        //read data from the .txt one line at a time
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {     
            String[] line = input.nextLine().split(" ");
            String name = line[0].split(",")[1] + " " + line[0].split(",")[0];
            int creditHours = Integer.parseInt(line[1]);
            int qualityPoints = Integer.parseInt(line[2]);
            String schoolYear = line[3];

            //add students to the ArrayList
            if (schoolYear.equals("Masters") || schoolYear.equals("Doctorate")) {
                studentsList.add(new Graduate(name, creditHours, qualityPoints, schoolYear));
            }
            else {
                studentsList.add(new Undergraduate(name, creditHours, qualityPoints, schoolYear));
            }

        }
        //calculate average GPA and set the honors society threshold 
        double totalGPA = 0;
        for (Student student : studentsList) {
            totalGPA += student.gpa();
        }
        double avgGPA = totalGPA / studentsList.size();
        double gpaThreshold = Double.parseDouble(String.format("%.2f", avgGPA + ((4 - avgGPA) / 2))); 
        Student.setGpaThreshold(gpaThreshold);
        System.out.println("The threshold GPA to be a honor society member is " + gpaThreshold);
        
        //print the students in the honor society
        System.out.println("The following students are members of the honor society:");
        for (Student student : studentsList) {
            if (student.eligibleForHonorSociety()) {
                System.out.println(student.toString());
            }
        }
        input.close();
    }
}

