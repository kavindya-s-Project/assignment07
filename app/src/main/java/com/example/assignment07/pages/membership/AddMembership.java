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

public class AddMembership extends AppCompatActivity {

    private EditText name, address, Cnum, unpaid;
    private Button btnInsert, btnView;
    private DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_membership);

        name =findViewById(R.id.name);
        address =findViewById(R.id.address);
        Cnum =findViewById(R.id.Cnum);
        unpaid =findViewById(R.id.unpaid);
        btnInsert =findViewById(R.id.btnInsert);
        btnView =findViewById(R.id.btnView);

        DB = new DBHandler(this);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddMembership.this, MemberList.class));
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String addressTXT = address.getText().toString();
                String CnumTxt = Cnum.getText().toString();
                String unpaidTxt = unpaid.getText().toString();

                Boolean checkinsertdata  = DB.insertuserdata(nameTXT, addressTXT, CnumTxt, unpaidTxt);
                if(checkinsertdata==true)
                {
                    Toast.makeText(AddMembership.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(AddMembership.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}