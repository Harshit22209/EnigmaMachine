package com.enigma.enigmamachine;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class EnigmaController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField message;

    @FXML
    private TextField rings;
    @FXML
    private TextField plugBoard;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private Label output;
    @FXML TextField ekey;
    @FXML
    private TextField rotors;

    void encrypt(String input, String msg, String k, String r, String r1,String r2,String r3,String wiring,char n1,char n2,char n3,ArrayList<String> pairs) throws InterruptedException {
        PlugBoard pb=new PlugBoard(input,pairs);
        Rotor rotor1=new Rotor(input,r1,n1);
        Rotor rotor2=new Rotor(input,r2,n2);
        Rotor rotor3=new Rotor(input,r3,n3);
        Reflector reflector=new Reflector(input,wiring);
        System.out.println(input+" "+k);
        rotor1.forwardRotate(input.indexOf(k.charAt(0)+""));
        rotor2.forwardRotate(input.indexOf(k.charAt(1)+""));
        rotor3.forwardRotate(input.indexOf(k.charAt(2)+""));
        rotor1.backwardRotate(r.charAt(0)-'1');
        rotor2.backwardRotate(r.charAt(1)-'1');
        rotor3.backwardRotate(r.charAt(2)-'1');
        System.out.println(k);
         // Run the KeyFrame once

        // Start the Timeline

        ArrayList<String> outputs=new ArrayList<>();
            String res=output.getText();
            for (char c : msg.toCharArray()) {
                String curr = res;
                int idx = input.indexOf(c + "");
                // Sleep for 1000 milliseconds (1 second)
                outputs.add(curr + pb.right[idx]);
                //for(char a:msg.toCharArray());
                waiting();
                idx = pb.next(idx);
                outputs.add(curr + rotor3.right[idx]);

                //  for(char a:msg.toCharArray());
                idx = rotor3.next(idx);
                outputs.add(curr + rotor2.right[idx]);

                idx = rotor2.next(idx);
                outputs.add(curr + rotor1.right[idx]);

                idx = rotor1.next(idx);
                outputs.add(curr + reflector.right[idx]);

                idx = reflector.reflect(idx);
                outputs.add(curr + rotor1.left[idx]);

                idx = rotor1.back(idx);
                outputs.add(curr + rotor2.left[idx]);

                idx = rotor2.back(idx);
                outputs.add(curr + rotor3.left[idx]);

               // waiting();
                idx = rotor3.back(idx);
                idx = pb.back(idx);

                outputs.add(curr + input.charAt(idx));
                res+=input.charAt(idx);
            }
        Timeline timeline = new Timeline();
        timeline.setCycleCount(outputs.size());
        final int[] outIdx = {0};
        // Define a KeyFrame that updates the text
        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(150), // Duration between keyframes
                event -> {


                    output.setText(outputs.get(outIdx[0]));
                    outIdx[0]++;
                }
        );

        // Add the KeyFrame to the Timeline
        timeline.getKeyFrames().add(keyFrame);

        // Start the animation
        timeline.play();

    }
    void waiting(){

    }
    @FXML
     void encryptBasic(ActionEvent e) throws IOException {
        String msg= message.getText().toUpperCase();
       // String k=key.getText().toUpperCase();
        String r=rings.getText().toUpperCase();
        String p=plugBoard.getText().toUpperCase();
        //String[] s=p.split(" ");
        ArrayList<String> pairs=new ArrayList<>();
        for(var str:p.split(" ")){
            pairs.add(str);
        }
        String I=  "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        String II= "AJDKSIRUXBLHWTMCQGZNPYFVOE";
        String III="BDFHJLCPRTXVZNYEIWGAKMUSQO";
        String IV= "ESOVPZJAYQUIRHXLNFTGKDCMWB";

        HashMap<String,String > keys=new HashMap<>();
        HashMap<String,Character > notchs=new HashMap<>();
        keys.put("I",I);
        notchs.put("I",'Q');

        keys.put("II",II);
        notchs.put("II",'E');

        keys.put("III",III);
        notchs.put("III",'V');

        keys.put("IV",IV);
        notchs.put("IV",'J');


        String[] rs=rotors.getText().split("-");
        String wiring="EJMZALYXVBWFCRQUONTSPIKHGD";
        output.setText("Output: ");
        try{
            encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ", msg, ekey.getText(), r, keys.get(rs[0]), keys.get(rs[1]), keys.get(rs[2]), wiring, notchs.get(rs[0]), notchs.get(rs[1]), notchs.get(rs[2]), pairs);
    }
        catch(InterruptedException ie){
        System.out.println("Interruuption");
    }


    }
    @FXML
    void encryptAdvance(ActionEvent e) {
        String msg= message.getText().toUpperCase();
       // String k=key.getText().toUpperCase();

        String r=rings.getText().toUpperCase();
        String p=plugBoard.getText().toUpperCase();
       String I= "JIHGFEDCBA9876543210ZYXWVUTSRQPONMLK";
       String II="TYR92KO4DZJ8WG1Q5L0N7A3UPEXBH6VCIFSM";
        String III="L7RHXKGJ6VUIBCW2M4NQO9ZAEPY501DF8ST3";



        ArrayList<String> pairs=new ArrayList<>();
        for(var str:p.split(" ")){
            pairs.add(str);
        }

        output.setText("Output: ");
        System.out.println("Done");
        try {
            encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", msg, ekey.getText(), rings.getText().toUpperCase(), I, II, III, "J2PY4QKU3HFGO70BWE61MDXZ8IVARNL9C5TS", 'Q', 'E', 'V', pairs);
        }
        catch(InterruptedException ie){
            System.out.println("Interruuption");
        }
    }
    @FXML
    void switchToBasic(ActionEvent e) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("EnigmaBasic.fxml"));

        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);

        stage.show();



    }
    @FXML
    void switchToAdvance(ActionEvent e) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("EnigmaAdvance.fxml"));
        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        scene=new Scene(root);

        stage.setScene(scene);
//        key.getItems().add("I-II-III");
        //key.setValue("I-II-III");
        stage.show();

    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        String[] ks={"I-II-III","II-I-III","I-II-IV","I-IV-III","II-IV-III"};
//        key.getItems().addAll(ks);
////        key.getItems().add("I-II-III");
////        key.getItems().add("II-I-III");
////        key.getItems().add("I-II-IV");
////        key.getItems().add("I-IV-III");
////        key.getItems().add("II-IV-III");
//    }
}