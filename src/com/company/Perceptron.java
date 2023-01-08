package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Perceptron {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";


    public Entry entry;
    public double threshold;
    public double alpha;
    public double[] wektorW;
    public String name;
    

    public Perceptron(Entry entry,double alpha,String name){
        this.entry = entry;
        this.threshold = (Math.random()*10)-5;
        this.alpha=alpha;

        this.wektorW = new double[entry.getWektorXList().get(0).size()-1];
        for(int i = 0;i<wektorW.length;i++){
            wektorW[i] = (Math.random()*10)-5;
        }
        this.name=name;

    }

    public void setEntry(Entry entry){
        this.entry =entry;
    }

    public void learn(int iteration){
        int iter=0;
        double net = 0;
        double globalError=0;

        Collections.shuffle(entry.getWektorXList());
        List<ArrayList<String>> wektorXList = new ArrayList<>();
        for(int i = 0 ; i<entry.getWektorXList().size();i++){
            ArrayList<String> tmp = new ArrayList<>(entry.getWektorXList().get(i));
            wektorXList.add(tmp);
        }


        List<double[]> list = new ArrayList<>();


        for (ArrayList<String> arrayList : wektorXList) {
            if(arrayList.get(arrayList.size()-1).equals(this.name)){
                arrayList.remove(arrayList.size()-1);
                arrayList.add("1");
            }else {
                arrayList.remove(arrayList.size()-1);
                arrayList.add("0");
            }

            double[] tmp = new double[arrayList.size()-1];
            for (int i= 0 ; i<arrayList.size()-1;i++){
                tmp[i] = Double.parseDouble(arrayList.get(i));
            }
            list.add(tmp);

        }


        do{
            for(int i =0; i < list.size();i++){

                net = WektorCalculationService.scalarProduct(list.get(i),wektorW);
                System.out.println(net);

                int y = (net>=threshold?1:0);

                System.out.println(Arrays.toString(list.get(i)));
                System.out.println(ANSI_CYAN + Arrays.toString(wektorW)+ANSI_RESET);
                if(y!=0){
                    System.out.println(ANSI_RED + this.name+ANSI_RESET);
                }else {
                    System.out.println(ANSI_RED+"unknowed"+ANSI_RESET);
                }
                System.out.println(ANSI_YELLOW+ (entry.getWektorXList().get(i).get(wektorXList.get(i).size()-1)+ANSI_RESET));
                if(y!=Double.parseDouble(wektorXList.get(i).get(wektorXList.get(i).size()-1))){
                    double[] wektorPrime = WektorCalculationService.add(wektorW,(WektorCalculationService.multiply((Double.parseDouble(wektorXList.get(i).get(wektorXList.get(i).size()-1))-y)*alpha,list.get(i))));
                    this.wektorW = wektorPrime;
                    this.threshold = threshold + ((Double.parseDouble(wektorXList.get(i).get(wektorXList.get(i).size()-1))-y)-y)*alpha*-1;
                    System.out.println("updating!!");
                }
                globalError += (Double.parseDouble(wektorXList.get(i).get(wektorXList.get(i).size()-1))-y);
            }
            iter++;
        }while (globalError!=0&&iter<=iteration);

        for (ArrayList<String>ar:wektorXList) {
            ar.clear();
        }
        wektorXList.clear();

    }

    public void test(Entry entry) {
        int err=0;
        List<ArrayList<String>> wektorXList = new ArrayList<>();
        for(int i = 0 ; i<entry.getWektorXList().size();i++){
            ArrayList<String> tmp = new ArrayList<>(entry.getWektorXList().get(i));
            wektorXList.add(tmp);
        }

        double net = 0;

        List<double[]> list = new ArrayList<>();
        for (ArrayList<String> arrayList : entry.getWektorXList()) {
            double[] tmp = new double[arrayList.size()-1];
            for (int i= 0 ; i<arrayList.size()-1;i++){
                tmp[i] = Double.parseDouble(arrayList.get(i));
            }
            list.add(tmp);
        }

        int y = 0;


        for (int i = 0; i < entry.getWektorXList().size(); i++) {

                net = WektorCalculationService.scalarProduct(list.get(i), wektorW);
                y = (net >= threshold ? 1 : 0);

                if (y == 1) {
                    System.out.println(entry.getFileNames().get(i));
                    System.out.println(ANSI_YELLOW + this.name + ANSI_RESET);
                    err=1;
                }

        }


        for (ArrayList<String>ar:wektorXList) {
            ar.clear();
        }
        wektorXList.clear();

    }

    public void test(String txt) {
        ArrayList<String> result = EntryService.countWektor(txt);
        double[] tmp = new double[result.size()];
        for (int i= 0 ; i<result.size();i++){
            tmp[i] = Double.parseDouble(result.get(i));
        }

        double net = 0;
        int y = 0;

        net = WektorCalculationService.scalarProduct(tmp, wektorW);
        y = (net >= threshold ? 1 : 0);
        if(y==1){
            System.out.println("Ten tekst prawdopodobnie jest w jezyku: ");
            System.out.println(ANSI_YELLOW + this.name + ANSI_RESET);
        }
    }





}
