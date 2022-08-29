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

public class FormStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting the title os app bar
        setTitle("Form to fill student's data");
        //Loading layout
        setContentView(R.layout.activity_form_student);

        //Loading views
        EditText editTextName = findViewById(R.id.activity_form_student_et_name);
        EditText editTextPhone = findViewById(R.id.activity_form_student_et_phone);
        EditText editTextEmail = findViewById(R.id.activity_form_student_et_email);
        Button buttonSave = findViewById(R.id.activity_form_student_bt_save);

        //Setting listeners of view
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting data of the fields
                final String name = editTextName.getText().toString();
                final String phone = editTextPhone.getText().toString();
                final String email = editTextEmail.getText().toString();
                //Creating new student
                final Student student = new Student(name, phone, email);
                //Creating the tool to save student
                final StudentDAO studentDAO = new StudentDAO();
                //Saving student in a list
                studentDAO.saveStudent(student);
                //Showing how many students we have created
                Toast.makeText(FormStudentActivity.this, "Quantity of students =" +
                        Student.getQtyStudents(), Toast.LENGTH_SHORT).show();
                //Returning to student's list activity
                startActivity(new Intent(FormStudentActivity.this,StudentsListActivity.class));

            }
        });


    }
}