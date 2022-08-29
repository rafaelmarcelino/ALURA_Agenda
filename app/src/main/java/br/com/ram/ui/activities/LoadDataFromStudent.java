package br.com.ram.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.ram.R;
import br.com.ram.model.Student;
import br.com.ram.model.StudentDAO;

public class LoadDataFromStudent extends AppCompatActivity {
    //Creating variables of views
    TextView textViewName;
    TextView textViewPhone;
    TextView textViewEmail;
    Button buttonBack;
    //Creating variables
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data_from_student);
        //Set title
        setTitle("Student data viewer");
        //Init variables
        initVariables();
        //Load data from required student
        loadDataFromRequiredStudent();
        //Call listener of views
        callListenersOfViews();
    }

    private void initVariables() {
        textViewName = findViewById(R.id.activity_load_data_from_student_tv_name);
        textViewPhone = findViewById(R.id.activity_load_data_from_student_tv_phone);
        textViewEmail = findViewById(R.id.activity_load_data_from_student_tv_email);
        buttonBack = findViewById(R.id.activity_load_data_from_student_bt_back);

        intent = getIntent();

    }
    private void loadDataFromRequiredStudent() {
        //Get Id of student
        long id = intent.getLongExtra("ID", -1);
        //Check if id is valid
        if (id > -1) {
            fillDataOfStudentWithId(id);
        } else {
            Toast.makeText(this, "ID is not valid", Toast.LENGTH_SHORT).show();
        }
    }
    private void fillDataOfStudentWithId(long id) {
        //Get data from buffer of students
        final StudentDAO studentDAO = new StudentDAO();
        final List<Student> students = studentDAO.getStudents();
        //Fill the fields
        textViewName.setText(students.get(Integer.parseInt(Long.toString(id))).getName());
        textViewPhone.setText(students.get(Integer.parseInt(Long.toString(id))).getPhone());
        textViewEmail.setText(students.get(Integer.parseInt(Long.toString(id))).getEmail());
    }
    private void callListenersOfViews() {
        //Button
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}