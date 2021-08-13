package com.example.mpip_lab4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.PostViewHolder> {

    private List<Student> students;

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_student, null, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bindData(this.students.get(position));
    }

    @Override
    public int getItemCount() {
        return this.students.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        private TextView name, surname, index,telephone,adress;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.student_name);
            surname = (TextView) itemView.findViewById(R.id.student_surname);
            index = (TextView) itemView.findViewById(R.id.student_id);
            telephone = (TextView) itemView.findViewById(R.id.student_phone);
            adress = (TextView) itemView.findViewById(R.id.student_adress);

        }

        public void bindData(Student student) {
            name.setText(student.getName());
            surname.setText(student.getSurname());
            index.setText(student.getIndex());
            telephone.setText(student.getTelephone());
            adress.setText(student.getAdress());
        }
    }
}