package com.company;

import java.util.List;

public class Combined implements Expression{
    Expression left;
    Expression right;

    Operation op;
    public Combined(Expression left, Expression right, Operation op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public boolean evaluate() {
        return op.calculate(left,right);
    }

    @Override
    public List<Simple> calculatePropositions() {
        List a = left.calculatePropositions();
        for (Expression temp : right.calculatePropositions()) {
            if(!(a.contains(temp)))
                a.add(temp);

        }
        return a;

    }
    private String loop(List<Simple> basicProps, int index){
        if(index < basicProps.size()){
            basicProps.get(index).setValue(true);
            String str1 = loop(basicProps,index+1);
            basicProps.get(index).setValue(false);
            String str2 = loop(basicProps,index+1);

            if (str1 == null){
                return str2;
            }
            else if(str2 == null){
                return str1;
            }
            else {
                return str1.concat(" (OR) ").concat(str2);
            }

        }
        else {
            if (this.evaluate()){
                System.out.print(basicProps.get(0).getLetter()+": ");
                System.out.println(basicProps.get(0).getValue());
                String prop = "( ";
                if(!basicProps.get(0).getValue())
                    prop = prop.concat(" (NOT) ");
                prop= prop.concat(""+basicProps.get(0).getLetter());
                for (int i = 1;i<basicProps.size();i++) {
                    System.out.print(basicProps.get(i).getLetter()+": ");
                    System.out.println(basicProps.get(i).getValue());
                    prop = prop.concat(" (AND) ");
                    if(!basicProps.get(i).getValue())
                        prop = prop.concat(" (NOT) ");
                    prop = prop.concat(""+basicProps.get(i).getLetter());

                }
                prop  = prop.concat(" )");
                System.out.println(prop);
                return prop;
            }
            else
                return null;
        }

    }
    public String trueVals(){
        return loop(this.calculatePropositions(),0);

    }
}

