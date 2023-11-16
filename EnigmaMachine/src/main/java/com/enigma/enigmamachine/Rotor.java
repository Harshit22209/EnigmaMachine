package com.enigma.enigmamachine;

import java.util.Arrays;

public class Rotor {
    char notch;
    char[] left;
    char[] right;
    String input;
    Rotor(String input,String wiring,char notch){
        this.left=input.toCharArray();
        this.input=input;
        this.right=wiring.toCharArray();
        this.notch=notch;
    }
    int next(int signal){

        return Helper.find(left,right[signal]);
    }
    int back(int signal){

        return Helper.find(right,left[signal]);
    }
    void forwardRotate(int idx){
        char[] newLeft=new char[left.length];
        char[] newRight=new char[right.length];
        int kLeft=0;
        int kRight=0;
        for(int i=idx;i<left.length;i++){
            newLeft[kLeft++]=left[i];
            newRight[kRight++]=right[i];
        }
        for(int i=0;i<idx;i++){
            newLeft[kLeft++]=left[i];
            newRight[kRight++]=right[i];
        }
        left=newLeft;
        right=newRight;
        //System.out.println(Arrays.toString(newLeft));
    }
    void backwardRotate(int idx){
        System.out.println(idx);
        char[] newLeft=new char[left.length];
        char[] newRight=new char[right.length];
        int kLeft=0;
        int kRight=0;
        int n=left.length;
        for(int i=n-idx;i<n;i++){
            newLeft[kLeft++]=left[i];
            newRight[kRight++]=right[i];
        }
        for(int i=0;i<n-idx;i++){
            newLeft[kLeft++]=left[i];
            newRight[kRight++]=right[i];
        }
        left=newLeft;
        right=newRight;
    }
    void ring(int idx){
        backwardRotate(idx-1);
        int newNotch_idx=input.indexOf(""+this.notch);
        this.notch=input.charAt((notch-idx)%input.length());
    }
}
