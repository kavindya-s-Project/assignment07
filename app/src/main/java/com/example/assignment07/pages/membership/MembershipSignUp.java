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

public class MembershipSignUp extends AppCompatActivity {

    private EditText typeUserName, typePassword, reTypePassword;
    private Button signUpButton, loginButton;
    private DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_sign_up);

        typeUserName = findViewById(R.id.typeUserName);
        typePassword = findViewById(R.id.typePassword);
        reTypePassword = findViewById(R.id.reTypePassword);
        signUpButton = findViewById(R.id.signUpButton);
        loginButton = findViewById(R.id.loginButton);
        DB = new DBHandler(this);

        //press after signUp button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = typeUserName.getText().toString();
                String pass = typePassword.getText().toString();
                String repass = reTypePassword.getText().toString();
                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(MembershipSignUp.this, "Please fill all the fields",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkUsername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(MembershipSignUp.this, "Registered successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Membership.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MembershipSignUp.this, "Registration failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MembershipSignUp.this, "User already exists! please sign in",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MembershipSignUp.this, "Passwords not matching",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //press after login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Membership.class);
                startActivity(intent);
            }
        });
    }
}