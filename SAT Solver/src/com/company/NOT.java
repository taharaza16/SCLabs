package com.company;

/**
 * Created by Taha-PC on 4/10/2017.
 */
public class NOT implements Operation {
    @Override
    public boolean calculate(Expression exp1, Expression exp2){
        return !(exp1.evaluate());
    }
    public boolean calculate(Expression exp1){
        return !(exp1.evaluate());
    }
}
