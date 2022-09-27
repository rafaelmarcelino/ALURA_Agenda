package br.com.ram.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.ram.R;
import br.com.ram.model.Student;
import br.com.ram.tools.Constants;

public class LoadDataFromStudentActivity extends AppCompatActivity {
    //Creating variables of views
    TextView textViewId;
    TextView textViewName;
    TextView textViewPhone;
    TextView textViewEmail;
    TextView textViewAge;
    TextView textViewGender;
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
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        //Inflating layout
        getMenuInflater().inflate(R.menu.activity_load_data_from_student_option_back,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_load_data_from_student_opt_back) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initVariables() {
        textViewId = findViewById(R.id.activity_load_data_from_student_tv_id);
        textViewName = findViewById(R.id.activity_load_data_from_student_tv_name);
        textViewPhone = findViewById(R.id.activity_load_data_from_student_tv_phone);
        textViewEmail = findViewById(R.id.activity_load_data_from_student_tv_email);
        textViewAge = findViewById(R.id.activity_load_data_from_student_tv_age);
        textViewGender = findViewById(R.id.activity_load_data_from_student_tv_gender);
        intent = getIntent();

    }
    private void loadDataFromRequiredStudent() {
        //Get student to be show
        if (intent.hasExtra(getString(R.string.KEY_STUDENT))) {
            //Collect student sent from activity
            final Student student = getStudentFromIntent();
            //Fill data to be updated
            fillDataOfStudentReceived(student);
        } else {
            Toast.makeText(this, "No student received from last activity", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    private Student getStudentFromIntent() {
        return (Student) intent.getSerializableExtra(getString(R.string.KEY_STUDENT));
    }
    private void fillDataOfStudentReceived(Student student) {
        //Fill the fields
        textViewId.setText(Long.toString(student.getIdStudent()));
        textViewName.setText(student.getName());
        textViewAge.setText(String.valueOf(student.getAge())+" years old");
        textViewPhone.setText(student.getPhone());
        textViewEmail.setText(student.getEmail());
        final String gender = student.getGender()== Constants.MALE?"MALE":"FEMALE";
        textViewGender.setText(gender);
    }
}