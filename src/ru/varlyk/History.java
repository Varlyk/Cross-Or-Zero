package ru.varlyk;

public class History {
    private final char[] hero;
    private final int[] cell;
    private final char DEFAULT = ' ';
    private int step = 0;

    public History(int maxLength) {
        hero = new char[maxLength];
        cell = new int[maxLength];
    }

    public void putHistory(char str, int pos) {
        hero[step] = str;
        cell[step] = pos;
        step ++;
        if(step > hero.length) step --;
    }
    public char lastHero() {
        char value;
        if((step -1) < 0) {
            value = hero[step] ;
            hero[step] = DEFAULT;
        }else {
            value = hero[step -1] ;
            hero[step -1] = DEFAULT;
        }
        return value;
    }
    public int lastCell() {
        int value;
        if((step -1) < 0) {
            value = cell[step] ;
            cell[step] = 0;
        }else {
        value = cell[step -1] ;
        cell[step -1] = 0;
        }
        return value;
    }
    public void backStep() {
        step --;
    }

    public  void printHistory() {
        for(int i =0; i< hero.length; i++) {
            System.out.println("Step " + i +", Cell "+ cell[i] + ", Hero "+ hero[i]);
        }
    }
}
