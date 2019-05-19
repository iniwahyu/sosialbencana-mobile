package com.example.wmc.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wmc.DetailUserPost;
import com.example.wmc.R;
import com.example.wmc.recycleritem.UserItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private List<UserItem> listItems;
    private Context context;

    public UserAdapter(List<UserItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_post, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final UserItem listItem = listItems.get(position);
        holder.postSlug.setText(listItem.getSlug());
        holder.postCaption.setText(listItem.getCaption());
        holder.postLokasi.setText(listItem.getLokasi());
        holder.postTanggal.setText(listItem.getTanggal());

        holder.postCaption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(context.getApplicationContext(), DetailUserPost.class);
                pindah.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                pindah.putExtra("Slug", listItem.getSlug());
                pindah.putExtra("Caption", listItem.getCaption());
                context.startActivity(pindah);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView postSlug;
        public TextView postUserkode;
        public TextView postCaption;
        public TextView postLokasi;
        public TextView postTanggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postSlug = itemView.findViewById(R.id.postSlug);
            postSlug.setVisibility(View.GONE);
            postUserkode = itemView.findViewById(R.id.postUserkode);
            postCaption = itemView.findViewById(R.id.postCaption);
            postLokasi = itemView.findViewById(R.id.postLokasi);
            postTanggal = itemView.findViewById(R.id.postTanggal);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
