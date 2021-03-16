package com.example.facturi;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="FacturiTable")
public class Factura implements Parcelable {
    @PrimaryKey(autoGenerate=true)
    @NonNull
    private int id;
    private String furnizor;
    private float valoare;
    private String dataSacdenta;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Factura(String furnizor, float valoare, String dataSacdenta, String status) {
        this.furnizor = furnizor;
        this.valoare = valoare;
        this.dataSacdenta = dataSacdenta;
        this.status = status;
    }

    public Factura() {
        this.furnizor = "";
        this.valoare = 0;
        this.dataSacdenta = "";
        this.status = "";
    }

    protected Factura(Parcel in) {
        furnizor = in.readString();
        valoare = in.readFloat();
        dataSacdenta = in.readString();
        status=in.readString();
    }

    public static final Creator<Factura> CREATOR = new Creator<Factura>() {
        @Override
        public Factura createFromParcel(Parcel in) {
            return new Factura(in);
        }

        @Override
        public Factura[] newArray(int size) {
            return new Factura[size];
        }
    };

    public String getFurnizor() {
        return furnizor;
    }

    public float getValoare() {
        return valoare;
    }

    public String getDataSacdenta() {
        return dataSacdenta;
    }

    public void setFurnizor(String furnizor) {
        this.furnizor = furnizor;
    }

    public void setValoare(float valoare) {
        this.valoare = valoare;
    }

    public void setDataSacdenta(String dataSacdenta) {
        this.dataSacdenta = dataSacdenta;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(furnizor);
        parcel.writeFloat(valoare);
        parcel.writeString(dataSacdenta);
        parcel.writeString(status);
    }

    @Override
    public String toString() {
        return "Furnizor=" + furnizor +
                ", valoarea=" + valoare +
                ", data=" + dataSacdenta+ "." ;
    }

}
