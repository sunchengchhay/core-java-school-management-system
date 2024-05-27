package School_management;

import java.io.*;
import java.util.Scanner;

public class Login {
    private static final String FILENAME = "Role.txt";
    static File file = new File(FILENAME);

    static void initialize(){
        try(FileWriter writer = new FileWriter(FILENAME)){
            writer.write("1,Teacher\n");
            writer.write("2,Student");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayMenu(){
        System.out.println("---School Management System---");
        System.out.println("1. manage faculty");
        System.out.println("2. manage department");
        System.out.println("3. manage student");
        System.out.println("4. enroll students to department");
        System.out.println("5. manage courses");
        System.out.println("6. manage teacher");
        System.out.println("7. assign course to teachers");
        System.out.println("8. create teacher and student account");
        System.out.println("9. exit the program");
        System.out.print("choose an option above : ");
    }

    static boolean validate(String username, String password) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Account.txt"));

        String line = "";
        while ((line = br.readLine()) != null) {
            String[] arr = line.split(",");
            if (arr[2].equals(username) && arr[3].equals(password)) {
                br.close();
                return true;
            }
        }
        br.close();
        return false;
    }

    static String getRoleName(String username) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("Account.txt"));

        String line;
        String roleID = "";
        while ((line = br.readLine()) != null) {
            String[] arr = line.split(",");
            if (arr[2].equals(username)) {
                roleID = arr[1];
                break;
            }
        }
        br.close();

        br = new BufferedReader(new FileReader("Role.txt"));

        while ((line = br.readLine()) != null) {
            String[] arr = line.split(",");
            if (arr[0].equals(roleID)) {
                br.close();
                return arr[1];
            }
        }
        br.close();
        return "";
    }

    static String getName(String username, String fileName, int accountIDPosition ) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("Account.txt"));

        String line;
        String accID = "";
        while ((line = br.readLine()) != null) {
            String[] arr = line.split(",");
            if (arr[2].equals(username)) {
                accID = arr[0];
                break;
            }
        }
        br.close();

        br = new BufferedReader(new FileReader(fileName));


        while ((line = br.readLine()) != null) {
            String[] arr = line.split(",");
            if (arr[accountIDPosition].equals(accID)) {
                br.close();
                return arr[1];
            }
        }
        br.close();
        return "";
    }

    public static void main() throws IOException {
        Scanner input = new Scanner(System.in);

        //initialize Role data
        initialize();
        boolean loggedIn = false;

        while(!loggedIn){
            System.out.println("---------Login---------");
            System.out.print("Enter username: ");
            String username = input.nextLine();
            System.out.print("Enter password: ");
            String password = input.nextLine();

            if (validate(username, password)) {
                boolean exitMenu = false;
                String roleName = getRoleName(username);
                System.out.print("Hi, " + roleName + ": ");

                if (roleName.equals("Teacher")) {
                    System.out.println(getName(username, "Teacher.txt",6 ));
                }
                else {
                    System.out.println(getName(username, "Student.txt",9));
                }

                //display system menu
                while (!exitMenu) {
                    displayMenu();
                    String choice = input.nextLine();

                    switch (choice){
                        case "1"-> Manage_faculties.main();
                        case "2"-> Manage_departments.main();
                        case "3"-> Manage_students.main();
                        case "4"-> Enroll_students_into_departments.main();
                        case "5"-> Manage_courses.main();
                        case "6"-> Manage_teachers.main();
                        case "7"-> Assign_course_to_teachers.main();
                        case "8"-> CreateAccount.main();
                        case "9"-> {
                            exitMenu = true;
                            loggedIn = true;
                            System.out.println("Exiting program...");
                        }
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
            else {
                System.out.println("Invalid account number or password. Please try again...");
            }
        }
        input.close();
    }
}
