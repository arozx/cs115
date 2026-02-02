public class Test {

    public void main () {

        Student[] students = {new Student("A", "B", 51, 10, 100), new Student("C", "D", 52, 50, 50), new Student("E", "F", 53, 1, 99)};
        System.out.println(students[2].toString());
        System.out.println(students[1].toString());
        System.out.println(students[1].averageMark());
        System.out.println(students[2].averageMark());
        students[1].setLabPartner(students[2]);
        System.out.println(students[1].getLabPartner().toString());
    }

}
