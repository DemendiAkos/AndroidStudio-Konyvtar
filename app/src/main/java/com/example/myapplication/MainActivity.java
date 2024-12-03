package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText authorEditText;
    private EditText pageCountEditText;
    private Button addButton;

    private ListView bookListView;
    private List<Book> books;
    private BookAdapter adapter;

    private String title;
    private String author;
    private int pageCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEditText.getText().toString().trim();
                String author = authorEditText.getText().toString().trim();
                int pageCount;

                try {
                    pageCount = Integer.parseInt(pageCountEditText.getText().toString().trim());
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Page count must be a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (title.isEmpty() || author.isEmpty() || pageCount <= 50) {
                    Toast.makeText(MainActivity.this, "Please enter a valid title, author, and page count greater than 50", Toast.LENGTH_SHORT).show();
                    return;
                }

                Book book = new Book(title, author, pageCount);
                books.add(book);
                adapter.notifyDataSetChanged();

                titleEditText.setText("");
                authorEditText.setText("");
                pageCountEditText.setText("");
            }
        });
    }

    public void init() {
        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        pageCountEditText = findViewById(R.id.pageCountEditText);
        addButton = findViewById(R.id.addButton);

        books = new ArrayList<>();
        bookListView = findViewById(R.id.bookListView);
        adapter = new BookAdapter(books, this);
        bookListView.setAdapter(adapter);
    }
}