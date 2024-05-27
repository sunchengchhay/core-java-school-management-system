package School_management;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Assign_course_to_teachers {
    private static final String FILENAME = "Course.txt";
    static final File file = new File(FILENAME);

    static void assignCourse(String courseID, String teacherID) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILENAME));

        String line = "";
        ArrayList<String> arrList = new ArrayList<>();

        // copy from file to arraylist
        while ((line = br.readLine()) != null) {
            arrList.add(line);
        }
        br.close();

        for (int i = 0; i < arrList.size(); i++) {
            String[] arr = arrList.get(i).split(",");
            if (arr[0].equals(courseID)) {
                arr[arr.length - 1] = teacherID;
                String arr2String = "";
                for (int j = 0; j < arr.length; j++) {
                    arr2String += arr[j];

                    // avoid last element have ","
                    if (j + 1 != arr.length) {
                        arr2String += ",";
                    }
                }
                arrList.set(i, arr2String);
            }
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));

        // copy arraylist to file
        for (int i = 0; i < arrList.size(); i++) {
            bw.write(arrList.get(i));

            // avoid last element have next line
            if (i + 1 != arrList.size()) {
                bw.newLine();
            }
        }
        bw.close();
    }

    static void displayCourse(String teacherID) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILENAME));

        String line = "";
        while ((line = br.readLine()) != null) {
            String[] arr = line.split(",");
            if (arr[1].equals(teacherID)) {
                System.out.println(arr[2]);
            }
        }
        br.close();
    }

    public static void main() throws IOException {
        Scanner input = new Scanner(System.in);

        //check file exist
        if(!file.exists()){
            file.createNewFile();
        }

        boolean exitMenu = false;
        String option;

        while (!exitMenu) {
            // show menu
            System.out.println("--------------Assign Course---------------");
            System.out.println("a. Assign a course to a teacher");
            System.out.println("b. Remove a course from a teacher");
            System.out.println("c. Display all courses taught by a teacher");
            System.out.println("d. Exit assign course ");
            System.out.print("please choose one option : ");

            option = input.nextLine();
            switch (option.toLowerCase()) {
                case "a" ->{
                    // tell user to input
                    System.out.print("Enter Course ID: ");
                    String inputCourseID = input.nextLine();
                    System.out.print("Enter Teacher ID: ");
                    String inputTeacherID = input.nextLine();

                    assignCourse(inputCourseID, inputTeacherID);
                }
                case "b"->{
                    // tell user to input
                    System.out.print("Enter Course ID: ");
                    String inputCourseID = input.nextLine();

                    assignCourse(inputCourseID, "null");
                }
                case "c"->{
                    System.out.print("Enter Teacher ID: ");
                    displayCourse(input.nextLine());
                }
                case "d"-> exitMenu = true;
                default -> System.out.println("Invalid option please choose again !!\n");
            }
        }
    }
}