package com.example.wmc.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wmc.R;
import com.example.wmc.adapter.ProfilAdapter;
import com.example.wmc.adapter.UserAdapter;
import com.example.wmc.recycleritem.HomeItem;
import com.example.wmc.recycleritem.ProfilItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    public String nama = "Wahyu Rizky";
    public String username = "iniwahyu";
    private final String URL_DATA = "http://dinusheroes.com/sosialbencana/api_admin/userposting/"+username;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ProfilItem> listItems;

    // PROFIL ATAS
    public TextView teksNama;
    public TextView teksUsername;

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
        teksNama = (TextView)view.findViewById(R.id.teksNama);
        teksNama.setText(nama);
        teksUsername = (TextView)view.findViewById(R.id.teksUsername);
        teksUsername.setText(username);

        listItems = new ArrayList<>();

        loadData();
        return view;
    }

    // MENAMPILKAN DATA
    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Memuat Data...");
        progressDialog.show();

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
                                data.getString("id_post"),
                                data.getString("username"),
                                data.getString("lokasi"),
                                data.getString("nama_img"),
                                data.getString("caption"),
                                data.getString("tanggal")
                        );
                        listItems.add(item);
                    }
                    adapter = new ProfilAdapter(listItems, getContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue antrian = Volley.newRequestQueue(requireContext());
        antrian.add(stringRequest);
    }

}
