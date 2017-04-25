package com.company;

public class AND implements Operation{
    @Override
    public boolean calculate(Expression exp1, Expression exp2) {
        if (exp1.evaluate() == false){
            return false;
        }
        if (exp2.evaluate() == false){
            return false;
        }
        return true;
    }
}
