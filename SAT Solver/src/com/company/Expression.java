package com.company;

import java.util.List;

public interface Expression {
    boolean evaluate();

    List<Simple> calculatePropositions();
}

