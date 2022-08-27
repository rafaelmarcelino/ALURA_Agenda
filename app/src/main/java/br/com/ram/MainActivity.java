package br.com.ram;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting the title os app bar
        setTitle("Students list");
        //Call a view in this activity
        setContentView(R.layout.activity_main);
        //Creating students
        List <String> students = new ArrayList<>(Arrays.asList("Rafael","Maria","Carmem"));
        //Searching List View
        ListView lv_students = findViewById(R.id.activity_main_lv_students);
        //Creating an adapter to send data to List View
        ArrayAdapter<String> adapter_lv_students = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,students);
        //Linking the adapter in list view
        lv_students.setAdapter(adapter_lv_students);
     }
}
