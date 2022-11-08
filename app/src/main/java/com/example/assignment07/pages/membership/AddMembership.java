package com.example.assignment07.pages.membership;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment07.DBHandler;
import com.example.assignment07.R;
import com.example.assignment07.pages.books.Books;
import com.example.assignment07.pages.books.MaintainBook;

public class AddMembership extends AppCompatActivity {

    private EditText name, address, Cnum, unpaid;
    private Button btnInsert, btnView, btnUpdate, btnDelete;
    private DBHandler DB;
    private Context context;

    String id, Mem_name, Mem_address, Contact_num, Mem_unpaid;

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
        btnUpdate =findViewById(R.id.btnUpdate);
        btnDelete =findViewById(R.id.btnDelete);
        context = this;

        DB = new DBHandler(this);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(AddMembership.this, MemberList.class));
                Intent intent = new Intent(getApplicationContext(), MemberList.class);
                startActivity(intent);
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

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB = new DBHandler(AddMembership.this);
                Mem_name = name.getText().toString().trim();
                Mem_address = address.getText().toString().trim();
                Contact_num = Cnum.getText().toString().trim();
                Mem_unpaid = unpaid.getText().toString().trim();
                DB.updateMember(id, Mem_name, Mem_address, Contact_num, Mem_unpaid);
                //return back member list
                startActivity(new Intent(AddMembership.this, MemberList.class));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
                //return back book list
                startActivity(new Intent(AddMembership.this, MemberList.class));
            }
        });
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + Mem_name + " ?");
        builder.setMessage("Are you sure you want to delete " + Mem_name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHandler DB = new DBHandler(AddMembership.this);
                DB.deleteMemberRow(id);
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