package br.com.ram.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.ram.adapters.StudentsRecyclerViewAdapter;
import br.com.ram.interfaces.OnItemClickListener;
import br.com.ram.tools.Constants;
import br.com.ram.R;
import br.com.ram.model.Student;
import br.com.ram.model.StudentDAO;

public class StudentsListActivity extends AppCompatActivity {
    //Creating variables of views
    private FloatingActionButton fabAddStudent;
    private RecyclerView rv_students;
    //Creating a custom adapter to send data to recycler view
    StudentsRecyclerViewAdapter studentsRecyclerViewAdapter;
    //Creating tool to handle students
    StudentDAO studentDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting the title os app bar
        setTitle("RAM Automation");
        //Call a view in this activity
        setContentView(R.layout.activity_students_list);
        //Init variables
        initVariables();
        //Call listeners of views
        callListenersOfViewsAndAdapters();
     }

    @Override
    protected void onResume() {
        super.onResume();
        //Update adapters to show all students stored
        updatingDataOfAdapter(studentDAO.getStudents());
        //Showing how many students we have created if greater than 0
        if (studentDAO.getStudents().size() > Constants.EMPTY){
            Toast.makeText(this, "Quantity of students = "+ studentDAO.getStudents().size(), Toast.LENGTH_SHORT).show();
        }
    }

    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //Inflating layout from menu
        getMenuInflater().inflate(R.menu.activity_students_list_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //Get feedback from adapter data
        final AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //Check action to be performed
        if (item.getItemId()== R.id.activity_students_list_ctx_menu_update_student){
            //Update action
            startUpdateStudentAction(menuInfo.position);
        }else if(item.getItemId()== R.id.activity_students_list_ctx_menu_remove_student){
            //Call dialog to confirm remove action
            callDialogs(menuInfo.position);
            //Remove action
            //startRemoveStudentAction(menuInfo.position);
        }
        return super.onContextItemSelected(item);

    }*/

    //Methods
    private void initVariables() {
        //Init views
        fabAddStudent = findViewById(R.id.activity_students_list_fab_add_student);
        rv_students = findViewById(R.id.activity_students_list_rv_students);
        //Configure recycler view
        configureRecyclerView();
        //Register a context menu to this list view
        //registerForContextMenu(rv_students);
        //Init tool to handle student
        studentDAO = new StudentDAO();
    }

    private void configureRecyclerView() {
        //Init adapters
        studentsRecyclerViewAdapter = new StudentsRecyclerViewAdapter(this,R.layout.cell_rv_students);
        //Set the adapter
        rv_students.setAdapter(studentsRecyclerViewAdapter);
    }

    @SuppressWarnings("Convert2Lambda")
    private void callListenersOfViewsAndAdapters(){
        //Floating action button
        fabAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityToFillDataOfStudent();
            }
        });

        studentsRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Student student) {
                openActivityToShowDataOfStudent(student);
            }
        });
    }

    private List<Student> getAllStudentsStored(){
        //Creating students
        return new StudentDAO().getStudents();
    }

    private void updatingDataOfAdapter(List<Student>students){
        //Updating data of adapter
        studentsRecyclerViewAdapter.updateDataFromList(students);
    }

    private void openActivityToFillDataOfStudent() {
        startActivity(new Intent(StudentsListActivity.this,FormStudentActivity.class));
    }

    private void startRemoveStudentAction(int position) {
        //Getting a student to send to update
        final Student student = studentDAO.getStudentByPosition(position);
        studentDAO.removeStudent(student);
        //Updating data to refresh list view
        updatingDataOfAdapter(studentDAO.getStudents());
    }

    private void startUpdateStudentAction(int position) {
        //Getting a student to send to update
        final Student student = studentDAO.getStudentByPosition(position);
        openActivityToUpdateDataOfStudent(student,position);
    }

    private void openActivityToUpdateDataOfStudent(Student student, int positionToBeUpdated) {
        Intent intent = new Intent(StudentsListActivity.this, FormStudentActivity.class);
        intent.putExtra(getString(R.string.KEY_STUDENT), student);
        intent.putExtra(getString(R.string.KEY_POSITION_STUDENT),positionToBeUpdated);
        startActivity(intent);
    }

    private void openActivityToShowDataOfStudent(Student student) {
        Intent intent = new Intent(StudentsListActivity.this, LoadDataFromStudentActivity.class);
        intent.putExtra(getString(R.string.KEY_STUDENT), student);
        startActivity(intent);
    }

    @SuppressWarnings("Convert2Lambda")
    private void callDialogs(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Removing Student")
                .setMessage("Are you sure that you want to remove this student?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startRemoveStudentAction(position);
                    }
                })
                .show();
    }

}
