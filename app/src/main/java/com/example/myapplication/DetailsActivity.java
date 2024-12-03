package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class DetailsActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView authorTextView;
    private TextView pageCountTextView;
    private TextView yearTextView;


    private Random random = new Random();


    private Button backButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void init() {


        titleTextView = findViewById(R.id.titleTextView);
        authorTextView = findViewById(R.id.authorTextView);
        pageCountTextView = findViewById(R.id.pageCountTextView);
        yearTextView = findViewById(R.id.yearTextView);

        backButton = findViewById(R.id.backButton);



        Intent intent = getIntent();

        titleTextView.setText(intent.getStringExtra("title"));
        authorTextView.setText(intent.getStringExtra("author"));
        pageCountTextView.setText("(" + intent.getIntExtra("pageCount", 0) + ")");

        yearTextView.setText(String.valueOf(1 + random.nextInt(2025 - 1)));
    }

}