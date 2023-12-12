package progresstracker;

import java.util.Scanner;

public class Application {
    private Scanner scanner;

    public Application() {
        scanner = new Scanner(System.in);
    }

    public void run(){
        System.out.println("Learning Progress Tracker");
        String input = scanner.nextLine();
        while(!input.equals("exit")) {
            if(input.isBlank() || input.isEmpty()){
                System.out.println("No input.");
                input = scanner.next();
                continue;
            }
            switch (input){
                case "add students":
                    addStudents();
                    break;
                case "back":
                    System.out.println("Enter 'exit' to exit the program.");
                    break;
                default:
                    System.out.println("Error: unknown command!");
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye!");
    }

    private void addStudents() {
        System.out.println("Enter student credentials or 'back' to return.");
        int addedStudents = 0;
        String input = scanner.nextLine();
        while (!input.equals("back")) {
            String[] studentCredentials = input.split(" ");
            if (studentCredentials.length < 3) {
                System.out.println("Incorrect credentials.");
                input = scanner.nextLine();
                continue;
            }
            if (studentCredentials.length > 3) {
                for (int i = 1; i < studentCredentials.length - 1; i++) {
                    studentCredentials[1] += " " + studentCredentials[i];
                }
            }
            if (checkFirstName(studentCredentials[0])) {
                if (checkLastName(studentCredentials[1])) {
                    if (studentCredentials[studentCredentials.length-1].matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z0-9]+$")) {
                        System.out.println("The student has been added.");
                        addedStudents++;
                        input = scanner.nextLine();
                    } else {
                        System.out.println("Incorrect email.");
                        input = scanner.nextLine();
                    }
                }else{
                    System.out.println("Incorrect last name.");
                    input = scanner.nextLine();
                }
            } else {
                System.out.println("Incorrect first name.");
                input = scanner.nextLine();
            }
        }
        System.out.println("Total " + addedStudents + " students have been added.");
    }

    private boolean checkFirstName(String studentCredential) {
        return studentCredential.matches("^(?![-'])[A-Za-z]+((?!['-]['-])[A-Za-z'-])*(?<![-'])$") && studentCredential.length() >= 2;
    }

private boolean checkLastName(String studentCredential) {
    return studentCredential.matches("^(?![-' ])[A-Za-z]+([-' ][A-Za-z]+|\\s[A-Za-z]+([-' ][A-Za-z]+)*)*$") && studentCredential.length() >= 2;
}
}
