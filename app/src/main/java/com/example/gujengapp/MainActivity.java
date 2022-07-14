package com.example.gujengapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView numbers = (TextView) findViewById(R.id.number);
        TextView family = (TextView) findViewById(R.id.family);
        TextView color = (TextView) findViewById(R.id.colors);
        TextView phrases = (TextView) findViewById(R.id.phrases);

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(i);
                Toast.makeText(v.getContext(), "Opening Numbers Activity", Toast.LENGTH_SHORT).show();
            }
        });

        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(i);
                Toast.makeText(v.getContext(), "Opening Family List", Toast.LENGTH_SHORT).show();
            }
        });

        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(i);
                Toast.makeText(v.getContext(), "Opening Colors List", Toast.LENGTH_SHORT).show();
            }
        });

        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(i);
                Toast.makeText(v.getContext(), "Opening Phrases List", Toast.LENGTH_SHORT).show();
            }
        });

    }
}