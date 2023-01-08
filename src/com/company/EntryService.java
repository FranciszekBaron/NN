package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.MarshalException;
import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;

public class EntryService {


    public EntryService(){
    }


    public static Entry fillWithDirectory(File path) {
        Entry entry = new Entry();
        List<ArrayList<String>> wektorXList = new ArrayList<>();
        entry.setWektorXList(wektorXList);
        String[] tmp = new String[path.listFiles().length];
        for(int i = 0 ; i<path.listFiles().length;i++){
            tmp[i] = path.listFiles()[i].getName();
        }
        entry.setInstances(tmp);
        entry.setText(new ArrayList<>());
        entry.setFileNames(new ArrayList<>());

        try {
            for(File dir : path.listFiles()){

                for (File file : dir.listFiles()) {
                    String line;
                    String txt = "";
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        txt += line;
                    }
                    txt = Normalizer.normalize(txt, Normalizer.Form.NFD);
                    txt = txt.replaceAll("ł", "l");
                    txt = txt.replaceAll("[^\\x00-\\x7F]", "");
                    txt = txt.toLowerCase(Locale.ROOT);
                    entry.getFileNames().add(file.getName());
                    ArrayList wektorX = countWektor(txt);
                    wektorX.add(dir.getName());
                    entry.getWektorXList().add(wektorX);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entry;
    }

    public static Entry fillWithFile(File file) {
        Entry entry = new Entry();
        List<ArrayList<String>> wektorXList = new ArrayList<>();
        entry.setWektorXList(wektorXList);


        try {
            String line;
            String txt = "";
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                txt += line;
            }
            txt = Normalizer.normalize(txt, Normalizer.Form.NFD);
            txt = txt.replaceAll("ł", "l");
            txt = txt.replaceAll("[^\\x00-\\x7F]", "");
            txt = txt.toLowerCase(Locale.ROOT);
            ArrayList wektorX = countWektor(txt);
            entry.getWektorXList().add(wektorX);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return entry;
    }





    public static ArrayList<String> countWektor(String txt){
        ArrayList<String> result = new ArrayList();
        for(char i = 'a';i<='z';i++){
            int count = 0;
            for(int j = 0; j<txt.length();j++){
                if(txt.charAt(j) == i){
                    count++;
                }
            }
            double tmp = (double) count / 26.0;
            result.add(String.valueOf(tmp));
        }
        return result;

    }
}
