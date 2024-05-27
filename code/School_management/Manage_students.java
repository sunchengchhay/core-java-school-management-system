package School_management;

import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Manage_students {
    private static final String FILENAME = "Student.txt";
    private static HashMap<Integer,HashMap<String,String>> students = new HashMap<>();
    private static int studentID = 1;
    static File file = new File(FILENAME);

    private static void initialize(){
        HashMap<String,String> studentData = new HashMap<>();
        studentData.put("studentName","Sethy Vitra");
        studentData.put("gender","Male");
        studentData.put("DOB","01-01-1998");
        studentData.put("phoneNo","099788212");
        studentData.put("address","Takhmao");
        studentData.put("generation","8");
        studentData.put("year","2");
        studentData.put("degree","Bachelor");
        studentData.put("accountID","null");
        students.put(studentID++,studentData);

        studentData.put("studentName","Son Chengchhay");
        studentData.put("gender","Male");
        studentData.put("DOB","30-04-2000");
        studentData.put("phoneNo","010752924");
        studentData.put("address","Takhmao");
        studentData.put("generation","8");
        studentData.put("year","2");
        studentData.put("degree","Bachelor");
        studentData.put("accountID","null");
        students.put(studentID++,studentData);

        studentData.put("studentName","Seng Rathanavibol");
        studentData.put("gender","Male");
        studentData.put("DOB","02-02-2004");
        studentData.put("phoneNo","087123443");
        studentData.put("address","Phnom Penh");
        studentData.put("generation","8");
        studentData.put("year","2");
        studentData.put("degree","Bachelor");
        studentData.put("accountID","null");
        students.put(studentID++,studentData);

        studentData.put("studentName","Phearum Sivmeng");
        studentData.put("gender","Male");
        studentData.put("DOB","11-02-2000");
        studentData.put("phoneNo","010988821");
        studentData.put("address","Phnom Penh");
        studentData.put("generation","8");
        studentData.put("year","2");
        studentData.put("degree","Bachelor");
        studentData.put("accountID","null");
        students.put(studentID++,studentData);
        updateFile();
    }

    private static void updateFile(){
        try(FileWriter writer = new FileWriter(FILENAME)) {
            for(Map.Entry<Integer,HashMap<String,String>>temp : students.entrySet()){
                int studentID = temp.getKey();
                String studentName = temp.getValue().get("studentName");
                String gender = temp.getValue().get("gender");
                String DOB = temp.getValue().get("DOB");
                String phoneNo = temp.getValue().get("phoneNo");
                String address = temp.getValue().get("address");
                String generation = temp.getValue().get("generation");
                String year = temp.getValue().get("year");
                String degree = temp.getValue().get("degree");
                String accountID = temp.getValue().get("accountID");
                writer.write(studentID+","+studentName+","
                        +gender+","+DOB+","+phoneNo+","+address+","+generation+","
                        +year+","+degree+","+accountID+"\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //display menu
    private static void displayMenu(){
        System.out.println("---------Student Menu----------");
        System.out.println("a. Add a new Student");
        System.out.println("b. Search a student by id");
        System.out.println("c. Update a student by id");
        System.out.println("d. Delete a student by id");
        System.out.println("e. Display all student");
        System.out.println("f. exit the manage student");
        System.out.print("select your option : ");
    }

    // making separator method
    public static void separator(){
        for(int i = 0 ; i < 144 ; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    //get students from student file
    private static HashMap<Integer,HashMap<String,String>> getStudents(){
        HashMap<Integer, HashMap<String,String>> keyValueMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
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

    //display all faculties
    private static void displayAllStudents(){
        separator();
        System.out.printf("%-10s %-16s %-8s %-15s %-12s %-15s %-13s %-7s %-10s %-10s%n",
                "StudentID", "StudentName",
                "Gender","Date Of Birth","PhoneNo","Address","Generation","Year","Degree","AccountID");
        separator();
        for(Map.Entry<Integer,HashMap<String,String>>temp : students.entrySet()){
            int studentID = temp.getKey();
            String studentName = temp.getValue().get("studentName");
            String gender = temp.getValue().get("gender");
            String dob = temp.getValue().get("DOB");
            String phoneNo = temp.getValue().get("phoneNo");
            String address = temp.getValue().get("address");
            String generation = temp.getValue().get("generation");
            String year = temp.getValue().get("year");
            String degree = temp.getValue().get("degree");
            String accountID = temp.getValue().get("accountID");
            System.out.printf("%-10s %-16s %-8s %-15s %-12s %-15s %-13s %-7s %-10s %-10s%n", studentID,studentName,
                    gender,dob,phoneNo,address,generation,year,degree,accountID);
        }
        System.out.println();
    }

    //display all students
    private static void displayStudentByID(int id){
        HashMap<String,String> temp = students.get(id);
        String studentName = temp.get("studentName");
        String gender = temp.get("gender");
        String dob = temp.get("DOB");
        String phoneNo = temp.get("phoneNo");
        String address = temp.get("address");
        String generation = temp.get("generation");
        String year = temp.get("year");
        String degree = temp.get("degree");
        String accountID = temp.get("accountID");

        separator();
        System.out.printf("%-10s %-16s %-8s %-15s %-12s %-15s %-13s %-10s %-10s %-10s%n",
                "StudentID", "StudentName",
                "Gender","Date Of Birth","PhoneNo","Address","Generation","Year","Degree","AccountID");
        separator();
        System.out.printf("%-10s %-16s %-8s %-15s %-12s %-15s %-13s %-10s %-10s %-10s%n", id,studentName,
                gender,dob,phoneNo,address,generation,year,degree,accountID);
        System.out.println();
    }

    private static void update_student(int id , String name , String gender , String dob , String phone , String address,
                                    String generation , String year , String degree ){
        HashMap<String,String> studentData = new HashMap<>();
        studentData.put("studentName",name);
        studentData.put("gender",gender);
        studentData.put("DOB",dob);
        studentData.put("phoneNo",phone);
        studentData.put("address",address);
        studentData.put("generation",generation);
        studentData.put("year",year);
        studentData.put("degree",degree);
        studentData.put("accountID","null");
        students.put(id,studentData);
        System.out.println("adding and updating student successfully");
        updateFile();
    }

    //find last faculty id in text file
    private static int findLastStudentID(){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))){
            String line;
            String[] words = new String[11];
            while ((line = reader.readLine()) != null) {
                words = line.split(",");
            }
            if(words[0] == null || words[0].isEmpty()){
                reader.close();
                return 0;
            }
            else{
                reader.close();
                return Integer.parseInt(words[0]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isStudentIDFound(int id){
        return students.containsKey(id);
    }

    private static void delete_student(int id){
        students.remove(id);
        updateFile();
    }

    public static void main() {
        Scanner input = new Scanner(System.in);

        // Check if the file exists or empty
        if(!file.exists()){
            initialize();
        }else{
            students = getStudents();
            studentID = findLastStudentID()+1;
        }

        boolean exitMenu = false;
        String option;

        while (!exitMenu) {
            displayMenu();
            option = input.nextLine();
            switch (option.toLowerCase()) {
                case "a"->{
                        System.out.print("enter student name : ");
                        String name = input.nextLine();
                        System.out.print("enter student gender (Male/Female) : ");
                        String gender = input.nextLine();
                        System.out.print("enter student date of birth (DD-MM-YY) : ");
                        String DOB = input.nextLine();
                        System.out.print("enter student phone number : ");
                        String phone = input.nextLine();
                        System.out.print("enter student address(province) : ");
                        String address = input.nextLine();
                        System.out.print("enter student generation : ");
                        String generation = input.nextLine();
                        System.out.print("enter student year of current study : ");
                        String year = input.nextLine();
                        System.out.print("enter student degree : ");
                        String degree = input.nextLine();
                        update_student(studentID++,name,gender,DOB,phone,address,generation,year,degree);
                }
                case "b"->{
                    System.out.print("enter student id : ");
                    try{
                        int id  = input.nextInt();
                        input.nextLine();
                        if(isStudentIDFound(id)){
                            System.out.println("Search found!! Student id = "+id);
                            displayStudentByID(id);
                        }else{
                            System.out.println("Student id not exist!!");
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid input! Please enter a valid integer.\n");
                    }
                }
                case "c"->{
                    System.out.print("enter student id to update : ");
                    try{
                        int id  = input.nextInt();
                        input.nextLine();
                        if(isStudentIDFound(id)){
                                System.out.print("enter new/old student name : ");
                                String name = input.nextLine();
                                System.out.print("enter new/old student gender (Male/Female) : ");
                                String gender = input.nextLine();
                                System.out.print("enter new/old student date of birth (DD-MM-YY) : ");
                                String DOB = input.nextLine();
                                System.out.print("enter new/old student phone number : ");
                                String phone = input.nextLine();
                                System.out.print("enter new/old student address(province) : ");
                                String address = input.nextLine();
                                System.out.print("enter new/old student generation : ");
                                String generation = input.nextLine();
                                System.out.print("enter new/old student year of current study : ");
                                String year = input.nextLine();
                                System.out.print("enter new/old student degree : ");
                                String degree = input.nextLine();
                                update_student(id,name,gender,DOB,phone,address,generation,year,degree);
                                System.out.println("update student successfully");
                        }else{
                            System.out.println("Student id not exist!!");
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid input! Please enter a valid integer.\n");
                    }
                }
                case "d"->{
                    System.out.print("enter student id : ");
                    try{
                        int id  = input.nextInt();
                        input.nextLine();
                        if(isStudentIDFound(id)){
                            delete_student(id);
                            System.out.println("delete successfully");
                        }else{
                            System.out.println("Student id not exist!!");
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid input! Please enter a valid integer.\n");
                    }
                }
                case "e"->{
                    System.out.println("Student Table");
                    displayAllStudents();
                    System.out.println();
                }
                case "f"-> exitMenu = true;
                default -> System.out.println("Invalid option please choose again !!\n");
            }
        }
    }
}
