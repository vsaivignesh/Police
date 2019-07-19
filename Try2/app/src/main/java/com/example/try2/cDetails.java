package com.example.try2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class cDetails extends AppCompatActivity {
    private TextView desc;
    private Button click;
    DatabaseHelper myDb;


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SQLiteDatabase.OpenParams oP = new SQLiteDatabase.OpenParams.Builder()
                .build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);
        desc = findViewById(R.id.Desc);
        click=findViewById(R.id.Clcik);
        myDb=new DatabaseHelper(this, oP);
        click.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDb.insertdata(getIntent().getStringExtra("Cat"),getIntent().getStringExtra("PID"));

                    }
                }
        );


        if (getIntent().hasExtra("Cat")) {
            Intent intent = getIntent();
            String message="";
            message+="Category: " +intent.getStringExtra("Cat") + "\n";
            message+="Context: " +intent.getStringExtra("Context") + "\n";
            message+="Location type: " +intent.getStringExtra("Lt")+ "\n";
            message+="Location sub-type: " +intent.getStringExtra("Lst")+ "\n";
            message+="Month: " +intent.getStringExtra("mon")+ "\n";
            message+="Persistent ID " +intent.getStringExtra("PID")+ "\n";
            message+="ID: " + intent.getIntExtra("ID", 0) + "\n";
            desc.setText(message);



        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }


    }}



