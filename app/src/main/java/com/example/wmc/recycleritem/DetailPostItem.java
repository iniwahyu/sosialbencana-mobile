package com.example.wmc.recycleritem;

public class DetailPostItem {

    private String gambar;
    private String caption;
    private String lokasi;
    private String latitude;
    private String longitude;
    private String tanggal;
    private String waktu;
    private String userkode;

    public DetailPostItem(String gambar, String caption, String lokasi, String latitude, String longitude, String tanggal, String waktu, String userkode) {
        this.gambar = gambar;
        this.caption = caption;
        this.lokasi = lokasi;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.userkode = userkode;
    }

    public String getGambar() {
        return gambar;
    }

    public String getCaption() {
        return caption;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getUserkode() {
        return userkode;
    }
}
