package com.example.facturi;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings("unchecked")
public final class UserDAO_Impl implements UserDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfUser;

  public UserDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `User`(`id`,`nume`,`prenume`,`email`,`parola`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        stmt.bindLong(1, value.getId());
        if (value.getNume() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNume());
        }
        if (value.getPrenume() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPrenume());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEmail());
        }
        if (value.getParola() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getParola());
        }
      }
    };
  }

  @Override
  public void insert(User user) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser.insert(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public User getUser(String mail, String password) {
    final String _sql = "SELECT * FROM User where email= ? and parola= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (mail == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, mail);
    }
    _argIndex = 2;
    if (password == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, password);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfNume = _cursor.getColumnIndexOrThrow("nume");
      final int _cursorIndexOfPrenume = _cursor.getColumnIndexOrThrow("prenume");
      final int _cursorIndexOfEmail = _cursor.getColumnIndexOrThrow("email");
      final int _cursorIndexOfParola = _cursor.getColumnIndexOrThrow("parola");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpNume;
        _tmpNume = _cursor.getString(_cursorIndexOfNume);
        final String _tmpPrenume;
        _tmpPrenume = _cursor.getString(_cursorIndexOfPrenume);
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final String _tmpParola;
        _tmpParola = _cursor.getString(_cursorIndexOfParola);
        _result = new User(_tmpNume,_tmpPrenume,_tmpEmail,_tmpParola);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getUserById(int idAles) {
    final String _sql = "SELECT * FROM User where id=? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, idAles);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfNume = _cursor.getColumnIndexOrThrow("nume");
      final int _cursorIndexOfPrenume = _cursor.getColumnIndexOrThrow("prenume");
      final int _cursorIndexOfEmail = _cursor.getColumnIndexOrThrow("email");
      final int _cursorIndexOfParola = _cursor.getColumnIndexOrThrow("parola");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpNume;
        _tmpNume = _cursor.getString(_cursorIndexOfNume);
        final String _tmpPrenume;
        _tmpPrenume = _cursor.getString(_cursorIndexOfPrenume);
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final String _tmpParola;
        _tmpParola = _cursor.getString(_cursorIndexOfParola);
        _result = new User(_tmpNume,_tmpPrenume,_tmpEmail,_tmpParola);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getUserId(String mail) {
    final String _sql = "SELECT id FROM User where email= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (mail == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, mail);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
