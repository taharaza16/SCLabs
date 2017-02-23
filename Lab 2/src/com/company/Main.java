package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Matrix m = new Matrix(2,2);
        Matrix n = new Matrix(2,2);
        //Matrix o = new Matrix(2,2);
        System.out.print("\n\n");
        System.out.print("\n\n");
        m.printMatrix();
        System.out.print("\n\n");
        System.out.print("\n\n");
        n.printMatrix();

        m.iterativeMultiplication(n);




    }
}
class Matrix {
    public int[][] matrixarray;
    private int width;
    private int height;
    public int getHeight(){return height;}
    public int getWidth(){return width;}
    public Matrix(int wid, int hei){
        width=wid;
        height=hei;
        matrixarray = new int[width][height];
    }
    public void populate(int a, int b, int c, int d){
        matrixarray[0][0] = a;
        matrixarray[0][1] = b;
        matrixarray[1][0] = c;
        matrixarray[1][1] = d;
    }
    public void populate(){
        Scanner sc =new Scanner(System.in);
        for (int a = 0; a<width;a++){
            for (int b = 0; b<height;b++){
                System.out.println("Enter value "+a+","+b);
                matrixarray[a][b]=sc.nextInt();
            }
        }
    }
    public Matrix iterativeMultiplication(Matrix m){
        if (height != m.getWidth()){
            return null;
        }
        else {
            Matrix result = new Matrix(width,m.getHeight());
            for (int a = 0; a<width;a++){
                for (int b = 0; b<m.height;b++){
                    int sum = 0;
                    for (int c=0; c < height; c++){
                        sum = sum + this.matrixarray[a][c] * m.matrixarray[c][b];
                    }
                    result.matrixarray[a][b] = sum;
                }

            }
            result.printMatrix();
            return result;
        }
    }
    public void printMatrix(){
        for (int a = 0; a<width;a++){
            for (int b = 0; b<height;b++){
                System.out.print(matrixarray[a][b]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

}