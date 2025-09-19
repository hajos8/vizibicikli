package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Kolcsonzes {
    private ArrayList<String[]> kolcsonzesek = new ArrayList<>();

    public ArrayList<String[]> getKolcsonzesek() {
        return kolcsonzesek;
    }
    public void setKolcsonzesek(ArrayList<String[]> kolcsonzesek) {
        this.kolcsonzesek = kolcsonzesek;
    }

    public Kolcsonzes() {
        setKolcsonzesek(this.kolcsonzesek);
    }

    public void fajleBeolvasa(String path) {
        try{
            Scanner fajlScanner = new Scanner(new File(path));

            fajlScanner.nextLine(); //header miatt

            while (fajlScanner.hasNextLine()) {
                String sor = fajlScanner.nextLine();
                String[] adatok = sor.split(";");

                ArrayList<String[]> kolcsonzesek = getKolcsonzesek();
                kolcsonzesek.add(adatok);
                setKolcsonzesek(kolcsonzesek);

            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Hiba a fájl beolvasásakor: " + e.getMessage());
        }

    }

    public ArrayList<String[]> valakiKolcsonzesei(String nev){
        ArrayList<String[]> valakiKolcsonzesei = new ArrayList<>();
        for(String[] kolcsonzes : kolcsonzesek){
            if(kolcsonzes[0].equalsIgnoreCase(nev)){
                valakiKolcsonzesei.add(kolcsonzes);
            }
        }

        return valakiKolcsonzesei;
    }

    public ArrayList<String[]> adottIdopont(String idopont){
        ArrayList<String[]> vizenLevok = new ArrayList<>();
        String[] idopontReszek = idopont.split(":");
        for(String[] kolcsonzes : kolcsonzesek){
            //Név;JAzon;EÓra;EPerc;VÓra;Vperc
            if((Integer.parseInt(kolcsonzes[2]) < Integer.parseInt(idopontReszek[0])  //Eóra kisebb mint a megadott óra
                    && Integer.parseInt(kolcsonzes[3]) < Integer.parseInt(idopontReszek[1])) //Eperc kisebb mint a megadott perc
            &&
            (Integer.parseInt(kolcsonzes[4]) > Integer.parseInt(idopontReszek[0] ) //Vóra nagyobb mint a megadott óra
                    && Integer.parseInt(kolcsonzes[5]) > Integer.parseInt(idopontReszek[1]) //Vperc nagyobb mint a megadott perc
            ))
                vizenLevok.add(kolcsonzes);{
            }
        }

        return vizenLevok;
    }
}
