package School_management;

import java.io.*;
import java.util.*;

public class Enroll_students_into_departments {
    private static final String FILENAME = "DepartmentEnroll.txt";
    private static final String FILENAME1 = "Department.txt";
    private static final String FILENAME2 = "Student.txt";
    private static HashMap<Integer,HashMap<String,Integer>> enroll = new HashMap<>();
    private static final HashMap<Integer,HashMap<String,String>> departments = getDepartments();
    private static final HashMap<Integer,HashMap<String,String>> students = getStudents();
    private static final File file = new File(FILENAME);
    private static int increment = 1;

    //get students from student file
    private static HashMap<Integer,HashMap<String,String>> getStudents(){
        HashMap<Integer, HashMap<String,String>> keyValueMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME2))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 10){
                    HashMap<String,String> temp = new HashMap<>();

                    String studentID = parts[0];
                    String studentName = parts[1];
                    String gender = parts[2];
                    String dob = parts[3];
                    String phone = parts[4];
                    String address = parts[5];
                    String generation = parts[6];
                    String year = parts[7];
                    String degree = parts[8];
                    String accountID = parts[9];

                    temp.put("studentName",studentName);
                    temp.put("gender",gender);
                    temp.put("DOB",dob);
                    temp.put("phoneNo",phone);
                    temp.put("address",address);
                    temp.put("generation",generation);
                    temp.put("year",year);
                    temp.put("degree",degree);
                    temp.put("accountID",accountID);

                    keyValueMap.put(Integer.parseInt(studentID), temp);
                    reader.close();
                }
                else{
                    reader.close();
                    return new HashMap<>();
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return keyValueMap;
    }

    // find data in text file and store in Hashmap
    private static HashMap<Integer,HashMap<String,String>> getDepartments(){
        HashMap<Integer, HashMap<String,String>> keyValueMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 5){
                    HashMap<String,String> temp = new HashMap<>();

                    String departmentId = parts[0];
                    String facultyID = parts[1];
                    String departmentName = parts[2];
                    String headName = parts[3];
                    String officeNo = parts[4];
                    temp.put("facultyID",facultyID);
                    temp.put("departmentName",departmentName);
                    temp.put("headName",headName);
                    temp.put("officeNo",officeNo);
                    keyValueMap.put(Integer.parseInt(departmentId), temp);
                    reader.close();
                }
                else{
                    reader.close();
                    return new HashMap<>();
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return keyValueMap;
    }

    private static void displayMenu(){
        System.out.println("---------------Enroll Student Menu---------------------");
        System.out.println("a. Enroll a student into a department");
        System.out.println("b. Remove a student from a department");
        System.out.println("c. Display all student study at given department");
        System.out.println("d. Display all department studied by given student");
        System.out.println("e. exit the enroll student");
        System.out.print("select your option : ");
    }

    // making separator method
    public static void separator(){
        for(int i = 0 ; i < 144 ; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    // making separator method
    public static void smallSeparator(){
        for(int i = 0 ; i < 40 ; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    private static void removeStudentFromDepartmentByID( int studentID , int departmentID ){
        for(Map.Entry<Integer,HashMap<String,Integer>>temp:enroll.entrySet()){
            int key = temp.getKey();
            if(temp.getValue().get("studentID") == studentID &&
                    temp.getValue().get("departmentID") == departmentID){
                enroll.remove(key);
                updateFile();
                System.out.println("remove successfully");
                return;
            }
        }
        System.out.println("student not in department!!\n");
    }

    private static boolean isDepartmentIDFound(int id){
        return departments.containsKey(id);
    }

    private static boolean isStudentIDFound(int id){
        return students.containsKey(id);
    }

    private static void enrollStudentToDepartment(int studentID , int departmentID){
        HashMap<String,Integer>enrollData = new HashMap<>();
        enrollData.put("studentID",studentID);
        enrollData.put("departmentID",departmentID);
        enroll.put(increment++,enrollData);
        updateFile();
    }

    private static boolean isStudentAlreadyEnrollment(int studentID , int departmentID){
        for(Map.Entry<Integer,HashMap<String,Integer>>temp : enroll.entrySet()){
            if(temp.getValue().get("studentID") == studentID &&
                    temp.getValue().get("departmentID") == departmentID){
                return true;
            }
        }
        return false;
    }

    private static HashMap<Integer,HashMap<String,Integer>> getEnroll(){
        HashMap<Integer,HashMap<String,Integer>> enrollment = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ( (line = reader.readLine()) != null ) {
                HashMap<String,Integer> temp = new HashMap<>();
                String[] parts = line.split(",");
                if(parts.length == 2){
                    int departmentID = Integer.parseInt(parts[0]);
                    int studentID = Integer.parseInt(parts[1]);
                    temp.put("departmentID",departmentID);
                    temp.put("studentID",studentID);
                    enrollment.put(increment++,temp);
                }
                else{
                    reader.close();
                    return new HashMap<>();
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return enrollment;
    }

    //update text file data from Hashmap
    private static void updateFile(){
        try(FileWriter writer = new FileWriter(FILENAME)) {
            for(Map.Entry<Integer,HashMap<String,Integer>>temp : enroll.entrySet()){
                int studentID = temp.getValue().get("studentID");
                int departmentID = temp.getValue().get("departmentID");
                writer.write(departmentID+","+studentID+"\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void displayStudentByDepartment( int departmentID ){
        boolean output = false;
        String departmentName = departments.get(departmentID).get("departmentName");
        System.out.println("Display student in Department "+departmentName);
        separator();
        System.out.printf("%-10s %-16s %-8s %-15s %-12s %-15s %-13s %-10s %-10s %-10s%n",
                "StudentID", "StudentName",
                "Gender","Date Of Birth","PhoneNo","Address","Generation","Year","Degree","AccountID");

        for(Map.Entry<Integer,HashMap<String,Integer>>temp : enroll.entrySet()){
            if(temp.getValue().get("departmentID") == departmentID){
                int studentID = temp.getValue().get("studentID");
                if(students.containsKey(studentID)){
                    output = true;
                    HashMap<String,String> student = students.get(studentID);
                    String studentName = student.get("studentName");
                    String gender = student.get("gender");
                    String dob = student.get("DOB");
                    String phoneNo = student.get("phoneNo");
                    String address = student.get("address");
                    String generation = student.get("generation");
                    String year = student.get("year");
                    String degree = student.get("degree");
                    String accountID = student.get("accountID");
                    separator();
                    System.out.printf("%-10s %-16s %-8s %-15s %-12s %-15s %-13s %-10s %-10s %-10s%n", studentID,studentName,
                            gender,dob,phoneNo,address,generation,year,degree,accountID);
                    System.out.println();
                }
            }
        }
        if(!output){
            System.out.println("it seems like no student data found in department " + departmentName);
        }
    }

    private static void displayDepartmentByStudentID( int studentID ){
        boolean output = false;
        String studentName = students.get(studentID).get("studentName");
        System.out.println("Display department that student : "+studentName+" learn in");
        smallSeparator();
        System.out.printf("%-16s %-20s%n", "DepartmentID", "DepartmentName");
        smallSeparator();
        for(Map.Entry<Integer,HashMap<String,Integer>>temp : enroll.entrySet()){
            if(temp.getValue().get("studentID") == studentID ){
                output = true;
                int departmentID = temp.getValue().get("departmentID");
                String departmentName = departments.get(departmentID).get("departmentName");
                System.out.printf("%-16s %-20s%n", departmentID, departmentName);
            }
        }
        if(!output){
            System.out.println("It seems like student don't study in any department.");
        }
    }

    public static void main() {
        Scanner input = new Scanner(System.in);

        // Check if the file exists or empty
        if(file.exists()) {
            enroll = getEnroll();
        }

        boolean exit = false;
        String option;

        while (!exit) {
            displayMenu();
            option = input.nextLine();
            switch (option) {
                case "a"->{
                    System.out.print("enter department id : ");
                    try{
                        int departmentID  = input.nextInt();
                        input.nextLine();
                        if(isDepartmentIDFound(departmentID)){
                            System.out.print("enter student id : ");
                            int studentID = input.nextInt();
                            input.nextLine();
                            if(isStudentIDFound(studentID)){
                                if(!isStudentAlreadyEnrollment(studentID,departmentID)){
                                    enrollStudentToDepartment(studentID,departmentID);
                                    System.out.println("enroll student successfully");
                                }
                                else System.out.println("Student already enrolled to department id = "+departmentID+".\n");
                            }
                            else System.out.println("Student ID not exist!!\n");
                        }
                        else System.out.println("Department ID not exists!!\n");
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid input! Please enter a valid integer.\n");
                    }
                }
                case "b"->{
                    System.out.print("enter student id : ");
                    try{
                        int studentID = input.nextInt();
                        input.nextLine();
                        if(isStudentIDFound(studentID)){
                            System.out.print("enter department id  : ");
                            int departmentID = input.nextInt();
                            input.nextLine();
                            if(isDepartmentIDFound(departmentID)){
                                removeStudentFromDepartmentByID(studentID,departmentID);
                            }else System.out.println("department id not exists!!");
                        }else System.out.println("Student id not exists!!");
                    }catch (InputMismatchException e){
                        System.out.println("Invalid input! Please enter a valid integer.\n");
                    }
                }
                case "c"->{
                    System.out.print("enter department id : ");
                    try{
                        int departmentID = input.nextInt();
                        input.nextLine();
                        if(isDepartmentIDFound(departmentID)){
                            displayStudentByDepartment(departmentID);
                            System.out.println();
                        }else System.out.println("Department not exist!!");
                    }catch (InputMismatchException e){
                        System.out.println("Invalid input! Please enter a valid integer.\n");
                    }
                }
                case "d"-> {
                    System.out.print("enter student id : ");
                    try{
                        int studentID = input.nextInt();
                        input.nextLine();
                        if(isStudentIDFound(studentID)){
                            displayDepartmentByStudentID(studentID);
                            System.out.println();
                        }else System.out.println("student not exist!!");
                    }catch (InputMismatchException e){
                        System.out.println("Invalid input! Please enter a valid integer.\n");
                    }
                }
                case "e"-> exit = true;
                default -> System.out.println("Invalid option please choose again !!\n");
            }
        }
    }
}
