package com.example.assignment07.pages.membership;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment07.R;

import java.util.ArrayList;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MyViewHolder> {
    private Context context;
    private ArrayList id, name_id, address_id, Cnum_id, unpaid_id;

    public MemberAdapter(Context context, ArrayList id, ArrayList name_id, ArrayList address_id, ArrayList cnum_id,
                         ArrayList unpaid_id) {
        this.context = context;
        this.id = id;
        this.name_id = name_id;
        this.address_id = address_id;
        this.Cnum_id = cnum_id;
        this.unpaid_id = unpaid_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.member_details_entry, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.address_id.setText(String.valueOf(address_id.get(position)));
        holder.Cnum_id.setText(String.valueOf(Cnum_id.get(position)));
        holder.unpaid_id.setText(String.valueOf(unpaid_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, name_id, address_id, Cnum_id, unpaid_id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.textCardNo);
            name_id = itemView.findViewById(R.id.textname);
            address_id = itemView.findViewById(R.id.textAddress);
            Cnum_id = itemView.findViewById(R.id.textCNum);
            unpaid_id = itemView.findViewById(R.id.textUnDU);
        }
    }
}
