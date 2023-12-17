package progresstracker;

public class Student {

    private String firstName;
    private String lastName;
    private String email;

    private int DSApoints;
    private int JavaPoints;
    private int SpringPoints;
    private int DBpoints;

    private int DSAsubmissions;
    private int JavaSubmissions;
    private int SpringSubmissions;
    private int DBsubmissions;

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

    public void setPoints(int javaPoints, int dsaPoints, int dbPoints, int springPoints) {
        if(javaPoints > 0){
            JavaSubmissions++;
        }
        if(dsaPoints > 0){
            DSAsubmissions++;
        }
        if(dbPoints > 0){
            DBsubmissions++;
        }
        if(springPoints > 0){
            SpringSubmissions++;
        }
        setJavaPoints( getJavaPoints() + javaPoints);
        setDSApoints(getDSApoints() + dsaPoints);
        setDBpoints(getDBpoints()+ dbPoints);
        setSpringPoints(getSpringPoints()+ springPoints);
    }

    public String getPoints(){
        return "Java=%d; DSA=%d; Databases=%d; Spring=%d".formatted(getJavaPoints(), getDSApoints(),
                getDBpoints(), getSpringPoints());
    }

    public int getDSAsubmissions() {
        return DSAsubmissions;
    }

    public int getJavaSubmissions() {
        return JavaSubmissions;
    }

    public int getSpringSubmissions() {
        return SpringSubmissions;
    }

    public int getDBsubmissions() {
        return DBsubmissions;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student){
            Student student = (Student) obj;
            return student.getFirstName().equals(this.getFirstName()) &&
                    student.getLastName().equals(this.getLastName()) &&
                    student.getEmail().equals(this.getEmail());

        }
        return false;
    }
}
