package br.com.ram.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.ram.R;
import br.com.ram.model.Student;
import br.com.ram.model.StudentDAO;

public class StudentsListActivity extends AppCompatActivity {
    //Creating variables of views
    private FloatingActionButton fabAddStudent;
    private ListView lv_students;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting the title os app bar
        setTitle("Students list");
        //Call a view in this activity
        setContentView(R.layout.lista_alunos_activity);

        //Init variables
        initVariables();
        //Call listeners of views
        callListenersOfViews();
     }

    @Override
    protected void onResume() {
        super.onResume();
        //Load all students stored
        final List<Student> allStudentsStored = getAllStudentsStored();
        //Set adapters to show all students stored
        setAdapters(allStudentsStored);

    }

    //Methods
    private void initVariables() {
        fabAddStudent = findViewById(R.id.activity_students_list_fab_add_students);
        lv_students = findViewById(R.id.activity_students_list_lv_students);
    }
    private List<Student> getAllStudentsStored(){
        //Creating students
        return new StudentDAO().getStudents();
    }
    private void setAdapters(List<Student>students){
        //Creating an adapter to send data to List View
        ArrayAdapter<Student> adapter_lv_students = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,students);
        //Linking the adapter in list view
        lv_students.setAdapter(adapter_lv_students);
    }
    private void callListenersOfViews(){
        //Floating action button
        fabAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentsListActivity.this,FormStudentActivity.class));
            }
        });


    }
}
