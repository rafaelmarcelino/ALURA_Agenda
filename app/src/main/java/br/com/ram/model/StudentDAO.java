package br.com.ram.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentDAO {
    //Class attribute (THIS LIST IS ONLY TO LEARN PURPOSES. THIS LIST COULD BE A DATABASE, ETC)
    private static final List<Student> students = new ArrayList<>();

    //Methods
    public void saveStudent(Student student) {
        students.add(student);
    }

    public void saveUpdatesInStudent(int position, Student studentToBeUpdated){
        students.set(position,studentToBeUpdated);
    }

    public Student getStudentByPosition (int position){
        return students.get(position);
    }

    public void removeStudent(Student studentToBeRemoved){
        students.remove(studentToBeRemoved);
    }

    public void swapStudentsByPositions(int draggedStudentPosition, int targetStudentPosition) {
        Collections.swap(students,draggedStudentPosition,targetStudentPosition);
    }

    public List<Student> getStudents(){
        return new ArrayList<>(students);
    }
}
