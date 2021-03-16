package com.example.facturi;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM User where email= :mail and parola= :password")
    User getUser(String mail, String password);

    @Query("SELECT * FROM User where id=:idAles LIMIT 1")
    User getUserById(int idAles);

    @Query("SELECT id FROM User where email= :mail")
    int getUserId(String mail);

    @Insert
    void insert(User user);
}
