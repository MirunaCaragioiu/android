package com.example.facturi;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Float;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class FacturaDAO_Impl implements FacturaDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfFactura;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfFactura;

  public FacturaDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFactura = new EntityInsertionAdapter<Factura>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `FacturiTable`(`id`,`furnizor`,`valoare`,`dataSacdenta`,`status`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Factura value) {
        stmt.bindLong(1, value.getId());
        if (value.getFurnizor() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFurnizor());
        }
        stmt.bindDouble(3, value.getValoare());
        if (value.getDataSacdenta() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDataSacdenta());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getStatus());
        }
      }
    };
    this.__deletionAdapterOfFactura = new EntityDeletionOrUpdateAdapter<Factura>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `FacturiTable` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Factura value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void insert(Factura factura) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfFactura.insert(factura);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Factura factura) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfFactura.handle(factura);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Factura> getFacturi() {
    final String _sql = "SELECT * FROM FacturiTable ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfFurnizor = _cursor.getColumnIndexOrThrow("furnizor");
      final int _cursorIndexOfValoare = _cursor.getColumnIndexOrThrow("valoare");
      final int _cursorIndexOfDataSacdenta = _cursor.getColumnIndexOrThrow("dataSacdenta");
      final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("status");
      final List<Factura> _result = new ArrayList<Factura>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Factura _item;
        _item = new Factura();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpFurnizor;
        _tmpFurnizor = _cursor.getString(_cursorIndexOfFurnizor);
        _item.setFurnizor(_tmpFurnizor);
        final float _tmpValoare;
        _tmpValoare = _cursor.getFloat(_cursorIndexOfValoare);
        _item.setValoare(_tmpValoare);
        final String _tmpDataSacdenta;
        _tmpDataSacdenta = _cursor.getString(_cursorIndexOfDataSacdenta);
        _item.setDataSacdenta(_tmpDataSacdenta);
        final String _tmpStatus;
        _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        _item.setStatus(_tmpStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Float> getValoareDB() {
    final String _sql = "SELECT valoare FROM FacturiTable ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final List<Float> _result = new ArrayList<Float>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Float _item;
        if (_cursor.isNull(0)) {
          _item = null;
        } else {
          _item = _cursor.getFloat(0);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Factura> getFacturiDupaValoare() {
    final String _sql = "SELECT * FROM FacturiTable WHERE valoare>300 ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfFurnizor = _cursor.getColumnIndexOrThrow("furnizor");
      final int _cursorIndexOfValoare = _cursor.getColumnIndexOrThrow("valoare");
      final int _cursorIndexOfDataSacdenta = _cursor.getColumnIndexOrThrow("dataSacdenta");
      final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("status");
      final List<Factura> _result = new ArrayList<Factura>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Factura _item;
        _item = new Factura();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpFurnizor;
        _tmpFurnizor = _cursor.getString(_cursorIndexOfFurnizor);
        _item.setFurnizor(_tmpFurnizor);
        final float _tmpValoare;
        _tmpValoare = _cursor.getFloat(_cursorIndexOfValoare);
        _item.setValoare(_tmpValoare);
        final String _tmpDataSacdenta;
        _tmpDataSacdenta = _cursor.getString(_cursorIndexOfDataSacdenta);
        _item.setDataSacdenta(_tmpDataSacdenta);
        final String _tmpStatus;
        _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        _item.setStatus(_tmpStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Factura> getFacturiDupaValoare(String status) {
    final String _sql = "SELECT * FROM FacturiTable WHERE status=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (status == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, status);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfFurnizor = _cursor.getColumnIndexOrThrow("furnizor");
      final int _cursorIndexOfValoare = _cursor.getColumnIndexOrThrow("valoare");
      final int _cursorIndexOfDataSacdenta = _cursor.getColumnIndexOrThrow("dataSacdenta");
      final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("status");
      final List<Factura> _result = new ArrayList<Factura>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Factura _item;
        _item = new Factura();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpFurnizor;
        _tmpFurnizor = _cursor.getString(_cursorIndexOfFurnizor);
        _item.setFurnizor(_tmpFurnizor);
        final float _tmpValoare;
        _tmpValoare = _cursor.getFloat(_cursorIndexOfValoare);
        _item.setValoare(_tmpValoare);
        final String _tmpDataSacdenta;
        _tmpDataSacdenta = _cursor.getString(_cursorIndexOfDataSacdenta);
        _item.setDataSacdenta(_tmpDataSacdenta);
        final String _tmpStatus;
        _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        _item.setStatus(_tmpStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
