package com.prabodhmayekar.androidassignment;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Form extends AppCompatActivity{
    List<EditText> editTextList = new ArrayList<>();
    List<String> courses = new ArrayList<>(Arrays.asList("Select Option","BCA","MCA","B.Tech","M.Tech","BBA","MBA"));
    String course = "";
    String gender = "male";
    boolean update;
    SQLiteHelper sqLiteHelper;
    Registrations registrations;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Intent intent = getIntent();
        update = intent.hasExtra("id");
        id = intent.getIntExtra("id",0);

        sqLiteHelper = new SQLiteHelper(this,"Students");
        Spinner selectCourse = findViewById(R.id.course);
        RadioGroup selectGender = findViewById(R.id.gender);
        editTextList.addAll(Arrays.asList(findViewById(R.id.fn),findViewById(R.id.ln),findViewById(R.id.age),findViewById(R.id.rn),findViewById(R.id.phone),findViewById(R.id.email)));
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, courses);
        selectCourse.setAdapter(courseAdapter);
        selectCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                course = i != 0 ? courses.get(i) : "";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        selectGender.setOnCheckedChangeListener((radioGroup, i) -> gender = ((RadioButton)radioGroup.findViewById(i)).getText().toString());
        if(update){
            TextView htxt = findViewById(R.id.headerTxt);
            htxt.setText(R.string.update);
            try {
                registrations = sqLiteHelper.getRegistration(id);
                editTextList.get(0).setText(registrations.getFirstName());
                editTextList.get(1).setText(registrations.getLastName());
                editTextList.get(2).setText(String.valueOf(registrations.getAge()));
                editTextList.get(3).setText(String.valueOf(registrations.getRegNo()));
                editTextList.get(4).setText(String.valueOf(registrations.getPhone()));
                editTextList.get(5).setText(registrations.getEmail());
                selectCourse.setSelection(courses.indexOf(registrations.getCourse()));
                course = registrations.getCourse();
                switch (registrations.getGender()){
                    case "male":
                        selectGender.check(R.id.m);
                        gender = "male";
                        break;
                    case "female":
                        selectGender.check(R.id.f);
                        gender = "female";
                        break;
                    default:
                        selectGender.check(R.id.o);
                        gender = "others";
                        break;
                }
            } catch (Exception e) {
                update = !update;
                Snackbar.make(findViewById(R.id.form),"Problem 212345 ",Snackbar.LENGTH_LONG).show();
                startActivity(new Intent(Form.this,MainActivity.class));
            }
        }

    }
//[Prabodh, Mayekar, 21, 11900868, 7757803132, prabodhmayekar@yahoo.com]
    public void addData(View view) {
        List<String> data = editTextList.stream()
                .map(d -> d.getText().toString())
                .collect(Collectors.toList());

        if(!data.stream().map(String::isEmpty).collect(Collectors.toList()).contains(true) && !course.isEmpty()){
            ContentValues contentValues = sqLiteHelper.createContentValues(data.get(0),data.get(1),Integer.parseInt(data.get(2)),Integer.parseInt(data.get(3)),course,gender,data.get(4),data.get(5));
            if (update){
                sqLiteHelper.update.accept(id,contentValues);
            }else {
                sqLiteHelper.insert.accept(contentValues);
            }

            startActivity(new Intent(Form.this,MainActivity.class));
            finish();
        }else {
            Snackbar.make(findViewById(R.id.form),"Some fields are empty",Snackbar.LENGTH_LONG).show();
        }

    }
}
