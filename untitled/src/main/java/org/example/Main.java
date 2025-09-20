package org.example;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static boolean isRunningTest = false;
    public static String testNev;
    public static String testIdopont;

    public static void main(String[] args) {
        ArrayList<Kolcsonzes> kolcsonzesek = new ArrayList<>();

        //4. feladat
        try{
            Scanner fileScanner = new Scanner(new File("kolcsonzesek.txt"));

            fileScanner.nextLine(); //első sor átugrása
            while(fileScanner.hasNextLine()){
                Kolcsonzes k = new Kolcsonzes(fileScanner.nextLine());
                kolcsonzesek.add(k);
            }
        }
        catch(Exception e){
            System.out.println("Hiba a fájl megnyitásakor.");
            return;
        }

        //5. feladat
        System.out.println("5. feladat: Napi kölcsönzések száma: " + kolcsonzesek.size() + " db");

        //6. feladat
        Scanner sc = new Scanner(System.in);

        System.out.print("6. feladat: Kérek egy nevet: ");
        String nev = isRunningTest ? testNev : sc.nextLine();

        ArrayList<Kolcsonzes> nevKolcsonzesei = valakiKolcsonzesei(kolcsonzesek, nev);

        System.out.println(nevKolcsonzesei.isEmpty() ? nev.substring(0, 1).toUpperCase() + nev.substring(1) + "kölcsönzései: \n Nem volt neki kölcsönzései még." : nev.substring(0, 1).toUpperCase() + nev.substring(1) + " kölcsönzései:");

        for(Kolcsonzes kolcsonzes : nevKolcsonzesei){
            System.out.println(
                    kolcsonzes.getEOra() + ":" + (kolcsonzes.getEPerc() < 9 ? "0" + kolcsonzes.getEPerc() : kolcsonzes.getEPerc())
                    + " - " +
                    kolcsonzes.getVOra() + ":" + (kolcsonzes.getVPerc() < 9 ? "0" + kolcsonzes.getVPerc() : kolcsonzes.getVPerc()));
        }

        //7. feladat

        System.out.print("7. feladat: Adjon meg egy időpontot óra:perc alakban: ");

        String idopont = isRunningTest ? testIdopont : sc.nextLine();

        ArrayList<Kolcsonzes> vizenLevok = adottIdopont(kolcsonzesek, idopont);

        System.out.println(vizenLevok.isEmpty() ? "A vízen lévő járművek:\nNem volt vízen jármű épp." : "A vízen lévő járművek:");

        for(Kolcsonzes kolcsonzes : vizenLevok){
            System.out.println(
                            kolcsonzes.getEOra() + ":" + (kolcsonzes.getEPerc() < 9 ? "0" + kolcsonzes.getEPerc() : kolcsonzes.getEPerc())
                            + " - " +
                            kolcsonzes.getVOra() + ":" + (kolcsonzes.getVPerc() < 9 ? "0" + kolcsonzes.getVPerc() : kolcsonzes.getVPerc())
                            + " - " + kolcsonzes.getNev()
            );

        }

        //8. feladat

        System.out.println("8. feladat: A napi bevétel: " + napiBevetel(kolcsonzesek) + " Ft");

        //9. feladat

        fajlbaIr(kolcsonzesek, "F.txt");

        //10. feladat

        System.out.println("10. feladat: Statisztika");

        HashMap<String, Integer> statisztika = statisztika(kolcsonzesek);
        for(String key : statisztika.keySet()){
            System.out.println(key + " - " + statisztika.get(key) + " db");
        }

    }

    public static ArrayList<Kolcsonzes> valakiKolcsonzesei(ArrayList<Kolcsonzes> kolcsonzesek, String nev){
        ArrayList<Kolcsonzes> valakiKolcsonzesei = new ArrayList<>();
        for(Kolcsonzes kolcsonzes : kolcsonzesek){
            if(kolcsonzes.getNev().equalsIgnoreCase(nev)){
                valakiKolcsonzesei.add(kolcsonzes);
            }
        }

        return valakiKolcsonzesei;
    }



    public static ArrayList<Kolcsonzes> adottIdopont(ArrayList<Kolcsonzes> kolcsonzesek, String idopont){
        ArrayList<Kolcsonzes> vizenLevok = new ArrayList<>();
        String[] idopontReszek = idopont.split(":");
        int keresettIdopont = Integer.parseInt(idopontReszek[0]) * 60 + Integer.parseInt(idopontReszek[1]);

        for(Kolcsonzes kolcsonzes : kolcsonzesek){
            //Név;JAzon;EÓra;EPerc;VÓra;Vperc

            int elvitte = kolcsonzes.getEOra() * 60 + kolcsonzes.getEPerc();
            int visszahozta = kolcsonzes.getVOra() * 60 + kolcsonzes.getVPerc();

            if(keresettIdopont >= elvitte && keresettIdopont < visszahozta){
                vizenLevok.add(kolcsonzes);
            }

        }

        return vizenLevok;
    }

    public static int napiBevetel(ArrayList<Kolcsonzes> kolcsonzesek){
        int bevetel = 0;
        for(Kolcsonzes kolcsonzes : kolcsonzesek){
            int idoTartam = kolcsonzes.getVOra() * 60 + kolcsonzes.getVPerc() - (kolcsonzes.getEOra() * 60 + kolcsonzes.getEPerc());

            bevetel += idoTartam / 30 + (idoTartam % 30 == 0 ? 0 : 1); //minden megkezdett fél óra 2400 ft
        }

        return bevetel * 2400;
    }

    public static void fajlbaIr(ArrayList<Kolcsonzes> kolcsonzesek, String path){
        try{
            FileWriter writer = new FileWriter(path);

            for(Kolcsonzes kolcsonzes : kolcsonzesek){
                if (kolcsonzes.getJazon().equals("F")) {
                    writer.write(
                                kolcsonzes.getEOra() + ":" +
                                    (kolcsonzes.getEPerc() < 10 ? "0" + kolcsonzes.getEPerc() : kolcsonzes.getEPerc())
                                    + "-" +
                                     kolcsonzes.getVOra() + ":" +
                                    (kolcsonzes.getVPerc()  < 10 ? "0" + kolcsonzes.getVPerc() : kolcsonzes.getVPerc())
                                    + " : " + kolcsonzes.getNev()
                                    + "\n"
                    );
                }


            }

            writer.close();
        }
        catch (Exception e){
            System.out.println("Hiba a fájl írásakor: " + e.getMessage());
        }
    }

    public static HashMap<String, Integer> statisztika(ArrayList<Kolcsonzes> kolcsonzesek){
        HashMap<String, Integer> statisztika = new HashMap<>();

        for(Kolcsonzes kolcsonzes : kolcsonzesek){
            if(statisztika.containsKey(kolcsonzes.getJazon())){
                statisztika.put(kolcsonzes.getJazon(), statisztika.get(kolcsonzes.getJazon()) + 1);
            }
            else{
                statisztika.put(kolcsonzes.getJazon(), 1);
            }
        }

        return statisztika;
    }
}