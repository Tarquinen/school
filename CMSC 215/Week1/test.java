// package Week1;

public class test {
        public static void main(String[] args) {

            m(new GraduateStudent());
            m(new Student());
            m(new Person());
            m(new Object());
    } 
        
    public static void m(Student x) {
            System.out.println(x.toString());
    }
}
        
class GraduateStudent extends Student {

}
        
class Student extends Person {
    @Override
    public String toString() {
        return "Student";
    }
}
    
class Person extends Object {
    @Override
    public String toString() {
        return "Person";
    }

}
