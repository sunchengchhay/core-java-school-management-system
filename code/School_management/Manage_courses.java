package School_management;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Manage_courses {

    private int CourseID;
    private String CourseName;
    private int Credit;
    private String Type;
    private String DepartmentID;
    private String TeacherID = "null";

    static private final Scanner cin = new Scanner(System.in);

    public Manage_courses() {
        CourseID = 0;

        boolean exitMenu = false;
        do {
            System.out.println("----- Course MENU -----");
            System.out.println("1. Create Course");
            System.out.println("2. Display Course");
            System.out.println("3. Update Course");
            System.out.println("4. Delete Course");
            System.out.println("5. Exit");
            System.out.println("----- **** -----");

            System.out.print("Input your choice: ");
            String op = cin.nextLine();
            switch (op.toLowerCase()) {
                case "a" -> createCourse();
                case "b" -> readCourse();
                case "c" -> updateCourse();
                case "d" -> deleteCourse();
                case "e" -> exitMenu = true;
                default -> System.out.println("Invalid option please choose again !!\n");
            }
        } while (!exitMenu);
    }

    public Manage_courses( String CourseName, int Credit, String Type, String DepartmentID) {
        this.CourseName = CourseName;
        this.Credit = Credit;
        this.Type = Type;
        this.DepartmentID = DepartmentID;
    }

    public void inputCourse() {
        File file = new File("Course.txt");
        File file1 = new File("Department.txt");
        File file2= new File("Teacher.txt");

        ArrayList<String> allDepartmentID = new ArrayList<>();
        ArrayList<String> allTeacherID = new ArrayList<>();

        FileReader reader;
        try {
            reader = new FileReader("Course.txt");
            Scanner myReader = new Scanner(reader);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                this.CourseID++;
            }

            reader = new FileReader("Department.txt");
            myReader = new Scanner(reader);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] datas = data.split(",");
                allDepartmentID.add(datas[0]);
            }

            reader = new FileReader("Teacher.txt");
            myReader = new Scanner(reader);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] datas = data.split(",");
                allTeacherID.add(datas[0]);
            }

            this.CourseID++;
            System.out.print("Input Course's Name: ");
            this.CourseName = cin.nextLine();
            System.out.print("Input Credit: ");
            this.Credit = cin.nextInt();
            System.out.print("Input Type: ");
            cin.nextLine();
            this.Type = cin.nextLine();
            System.out.print("Input Department ID: ");
            this.DepartmentID = cin.nextLine();
            if (!allDepartmentID.contains(DepartmentID)) {
                System.out.println("Please Input Foreign Key base on this: " + allDepartmentID);
                System.out.print("Input New Department ID: ");
                DepartmentID = cin.nextLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void createCourse() {
        File file = new File("Course.txt");

        try {

            boolean fileCreate = file.createNewFile();

            FileWriter writer = new FileWriter("Course.txt", true);
            System.out.print("Input Number of Employee: ");
            int size = cin.nextInt();
            for (int i = 0; i < size; i++) {
                cin.nextLine();
                inputCourse();
                writer.write(this.toString());
            }

            System.out.println("This Employee Successful Input into File!");
            writer.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void readCourse() {
        File file = new File("Course.txt");

        FileReader reader;
        try {

            reader = new FileReader("Course.txt");
            Scanner myReader = new Scanner(reader);
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.printf("%-10s %-15s %-20s %-15s %-15s %-15s \n","CourseID","DepartmentID","Course","Credit","Type","TeacherID");
            System.out.println("----------------------------------------------------------------------------------------");
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                String[] datas =data.split(",");
                System.out.printf("%-10s %-15s %-20s %-15s %-15s %-15s \n"
                        ,datas[0],datas[1],datas[2],datas[3],datas[4],datas[5]);

            }
            System.out.println("----------------------------------------------------------------------------------------");

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateCourse() {

        System.out.print("Input Course's ID to Update Information: ");
        String id = cin.next();

        File file = new File("Course.txt");
        File file1 = new File("Course1.txt");

        FileReader reader;
        try {

            boolean fileCreate = file1.createNewFile();
            if (!fileCreate) {
                System.out.println("File already Exist!");
            }

            FileWriter writer = new FileWriter("Course1.txt");
            reader = new FileReader("Course.txt");
            Scanner myReader = new Scanner(reader);

            boolean check = false;
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                String[] datas = data.split(",");

                if (!datas[0].substring(0, datas[0].length() - 1).contains(id)) {
                    writer.write(data + "\n");
                    check = true;
                } else {
                    CourseID = Integer.parseInt(datas[0]);
                    DepartmentID = datas[1];
                    TeacherID = datas[2];
                    System.out.print("Input Course Name: ");
                    this.CourseName = cin.nextLine();
                    System.out.print("Input Credit: ");
                    this.Credit = cin.nextInt();
                    System.out.print("Input Type: ");
                    cin.nextLine();
                    this.Type = cin.nextLine();
                    writer.write(this.toString());
                }
            }

            file.delete();
            file1.renameTo(file);
            if (check) {
                System.out.println("This Employee Successfully Update From The File!");
            } else {
                System.out.println("Can not Found this ID!");
            }
            writer.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteCourse() {

        System.out.print("enter Course ID to Delete: ");
        String id = cin.next();

        File file = new File("Course.txt");
        File file1 = new File("Course1.txt");

        FileReader reader;
        try {

            boolean fileCreate = file1.createNewFile();
            if (!fileCreate) {
                System.out.println("File already Exist!");
            }

            FileWriter writer = new FileWriter("Course1.txt");
            reader = new FileReader("Course.txt");
            Scanner myReader = new Scanner(reader);

            boolean check = false;
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                String[] datas = data.split(",");

                if (!datas[0].contains(id)) {
                    writer.write(data + "\n");
                    check = true;
                }
            }

            file.delete();
            file1.renameTo(file);
            if (check) {
                System.out.println("This Employee Successfully Delete From The File!");
            } else {
                System.out.println("Can not Found this ID!");
            }
            writer.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public String toString() {
        return CourseID +","+ DepartmentID + "," + CourseName + "," + Credit + "," + Type +","+TeacherID+"\n";
    }

    public static void main() {
        new Manage_courses();
    }
}
