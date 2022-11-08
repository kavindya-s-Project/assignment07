package com.example.assignment07.pages.books;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.assignment07.DBHandler;
import com.example.assignment07.R;
import com.example.assignment07.pages.admin.LibraryAdmin;

import java.util.ArrayList;
import java.util.List;

public class Books extends AppCompatActivity {

    private Button admin;
    private TextView bookCount;
    Context context;
    ImageView empty_imageview;
    TextView no_data;
    RecyclerView recyclerView;
    ArrayList<String> id, title, publisher, author, branches;
    DBHandler dbHandler;
    BookAdapter1 adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        context = this;

        recyclerView = findViewById(R.id.recyclerView);
        admin = findViewById(R.id.admin);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        dbHandler = new DBHandler(Books.this);
        id = new ArrayList<>();
        title = new ArrayList<>();
        publisher = new ArrayList<>();
        author = new ArrayList<>();
        branches = new ArrayList<>();

        //get book counts from the table
//        int countBooks = dbHandler.countBooks();
//        bookCount.setText("You have " + countBooks + " books");

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, LibraryAdmin.class));
            }
        });


        storeDataInArrays();

        adapter1 = new BookAdapter1(Books.this,this, id, title, publisher, author, branches);
        recyclerView.setAdapter(adapter1);
        recyclerView.setLayoutManager(new LinearLayoutManager(Books.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = dbHandler.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                title.add(cursor.getString(1));
                publisher.add(cursor.getString(2));
                author.add(cursor.getString(3));
                branches.add(cursor.getString(4));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
}