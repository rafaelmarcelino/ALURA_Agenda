package br.com.ram.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.ram.R;
import br.com.ram.model.Student;
import br.com.ram.tools.Constants;

public class StudentsAdapter extends BaseAdapter {
    private final Context context;
    private final int layoutResource;
    private final List <Student> students;

    //Constructor
    public StudentsAdapter(Context context, int layoutResource) {
        this.context = context;
        this.layoutResource = layoutResource;
        this.students = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.students.size();
    }

    @Override
    public Student getItem(int position) {
        return this.students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.students.get(position).getIdStudent();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View viewInflated = LayoutInflater.from(this.context).inflate(layoutResource, parent, false);
        //Fill data in views inside view inflated
        fillDataInViews(position,viewInflated);
        return viewInflated;
    }

    private void fillDataInViews(int position, View viewInflated) {
        //Get views
        final TextView tvName = viewInflated.findViewById(R.id.cell_constraint_tv_name_student);//(R.id.lv_item_student_name);
        final TextView tvPhone = viewInflated.findViewById(R.id.cell_constraint_tv_phone_student);//(R.id.lv_item_student_phone);
        final TextView tvEmail = viewInflated.findViewById(R.id.cell_constraint_tv_email_student);
        final TextView tvAge = viewInflated.findViewById(R.id.cell_constraint_tv_age_student);
        final ImageView ivPhoto = viewInflated.findViewById(R.id.cell_constraint_iv_photo_student);
        final View vDivider = viewInflated.findViewById(R.id.cell_constraint_view_divider);

         //Data based on gender
        final Resources resources = context.getResources();
        if (this.students.get(position).getGender()== Constants.MALE) {
            ivPhoto.setImageDrawable(context.getDrawable(R.drawable.male_avatar));
            vDivider.setBackgroundColor(resources.getColor(R.color.blue_200));
        }else{
            ivPhoto.setImageDrawable(context.getDrawable(R.drawable.female_avatar));
            vDivider.setBackgroundColor(resources.getColor(R.color.purple_200));
        }

        //Fill data
        tvName.setText(this.students.get(position).getName());
        tvEmail.setText(this.students.get(position).getEmail());
        tvPhone.setText(this.students.get(position).getPhone());
        tvAge.setText(this.students.get(position).getAge() + " yrs");

    }

    public void updateDataFromList(List<Student> students){
        this.students.clear();
        this.students.addAll(students);
        //Notify view to update the data
        notifyDataSetChanged();
    }
}
