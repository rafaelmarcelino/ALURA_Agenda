package br.com.ram.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.ram.R;
import br.com.ram.model.Student;

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
                String name = editTextName.getText().toString();
                String phone = editTextPhone.getText().toString();
                String email = editTextEmail.getText().toString();
                //Creating new student at list
                Student student = new Student(name, phone, email);

                Toast.makeText(FormStudentActivity.this, student.getName()
                        + " - "
                        + student.getPhone()
                        + " - "
                        + student.getEmail()
                        + " - "
                        + Student.getQtyStudents(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}