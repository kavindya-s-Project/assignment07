package com.example.assignment07.pages.books;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment07.R;
import java.util.ArrayList;

public class BookAdapter1 extends RecyclerView.Adapter<BookAdapter1.MyViewHolder>{

    private Context context;
    private Activity activity;
    private ArrayList id, book_title, book_publisher, book_author, book_branches;

    public BookAdapter1(Activity activity, Context context, ArrayList id, ArrayList book_title,
                        ArrayList book_publisher,ArrayList book_author, ArrayList book_branches) {
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.book_title = book_title;
        this.book_publisher = book_publisher;
        this.book_author = book_author;
        this.book_branches = book_branches;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.single_book_details, parent, false);
        return new MyViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.title_txt.setText(String.valueOf(book_title.get(position)));
        holder.book_publisher_txt.setText(String.valueOf(book_publisher.get(position)));
        holder.author_txt.setText(String.valueOf(book_author.get(position)));
        holder.branch_txt.setText(String.valueOf(book_branches.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MaintainBook.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("title", String.valueOf(book_title.get(position)));
                intent.putExtra("publisher", String.valueOf(book_publisher.get(position)));
                intent.putExtra("author", String.valueOf(book_author.get(position)));
                intent.putExtra("branch", String.valueOf(book_branches.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, title_txt, book_publisher_txt, author_txt, branch_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            title_txt = itemView.findViewById(R.id.title_txt);
            book_publisher_txt = itemView.findViewById(R.id.book_publisher_txt);
            author_txt = itemView.findViewById(R.id.author_txt);
            branch_txt = itemView.findViewById(R.id.branch_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }
}
