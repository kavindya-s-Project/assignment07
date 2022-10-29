package com.example.assignment07.pages.membership;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment07.DBHandler;
import com.example.assignment07.R;

public class Membership extends AppCompatActivity {

    private EditText typeUserName, typePassword;
    private Button loginButton, signUpButton;
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);

        typeUserName = findViewById(R.id.typeUserName);
        typePassword = findViewById(R.id.typePassword);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);
        DB = new DBHandler(this);

        //press after login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = typeUserName.getText().toString();
                String pass = typePassword.getText().toString();
                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(Membership.this, "Please fill all the fields",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuserpass = DB.checkUsernameAndPassword(user, pass);
                    if (checkuserpass == true) {
                        Toast.makeText(Membership.this, "Sign in successfully",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AddMembership.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Membership.this, "Invalid Credentials",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //press after signUp button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MembershipSignUp.class);
                startActivity(intent);
            }
        });
    }
}