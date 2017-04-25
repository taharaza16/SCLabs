package com.company;

import java.util.List;

public class NotExpression implements Expression{


    Expression oneandonly;
    NOT op = new NOT();

    public NotExpression(Expression oneandonly) {
        this.oneandonly = oneandonly;
    }

    @Override
    public boolean evaluate() {
        return op.calculate(oneandonly);
    }

    @Override
    public List<Simple> calculatePropositions() {
        return oneandonly.calculatePropositions();
    }
}
