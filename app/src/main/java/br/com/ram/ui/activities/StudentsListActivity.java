package br.com.ram.ui.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.ram.R;
import br.com.ram.model.Student;
import br.com.ram.model.StudentDAO;

public class StudentsListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting the title os app bar
        setTitle("Students list");
        //Call a view in this activity
        setContentView(R.layout.lista_alunos_activity);

        //Creating students
        List <Student> students = new StudentDAO().getStudents();
        //Searching List View
        ListView lv_students = findViewById(R.id.lista_alunos_activity_lv_students);
        //Creating an adapter to send data to List View
        ArrayAdapter<Student> adapter_lv_students = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,students);
        //Linking the adapter in list view
        lv_students.setAdapter(adapter_lv_students);
     }
}
