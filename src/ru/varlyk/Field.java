package ru.varlyk;

public class Field {

    public  final int MAX_FIELD_LENGTH;

    private static final int DEFAULT_SIZE = 3;
    private static final char DEFAULT_VALUE = ' ';

    private final int fieldSize;
    private char[] field;

    public Field() {
        this(DEFAULT_SIZE);
    }

    public Field(int size) {
        fieldSize = size;
        MAX_FIELD_LENGTH =  (size * size);
        field = new char[MAX_FIELD_LENGTH];
        clearField();
    }
    public char[] getField() {
        return field;
    }
    public void clearField() {
        for(int i=0; i<field.length; i++) {
            field[i] = DEFAULT_VALUE;
        }
    }
    public  boolean chekBusy(int pos) {
        if(field[pos] != DEFAULT_VALUE) return true;
        return false;
    }

    public void drawField() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        for(int i=0; i<fieldSize; i++) {
            drawLine(fieldSize * i);
        }
    }

    private void drawLine (int startPos) {
        for(int i = 0;i<fieldSize; i++) {
            System.out.print("[" + field[startPos + i] + "]");
        }
        System.out.println();
    }

    public void setCell(char value, int pos) {
        field[pos] = value;
    }

   public boolean chekWin(char value) {
       boolean result = false;
        for(int i=0; i<fieldSize; i++) {
           result = chekLine(i, fieldSize, value);
            if(result) return result;
       }
       for(int i=0; i<field.length; i+= fieldSize) {
           result = chekLine(i, 1, value);
           if(result) return result;
       }
       if( chekLine(0, fieldSize + 1, value)) return true;
       if( chekLine(fieldSize-1, fieldSize - 1, value)) return true;

       return  result;
   }

    private boolean chekLine(int startPos, int step, char value) {
        boolean result = false;
        for(int i = 0; i < fieldSize; i++) {
            result = chekCell(startPos, value);
            if(!result) return result;
            startPos += step;
        }
        return  result;
    }
    private boolean chekCell(int startPos, char value) {
        if(field[startPos] == value) return true;
        return false;
    }
}
