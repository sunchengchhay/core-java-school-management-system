package School_management;

import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Manage_departments {
    private static final String FILENAME = "Department.txt";
    private static final String FILENAME1 = "Faculty.txt";
    private static final HashMap<Integer,HashMap<String,String>> faculties = getFaculties();
    private static HashMap<Integer,HashMap<String,String>> departments = new HashMap<>();
    private static int departmentID = 1;
    static File file = new File(FILENAME);

    //initial data in program and file
    private static void initialize(){
        HashMap<String,String> departmentData = new HashMap<>();
        departmentData.put("facultyID","1");
        departmentData.put("departmentName","IT Engineering");
        departmentData.put("headName","Oppenheimer");
        departmentData.put("officeNo","T405");
        departments.put(departmentID++,departmentData);

        departmentData.put("facultyID","1");
        departmentData.put("departmentName","FOOD Engineering");
        departmentData.put("headName","Sun Chengchhay");
        departmentData.put("officeNo","T401");
        departments.put(departmentID++,departmentData);

        departmentData.put("facultyID","1");
        departmentData.put("departmentName","Telecommunication Engineering");
        departmentData.put("headName","Mark Zuckerberg");
        departmentData.put("officeNo","T001");
        departments.put(departmentID++,departmentData);

        departmentData.put("facultyID","1");
        departmentData.put("departmentName","Data Science and Engineering");
        departmentData.put("headName","Elon Musk");
        departmentData.put("officeNo","T101");
        departments.put(departmentID++,departmentData);

        departmentData.put("facultyID","2");
        departmentData.put("departmentName","Computer Science");
        departmentData.put("headName","Lionel Messi");
        departmentData.put("officeNo","T201");
        departments.put(departmentID++,departmentData);

        departmentData.put("facultyID","5");
        departmentData.put("departmentName","Weapon Study");
        departmentData.put("headName","Zhao Zilong");
        departmentData.put("officeNo","T001");
        departments.put(departmentID++,departmentData);
        updateFile();
    }

    //update data in text file
    private static void updateFile(){
        try(FileWriter writer = new FileWriter(FILENAME)) {
            for(Map.Entry<Integer,HashMap<String,String>>temp : departments.entrySet()){
                int departmentId = temp.getKey();
                String facultyID = temp.getValue().get("facultyID");
                String facultyName = temp.getValue().get("departmentName");
                String headName = temp.getValue().get("headName");
                String officeNo = temp.getValue().get("officeNo");
                writer.write(departmentId+","+facultyID+","+facultyName+","+headName+","+officeNo+"\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // find data in text file and store in Hashmap
    private static HashMap<Integer,HashMap<String,String>> getFaculties(){
        HashMap<Integer, HashMap<String,String>> keyValueMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 4){
                    HashMap<String,String> temp = new HashMap<>();

                    String facultyID = parts[0];
                    String facultyName = parts[1];
                    String deanName = parts[2];
                    String officeNo = parts[3];
                    temp.put("facultyName",facultyName);
                    temp.put("deanName",deanName);
                    temp.put("officeNo",officeNo);
                    keyValueMap.put(Integer.parseInt(facultyID), temp);
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

    //find last faculty id in text file
    private static int findLastDepartmentID(){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))){
            String line;
            String[] words = new String[5];
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

    private static boolean isFacultyIDExist(int id){
        return faculties.containsKey(id);
    }

    // find data in text file and store in Hashmap
    private static HashMap<Integer,HashMap<String,String>> getDepartments(){
        HashMap<Integer, HashMap<String,String>> keyValueMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
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

    //display menu
    private static void displayMenu(){
        System.out.println("---------Department Menu----------");
        System.out.println("a. Add a new department");
        System.out.println("b. Search a department by id");
        System.out.println("c. Update a department");
        System.out.println("d. Delete a department by id");
        System.out.println("e. Display all department belong to a faculty");
        System.out.println("f. exit the manage department");
        System.out.print("select your option : ");
    }

    // making separator method
    public static void separator(){
        for(int i = 0 ; i < 70 ; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    //add new faculty
    private static void add_new_department(String facultyID, String departmentName ,String headName, String officeNo) {
        HashMap<String,String> departmentData = new HashMap<>();
        departmentData.put("facultyID",facultyID);
        departmentData.put("departmentName",departmentName);
        departmentData.put("headName",headName);
        departmentData.put("officeNo",officeNo);
        departments.put(departmentID++,departmentData);
        updateFile();
    }

    //search faculty by id
    private static boolean isSearchDepartmentIDFound(int id){
        return departments.containsKey(id);
    }

    //update faculty by id
    private static void updateDepartmentByID(int id, String facultyID,String departmentName, String headName, String officeNo) {
        departments.get(id).replace("facultyID",facultyID);
        departments.get(id).replace("departmentName",departmentName);
        departments.get(id).replace("headName",headName);
        departments.get(id).replace("officeNo",officeNo);
        updateFile();
    }

    //delete faculty by id
    private static void deleteDepartmentByID(int id){
        departments.remove(id);
        updateFile();
    }

    //display all faculties
    private static void displayAllDepartmentsBelongToAFaculty(String facultyId){
        boolean noOutput = false;
        separator();
        System.out.printf("%-13s %-10s %-20s %-15s %-10s%n", "DepartmentID", "FacultyID", "DepartmentName","HeadName","officeNo");
        separator();
        for(Map.Entry<Integer,HashMap<String,String>>temp : departments.entrySet()){
            int departmentID = temp.getKey();
            String facultyID = temp.getValue().get("facultyID");
            String departmentName = temp.getValue().get("departmentName");
            String headName = temp.getValue().get("headName");
            String officeNo = temp.getValue().get("officeNo");
            if(facultyId.equals(facultyID)){
                noOutput = true;
                System.out.printf("%-13s %-10s %-20s %-15s %-10s%n", departmentID, facultyID, departmentName,headName,officeNo);
            }
        }
        if( !noOutput ){
            System.out.println("No data exist with id = " + facultyId);
        }
        System.out.println();
    }

    //main method
    public static void main() {
        Scanner input = new Scanner(System.in);

        // Check if the file exists or empty
        if(!file.exists()) {
            initialize();
        }
        else{
            departmentID = findLastDepartmentID() + 1;
            departments = getDepartments();
        }

        boolean exitMenu = false;
        String option;

        while (!exitMenu){
            displayMenu();
            option = input.nextLine();
            switch (option.toLowerCase()){
                case "a" ->{
                    System.out.print("Enter faculty id of the department : ");
                    String facultyID = input.nextLine();
                    if(isFacultyIDExist(Integer.parseInt(facultyID))){
                        System.out.print("Enter the department name : ");
                        String departmentName = input.nextLine();
                        System.out.print("Enter Head Department name : ");
                        String headName = input.nextLine();
                        System.out.print("Enter the department office number : ");
                        String officeNo = input.nextLine();
                        add_new_department(facultyID,departmentName,headName,officeNo);
                        System.out.println("adding new faculty successfully\n");
                    }
                    else{
                        System.out.println("Faculty id not found in faculty table!!");
                    }
                }
                case "b" ->{
                    System.out.print("enter faculty id to search : ");
                    try {
                        int id = input.nextInt();
                        input.nextLine();
                        if(isSearchDepartmentIDFound(id)){
                            System.out.println("Search found.");
                            System.out.printf("%d  %15s  %15s  %5s %5s\n",id,departments.get(id).get("facultyID"),
                                    departments.get(id).get("departmentName"),departments.get(id).get("headName"),
                            departments.get(id).get("officeNo"));
                            System.out.println();
                        }
                        else{
                            System.out.println("department id not exists!!\n");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a valid integer.\n");
                    }
                }
                case "c"->{
                    System.out.print("Enter department id to update : ");
                    try{
                        int id  = input.nextInt();
                        input.nextLine();
                        if(isSearchDepartmentIDFound(id)){
                            System.out.print("Enter old/new faculty id to update : ");
                            String facultyID  = input.nextLine();
                            System.out.print("Enter new department name to update : ");
                            String departmentName  = input.nextLine();
                            System.out.print("Enter old/new Head department name : ");
                            String headName = input.nextLine();
                            System.out.print("Enter old/new department office number : ");
                            String officeNo  = input.nextLine();
                            updateDepartmentByID(id,facultyID,departmentName,headName,officeNo);
                            System.out.println("updated successfully\n");
                        }
                        else{
                            System.out.println("department id not exists!!\n");
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid input! Please enter a valid integer.\n");
                    }
                }
                case "d"->{
                    System.out.println("enter department id to delete : ");
                    try{
                        int id  = input.nextInt();
                        input.nextLine();
                        if(isSearchDepartmentIDFound(id)){
                            deleteDepartmentByID(id);
                            System.out.println("delete successfully\n");
                        }
                        else{
                            System.out.println("faculty id not exists!!\n");
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid input! Please enter a valid integer.\n");
                    }
                }
                case "e"-> {
                    System.out.print("Enter Faculty id : ");
                    String facultyID = input.nextLine();
                    System.out.println();
                    System.out.println("Department Table Search By Faculty ID = "+ facultyID);
                    displayAllDepartmentsBelongToAFaculty(facultyID);
                }
                case "f" -> exitMenu = true;
                default -> System.out.println("Invalid option please choose again !!\n");
            }
        }
        System.out.println();
    }
}
