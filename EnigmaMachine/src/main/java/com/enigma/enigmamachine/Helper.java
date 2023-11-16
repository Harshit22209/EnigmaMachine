package com.enigma.enigmamachine;

public class Helper {
    static int find(char[] array,char c){
        for(int i=0;i< array.length;i++){
            if(array[i]==c) return i;
        }
        return -1;
    }
}
