package br.com.ram.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.ram.R;
import br.com.ram.model.Student;

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
        final TextView tvName = viewInflated.findViewById(R.id.lv_item_student_name);
        final TextView tvPhone = viewInflated.findViewById(R.id.lv_item_student_phone);
        //Fill data
        tvName.setText(this.students.get(position).getName());
        tvPhone.setText(this.students.get(position).getPhone());
    }

    public void updateDataFromList(List<Student> students){
        this.students.clear();
        this.students.addAll(students);
        //Notify view to update the data
        notifyDataSetChanged();
    }
}
