package com.example.assignment07.pages.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment07.DBHandler;
import com.example.assignment07.R;
import com.example.assignment07.pages.books.MaintainBook;

public class LibraryAdmin extends AppCompatActivity {

    private EditText typeUserName, typePassword;
    private Button loginButton;
    DBHandler DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_admin);

        typeUserName = findViewById(R.id.typeUserName);
        typePassword = findViewById(R.id.typePassword);
        loginButton = findViewById(R.id.loginButton);
        DB = new DBHandler(this);

        //press after login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = typeUserName.getText().toString();
                String pass = typePassword.getText().toString();
                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(LibraryAdmin.this, "Please fill all the fields",
                            Toast.LENGTH_SHORT).show();
                }
                if (user.equals("Admin") && pass.equals("1234")) {
                    Toast.makeText(LibraryAdmin.this, "Sign in successfully",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MaintainBook.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LibraryAdmin.this, "Invalid Credentials",
                            Toast.LENGTH_SHORT).show();
                }


                /*else {
                    Boolean checkuserpass = DB.checkAdminUsernameAndPassword(user, pass);
                    if (checkuserpass == true) {
                        Toast.makeText(LibraryAdmin.this, "Sign in successfully",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MaintainBook.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LibraryAdmin.this, "Invalid Credentials",
                                Toast.LENGTH_SHORT).show();
                    }
                }*/
            }
        });
    }
}