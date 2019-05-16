package com.example.wmc.recycleritem;

public class UserItem {

    private String slug;
    private String caption;
    private String lokasi;
    private String tanggal;

    public UserItem(String slug, String caption, String lokasi, String tanggal) {
        this.slug = slug;
        this.caption = caption;
        this.lokasi = lokasi;
        this.tanggal = tanggal;
    }

    public String getSlug() {
        return slug;
    }
    public String getCaption() {
        return caption;
    }
    public String getLokasi() {
        return lokasi;
    }

    public String getTanggal() {
        return tanggal;
    }
}
