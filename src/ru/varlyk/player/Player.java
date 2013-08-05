package ru.varlyk.player;

import java.util.Scanner;

public class Player {

    private static final char DEFAULT = ' ';
    private static final Scanner scaner = new Scanner(System.in);

    private final char hero;
   // private final char[] field;

    private  String name;

    public Player(char hero) {
        this.hero = hero;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getHero() {
        return hero;
    }

    public static String inputString() {
        return scaner.next();
    }

    public static int inputInt() {
        return scaner.nextInt();
    }


}
