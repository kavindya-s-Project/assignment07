package com.example.assignment07.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignment07.DBHandler;
import com.example.assignment07.R;
import com.example.assignment07.models.BookModel;

public class MaintainBook extends AppCompatActivity {

    private EditText editTextTitle, editTextPublisher, editTextId;
    private Button addBtn, removeBtn, editBtn;
    private DBHandler dbHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_book);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextPublisher = findViewById(R.id.editTextPublisher);
        editTextId = findViewById(R.id.editTextId);
        addBtn = findViewById(R.id.addBtn);
        removeBtn = findViewById(R.id.removeBtn);
        editBtn = findViewById(R.id.editBtn);
        context = this;

        dbHandler = new DBHandler(context);

// add book
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookTitle = editTextTitle.getText().toString();
                int bookId = Integer.parseInt(editTextId.getText().toString());
                String bookPublisher = editTextPublisher.getText().toString();

                BookModel bookModel = new BookModel(bookTitle,bookId,bookPublisher);
                dbHandler.addBook(bookModel);

                startActivity(new Intent(context,Books.class));
            }
        });

        //edit book
       /*final String id = getIntent().getStringExtra("id");
        BookModel bookModel = dbHandler.getSingleBook(Integer.parseInt(id));

        editTextTitle.setText(bookModel.getTitle());
//        editTextId.setText(bookModel.getId());
        editTextPublisher.setText(bookModel.getPublisher());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookTitle = editTextTitle.getText().toString();
//                int bookId = Integer.parseInt(editTextId.getText().toString());
                String bookPublisher = editTextPublisher.getText().toString();


                BookModel bookModel1 = new BookModel(bookTitle, bookPublisher);
                int state = dbHandler.updateSingleBook(bookModel);
                System.out.println(state);
                startActivity(new Intent(context, Books.class));
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

    }
}