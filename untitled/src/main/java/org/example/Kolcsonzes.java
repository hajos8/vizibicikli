package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Kolcsonzes {
    private String nev;
    private String jazon;
    private int eOra;
    private int ePerc;
    private int vOra;
    private int vPerc;

    public String getNev() {
        return nev;
    }
    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getJazon() {
        return jazon;
    }
    public void setJazon(String jazon) {
        this.jazon = jazon;
    }

    public int getEOra() {
        return eOra;
    }
    public void setEOra(int eOra) {
        this.eOra = eOra;
    }

    public int getEPerc() {
        return ePerc;
    }
    public void setEPerc(int ePerc) {
        this.ePerc = ePerc;
    }

    public int getVOra() {
        return vOra;
    }
    public void setVOra(int vOra) {
        this.vOra = vOra;
    }

    public int getVPerc() {
        return vPerc;
    }
    public void setVPerc(int vPerc) {
        this.vPerc = vPerc;
    }


    public Kolcsonzes(String nev, String jazon, int eOra, int ePerc, int vOra, int vPerc) {
        this.nev = nev;
        this.jazon = jazon;
        this.eOra = eOra;
        this.ePerc = ePerc;
        this.vOra = vOra;
        this.vPerc = vPerc;
    }

    public Kolcsonzes(String sor){
        String[] adatok = sor.split(";");

        setNev(adatok[0]);
        setJazon(adatok[1]);
        setEOra(Integer.parseInt(adatok[2]));
        setEPerc(Integer.parseInt(adatok[3]));
        setVOra(Integer.parseInt(adatok[4]));
        setVPerc(Integer.parseInt(adatok[5]));

    }
}
