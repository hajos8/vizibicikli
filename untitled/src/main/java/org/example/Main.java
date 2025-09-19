package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static boolean isRunningTest = false;
    public static String testNev;
    public static String testIdopont;

    public static void main(String[] args) {
        Kolcsonzes kolcsonzes = new Kolcsonzes();

        //4. feladat
        kolcsonzes.fajleBeolvasa("kolcsonzesek.txt");

        //5. feladat
        System.out.println("5. feladat: Napi kölcsönzések száma: " + kolcsonzes.getKolcsonzesek().size() + " db");

        //6. feladat
        Scanner sc = new Scanner(System.in);

        System.out.print("6. feladat: Kérek egy nevet: ");
        String nev = isRunningTest ? testNev : sc.nextLine();

        ArrayList<String[]> nevKolcsonzesei = kolcsonzes.valakiKolcsonzesei(nev);

        System.out.println(nevKolcsonzesei.isEmpty() ? nev.substring(0, 1).toUpperCase() + nev.substring(1) + "kölcsönzései: \n Nem volt neki kölcsönzései még." : nev.substring(0, 1).toUpperCase() + nev.substring(1) + " kölcsönzései:");

        for(String[] kolcsonzesei : nevKolcsonzesei){
            System.out.println(kolcsonzesei[2] + ":" + (kolcsonzesei[3].length() == 1 ? "0" + kolcsonzesei[3] : kolcsonzesei[3])
                    + "-" + kolcsonzesei[4] + ":"
                    + (kolcsonzesei[5].length() == 1 ? "0" + kolcsonzesei[5] : kolcsonzesei[5]));
        }

        //7. feladat

        System.out.print("7. feladat: Adjon meg egy időpontot óra:perc alakban: ");

        String idopont = isRunningTest ? testIdopont : sc.nextLine();

        ArrayList<String[]> vizenLevok = kolcsonzes.adottIdopont(idopont);

        System.out.println(vizenLevok.isEmpty() ? "A vízen lévő járművek:\nNem volt vízen jármű épp." : "A vízen lévő járművek:");

        for(String[] kolcsonzesei : vizenLevok){
            System.out.println(kolcsonzesei[2] + ":" + (kolcsonzesei[3].length() == 1 ? "0" + kolcsonzesei[3] : kolcsonzesei[3])
                    + "-" + kolcsonzesei[4] + ":"
                    + (kolcsonzesei[5].length() == 1 ? "0" + kolcsonzesei[5] : kolcsonzesei[5]) +
                    " : " + kolcsonzesei[0]);
        }
    }
}