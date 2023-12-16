package progresstracker;

public class Student {

    private String firstName;
    private String lastName;
    private String email;

    private int DSApoints;
    private int JavaPoints;
    private int SpringPoints;
    private int DBpoints;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDSApoints() {
        return DSApoints;
    }

    public void setDSApoints(int DSApoints) {
        this.DSApoints = DSApoints;
    }

    public int getJavaPoints() {
        return JavaPoints;
    }

    public void setJavaPoints(int javaPoints) {
        JavaPoints = javaPoints;
    }

    public int getSpringPoints() {
        return SpringPoints;
    }

    public void setSpringPoints(int springPoints) {
        SpringPoints = springPoints;
    }

    public int getDBpoints() {
        return DBpoints;
    }

    public void setDBpoints(int DBpoints) {
        this.DBpoints = DBpoints;
    }

    public void setPoints(int idAndPoint, int idAndPoint1, int idAndPoint2) {
        setJavaPoints( getJavaPoints() + idAndPoint);
        setDSApoints(getDSApoints() + idAndPoint1);
        setDBpoints(getDBpoints()+ idAndPoint2);
        setSpringPoints(getSpringPoints()+ idAndPoint2);
    }

    public String getPoints(){
        return "Java=%d; DSA=%d; Databases=%d; Spring=%d".formatted(getJavaPoints(), getDSApoints(),
                getDBpoints(), getSpringPoints());
    }
}
