package com.example.wmc.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wmc.BottomMenu;
import com.example.wmc.Konfigurasi;
import com.example.wmc.MainActivity;
import com.example.wmc.ProfilRelawan;
import com.example.wmc.R;
import com.example.wmc.adapter.ProfilAdapter;
import com.example.wmc.recycleritem.ProfilItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProfilFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ProfilItem> listItems;

    // PROFIL ATAS
    public TextView teksUsername;

    private Button btnEdit, btnLogout;

    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        recyclerView = view.findViewById(R.id.konten);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        SharedPreferences profil = this.getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        final String user = profil.getString("User", "");

        teksUsername = view.findViewById(R.id.teksUsername);
        teksUsername.setText(user);
        btnEdit = view.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfilRelawan.class);
                startActivity(intent);
            }
        });
        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Berhasil Keluar", Toast.LENGTH_LONG).show();
                SharedPreferences editor = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor hai = editor.edit();
                hai.remove("Sesi");
                hai.clear();
                hai.commit();
                Intent keluar = new Intent(getActivity(), MainActivity.class);
                startActivity(keluar);
            }
        });

        listItems = new ArrayList<>();

        loadData();
        return view;
    }

    // MENAMPILKAN DATA
    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Memuat Data...");
        progressDialog.show();

        SharedPreferences profil = this.getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        final String user = profil.getString("User", "");
        final String URL_DATA = Konfigurasi.DATA_BYRELAWAN+user;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("result");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject data = array.getJSONObject(i);
                        ProfilItem item = new ProfilItem(
                                data.getString("slug_post"),
                                data.getString("user_kode"),
                                data.getString("lokasi"),
                                data.getString("api_img"),
                                data.getString("caption"),
                                data.getString("tanggal")
                        );
                        listItems.add(item);
                    }
                    adapter = new ProfilAdapter(listItems, getContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    alertData();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                alertData();
            }
        });

        RequestQueue antrian = Volley.newRequestQueue(requireContext());
        antrian.add(stringRequest);
    }

    private void alertData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Data Tidak Ditemukan")
                .setTitle("Peringatan!");
        AlertDialog alert = builder.create();
        alert.show();
    }

}
