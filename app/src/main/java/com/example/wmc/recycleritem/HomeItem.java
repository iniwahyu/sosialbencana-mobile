package com.example.wmc.recycleritem;

public class HomeItem {

    private String idpost;
    private String username;
    private String lokasi;
    private String gambar;
    private String caption;
    private String tanggal;

    public HomeItem(String idpost, String username, String lokasi, String gambar, String caption, String tanggal) {
        this.idpost = idpost;
        this.username = username;
        this.lokasi = lokasi;
        this.gambar = gambar;
        this.caption = caption;
        this.tanggal = tanggal;
    }

    public String getIdpost() {
        return idpost;
    }

    public String getUsername() {
        return username;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getGambar() {
        return gambar;
    }

    public String getCaption() {
        return caption;
    }

    public String getTanggal() {
        return tanggal;
    }
}
