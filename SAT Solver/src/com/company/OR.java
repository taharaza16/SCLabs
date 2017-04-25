package com.company;

public class OR implements Operation{

    @Override
    public boolean calculate(Expression exp1, Expression exp2) {
        if (exp1.evaluate() == true){
            return true;
        }
        if (exp2.evaluate() == true){
            return true;
        }
        return false;
    }
}
