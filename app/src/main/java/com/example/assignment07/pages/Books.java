package com.example.assignment07.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.assignment07.DBHandler;
import com.example.assignment07.R;
import com.example.assignment07.models.BookModel;
import com.example.assignment07.pages.admin.LibraryAdmin;

import java.util.ArrayList;
import java.util.List;

public class Books extends AppCompatActivity {

    private Button admin;
    private ListView bookList;
    private TextView bookCount;
    Context context;
    private DBHandler dbHandler;
    private List<BookModel> bookModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        context = this;

        dbHandler = new DBHandler(context);
        admin = findViewById(R.id.admin);
        bookList = findViewById(R.id.bookList);
        bookCount = findViewById(R.id.bookCount);
        bookModels = new ArrayList<>();

        bookModels = dbHandler.getAllBooks();
        BookAdapter adapter = new BookAdapter(context,R.layout.single_book_details,bookModels);

        bookList.setAdapter(adapter);

        //get book counts from the table
        int countBooks = dbHandler.countBooks();
        bookCount.setText("You have "+countBooks+" books");

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, LibraryAdmin.class));
            }
        });

    }
}