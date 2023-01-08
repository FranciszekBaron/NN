package com.company;

public class WektorCalculationService {
    public WektorCalculationService(){

    }

    public static double[] add(double[] wektor1 , double[] wektor2){
        if(wektor1.length==wektor2.length){
            double[] result = new double[wektor1.length];
            for(int i = 0;i<wektor1.length;i++){
                result[i] = wektor1[i] + wektor2[i];
            }
            return result;
        }
        return null;
    }

    public static double[] multiply(double x, double[] wektor1){

        double[] result = new double[wektor1.length];
        for(int i = 0;i<wektor1.length;i++){
            result[i] = x * wektor1[i];
        }
        return result;


    }

    public static double scalarProduct(double[] wektor1 , double[] wektor2){
        if(wektor1.length==wektor2.length){
            double result = 0;
            for(int i = 0;i<wektor1.length;i++){
                result += wektor1[i] * wektor2[i];
            }
            return result;
        }
        System.out.println("nie takie same wektory");
        return 0;
    }



}
