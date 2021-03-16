package com.example.schimbcase;

import java.io.Serializable;
import java.util.Date;

public class HomeExchange implements Serializable {
    private String adresa;
    private int nrCamere;
    private float suprafata;
    private Date perioada;
    private String tipLocuinta;

    @Override
    public String toString() {
        return "HomeExchange{" +
                "adresa='" + adresa + '\'' +
                ", nrCamere=" + nrCamere +
                ", suprafata=" + suprafata +
                ", perioada=" + perioada +
                ", tipLocuinta='" + tipLocuinta + '\'' +
                '}';
    }

    public HomeExchange(String adresa, int nrCamere, float suprafata, Date perioada, String tipLocuinta) {
        this.adresa = adresa;
        this.nrCamere = nrCamere;
        this.suprafata = suprafata;
        this.perioada = perioada;
        this.tipLocuinta = tipLocuinta;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getNrCamere() {
        return nrCamere;
    }

    public void setNrCamere(int nrCamere) {
        this.nrCamere = nrCamere;
    }

    public float getSuprafata() {
        return suprafata;
    }

    public void setSuprafata(float suprafata) {
        this.suprafata = suprafata;
    }

    public Date getPerioada() {
        return perioada;
    }

    public void setPerioada(Date perioada) {
        this.perioada = perioada;
    }

    public String getTipLocuinta() {
        return tipLocuinta;
    }

    public void setTipLocuinta(String tipLocuinta) {
        this.tipLocuinta = tipLocuinta;
    }
}
