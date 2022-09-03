package br.com.ram.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.ram.R;
import br.com.ram.model.Student;
import br.com.ram.model.StudentDAO;

public class UpdateStudentActivity extends AppCompatActivity {
    //Creating variables of views
    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private Button buttonUpdateAndExit;
    //Creating the tool to handle student
    StudentDAO studentDAO;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting the title os app bar
        setTitle("Update data of student");
        //Loading layout
        setContentView(R.layout.activity_update_student);

        //Init variables
        initVariables();
        //Load data to update student
        loadDataToUpdateStudent();
        //Call listeners of views
        callListenersOfViews();
    }


    //Methods
    private void initVariables() {
        editTextName = findViewById(R.id.activity_update_student_et_name);
        editTextPhone = findViewById(R.id.activity_update_student_et_phone);
        editTextEmail = findViewById(R.id.activity_update_student_et_email);
        buttonUpdateAndExit = findViewById(R.id.activity_update_student_bt_update_exit);
        studentDAO = new StudentDAO();
        intent = getIntent();
    }
    private void callListenersOfViews(){
        //Button
        buttonUpdateAndExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Handle data of form
                handleDataToUpdateStudent();
                //Returning to activity that called this
                finish();

            }
        });

    }
    private void loadDataToUpdateStudent(){
        //Validation to collect student
        loadFieldsWithDataOfStudent(getStudentFromIntent());
    }
    private Student getStudentFromIntent() {
        //Validation of intent
        if (intent.hasExtra(getString(R.string.KEY_STUDENT))){
            return (Student) intent.getSerializableExtra(getString(R.string.KEY_STUDENT));
        }else{
            Toast.makeText(this, "No student to load data", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
    private void loadFieldsWithDataOfStudent(Student student) {
        //Fill the fields with data of this student
        editTextName.setText(student.getName());
        editTextPhone.setText(student.getPhone());
        editTextEmail.setText(student.getEmail());
    }
    private void handleDataToUpdateStudent(){
        //Update student with new data filled
        updateStudentWithNewData(getStudentWithDataUpdated());
    }
    private void updateStudentWithNewData(Student student) {
        //Updating student in a list
        studentDAO.updateStudent(student);
    }
    @NonNull
    private Student getStudentWithDataUpdated() {
        //Collecting student from intent
        final Student student = getStudentFromIntent();
        //Update student with new data filled
        student.setName(editTextName.getText().toString());
        student.setPhone(editTextPhone.getText().toString());
        student.setEmail(editTextEmail.getText().toString());
        return student;
    }
}