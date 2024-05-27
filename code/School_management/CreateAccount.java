package School_management;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateAccount {
    private static final String FILENAME = "Account.txt";
    private static final File file = new File(FILENAME);

    //find last account id in text file
    private static int findLastID(){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))){
            String line;
            String[] words = new String[5];
            while ((line = reader.readLine()) != null) {
                words = line.split(",");
            }
            if(words[0] == null || words[0].isEmpty()){
                return 0;
            }
            else{
                return Integer.parseInt(words[0]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //create account in arrayList
    static void createAcc(String username, String password, String phoneNum, int roleID)
            throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILENAME));

        String line;
        ArrayList<String> arrList = new ArrayList<>();

        // copy file to arraylist
        while ((line = br.readLine()) != null) {
            arrList.add(line);
        }
        br.close();

        String nextID = Integer.toString(findLastID() +1);

        arrList.add(nextID + "," + roleID + "," + username + "," + password + "," + phoneNum);

        BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));

        // copy arraylist to file
        for (int i = 0; i < arrList.size(); i++) {
            bw.write(arrList.get(i));

            if (i + 1 != arrList.size()) {
                bw.newLine();
            }
        }
        if(roleID == 1){
            System.out.println("account create to teacher successfully");
        } else if (roleID == 2) {
            System.out.println("account create to student successfully");
        }
        bw.close();
    }

    //update account in text file , add accountID position easy to update where the accountID in txt file
    static void updateTable(String id, String fileName, int accountIDPosition) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line;
        ArrayList<String> arrList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            arrList.add(line);
        }
        br.close();

        //add accountID to each filename
        for (int i = 0; i < arrList.size(); i++) {
            String[] arr = arrList.get(i).split(",");
            if (arr[0].equals(id)) {
                arr[accountIDPosition] = Integer.toString(findLastID());
                String str = "";
                for (int j = 0; j < arr.length; j++) {
                    str += arr[j];
                    if (j + 1 != arr.length) {
                        str += ",";
                    }
                }
                arrList.set(i, str);
                break;
            }
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

        for (int i = 0; i < arrList.size(); i++) {
            bw.write(arrList.get(i));
            if (i + 1 != arrList.size()) {
                bw.newLine();
            }
        }
        bw.close();
    }

    public static void main() throws IOException {
        Scanner input = new Scanner(System.in);
        boolean exitMenu = false;

        //check file exist
        if(!file.exists()){
            file.createNewFile();
        }

        while (!exitMenu) {
            System.out.println("--------Create Account---------");
            System.out.println("a. Create a teacher account");
            System.out.println("b. Create a student account");
            System.out.println("c. Exit create account");
            System.out.print("please one option above : ");
            String choice = input.nextLine();

            switch (choice.toLowerCase()) {
                case "a" -> {
                    System.out.print("Enter username: ");
                    String username = input.nextLine();
                    System.out.print("Enter password: ");
                    String password = input.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNum = input.nextLine();
                    System.out.print("Enter teacher ID: ");
                    String teacherID = input.nextLine();
                    createAcc(username, password, phoneNum, 1);
                    updateTable(teacherID, "Teacher.txt", 7);
                }
                case "b" -> {
                    System.out.print("Enter username: ");
                    String username = input.nextLine();
                    System.out.print("Enter password: ");
                    String password = input.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNum = input.nextLine();
                    System.out.print("Enter student ID: ");
                    String studentID = input.nextLine();
                    createAcc(username, password, phoneNum, 2);
                    updateTable(studentID, "Student.txt", 9);
                }
                case "c" -> exitMenu = true;
                default -> System.out.println("Invalid option please choose again !!\n");
            }
        }
    }
}