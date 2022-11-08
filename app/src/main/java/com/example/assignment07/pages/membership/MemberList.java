package com.example.assignment07.pages.membership;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment07.DBHandler;
import com.example.assignment07.R;

import java.util.ArrayList;

public class MemberList extends AppCompatActivity {

    Context context;
    ImageView empty_imageview;
    TextView no_data;
    RecyclerView recyclerView;
    ArrayList<String> id, name, address, Cnum, unpaid;
    DBHandler DB;
    MemberAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberlist);
        context = this;

        DB = new DBHandler(this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        address = new ArrayList<>();
        Cnum = new ArrayList<>();
        unpaid = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        displaydata();

        adapter = new MemberAdapter(MemberList.this, this, id, name, address, Cnum, unpaid);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MemberList.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    private void displaydata() {
        Cursor cursor = DB.getMemberData();
        if (cursor.getCount() == 0) {
            Toast.makeText(MemberList.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                address.add(cursor.getString(2));
                Cnum.add(cursor.getString(3));
                unpaid.add(cursor.getString(4));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
}