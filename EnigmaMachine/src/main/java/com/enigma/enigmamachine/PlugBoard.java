package com.enigma.enigmamachine;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlugBoard {
    char[] left;
    char[] right;

    PlugBoard(String input,ArrayList<String> pairs){
        right=input.toCharArray();
        left=input.toCharArray();
        for(String pair:pairs){
            char c1=pair.charAt(0);
            char c2=pair.charAt(1);
            swap(c1,c2);
        }
    }
    void swap(char c1,char c2){
        int idx1=Helper.find(left,c1);
        int idx2=Helper.find(left,c2);

        char temp=left[idx1];
        left[idx1]=left[idx2];
        left[idx2]=temp;

    }
    int next(int signal){

        return Helper.find(left,right[signal]);
    }
    int back(int signal){

        return Helper.find(right,left[signal]);
    }
}
