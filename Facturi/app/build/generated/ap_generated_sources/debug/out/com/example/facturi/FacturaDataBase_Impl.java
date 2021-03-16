package com.example.facturi;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class FacturaDataBase_Impl extends FacturaDataBase {
  private volatile FacturaDAO _facturaDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `FacturiTable` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `furnizor` TEXT, `valoare` REAL NOT NULL, `dataSacdenta` TEXT, `status` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"695fa263b7b66c6fca1b75b10aa4bcc5\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `FacturiTable`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsFacturiTable = new HashMap<String, TableInfo.Column>(5);
        _columnsFacturiTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsFacturiTable.put("furnizor", new TableInfo.Column("furnizor", "TEXT", false, 0));
        _columnsFacturiTable.put("valoare", new TableInfo.Column("valoare", "REAL", true, 0));
        _columnsFacturiTable.put("dataSacdenta", new TableInfo.Column("dataSacdenta", "TEXT", false, 0));
        _columnsFacturiTable.put("status", new TableInfo.Column("status", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFacturiTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFacturiTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFacturiTable = new TableInfo("FacturiTable", _columnsFacturiTable, _foreignKeysFacturiTable, _indicesFacturiTable);
        final TableInfo _existingFacturiTable = TableInfo.read(_db, "FacturiTable");
        if (! _infoFacturiTable.equals(_existingFacturiTable)) {
          throw new IllegalStateException("Migration didn't properly handle FacturiTable(com.example.facturi.Factura).\n"
                  + " Expected:\n" + _infoFacturiTable + "\n"
                  + " Found:\n" + _existingFacturiTable);
        }
      }
    }, "695fa263b7b66c6fca1b75b10aa4bcc5", "6029c43ab937de0c65b2a5ffe4863a8d");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "FacturiTable");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `FacturiTable`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public FacturaDAO getFacturaDAO() {
    if (_facturaDAO != null) {
      return _facturaDAO;
    } else {
      synchronized(this) {
        if(_facturaDAO == null) {
          _facturaDAO = new FacturaDAO_Impl(this);
        }
        return _facturaDAO;
      }
    }
  }
}
