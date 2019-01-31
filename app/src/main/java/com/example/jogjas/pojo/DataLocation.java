package com.example.jogjas.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataLocation implements Parcelable {
    @SerializedName("nama_pariwisata")
    private String nama_pariwisata;
    @SerializedName("alamat_pariwisata")
    private String alamat_pariwisata;
    @SerializedName("detail_pariwisata")
    private String detail_pariwisata;
    @SerializedName("gambar_pariwisata")
    private String gambar_pariwisata;

    protected DataLocation(Parcel in) {
        nama_pariwisata = in.readString();
        alamat_pariwisata = in.readString();
        detail_pariwisata = in.readString();
        gambar_pariwisata = in.readString();
    }

    public static final Creator<DataLocation> CREATOR = new Creator<DataLocation>() {
        @Override
        public DataLocation createFromParcel(Parcel in) {
            return new DataLocation(in);
        }

        @Override
        public DataLocation[] newArray(int size) {
            return new DataLocation[size];
        }
    };

    public String getNama_pariwisata() {
        return nama_pariwisata;
    }

    public String getAlamat_pariwisata() {
        return alamat_pariwisata;
    }

    public String getDetail_pariwisata() {
        return detail_pariwisata;
    }

    public String getGambar_pariwisata() {
        return gambar_pariwisata;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama_pariwisata);
        dest.writeString(alamat_pariwisata);
        dest.writeString(detail_pariwisata);
        dest.writeString(gambar_pariwisata);
    }
}
