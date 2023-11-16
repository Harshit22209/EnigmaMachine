package com.enigma.enigmamachine;

public class Reflector {
    char[] left;
    char[] right;
    Reflector(String input,String wiring){
        this.left=input.toCharArray();
        this.right=wiring.toCharArray();

    }
    int reflect(int signal){

        return Helper.find(left,right[signal]);
    }
}
