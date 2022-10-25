package com.example.assignment07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.assignment07.pages.Books;
import com.example.assignment07.pages.LendingDetails;
import com.example.assignment07.pages.Membership;
import com.example.assignment07.pages.UserAccount;

public class MainPage extends AppCompatActivity {

    Button booksBtn, membershipBtn, lendingDetailsBtn, userBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        booksBtn = findViewById(R.id.booksBtn);
        membershipBtn = findViewById(R.id.membershipBtn);
        lendingDetailsBtn = findViewById(R.id.lendingDetailsBtn);
        userBtn = findViewById(R.id.userBtn);

        booksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookDetails();
            }
        });

        membershipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                membershipDetails();
            }
        });

        lendingDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookLendDetails();
            }
        });

        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDetails();
            }
        });
    }

    private void bookDetails() {
        Intent intent = new Intent(this, Books.class);
        startActivity(intent);
    }

    private void membershipDetails() {
        Intent intent = new Intent(this, Membership.class);
        startActivity(intent);
    }

    private void bookLendDetails() {
        Intent intent = new Intent(this, LendingDetails.class);
        startActivity(intent);
    }

    private void userDetails() {
        Intent intent = new Intent(this, UserAccount.class);
        startActivity(intent);
    }
}