package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Entry {
    private List<ArrayList<String>> wektorXList;
    private String[] instances;
    private List<ArrayList<String>> text;
    private ArrayList<String> fileNames;

    public ArrayList<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(ArrayList<String> fileNames) {
        this.fileNames = fileNames;
    }

    public List<ArrayList<String>> getText() {
        return text;
    }

    public void setText(List<ArrayList<String>> text) {
        this.text = text;
    }

    public String[] getInstances() {
        return instances;
    }

    public void setInstances(String[] instances) {
        this.instances = instances;
    }


    public Entry(){
    }

    public List<ArrayList<String>> getWektorXList() {
        return wektorXList;
    }

    public void setWektorXList(List<ArrayList<String>> wektorXList) {
        this.wektorXList = wektorXList;
    }





}
