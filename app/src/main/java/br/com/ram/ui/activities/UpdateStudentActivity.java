package br.com.ram.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.ram.tools.Constants;
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
        //Validation to collect student
        if (intent.hasExtra(getString(R.string.KEY_STUDENT))){
            final Student student = (Student) intent.getSerializableExtra(getString(R.string.KEY_STUDENT));
            //Fill the fields with data of this student
            editTextName.setText(student.getName());
            editTextPhone.setText(student.getPhone());
            editTextEmail.setText(student.getEmail());
        }else{
            Toast.makeText(this, "No student to load data", Toast.LENGTH_SHORT).show();
        }
    }
    private void handleDataToUpdateStudent(){
        //Getting data of the fields
        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();
        String email = editTextEmail.getText().toString();
        //Collecting new data to student and updating
        final Student student = new Student(name, phone, email);

        //Validation
        if (intent.hasExtra(getString(R.string.KEY_ID_STUDENT))) {
            if (intent.getLongExtra(getString(R.string.KEY_ID_STUDENT), Constants.DEFAULT_ERROR_INDEX) > Constants.WRONG_INDEX) {
                //Getting ID of student
                long id = intent.getLongExtra(getString(R.string.KEY_ID_STUDENT), Constants.START_INDEX);
                //Creating the tool to update student
                StudentDAO studentDAO = new StudentDAO();
                //Updating student in a list
                studentDAO.updateStudent(id, student);
            } else {
                Toast.makeText(this, "WRONG ID", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "No ID to load data", Toast.LENGTH_SHORT).show();
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