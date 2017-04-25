package com.company;

import java.util.ArrayList;
import java.util.List;

public class Simple implements Expression {


    char letter;
    boolean value;


    public char getLetter() {
        return letter;
    }
    public boolean getValue() {
        return value;
    }
    public void setValue(boolean value) {
        this.value = value;
    }


    Simple(char letter, boolean value){
        this.letter = letter;
        this.value = value;
    }
    Simple(char letter){
        this.letter = letter;
    }

    @Override
    public boolean evaluate() {
        return value;
    }

    @Override
    public List<Simple> calculatePropositions() {
        List a = new ArrayList<>();
        a.add(this);
        return a;
    }
}
