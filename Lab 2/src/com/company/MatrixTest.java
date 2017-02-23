package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Taha-PC on 2/22/2017.
 */
public class MatrixTest {
    @Test
    public void Matrix() throws Exception{
        Matrix m = new Matrix(2,2);
        int[] a = {2,2};
        int[] b = {m.getWidth(),m.getHeight()};
        assertArrayEquals(a,b);
    }
    @Test
    public void iterativeMultiplication() throws Exception {
        Matrix m = new Matrix(2,2);
        Matrix n = new Matrix(2,2);
        m.populate(1,1,1,1);
        n.populate(1,0,0,1);
        Matrix o = m.iterativeMultiplication(n);
        assertEquals(o.matrixarray[0][0],m.matrixarray[0][0]);
        assertEquals(o.matrixarray[0][1],m.matrixarray[0][1]);
        assertEquals(o.matrixarray[1][0],m.matrixarray[1][0]);
        assertEquals(o.matrixarray[1][1],m.matrixarray[1][1]);


    }

    @Test
    public void printMatrix() throws Exception {

    }

    @org.junit.Test
    public void getHeight() throws Exception {
        Matrix m = new Matrix(2,2);
        assertEquals(m.getHeight(),2);
    }

    @org.junit.Test
    public void getWidth() throws Exception {
        Matrix m = new Matrix(2,2);
        assertEquals(m.getWidth(),2);
    }

}