package com.example.assignment07.pages.membership;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.assignment07.DBHandler;
import com.example.assignment07.R;

import java.util.ArrayList;

public class MemberList extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> id, name, address, Cnum, unpaid;
    DBHandler DB;
    MemberAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberlist);
        DB = new DBHandler(this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        address = new ArrayList<>();
        Cnum = new ArrayList<>();
        unpaid = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MemberAdapter(this,id, name, address, Cnum, unpaid);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        Cursor cursor = DB.getMemberData();
        if (cursor.getCount() == 0) {
            Toast.makeText(MemberList.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                address.add(cursor.getString(2));
                Cnum.add(cursor.getString(3));
                unpaid.add(cursor.getString(4));
            }
        }
    }
}