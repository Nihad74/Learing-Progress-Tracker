package progresstracker;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static tracker.Application.students;

public class Statistics {
    private static final Map<String, Long> coursePopularity = new HashMap<>();

    private static final Map<String, Long> courseActivity = new HashMap<>();

    private static final Map<String, Double> courseDifficulty = new HashMap<>();

    private static final Map<String, Double> pointsPerSubject = new HashMap<>();
    public static String mostPopularCourse() {
        coursePopularity.put("Java", students.values().stream().mapToInt(Student::getJavaPoints).filter(i -> i > 0).count());
        coursePopularity.put("DSA",students.values().stream().mapToInt(Student::getDSApoints).filter(i -> i > 0).count());
        coursePopularity.put("Spring",students.values().stream().mapToInt(Student::getSpringPoints).filter(i -> i > 0).count());
        coursePopularity.put("Databases",students.values().stream().mapToInt(Student::getDBpoints).filter(i -> i > 0).count());

        return findMax(coursePopularity).stream()
                .reduce("", (a, b) -> a + b + ", ");

    }

    public static String leastPopularCourse(){
        List<String> max = findMax(coursePopularity);
        List<String> min = findMin(coursePopularity);
        if(min.contains("n/a"))
            return "n/a";
        String minResult = min.stream()
                .filter(s -> !max.contains(s))
                .reduce("", (a, b) -> a + b + ", ");
        return minResult.isEmpty() ? "n/a" : minResult;
    }

    public static String mostActiveCourse(){
        courseActivity.put("Java", students.values().stream().mapToLong(Student::getJavaSubmissions).sum());
        courseActivity.put("Databases", students.values().stream().mapToLong(Student::getDBsubmissions).sum());
        courseActivity.put("DSA", students.values().stream().mapToLong(Student::getDSAsubmissions).sum());
        courseActivity.put("Spring", students.values().stream().mapToLong(Student::getSpringSubmissions).sum());

        return findMax(courseActivity).stream()
                .reduce("", (a, b) -> a + b + ", ");
    }

