package com.example.wmc.recycleritem;

public class HomeItem {

    private String idpost;
    private String userkode;
    private String lokasi;
    private String gambar;
    private String caption;
    private String tanggal;

    public HomeItem(String idpost, String userkode, String lokasi, String gambar, String caption, String tanggal) {
        this.idpost = idpost;
        this.userkode = userkode;
        this.lokasi = lokasi;
        this.gambar = gambar;
        this.caption = caption;
        this.tanggal = tanggal;
    }

    public String getIdpost() {
        return idpost;
    }

    public String getUserkode() {
        return userkode;
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
