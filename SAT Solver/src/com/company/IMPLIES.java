package com.company;

public class IMPLIES implements Operation{
    @Override
    public boolean calculate(Expression exp1, Expression exp2) {
        if (exp1.evaluate() == false){
            return true;
        }
        else {
            if (exp2.evaluate() == true) {
                return true;
            }
            else
                return false;
        }

    }
}
