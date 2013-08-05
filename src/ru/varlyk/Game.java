package ru.varlyk;

import ru.varlyk.player.Ai;
import ru.varlyk.player.Player;

public class Game {

    private static final char CROSS = 'X';
    private static final char ZERO = 'O';

    private boolean playerStep = true;
    private boolean game = true;
    private Field field;
    private Player plauer;
    private History history;
    private Ai ai;


    public void startGame() {

        System.out.println("======================");
        System.out.println("Welcome to the battle!");
        System.out.println("====Cross Or Zero=====");
        System.out.println("======================");
        initiatePlayer();
        System.out.println("Select the level of difficulty: 1, 2, 3");
        switch (Player.inputInt()){
            case 1:
                field = new Field(3);
                break;
            case 2:
                field = new Field(4);
                break;
            case 3:
                field = new Field(5);
                break;
            default:
                field = new Field(3);
                break;
        }
        history = new History(field.MAX_FIELD_LENGTH);
        initiateAI();
        rules();
        Player.inputInt();
        run();

    }
    private void rules() {
        System.out.println("========RULES========");
        System.out.println("Enter the number of the cell that needs to be changed");
        System.out.println("The cells are considered from right to left and top to bottom");
        System.out.println("Good luck.");
        System.out.println(" Enter any number");
    }
    private void initiateAI() {
        if(plauer.getHero() == CROSS) ai = new Ai(ZERO);
        else if(plauer.getHero() == ZERO) ai = new Ai(CROSS);

    }
    private void initiatePlayer() {
        int hero = 0;
        while (hero != 1 || hero !=2) {
            System.out.println("Select your Hero: 1-X, 2-O");
            hero =  Player.inputInt();
            if(hero == 1) {
                plauer = new Player(CROSS);
                break;
            }
            if(hero == 2) {
                plauer = new Player(ZERO);
                break;
            }
             System.out.println("Wrong choice, try again");
        }
        System.out.println("Tell me your name? Hero!");
        plauer.setName(Player.inputString());
        System.out.println("======================");
    }

    private void run() {
        field.drawField();
        while (game) {
            playerStep = true;
            playerStep();
            field.drawField();
            if(field.chekWin(plauer.getHero())) {
                gameOver(plauer.getHero());
                break;
            }
            aiStep();
            field.drawField();
            if(field.chekWin(ai.getHero())) {
                gameOver(ai.getHero());
                break;
            }


        }
    }
    private void aiStep() {
        while (!playerStep) {
            //System.out.println(ai.aiStep(field.MAX_FIELD_LENGTH));
           int input = ai.aiStep(field.MAX_FIELD_LENGTH);
           if(field.chekBusy(input))continue;
            else {
                playerStep =true;
                history.putHistory(ai.getHero(), input);
                field.setCell(ai.getHero(), input);
            }
        }
    }
    private void playerStep() {
        while (playerStep){
            int input = Player.inputInt();
            if (input < 1 || input >field.MAX_FIELD_LENGTH) {
                System.out.println("Wrong number, try again");
                continue;
            } else if(field.chekBusy(input-1)) {
                System.out.println("Cell not empty, try again");
                continue;
            }else {
                playerStep = false;
                history.putHistory(plauer.getHero(), input);
                field.setCell(plauer.getHero(), input - 1);
            }
        }
    }
     private void gameOver(char hero) {
         game = false;
         if(hero != plauer.getHero()) {
             System.out.println("Sorry "+plauer.getName()+", you lose");
         }else {
             System.out.println("Congratulations "+plauer.getName()+"! You won!");
         }
         plauer = null;
         field = null;
         int value = 0;
         while (value != 1 || value !=2) {
             System.out.println("Again? 1-yes, 2-no");
             value = Player.inputInt();
             if(value == 1) {
                 game = true;
                 startGame();
             }else if(value == 2) {
                 System.out.println("Want to see the story? 1-yes, 2-no");
                 if(Player.inputInt() == 1) {
                     history.printHistory();
                     history = null;
                 }else  break;

             }else System.out.println("Wrong choice, try again");
         }
     }
}
