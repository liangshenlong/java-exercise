package com.liangsl.java;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        int[] m = {5,3,6,9,0,1,2,4,8,7};
        App.sort(m);

    }

    public static void sort(int[] t){
        for(int i=1;i<t.length;i++){
            for(int j=0;j<t.length-i;j++){
                if(t[j]>t[j+1]){
                    int temp = t[j];
                    t[j] = t[j+1];
                    t[j+1] =temp;
                }
            }
        }
        for(int i:t){
            System.out.printf(i+",");
        }

    }
}
