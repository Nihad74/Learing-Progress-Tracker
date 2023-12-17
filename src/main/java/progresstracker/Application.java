package progresstracker;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    private Scanner scanner;
    private static int id;

    public static Map<Integer, Student> students;

    public Application() {
        scanner = new Scanner(System.in);
        students = new LinkedHashMap<>();
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
                case "list":
                    listStudents();
                    break;
                case "add points":
                    addPoints();
                    break;
                case "find":
                    find();
                    break;
                case "statistics":
                    statistics();
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

    public void statistics() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        System.out.println("Most popular: "+ Statistics.mostPopularCourse());
        System.out.println("Least popular: "+ Statistics.leastPopularCourse());
        System.out.println("Highest activity: "+ Statistics.mostActiveCourse());
        System.out.println("Lowest activity: "+ Statistics.leastActiveCourse());
        System.out.println("Easiest course: "+ Statistics.easiestCourse());
        System.out.println("Hardest course: "+ Statistics.hardestCourse());
        String input = scanner.nextLine();
        while(!input.equals("back")){
            switch (input){
                case "java":
                    Statistics.printInformationAboutSubject("Java");
                    break;
                case "dsa":
                    Statistics.printInformationAboutSubject("DSA");
                    break;
                case "databases":
                    Statistics.printInformationAboutSubject("Databases");
                    break;
                case "spring":
                    Statistics.printInformationAboutSubject("Spring");
                    break;
                default:
                    System.out.println("Unknown course.");
                    break;
            }
            input = scanner.nextLine();
        }

    }

    public void addPoints() {
        System.out.println("Enter an id and points or 'back' to return.");
        boolean trueFormat = false;
        int [] idAndPoints;
        while(true) {
            String input = scanner.nextLine();
            if(input.equals("back"))
                return;
            try {
                idAndPoints = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
                if (Arrays.stream(idAndPoints).allMatch(i -> i >= 0) && idAndPoints.length == 5) {
                    if (students.get(idAndPoints[0]) == null) {
                        System.out.printf("No student is found for id=%d\n", idAndPoints[0]);
                    } else {
                        students.get(idAndPoints[0]).setPoints(idAndPoints[1], idAndPoints[2], idAndPoints[3], idAndPoints[4]);
                        System.out.println("Points updated.");

                    }
                } else {
                    System.out.println("Incorrect points format.");
                }
            } catch (NumberFormatException e) {
                try{
                    id = Integer.parseInt(input.split(" ")[0]);
                    System.out.println("Incorrect points format.");
                }catch(NumberFormatException ex){
                    System.out.printf("No student is found for id=%s\n", input.split(" ")[0]);
                }
            }
        }

    }

    public void listStudents() {
        if(students.isEmpty()){
            System.out.println("No students found.");
        }else{
            System.out.println("Students:");
            for(Integer id : students.keySet()){
                System.out.println(id);
            }
        }
    }

    private void addStudents() {
        System.out.println("Enter student credentials or 'back' to return.");
        int NoOfStudentsBefore = students.size();
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
            input = checkInput(studentCredentials[0], studentCredentials[1], studentCredentials[studentCredentials.length-1]);

        }
        System.out.println("Total " + (students.size() - NoOfStudentsBefore) + " students have been added.");
    }

    private boolean checkFirstName(String studentCredential) {
        return studentCredential.matches("^(?![-'])[A-Za-z]+((?!['-]['-])[A-Za-z'-])*(?<![-'])$")
                && studentCredential.length() >= 2;
    }

    private boolean checkLastName(String studentCredential) {
        return studentCredential.matches("^(?![-' ])[A-Za-z]+([-' ][A-Za-z]+|\\s[A-Za-z]+([-' ][A-Za-z]+)*)*$")
                && studentCredential.length() >= 2;
    }

    public String checkInput(String firstName, String lastName, String email) {
        if (checkFirstName(firstName)) {
            if (checkLastName(lastName)) {
                if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z0-9]+$")) {
                    if(checkUsedEmail(email)){
                        System.out.println("This email is already taken.");
                    }else {
                        students.put(id++, new Student(firstName, lastName, email));
                        System.out.println("The student has been added.");
                    }
                    return scanner.nextLine();
                } else {
                    System.out.println("Incorrect email.");
                    return scanner.nextLine();
                }
            }else{
                System.out.println("Incorrect last name.");
                return scanner.nextLine();
            }
        } else {
            System.out.println("Incorrect first name.");
            return scanner.nextLine();
        }
    }

    public boolean checkUsedEmail(String email){
        for(Student student : students.values()){
            if(student.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public void find() {
        System.out.println("Enter an id or 'back' to return.");
        String input = scanner.nextLine();
        while(!input.equals("back")){
            try {
                int id = Integer.parseInt(input);
                if(students.get(id) == null){
                    System.out.printf("No student is found for id=%d.", id);
                    input = scanner.nextLine();
                }else{
                    System.out.println(id+ " points: " + students.get(id).getPoints());
                    input = scanner.nextLine();
                }
            }catch (NumberFormatException e){
                System.out.println("Incorrect format.");
            }
        }
    }
}
