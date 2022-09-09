package br.com.ram.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.ram.model.Student;

public class StudentAdapters extends BaseAdapter {
    private Context context;
    private int layoutResource;
    private List <Student> students;

    //Constructor
    public StudentAdapters(Context context, int layoutResource) {
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
        final View viewInflated = LayoutInflater.from(this.context).inflate(layoutResource, parent);
        return viewInflated;
    }
}
