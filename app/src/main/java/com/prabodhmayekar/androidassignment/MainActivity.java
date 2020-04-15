package com.prabodhmayekar.androidassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        StudentDataAdapter sda = new StudentDataAdapter(this);
        rv.setAdapter(sda);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void fillForm(View view) {
        startActivity(new Intent(MainActivity.this,Form.class));
    }
}
