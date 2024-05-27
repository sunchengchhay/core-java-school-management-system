package School_management;

import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Manage_faculties {
    private static final String FILENAME = "Faculty.txt";
    private static int facultyID = 1;
    private static HashMap<Integer,HashMap<String,String>> faculties = new HashMap<>();
    static File file = new File(FILENAME);

    //initial data in program and file
    private static void initialize(){
        HashMap<String,String> facultyData = new HashMap<>();
        facultyData.put("facultyName","Faculty of Engineering");
        facultyData.put("deanName","Nicolas Tesla");
        facultyData.put("officeNo","T405");
        faculties.put(facultyID++,facultyData);

        facultyData.put("facultyName","Faculty of Science");
        facultyData.put("deanName","Albert Einstein");
        facultyData.put("officeNo","T406");
        faculties.put(facultyID++,facultyData);

        facultyData.put("facultyName","Faculty of Education");
        facultyData.put("deanName","Uncle Roger");
        facultyData.put("officeNo","T407");
        faculties.put(facultyID++,facultyData);

        facultyData.put("facultyName","Faculty of Arts");
        facultyData.put("deanName","Cillian Murphy");
        facultyData.put("officeNo","T408");
        faculties.put(facultyID++,facultyData);

        facultyData.put("facultyName","Faculty of Nuclear");
        facultyData.put("deanName","J.Robert Oppenheimer");
        facultyData.put("officeNo","T409");
        faculties.put(facultyID++,facultyData);
        updateFile();
    }

    //update data in text file
    private static void updateFile(){
        try(FileWriter writer = new FileWriter(FILENAME)) {
            for(Map.Entry<Integer,HashMap<String,String>>temp : faculties.entrySet()){
                int facultyID = temp.getKey();
                String facultyName = temp.getValue().get("facultyName");
                String deanName = temp.getValue().get("deanName");
                String officeNo = temp.getValue().get("officeNo");
                writer.write(facultyID+","+facultyName+","+deanName+","+officeNo+"\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //find last faculty id in text file
    private static int findLastFacultyID(){
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

    // find data in text file and store in Hashmap
    private static HashMap<Integer,HashMap<String,String>> getFaculties(){
        HashMap<Integer, HashMap<String,String>> keyValueMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
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

    //display menu
    private static void displayMenu(){
        System.out.println("---------Faculty Menu----------");
        System.out.println("a. Add a new faculty");
        System.out.println("b. Search a faculty by id");
        System.out.println("c. Update a faculty");
        System.out.println("d. Delete a faculty by id");
        System.out.println("e. Display all faculty");
        System.out.println("f. exit the manage_faculty");
        System.out.print("select your option : ");
    }

    // making separator method
    public static void separator(){
        for(int i = 0 ; i < 65 ; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    //add new faculty
    private static void add_new_faculty(String facultyName, String deanName, String officeNo) {
        HashMap<String,String> facultyData = new HashMap<>();
        facultyData.put("facultyName",facultyName);
        facultyData.put("deanName",deanName);
        facultyData.put("officeNo",officeNo);
        faculties.put(facultyID++,facultyData);
        updateFile();
    }

    //search faculty by id
    private static boolean isSearchFacultyIDFound(int id){
        return faculties.containsKey(id);
    }

    //update faculty by id
    private static void updateFacultyByID(int id, String facultyName, String deanName, String officeNo) {
        faculties.get(id).replace("facultyName",facultyName);
        faculties.get(id).replace("deanName",deanName);
        faculties.get(id).replace("officeNo",officeNo);
        updateFile();
    }

    //delete faculty by id
    private static void deleteFacultyByID(int id){
        faculties.remove(id);
        updateFile();
    }

    //display all faculties
    private static void displayAllFaculties(){
        separator();
        System.out.printf("%-12s %-25s %-15s %-15s%n", "FacultyID", "FacultyName", "DeanName","OfficeNo");
        separator();
        for(Map.Entry<Integer,HashMap<String,String>>temp : faculties.entrySet()){
            int facultyID = temp.getKey();
            String facultyName = temp.getValue().get("facultyName");
            String deanName = temp.getValue().get("deanName");
            String officeNo = temp.getValue().get("officeNo");
            System.out.printf("%-12s %-25s %-15s %-15s%n", facultyID, facultyName, deanName,officeNo);
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
            facultyID = findLastFacultyID() + 1;
            faculties = getFaculties();
        }

        boolean exitMenu = false;
        String option;

        while (!exitMenu){
            displayMenu();
            option = input.nextLine();
            switch (option.toLowerCase()){
                case "a" ->{
                    System.out.print("enter new faculty name : ");
                    String facultyName = input.nextLine();
                    System.out.print("enter Dean Name : ");
                    String deanName = input.nextLine();
                    System.out.print("enter office Number : ");
                    String officeNo = input.nextLine();
                    add_new_faculty(facultyName,deanName,officeNo);
                    System.out.println("adding new faculty successfully\n");
                }
                case "b" ->{
                    System.out.print("enter faculty id to search : ");
                    try {
                        int id = input.nextInt();
                        input.nextLine();
                        if(isSearchFacultyIDFound(id)){
                            System.out.println("Search found.");
                            System.out.printf("%d  %15s  %5s  %5s\n",id,faculties.get(id).get("facultyName"),
                                    faculties.get(id).get("deanName"),faculties.get(id).get("officeNo"));
                            System.out.println();
                        }
                        else{
                            System.out.println("faculty id not exists!!\n");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a valid integer.\n");
                    }
                }
                case "c"->{
                    System.out.print("Enter faculty id to update : ");
                    try{
                        int id  = input.nextInt();
                        input.nextLine();
                        if(isSearchFacultyIDFound(id)){
                            System.out.print("Enter new faculty name to update : ");
                            String facultyName  = input.nextLine();
                            System.out.print("Enter new faculty Dean Name to update : ");
                            String deanName  = input.nextLine();
                            System.out.print("Enter new faculty office number to update : ");
                            String officeNo  = input.nextLine();
                            updateFacultyByID(id,facultyName,deanName,officeNo);
                            System.out.println("updated successfully\n");
                        }
                        else{
                            System.out.println("faculty id not exists!!\n");
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid input! Please enter a valid integer.\n");
                    }
                }
                case "d"->{
                    System.out.println("enter faculty id to delete : ");
                    try{
                        int id  = input.nextInt();
                        input.nextLine();
                        if(isSearchFacultyIDFound(id)){
                            deleteFacultyByID(id);
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
                case "e"-> displayAllFaculties();
                case "f" -> exitMenu = true;
                default -> System.out.println("Invalid option please choose again !!\n");
            }
        }
        System.out.println();
    }
}
