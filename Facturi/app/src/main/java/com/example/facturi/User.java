package com.example.facturi;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName="User")
public class User implements Serializable {
    @PrimaryKey(autoGenerate=true)
    @NonNull
    private int id;
    private String nume;
    private String prenume;
    private String email;
    private String parola;

    @Override
    public String toString() {
        return "Id=" + id +
                ", nume='" + nume +
                ", prenume='" + prenume +
                ", email='" + email +
                ", parola='" + parola + ".";
    }

    public User(String nume, String prenume, String email, String parola) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.parola = parola;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }
}
