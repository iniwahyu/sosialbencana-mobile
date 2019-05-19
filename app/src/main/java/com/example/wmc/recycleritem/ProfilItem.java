package com.example.wmc.recycleritem;

public class ProfilItem {

    private String slug;
    private String username;
    private String lokasi;
    private String gambar;
    private String caption;
    private String tanggal;

    public ProfilItem(String slug, String username, String lokasi, String gambar, String caption, String tanggal) {
        this.slug = slug;
        this.username = username;
        this.lokasi = lokasi;
        this.gambar = gambar;
        this.caption = caption;
        this.tanggal = tanggal;
    }

    public String getSlug() {
        return slug;
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
