package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    private List<Book> books;
    private Context context;

    public BookAdapter(List<Book> books, Context context) {
        this.books = books;
        this.context = context;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.book_item, viewGroup, false);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView authorTextView = view.findViewById(R.id.authorTextView);
        TextView pageCountTextView = view.findViewById(R.id.pageCountTextView);
        Button deleteButton = view.findViewById(R.id.deleteButton);

        titleTextView.setText(books.get(i).getTitle());
        authorTextView.setText(books.get(i).getAuthor());
        pageCountTextView.setText("(" + books.get(i).getPageCount() + ")");

        final int position = i;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("title", books.get(position).getTitle());
                intent.putExtra("author", books.get(position).getAuthor());
                intent.putExtra("pageCount", books.get(position).getPageCount());
                context.startActivity(intent);
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Book");

                builder.setMessage("Are you sure you want to delete this book?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        books.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }
}
