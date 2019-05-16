package com.example.wmc.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wmc.R;
import com.example.wmc.recycleritem.HomeItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RelawanAdapter extends RecyclerView.Adapter<RelawanAdapter.ViewHolder> {

    private List<HomeItem> listItems;
    private Context context;

    public RelawanAdapter(List<HomeItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public RelawanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new RelawanAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RelawanAdapter.ViewHolder holder, int position) {
        final HomeItem listItem = listItems.get(position);
        holder.postUserkode.setText(listItem.getUserkode());
        holder.postLokasi.setText(listItem.getLokasi());
        Picasso.get().load(listItem.getGambar()).into(holder.postGambar);
        holder.postCaption.setText(listItem.getCaption());
        holder.postTanggal.setText(listItem.getTanggal());

        holder.postGambar.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView postUserkode;
        public TextView postLokasi;
        public ImageView postGambar;
        public TextView postCaption;
        public TextView postTanggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postUserkode = (TextView)itemView.findViewById(R.id.postUserkode);
            postLokasi = (TextView)itemView.findViewById(R.id.postLokasi);
            postGambar = (ImageView)itemView.findViewById(R.id.postGambar);
            postCaption = (TextView)itemView.findViewById(R.id.postCaption);
            postTanggal = (TextView)itemView.findViewById(R.id.postTanggal);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
