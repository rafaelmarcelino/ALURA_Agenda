package br.com.ram.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.ram.R;
import br.com.ram.model.Student;
import br.com.ram.model.StudentDAO;
import br.com.ram.tools.Constants;

public class FormStudentActivity extends AppCompatActivity {
    //Creating variables of views
    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private Button buttonSaveUpdate;
    private StudentDAO studentDAO;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Loading layout
        setContentView(R.layout.activity_form_student);
        //Init variables
        initVariables();
        //Check which action should be executed to set the title of app bar
        setAppBarTitleByActionExecuted();
        //Handle data of student if action to be executed isn't be an action to create a new student
        if (getStudentFromIntent()!= null){
            changeTextOfButtonToUpdateStudent();
            loadDataToUpdateStudent();
        }
        //Call listeners of views
        callListenersOfViews();
    }

    private void changeTextOfButtonToUpdateStudent() {
        //Set text of button to fit better the context for update a student
        buttonSaveUpdate.setText(R.string.BTN_UPDATE_CONTEXT);
    }


    //Methods
    private void initVariables() {
        editTextName = findViewById(R.id.activity_form_student_et_name);
        editTextPhone = findViewById(R.id.activity_form_student_et_phone);
        editTextEmail = findViewById(R.id.activity_form_student_et_email);
        buttonSaveUpdate = findViewById(R.id.activity_form_student_bt_save_update);
        studentDAO = new StudentDAO();
        intent = getIntent();
    }
    private void callListenersOfViews(){
        //Button
        buttonSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getStudentFromIntent()== null) {
                    //Add new student
                    handleDataToAddNewStudent();
                }else{
                    //Update student
                    handleDataToUpdateStudent();
                }
                //Returning to activity that called this
                finish();

            }
        });

    }
    private void setAppBarTitleByActionExecuted() {
        if (checkActionCreateOrUpdateStudent()){
            //Setting the title os app bar
            setTitle("Form to fill student's data");
        }else{
            //Setting the title os app bar
            setTitle("Update student's data");
        }
    }
    private boolean checkActionCreateOrUpdateStudent() {
        //Validation to handle new student or update the student included in intent
        if (getStudentFromIntent() == null){
            return true;
        }
        return false;
    }
    private Student getStudentFromIntent() {
        //Validation of intent
        if (intent.hasExtra(getString(R.string.KEY_STUDENT))){
            return (Student) intent.getSerializableExtra(getString(R.string.KEY_STUDENT));
        }else{
            Toast.makeText(this, "No student to load data", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
    private int getPositionOfStudentFromIntent() {
        //Validation of intent
        if (intent.hasExtra(getString(R.string.KEY_POSITION_STUDENT))){
            return  intent.getIntExtra(getString(R.string.KEY_POSITION_STUDENT), Constants.DEFAULT_ERROR_INDEX);
        }else{
            Toast.makeText(this, "Position to be updated is not valid", Toast.LENGTH_SHORT).show();
        }
        return Constants.DEFAULT_ERROR_INDEX;
    }
    private void handleDataToAddNewStudent(){
        //Getting data of the fields
        Student student = getStudentDataFilled();
        //Saving student in database
        saveStudentInDataBase(student);
    }
    private void handleDataToUpdateStudent(){
        //Getting data of the fields
        Student student = getStudentDataFilled();
        //Getting position to be updated
        final int positionOfStudentToBeUpdated = getPositionOfStudentFromIntent();
        //Update data of student
        updateStudentWithNewData(student, positionOfStudentToBeUpdated);
    }
    private void loadDataToUpdateStudent(){
        //Validation to collect student
        loadFieldsWithDataOfStudent(getStudentFromIntent());
    }
    private void loadFieldsWithDataOfStudent(Student student) {
        //Fill the fields with data of this student
        editTextName.setText(student.getName());
        editTextPhone.setText(student.getPhone());
        editTextEmail.setText(student.getEmail());
    }

    private Student getStudentWithDataUpdated() {
        //Collecting student from intent
        final Student student = getStudentFromIntent();
        //Update student with new data filled
        student.setName(editTextName.getText().toString());
        student.setPhone(editTextPhone.getText().toString());
        student.setEmail(editTextEmail.getText().toString());
        return student;
    }
    private void updateStudentWithNewData(Student student, int positionOfStudentToBeUpdated) {
        //Updating student in a list
        studentDAO.updateStudent(positionOfStudentToBeUpdated,student);
    }
    private void saveStudentInDataBase(Student student) {
        //Creating the tool to save student
        StudentDAO studentDAO = new StudentDAO();
        //Saving student in a list
        studentDAO.saveStudent(student);
    }
    @NonNull
    private Student getStudentDataFilled() {
        Student student = null;
        if (getStudentFromIntent() == null){
        //Creating new student with data filled if action should be  create new
        student = new Student(editTextName.getText().toString(),
                editTextPhone.getText().toString(),
                editTextEmail.getText().toString());}
        else{
            //Loading student with data filled if action should be update
            student = getStudentWithDataUpdated();
        }
        return student;
    }

}