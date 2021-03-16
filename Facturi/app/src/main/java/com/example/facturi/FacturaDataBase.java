package com.example.facturi;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities={Factura.class},version = 1, exportSchema = false)
public abstract class FacturaDataBase extends RoomDatabase {

    public abstract FacturaDAO getFacturaDAO();
}
