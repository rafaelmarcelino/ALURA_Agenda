package br.com.ram.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.ram.R;
import br.com.ram.model.Student;
import br.com.ram.model.StudentDAO;

public class FormStudentActivity extends AppCompatActivity {
    //Creating variables of views
    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private Button buttonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting the title os app bar
        setTitle("Form to fill student's data");
        //Loading layout
        setContentView(R.layout.activity_form_student);

        //Init variables
        initVariables();
        //Call listeners of views
        callListenersOfViews();
    }

    //Methods
    private void initVariables() {
        editTextName = findViewById(R.id.activity_form_student_et_name);
        editTextPhone = findViewById(R.id.activity_form_student_et_phone);
        editTextEmail = findViewById(R.id.activity_form_student_et_email);
        buttonSave = findViewById(R.id.activity_form_student_bt_save);
    }
    private void callListenersOfViews(){
        //Button
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Handle data of form
                handleDataToAddNewStudent();
                //Returning to activity that called this
                finish();

            }
        });

    }
    private void handleDataToAddNewStudent(){
        //Getting data of the fields
        Student student = getStudentDataFilled();
        //Saving student in database
        saveStudentInDataBase(student);
    }
    private void saveStudentInDataBase(Student student) {
        //Creating the tool to save student
        StudentDAO studentDAO = new StudentDAO();
        //Saving student in a list
        studentDAO.saveStudent(student);
    }
    @NonNull
    private Student getStudentDataFilled() {
        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();
        String email = editTextEmail.getText().toString();

        //Creating new student
        Student student = new Student(name, phone, email);
        return student;
    }

}