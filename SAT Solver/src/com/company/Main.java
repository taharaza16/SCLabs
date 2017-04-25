package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Simple a = new Simple('a',true);
        Expression c = new Simple('c',true);
        Simple b = new Simple('b',false);
        NotExpression n = new NotExpression(c);

        Combined complicated = new Combined(c,n,new AND());

        String temp =complicated.trueVals();
        if(temp == null){
            System.out.println(c);
        }
        else
        /*

        Combined complicated = new Combined(a,b,new IMPLIES());
        Combined newone = new Combined(complicated,n, new AND());
        Expression n2 = new Combined(a,b, new AND());
        Combined n3 = new Combined(n2,n, new AND());
        System.out.println(complicated.evaluate());

        String args1 =  newone.trueVals();
        System.out.println(args1);
        String[] firstargs = args1.split("\\Q (OR) \\E");
        for (int i = 0;i<firstargs.length;i++){
            System.out.println(firstargs[i]);
        }

        String args2 = n3.trueVals();
        String[] secondargs = args2.split("\\Q (OR) \\E");
        for (int i = 0;i<secondargs.length;i++){
            System.out.println(secondargs[i]);
        }


        for (int i = 0;i<firstargs.length;i++){
            for (int j = 0;j<secondargs.length;j++){
                if (firstargs[i].contentEquals(secondargs[j])){
                    System.out.println("YAAAAAAAAAAAAAAAAAY");
                    System.out.println(firstargs[i]);
                }
            }
        }
        */

        /*
        System.out.println(x.getClass().toString());
        if(x.getClass().toString().contentEquals("class com.company.Simple"))
            System.out.println(x.getClass());

        List<Expression> ls = n3.calculatePropositions();
        System.out.println(ls.size());
        for (Expression temp : ls) {
            System.out.println(temp.getClass());

        }
        */

    }
}
