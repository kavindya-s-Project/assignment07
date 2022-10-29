package com.example.assignment07.pages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assignment07.R;
import com.example.assignment07.models.BookModel;

import java.util.List;

public class BookAdapter extends ArrayAdapter<BookModel> {

    private Context context;
    private int resource;
    List<BookModel> bookModels;

    BookAdapter(Context context, int resource, List<BookModel> bookModels) {
        super(context, resource, bookModels);
        this.context = context;
        this.resource = resource;
        this.bookModels = bookModels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView title = row.findViewById(R.id.title);
        TextView publisher = row.findViewById(R.id.publisher);
        TextView bookId = row.findViewById(R.id.bookId);

        BookModel bookModel = bookModels.get(position);
        title.setText(bookModel.getTitle());
        publisher.setText(bookModel.getPublisher());
        bookId.setText(bookModel.getId());

        return row;
    }
}
