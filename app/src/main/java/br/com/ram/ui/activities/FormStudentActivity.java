package br.com.ram.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.ram.R;
import br.com.ram.model.Student;
import br.com.ram.model.StudentDAO;

public class FormStudentActivity extends AppCompatActivity {
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
    private void handleDataToAddNewStudent(){
        //Getting data of the fields
        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();
        String email = editTextEmail.getText().toString();

        //Creating new student
        Student student = new Student(name, phone, email);

        //Creating the tool to save student
        StudentDAO studentDAO = new StudentDAO();

        //Saving student in a list
        studentDAO.saveStudent(student);

        //Showing how many students we have created
        Toast.makeText(FormStudentActivity.this, "Quantity of students =" +
                Student.getQtyStudents(), Toast.LENGTH_SHORT).show();


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
}