package com.example.wmc.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmc.DeletePost;
import com.example.wmc.recycleritem.HomeItem;
import com.example.wmc.R;
import com.example.wmc.recycleritem.ProfilItem;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class ProfilAdapter extends RecyclerView.Adapter<ProfilAdapter.ViewHolder> {

    private List<ProfilItem> listItems;
    private Context context;

    public ProfilAdapter(List<ProfilItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_profil, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ProfilItem listItem = listItems.get(position);
        holder.postLokasi.setText(listItem.getLokasi());
        Picasso.get().load(listItem.getGambar()).into(holder.postGambar);
        holder.postCaption.setText(listItem.getCaption());
        holder.postTanggal.setText(listItem.getTanggal());
        holder.postSlug.setText(listItem.getSlug());

        holder.postGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(context.getApplicationContext(), DeletePost.class);
                pindah.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                pindah.putExtra("Slug", listItem.getSlug());
//                pindah.putExtra("Image", listItem.getGambar());
//                pindah.putExtra("Judul", listItem.getJudul());
//                pindah.putExtra("Jumlah", listItem.getJumlah());
//                pindah.putExtra("Harga", listItem.getHarga());
                context.startActivity(pindah);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // PROFIL BAWAH
        public TextView postLokasi;
        public ImageView postGambar;
        public TextView postCaption;
        public TextView postTanggal;
        public TextView postSlug;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postLokasi = itemView.findViewById(R.id.postLokasi);
            postGambar = itemView.findViewById(R.id.postGambar);
            postCaption = itemView.findViewById(R.id.postCaption);
            postTanggal = itemView.findViewById(R.id.postTanggal);
            postSlug = itemView.findViewById(R.id.postSlug);
            postSlug.setVisibility(View.GONE);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
