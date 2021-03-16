package com.example.facturi;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FacturaDAO {
    @Insert
    void insert(Factura factura);

    @Query("SELECT * FROM FacturiTable ")
    List<Factura> getFacturi();

    @Query("SELECT valoare FROM FacturiTable ")
    List<Float> getValoareDB();

    @Query("SELECT * FROM FacturiTable WHERE valoare>300 ")
    List<Factura> getFacturiDupaValoare();

    @Query("SELECT * FROM FacturiTable WHERE status=:status")
    List<Factura> getFacturiDupaValoare(String status);

    @Delete
    void delete(Factura factura);

}
