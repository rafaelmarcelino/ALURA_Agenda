package br.com.ram.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.ram.R;
import br.com.ram.interfaces.OnItemClickListener;
import br.com.ram.model.Student;
import br.com.ram.tools.Constants;

public class StudentsRecyclerViewAdapter extends RecyclerView.Adapter <StudentsRecyclerViewAdapter.StudentViewHolder> {
    private final List<Student> students;
    private Context context;
    private int layoutResource;
    private OnItemClickListener onItemClickListener;

    //Constructor
    public StudentsRecyclerViewAdapter(Context context, int layoutResource) {
        this.students = new ArrayList<>();
        this.context = context;
        this.layoutResource = layoutResource;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Load / Inflate the layout created to recycler view
        View viewInflate = LayoutInflater.from(this.context).inflate(this.layoutResource, parent, false);
        // Send the view loaded to create the view holder
        StudentViewHolder studentViewHolder = new StudentViewHolder(viewInflate);
        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        //Fill all data in their respective views
        holder.loadAllDataInViews(this.context, this.students.get(position));
    }

    @Override
    public int getItemCount() {
        return this.students.size();
    }

    public void updateDataFromList(List<Student> students) {
        this.students.clear();
        this.students.addAll(students);
        //Notify view to update the data
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //Class to collect the view of each item in list of recycler view
    class StudentViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvPhone;
        private TextView tvEmail;
        private TextView tvAge;
        private ImageView ivPhoto;
        private View vDivider;
        private Student student;

        //Constructor
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            //Load all views childs of layout
            loadAllViews(itemView);
            // Load custom item click listener
            loadListernerOfView(itemView);

        }

        private void loadListernerOfView(View itemView) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(StudentViewHolder.this.student);
                }
            });
            }

        private void loadAllViews(View itemView) {
            //Get views
            this.tvName = itemView.findViewById(R.id.cell_constraint_tv_name_student);
            this.tvPhone = itemView.findViewById(R.id.cell_constraint_tv_phone_student);
            this.tvEmail = itemView.findViewById(R.id.cell_constraint_tv_email_student);
            this.tvAge = itemView.findViewById(R.id.cell_constraint_tv_age_student);
            this.ivPhoto = itemView.findViewById(R.id.cell_constraint_iv_photo_student);
            this.vDivider = itemView.findViewById(R.id.cell_constraint_view_divider);
        }

        public void loadAllDataInViews(Context context, Student student) {
            //Load the student
            this.student = student;
            //Data based on gender
            final Resources resources = context.getResources();
            if (student.getGender() == Constants.MALE) {
                this.ivPhoto.setImageDrawable(context.getDrawable(R.drawable.male_avatar));
                this.vDivider.setBackgroundColor(resources.getColor(R.color.blue_200));
            } else {
                this.ivPhoto.setImageDrawable(context.getDrawable(R.drawable.female_avatar));
                this.vDivider.setBackgroundColor(resources.getColor(R.color.purple_200));
            }
            //Fill data
            this.tvName.setText(student.getName());
            this.tvEmail.setText(student.getEmail());
            this.tvPhone.setText(student.getPhone());
            this.tvAge.setText(student.getAge() + " yrs");
        }
    }
}
