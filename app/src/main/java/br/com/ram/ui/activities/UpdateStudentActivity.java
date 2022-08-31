package br.com.ram.ui.activities;

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

        intent = getIntent();
    }
    private void loadDataToUpdateStudent(){
        //Creating the tool to save student
        StudentDAO studentDAO = new StudentDAO();
        //Collecting ID from intent
        long id = intent.getLongExtra("ID",-1);
        //Validation
        if (id > -1){
            //Creating a student to be updated
            Student student = studentDAO.getStudents().get(Integer.parseInt(Long.toString(id)));
            //Fill the fields with data of this student
            editTextName.setText(student.getName());
            editTextPhone.setText(student.getPhone());
            editTextEmail.setText(student.getEmail());

        }
    }
    private void handleDataToUpdateStudent(){
        //Getting data of the fields
        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();
        String email = editTextEmail.getText().toString();
        long id = intent.getLongExtra("ID", - 1);

        //Validation
        if (id > -1){
            //Creating new student
            Student student = new Student(name, phone, email);
            //Creating the tool to update student
            StudentDAO studentDAO = new StudentDAO();
            //Updating student in a list
            studentDAO.updateStudent(Integer.parseInt(Long.toString(id)), student);
        }else{
            Toast.makeText(this, "ID is not valid", Toast.LENGTH_SHORT).show();
        }

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
}