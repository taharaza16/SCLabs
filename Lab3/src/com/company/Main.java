package com.company;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    static int elt1 = 0;
    static int lt1 = 0;
    static int lt2 = 0;
    static int lt3 = 0;
    static int mt1 = 0;
    static int mt2 = 0;
    static int mt3 = 0;
    static int mt4 = 0;
    static int mt5 = 0;
    static int mt6 = 0;
    static int mt7 = 0;
    static int mt8 = 0;
    static int st1 = 0;
    static int st2 = 0;
    static int st3 = 0;
    static int st4 = 0;
    static private void booksmall(){
        if(st1 == 0)
            st1 = 40;
        else if(st2 == 0)
            st2 = 40;
        else if(st3 == 0)
            st3 = 40;
        else if(st4 == 0)
            st4 = 40;
    }
    static private void bookmedium(){
        if(mt1 == 0)
            mt1 = 40;
        else if(mt2 == 0)
            mt2 = 40;
        else if(mt3 == 0)
            mt3 = 40;
        else if(mt4 == 0)
            mt4 = 40;
        else if(mt5 == 0)
            mt5 = 40;
        else if(mt6 == 0)
            mt6 = 40;
        else if(mt7 == 0)
            mt7 = 40;
        else if(mt8 == 0)
            mt8 = 40;
    }
    static private void booklarge(){
        if(lt1 == 0)
            lt1 = 40;
        else if(lt2 == 0)
            lt2 = 40;
        else if(lt3 == 0)
            lt3 = 40;
    }
    static private void bookextralarge(int t){
        if(elt1 == 0)
            elt1 = t;
    }
    public static void main(String[] args) {
	// write your code here

        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                if(elt1 != 0)
                    elt1--;
                if(lt1 != 0)
                    lt1 --;
                if(lt2 != 0)
                    lt2 --;
                if(lt3 != 0)
                    lt3 --;
                if(mt1 != 0)
                    mt1 --;
                if(mt2 != 0)
                    mt2 --;
                if(mt3 != 0)
                    mt3 --;
                if(mt4 != 0)
                    mt4 --;
                if(mt5 != 0)
                    mt5 --;
                if(mt6 != 0)
                    mt6 --;
                if(mt7 != 0)
                    mt7 --;
                if(mt8 != 0)
                    mt8 --;
                if(st1 != 0)
                    st1 --;
                if(st2 != 0)
                    st2 --;
                if(st3 != 0)
                    st3 --;
                if(st4 != 0)
                    st4 --;
            }
        };

        Timer timer = new Timer("MyTimer");//create a new Timer

        timer.scheduleAtFixedRate(timerTask, 30, 3000);
        int people;
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Extra Large Tables:\n"+elt1+"\nLarge Tables:\n"+lt1+"\n"+lt2+"\n"+lt3+"\nMedium Tables:\n"+mt1+"\n"+mt2+"\n"+mt3+"\n"+mt4+"\n"+mt5+"\n"+mt6+"\n"+mt7+"\n"+mt8+"\nSmall Tables:\n"+st1+"\n"+st2+"\n"+st3+"\n"+st4);
            System.out.println("Hello, To book a table enter the number of people dining:");
            people = sc.nextInt();
            if (people < 2){
                booksmall();
            }
            else if (people < 4){
                bookmedium();
            }
            else if (people < 6){
                booklarge();
            }
            else if (people < 12){
                System.out.println("Please Enter the time(for how long do you want to book the table)");
                int time = sc.nextInt();
                bookextralarge(time);
            }
            else
                System.out.println("people should be between 1 and 12");
        }
    }




}

