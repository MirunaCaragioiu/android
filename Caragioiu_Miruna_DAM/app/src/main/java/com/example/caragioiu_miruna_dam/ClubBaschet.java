package com.example.caragioiu_miruna_dam;

import java.io.Serializable;

public class ClubBaschet implements Serializable {
    private String denumire;
    private String tip;
    private int numarMembrii;
    private int anInfiintare;

    @Override
    public String toString() {
        return "ClubBaschet{" +
                "denumire='" + denumire + '\'' +
                ", tip='" + tip + '\'' +
                ", numarMembrii=" + numarMembrii +
                ", anInfiintare=" + anInfiintare +
                '}';
    }

    public ClubBaschet(String denumire, String tip, int numarMembrii, int anInfiintare) {
        this.denumire = denumire;
        this.tip = tip;
        this.numarMembrii = numarMembrii;
        this.anInfiintare = anInfiintare;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getNumarMembrii() {
        return numarMembrii;
    }

    public void setNumarMembrii(int numarMembrii) {
        this.numarMembrii = numarMembrii;
    }

    public int getAnInfiintare() {
        return anInfiintare;
    }

    public void setAnInfiintare(int anInfiintare) {
        this.anInfiintare = anInfiintare;
    }
}