    public static List<String> findMax(Map<String, Long> courseActivity) {
        Long max = courseActivity.values().stream().max(Long::compareTo).orElse(0L);
        if(max == 0)
            return List.of("n/a");
        return courseActivity.entrySet().stream()
                .filter(e -> e.getValue().equals(max))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


    public static String leastActiveCourse(){
        List<String> max = findMax(courseActivity);
        List<String> min = findMin(courseActivity);
        if(min.contains("n/a"))
            return "n/a";
        String minResult = min.stream()
                .filter(s -> !max.contains(s))
                .reduce("", (a, b) -> a + b + ", ");
        return minResult.isEmpty() ? "n/a" : minResult;
    }

    public static List<String> findMin(Map<String, Long> course) {
        Long min = course.values().stream().min(Long::compareTo).orElse(0L);
        if(min == 0)
            return List.of("n/a");
        return course.entrySet().stream()
                .filter(e -> e.getValue().equals(min))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static String easiestCourse(){
        calulatePointsPerSubject();
        courseDifficulty.put("Java",(double) pointsPerSubject.get("Java") /courseActivity.get("Java"));
        courseDifficulty.put("DSA",(double) pointsPerSubject.get("DSA") /courseActivity.get("DSA"));
        courseDifficulty.put("Spring",(double) pointsPerSubject.get("Spring") /courseActivity.get("Spring"));
        courseDifficulty.put("Databases",(double) pointsPerSubject.get("Databases") /courseActivity.get("Databases"));
        return findMaxDouble(courseDifficulty).stream()
                .reduce("", (a, b) -> a + b + ", ");
    }

    public static List<String> findMinDouble(Map<String, Double> courseDifficulty) {
        Double min = courseDifficulty.values().stream().min(Double::compareTo).orElse(0.0);
        if(min == 0 || min.isNaN())
            return List.of("n/a");
        return courseDifficulty.entrySet().stream()
                .filter(e -> e.getValue().equals(min))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static List<String> findMaxDouble(Map<String,Double> courseDifficulty){
        Double max = courseDifficulty.values().stream().max(Double::compareTo).orElse(0.0);
        if(max == 0 || max.isNaN())
            return List.of("n/a");
        return courseDifficulty.entrySet().stream()
                .filter(e -> e.getValue().equals(max))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static String hardestCourse(){
        List<String> max = findMaxDouble(courseDifficulty);
        List<String> min = findMinDouble(courseDifficulty);
        if(min.contains("n/a"))
            return "n/a";
        String minResult = min.stream()
                .filter(s -> !max.contains(s))
                .reduce("", (a, b) -> a + b + ", ");
        return minResult.isEmpty() ? "n/a" : minResult;
    }

    public static List<Student> informationAboutSubject(String subject){
        System.out.println(subject);
        System.out.println("id      points  completed");
        List<Student> bestStudents = new ArrayList<>();
        switch (subject) {
            case "Java" -> bestStudents = students.values().stream()
                    .filter(s -> s.getJavaPoints() > 0 )
                    .sorted(Comparator.comparing(Student::getJavaPoints).reversed())
                    .collect(Collectors.toList());
            case "DSA" -> bestStudents = students.values().stream()
                    .filter(s -> s.getDSApoints() > 0)
                    .sorted(Comparator.comparing(Student::getDSApoints).reversed())
                    .collect(Collectors.toList());
            case "Databases" -> bestStudents = students.values().stream()
                    .filter(s -> s.getDBpoints() > 0)
                    .sorted(Comparator.comparing(Student::getDBpoints).reversed())
                    .collect(Collectors.toList());
            case "Spring" -> bestStudents = students.values().stream()
                    .filter(s -> s.getSpringPoints() > 0)
                    .sorted(Comparator.comparing(Student::getSpringPoints).reversed())
                    .collect(Collectors.toList());

        }
         return bestStudents;

    }

    public static void printInformationAboutSubject(String subject){
        List<Student> bestStudents = informationAboutSubject(subject);
        for(int i = 0; i < bestStudents.size(); i++){
            for(Map.Entry<Integer, Student> entry: students.entrySet()){
                if(entry.getValue().equals(bestStudents.get(i))){
                    switch(subject){
                        case "Java": {
                            BigDecimal decimal = new BigDecimal(((double) entry.getValue().getJavaPoints() / 600) * 100);
                            decimal = decimal.setScale(1, BigDecimal.ROUND_HALF_UP);
                            System.out.println(entry.getKey() + "       " + entry.getValue().getJavaPoints()
                                    + "       " + decimal + "%");
                            break;
                        }
                        case "DSA": {
                            BigDecimal decimal = BigDecimal.valueOf(((double) entry.getValue().getDSApoints() / 400) * 100);
                            decimal = decimal.setScale(1, BigDecimal.ROUND_HALF_UP);
                            System.out.println(entry.getKey() + "       " + entry.getValue().getDSApoints()
                                    + "       " + decimal + "%");
                            break;
                        }
                        case "Databases": {
                            BigDecimal decimal = BigDecimal.valueOf(((double) entry.getValue().getDBpoints() / 480) * 100);
                            decimal = decimal.setScale(1, BigDecimal.ROUND_HALF_UP);
                            System.out.println(entry.getKey() + "       " + entry.getValue().getDBpoints()
                                    + "       " + decimal + "%");
                            break;
                        }
                        case "Spring":{
                            BigDecimal decimal  = BigDecimal.valueOf(((double) entry.getValue().getSpringPoints()/550)*100);
                            decimal = decimal.setScale(1, BigDecimal.ROUND_HALF_UP);
                            System.out.println(entry.getKey() + "       " + entry.getValue().getSpringPoints()
                                    + "       " +decimal + "%");
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void calulatePointsPerSubject(){
        pointsPerSubject.put("Java", students.values().stream().mapToDouble(Student::getJavaPoints).sum());
        pointsPerSubject.put("DSA", students.values().stream().mapToDouble(Student::getDSApoints).sum());
        pointsPerSubject.put("Databases", students.values().stream().mapToDouble(Student::getDBpoints).sum());
        pointsPerSubject.put("Spring", students.values().stream().mapToDouble(Student::getSpringPoints).sum());
    }

}
