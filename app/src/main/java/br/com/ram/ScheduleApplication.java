package br.com.ram;

import android.app.Application;

import br.com.ram.model.Student;
import br.com.ram.model.StudentDAO;
import br.com.ram.tools.Constants;

public class ScheduleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        addingBundleOfStudents(2);

    }

    private void addingBundleOfStudents(int qtyOfStudents) {
        //Adding new students to test. NOT NECESSARY THIS. ONLY FOR TESTS PURPOSES!!!
        StudentDAO studentDAO = new StudentDAO();
        if (studentDAO.getStudents().size() == Constants.EMPTY) {
            for (int i = 0; i < qtyOfStudents; i++) {
                studentDAO.saveStudent(new Student("Rafael Alves Marcelino", "11 985264091", "r.marcelino@ram-automation.com"));
                studentDAO.saveStudent(new Student("Karine Alves Marcelino", "11 991764568", "k.marcelino@ram-automation.com"));
            }
        }
    }

}
