package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
        int keresettIdopont = Integer.parseInt(idopontReszek[0]) * 60 + Integer.parseInt(idopontReszek[1]);

        for(String[] kolcsonzes : kolcsonzesek){
            //Név;JAzon;EÓra;EPerc;VÓra;Vperc

            int elvitte = Integer.parseInt(kolcsonzes[2]) * 60 + Integer.parseInt(kolcsonzes[3]);
            int visszahozta = Integer.parseInt(kolcsonzes[4]) * 60 + Integer.parseInt(kolcsonzes[5]);

            if(keresettIdopont >= elvitte && keresettIdopont < visszahozta){
                vizenLevok.add(kolcsonzes);
            }

        }

        return vizenLevok;
    }

    public int napiBevetel(){
        int bevetel = 0;
        for(String[] kolcsonzes : kolcsonzesek){
            int idoTartam =
                    (Integer.parseInt(kolcsonzes[4]) * 60 + Integer.parseInt(kolcsonzes[5])) - //visszahozott idő percekben
                            (Integer.parseInt(kolcsonzes[2]) * 60 + Integer.parseInt(kolcsonzes[3])); //elvitte idő percekben

            bevetel += idoTartam / 30 + (idoTartam % 30 == 0 ? 0 : 1); //minden megkezdett fél óra 2400 ft
        }

        return bevetel * 2400;
    }

    public void fajlbaIr(String path){
        try{
            FileWriter writer = new FileWriter(path);

            for(String[] kolcsonzes : kolcsonzesek){
                if (kolcsonzes[1].equals("F")) {
                    writer.write(kolcsonzes[2] + ":" + kolcsonzes[3] + "-" +
                            kolcsonzes[4] + ":" + kolcsonzes[5] + " : " +
                            kolcsonzes[0] + "\n");
                }


            }

            writer.close();
        }
        catch (Exception e){
            System.out.println("Hiba a fájl írásakor: " + e.getMessage());
        }
    }

    public HashMap<String, Integer> statisztika(){
        HashMap<String, Integer> statisztika = new HashMap<>();

        for(String[] kolcsonzes : kolcsonzesek){
            if(statisztika.containsKey(kolcsonzes[1])){
                statisztika.put(kolcsonzes[1], statisztika.get(kolcsonzes[1]) + 1);
            }
            else{
                statisztika.put(kolcsonzes[1], 1);
            }
        }

        return statisztika;
    }
}
