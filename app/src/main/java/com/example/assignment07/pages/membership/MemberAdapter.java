package com.example.assignment07.pages.membership;

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
import com.example.assignment07.pages.books.MaintainBook;

import java.util.ArrayList;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList id, name, address, Contact_num, unpaid_id;

    public MemberAdapter(Activity activity, Context context, ArrayList id, ArrayList name,
                         ArrayList address, ArrayList Contact_num, ArrayList unpaid_id) {
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.name = name;
        this.address = address;
        this.Contact_num = Contact_num;
        this.unpaid_id = unpaid_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.member_details_entry, parent, false);
        return new MyViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.name_id.setText(String.valueOf(name.get(position)));
        holder.address_id.setText(String.valueOf(address.get(position)));
        holder.Contact_num_id.setText(String.valueOf(Contact_num.get(position)));
        holder.unpaid_id.setText(String.valueOf(unpaid_id.get(position)));

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddMembership.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("address", String.valueOf(address.get(position)));
                intent.putExtra("contact number", String.valueOf(Contact_num.get(position)));
                intent.putExtra("unpaid", String.valueOf(unpaid_id.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, name_id, address_id, Contact_num_id, unpaid_id;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.textCardNo);
            name_id = itemView.findViewById(R.id.textname);
            address_id = itemView.findViewById(R.id.textAddress);
            Contact_num_id = itemView.findViewById(R.id.textCNum);
            unpaid_id = itemView.findViewById(R.id.textUnDU);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
