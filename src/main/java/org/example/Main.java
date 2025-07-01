package org.example;

import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int numOfStudents = input.nextInt();

        System.out.println(); // print new line

        // Create arrays for storing names, scores, and letter grades
        String[] studentNames = new String[numOfStudents];
        int[] studentScores = new int[numOfStudents];
        char[] studentGrades = new char[numOfStudents];

        for (int i = 0; i < numOfStudents; i++) {
            System.out.printf("Enter name of student %d: ", i+1);
            String name = input.next();
            studentNames[i] = name; // Append student to array

            System.out.printf("Enter score for %s: ", name);
            int score = input.nextInt();

            // Validate score input to make sure the score is within range of 0-100
            while (score < 0 || score > 100 ) {
                System.out.print("\nNumeric score must be between 0 to 100\n");
                System.out.printf("\nEnter score for %s: ", name);
                score = input.nextInt();
            }

            studentScores[i] = score; // Append score to array

            char letterGrade = calculateLetterGrade(score);
            studentGrades[i] = letterGrade; // Append letter grade to array

            System.out.printf("%s got grade: %c\n\n", name, letterGrade);
        }

        generateClassSummary(studentNames, studentScores, studentGrades);
    }

    public static char calculateLetterGrade(int score) {
        if (score >= 90 && score <= 100) { // 90-100
            return 'A';
        } else if (score >= 80) { // 80-89
            return 'B';
        } else if (score >= 70) { // 70-79
            return 'C';
        } else if (score >= 60) { // 60-69
            return 'D';
        } else { // below 60
            return 'F';
        }
    };

    public static void generateClassSummary(String[] students, int[] scores, char[] grades) {
        // Initialize variables
        int countsOfA = 0, countsOfB = 0, countsOfC = 0, countsOfD = 0, countsOfF = 0;

        ArrayList<String> topStudents = new ArrayList<String>(); // Use arrayList for storing top students since number is unknown
        int topScore = 0;
        int totalScores = 0;

        // Iterate through all the students and their corresponding grades/scores
        for (int i = 0; i < students.length; i++) {
//            System.out.println("Count: " + i);
//            System.out.printf("Student: %s, Score: %d, Grade: %c\n", students[i], scores[i], grades[i]);

            // Count number of letter grades
            if (grades[i] == 'A') {
                countsOfA++;
            } else if (grades[i] == 'B') {
                countsOfB++;
            } else if (grades[i] == 'C') {
                countsOfC++;
            } else if (grades[i] == 'D') {
                countsOfD++;
            } else if (grades[i] == 'F') {
                countsOfF++;
            }

            totalScores += scores[i];

            if (scores[i] > topScore) {
                topScore = scores[i]; // Update the top score

                topStudents.clear(); // Clear top students if a higher score is found
                topStudents.add(students[i]); // Add new top student

            } else if (scores[i] == topScore) {
                // Retain the top score
                topStudents.add(students[i]); // Add student/s with the same score as top score
            }
        }

        double averageScore = (double) totalScores /students.length;
        String formattedTopStudents = topStudents.toString().replace("[","").replace("]",""); // Remove square brackets for printing

        System.out.println("----- Class Summary -----");
        System.out.printf("Average Score: %.2f\n", averageScore);
        System.out.printf("Grade Counts: A: %d, B: %d, C: %d, D: %d, F: %d\n", countsOfA, countsOfB, countsOfC, countsOfD, countsOfF);
        System.out.printf("Top Student(s): %s (%d)\n", formattedTopStudents, topScore);
    }
}