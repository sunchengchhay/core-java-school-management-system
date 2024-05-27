package School_management;

import java.io.*;
import java.util.Scanner;

public class Manage_teachers {

    private int TeacherID;
    private String TeacherName;
    private String Gender;
    private String DOB;
    private String PhoneNo;
    private String Address;
    private int DepartmentID;
    private final String AccountID = "null";

    private final Scanner cin = new Scanner(System.in);

    public Manage_teachers() {
        TeacherID = 0 ;
        boolean exitMenu = false;

        do{
            System.out.println("----- MENU -----");
            System.out.println("a. Create Teacher");
            System.out.println("b. Display Teacher");
            System.out.println("c. Update Teacher");
            System.out.println("d. Delete Teacher");
            System.out.println("e. Exit Manage Teacher");
            System.out.println("----- **** -----");
        
            System.out.print("please choose one option : ");
            String op= cin.nextLine();

            switch (op.toLowerCase()) {
                case "a" -> createTeacher();
                case "b" -> readTeacher();
                case "c" -> updateTeacher();
                case "d" -> deleteTeacher();
                case "e" -> exitMenu = true;
            }
        }while(!exitMenu);
    }

    public Manage_teachers(int TeacherID, String TeacherName, String Gender, String DOB, String PhoneNo, String Address, int DepartmentID) {
        this.TeacherID = TeacherID;
        this.TeacherName = TeacherName;
        this.Gender = Gender;
        this.DOB = DOB;
        this.PhoneNo = PhoneNo;
        this.Address = Address;
        this.DepartmentID = DepartmentID;
    }

    public void inputTeacher() {
        this.TeacherID++;
        System.out.print("Input Teacher's Name: ");
        TeacherName = cin.nextLine();
        System.out.print("Input Gender: ");
        Gender = cin.nextLine();
        System.out.print("Input Date of Birth: ");
        DOB = cin.nextLine();
        System.out.print("Input Phone-Number: ");
        PhoneNo = cin.nextLine();
        System.out.print("Input Address: ");
        Address = cin.nextLine();
        System.out.print("Input Department ID: ");
        this.DepartmentID = cin.nextInt();
        cin.nextLine();
    }

    public void createTeacher() {
        File file = new File("Teacher.txt");

        try {

            file.createNewFile();

            FileWriter writer = new FileWriter("Teacher.txt", true);
            System.out.print("enter number of Teacher: ");
            int size = cin.nextInt();
            for (int i = 0; i < size; i++) {
                cin.nextLine();
                inputTeacher();
                writer.write(this.toString());
            }

            System.out.println("This teacher Successful Input into File!");
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void line(){
        for(int i = 0 ; i < 110 ; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    public void readTeacher() {
        File file = new File("Teacher.txt");

        FileReader reader;
        try {

            reader = new FileReader("Teacher.txt");
            Scanner myReader = new Scanner(reader);
            System.out.println("Display all teachers");
            line();
            System.out.printf("%-10s %-20s %-8s %-15s %-12s %-10s %-13s %-13s%n",
                    "TeacherID", "TeacherName",
                    "Gender","Date Of Birth","PhoneNo","Address","DepartmentID","AccountID");
            line();
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(",");
                System.out.printf("%-10s %-20s %-8s %-15s %-12s %-10s %-13s %-13s%n",data[0],
                        data[1],data[2],data[3],data[4],data[5],data[6],data[7]);
            }
            line();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateTeacher() {

        System.out.print("Input Course's ID to Update Information: ");
        String id = cin.next();

        File file = new File("Teacher.txt");
        File file1 = new File("Teacher1.txt");

        FileReader reader;
        try {

            boolean fileCreate = file1.createNewFile();
            if (!fileCreate) {
                System.out.println("File already Exist!");
            }

            FileWriter writer = new FileWriter("Teacher1.txt");
            reader = new FileReader("Teacher.txt");
            Scanner myReader = new Scanner(reader);

            boolean check = false;
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                String[] datas = data.split("\t");

                if (!datas[0].trim().contains(id)) {
                    writer.write(data + "\n");
                    check = true;
                } else {
                    this.TeacherID = Integer.parseInt(datas[0]);
                    this.DepartmentID = Integer.parseInt(datas[datas.length - 1]);
                    System.out.print("Input Teacher's Name: ");
                    TeacherName = cin.nextLine();
                    System.out.print("Input Gender: ");
                    Gender = cin.nextLine();
                    System.out.print("Input Date of Birth: ");
                    DOB = cin.nextLine();
                    System.out.print("Input Phone-Number: ");
                    PhoneNo = cin.nextLine();
                    System.out.print("Input Address: ");
                    Address = cin.nextLine();
                    writer.write(this.toString());
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

    public void deleteTeacher() {

        System.out.print("enter course's ID to Delete: ");
        String id = cin.next();

        File file = new File("Teacher.txt");
        File file1 = new File("Teacher1.txt");

        FileReader reader;
        try {

            boolean fileCreate = file1.createNewFile();
            if (!fileCreate) {
                System.out.println("File already Exist!");
            }

            FileWriter writer = new FileWriter("Teacher1.txt");
            reader = new FileReader("Teacher.txt");
            Scanner myReader = new Scanner(reader);

            boolean check = false;
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                String[] datas = data.split("\t");

                if (!datas[0].trim().contains(id)) {
                    writer.write(data + "\n");
                    check = true;
                }
            }

            file.delete();
            file1.renameTo(file);
            if (check) {
                System.out.println("This teacher Successfully Delete From The File!");
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
        return TeacherID + "," + TeacherName + "," + Gender + "," + DOB + "," + PhoneNo + "," + Address + DepartmentID + "," + AccountID + "\n";
    }
    
    public static void main() {
        new Manage_teachers();
    }
}
