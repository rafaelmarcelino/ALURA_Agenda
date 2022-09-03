package br.com.ram.model;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    //Class attribute (THIS LIST IS ONLY TO LEARN PURPOSES. THIS LIST COULD BE A DATABASE, ETC)
    private static List<Student> students = new ArrayList<>();

    //Methods
    public void saveStudent(Student student) {
        students.add(student);
    }

    public void updateStudent ( Student studentToBeUpdated){
        students.set(studentToBeUpdated.getPosition(),studentToBeUpdated);
    }

    public List<Student> getStudents(){
        return new ArrayList<>(students);
    }

    public Student getStudentByPosition (int position){
        return students.get(position);
    }

}
