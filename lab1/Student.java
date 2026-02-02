public class Student {

    private String firstName;
    private String lastName;
    private int studentNumber;
    private double courseWorkMark;
    private double examMark;
    private Student labPartner;

    Student (String firstName, String lastName, int studentNumber, double courseWorkMark, double examMark) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.courseWorkMark = courseWorkMark;
        this.examMark = examMark;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return lastName;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setFirstName(String name) {
        firstName = name;
    }

    public void setLastName(String name) {
        lastName = name;
    }

    public void setStudentNumber(int number) {
        studentNumber = number;
    }

    public String getFullName() {
        return firstName + lastName;
    }

    public String toString() {
        return "Name: " + firstName + " " + lastName + "Student No: " + studentNumber;
    }

    public double averageMark () {
        return (examMark + courseWorkMark) / 2;
    }

    public void setLabPartner (Student partner) {
        labPartner = partner;
    }

    public Student getLabPartner () {
            return labPartner;
    }
}
