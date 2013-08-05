package ru.varlyk.player;

import java.util.Random;

public class Ai {

    private final char hero;
    Random random = new Random();

    public Ai(char hero) {
        this.hero = hero;
    }

    public int aiStep(int max) {
        return random.nextInt(max);

    }

    public char getHero() {
        return hero;
    }

}
