package com.prabodhmayekar.androidassignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentDataAdapter extends RecyclerView.Adapter<StudentDataAdapter.StudentViewHolder> {
    private List<Registrations> myList;
    private SQLiteHelper sqLiteHelper;
    private Context context;

    StudentDataAdapter(Context context){
        this.context = context;
        sqLiteHelper = new SQLiteHelper(context,"Students");
        this.myList = sqLiteHelper.getAllRegistrations();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_data,parent,false);
        return new StudentViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Registrations rg = this.myList.get(position);
        String info = "Name: "+rg.getFirstName()+" "+rg.getLastName()+" \nAge: "+rg.getAge()+"\nReg. No. "+rg.getRegNo()+"\nCourse: "+rg.getCourse()+" \t Gender: "+rg.getGender()+" \nPhone: "+rg.getPhone()+"\nE-mail: "+rg.getEmail();
        holder.text.setText(info);
        holder.delete.setOnClickListener(view -> {
            sqLiteHelper.delete.accept(rg.getId());
            this.myList = sqLiteHelper.getAllRegistrations();
            this.notifyDataSetChanged();
            Toast.makeText(context,"Successfully deleted item",Toast.LENGTH_LONG).show();
        });

        holder.update.setOnClickListener(view -> {
            Intent i = new Intent(this.context,Form.class);
            i.putExtra("id",rg.getId());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView text, update,delete;
        StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.mytxt);
            update = itemView.findViewById(R.id.update);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}