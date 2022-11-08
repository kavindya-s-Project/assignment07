package com.example.assignment07.pages.books;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment07.DBHandler;
import com.example.assignment07.R;


public class MaintainBook extends AppCompatActivity {

    private EditText title_input, publisher_input, author_input, branches_input;
    private Button add_btn, update_btn, delete_btn;
    private DBHandler dbHandler;
    private Context context;

    String id, title, publisher, author, branches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_book);

        title_input = findViewById(R.id.title_input);
        publisher_input = findViewById(R.id.publisher_input);
        author_input = findViewById(R.id.author_input);
        branches_input = findViewById(R.id.branches_input);
        add_btn = findViewById(R.id.add_btn);
        update_btn = findViewById(R.id.update_btn);
        delete_btn = findViewById(R.id.delete_btn);
        context = this;

        dbHandler = new DBHandler(context);

// add book
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(MaintainBook.this);
                dbHandler.addBook(
                        title_input.getText().toString().trim(),
                        publisher_input.getText().toString().trim(),
                        author_input.getText().toString().trim(),
                        branches_input.getText().toString().trim());
                //return back book list
                startActivity(new Intent(MaintainBook.this, Books.class));
            }
        });

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                 dbHandler = new DBHandler(MaintainBook.this);
                title = title_input.getText().toString().trim();
                publisher = publisher_input.getText().toString().trim();
                author = author_input.getText().toString().trim();
                branches = branches_input.getText().toString().trim();
                dbHandler.updateData(id, title, publisher, author, branches);

                //return back book list
                startActivity(new Intent(MaintainBook.this, Books.class));
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
                //return back book list
                startActivity(new Intent(MaintainBook.this, Books.class));
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("publisher") && getIntent().hasExtra("author") && 
                getIntent().hasExtra("branches")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            publisher = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            branches = getIntent().getStringExtra("branches");

            //Setting Intent Data
            title_input.setText(title);
            publisher_input.setText(title);
            author_input.setText(author);
            branches_input.setText(branches);
            Log.d("stev", title+" "+author+" "+branches);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHandler myDB = new DBHandler(MaintainBook.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}