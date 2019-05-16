package com.example.wmc.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wmc.DetailUserPost;
import com.example.wmc.MainActivity;
import com.example.wmc.R;
import com.example.wmc.recycleritem.DetailPostItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailPostAdapter extends RecyclerView.Adapter<DetailPostAdapter.ViewHolder> {

    private List<DetailPostItem> listItems;
    private Context context;

    public DetailPostAdapter(List<DetailPostItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public DetailPostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_user_post, viewGroup, false);
        return new DetailPostAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailPostAdapter.ViewHolder holder, int position) {
        final DetailPostItem listItem = listItems.get(position);

        Picasso.get().load(listItem.getGambar()).into(holder.postGambar);
        holder.postCaption.setText(listItem.getCaption());
        holder.postLokasi.setText(listItem.getLokasi());
        holder.postLatitude.setText(listItem.getLatitude());
        holder.postLongitude.setText(listItem.getLongitude());
        holder.postTanggal.setText(listItem.getTanggal());
        holder.postWaktu.setText(listItem.getWaktu());
        holder.postUserkode.setText(listItem.getUserkode());

        holder.postCaption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent pindah = new Intent(context.getApplicationContext(), DetailItem.class);
//                pindah.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                pindah.putExtra("Kode", listItem.getKode());
//                pindah.putExtra("Image", listItem.getGambar());
//                pindah.putExtra("Judul", listItem.getJudul());
//                pindah.putExtra("Jumlah", listItem.getJumlah());
//                pindah.putExtra("Harga", listItem.getHarga());
//                context.startActivity(pindah);
            }
        });
        holder.btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(context.getApplicationContext(), MainActivity.class);
                context.startActivity(kembali);
                ((Activity)v.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView postGambar;
        public TextView postCaption;
        public TextView postLokasi;
        public TextView postLatitude;
        public TextView postLongitude;
        public TextView postTanggal;
        public TextView postWaktu;
        public TextView postUserkode;
        public Button btnKembali;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            postGambar = itemView.findViewById(R.id.postGambar);
            postCaption = itemView.findViewById(R.id.postCaption);
            postLokasi = itemView.findViewById(R.id.postLokasi);
            postLatitude = itemView.findViewById(R.id.postLatitude);
            postLongitude = itemView.findViewById(R.id.postLongitude);
            postTanggal = itemView.findViewById(R.id.postTanggal);
            postWaktu = itemView.findViewById(R.id.postWaktu);
            postUserkode = itemView.findViewById(R.id.postUserkode);
            btnKembali = itemView.findViewById(R.id.btnKembali);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
