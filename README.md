# EnigmaMachine
Discrete Mathematics
Group Project

Topic- Understanding and developing Enigma Machine

Members:
Harshit Gupta- 2022209
Harsh Kumar- 2022198

Technology Used : Java and JavaFx (For UI)

Classes:
Enigma Controller
Enigma Basic
Enigma Advance (Currently Deprecated)
Keyboard
PlugBoard
Reflector
Rotor

GitHuB link: https://github.com/Harshit22209/EnigmaMachine.git


### How to Execute:
on terminal in folder EnigmaMachine run this command " mvn clean javafx:run "

You will see something like this

![image](https://github.com/Harshit22209/EnigmaMachine/assets/119040511/0c91dcc6-5714-41ce-a2af-26cf9b8982c8)

#### How code Works:


1. To encrypt a data we first get the text to be encrypt than few parameters like which 3 rotors to be used, rotor rings, rotor key, And few pair of characters for plugboard. The use of all these thing will get cleared as we move forward.
   
2. On the basis of user data we create 3 instances of rotors,a plugboard and a reflector.
   ```Java
   PlugBoard pb=new PlugBoard(input,pairs);
   Rotor rotor1=new Rotor(input,r1,n1);
   Rotor rotor2=new Rotor(input,r2,n2);
   Rotor rotor3=new Rotor(input,r3,n3);
   Reflector reflector=new Reflector(input,wiring);
   ```
   1. In rotor ,we have 2 sequence of character ,left and right.In left Sequence we have stored the normal sequence A-Z, 
     for right we have special sequences .
   2. In plugboard again there are 2 sequences left and right,In left we have swapped the sequence that was passed initial
    in the standard sequence i.e A-Z.In right Part we have Standard sequence only,no changes.
   3. In Reflector again we have two sequence left and right. left-> Standard Sequence, right ->some special sequence.

Before delving into Encryption, first Understand a few methods of each class


```Java
public class Rotor {
    // Other properties and methods...

    int next(int signal){
        return Helper.find(left, right[signal]);
    }

    int back(int signal){
        return Helper.find(right, left[signal]);
    }
}

```
'next' Method:
-> The next method takes the current signal as input and finds the corresponding output by looking up the position of the signal in the left array and returning the character at the same position in the right array.
-> This simulates the forward movement of the electrical signal through the rotor's internal wiring.

'back' Method:
-> The back method does the opposite. It takes the current signal as input and finds the corresponding output by looking up the position of the signal in the right array and returning the character at the same position in the left array.
-> This simulates the backward movement of the electrical signal through the rotor, as it returns from the reflector back through the rotors.
```Java
public class PlugBoard {
    // Other properties and methods...

    int next(int signal){
        return Helper.find(left, right[signal]);
    }

    int back(int signal){
        return Helper.find(right, left[signal]);
    }
}

```
In Plugboard, Next and Back method work similar to Rotor


```Java
public class Reflector {
    char[] left;
    char[] right;

    // Other properties and methods...

    Reflector(String input, String wiring){
        this.left = input.toCharArray();
        this.right = wiring.toCharArray();
    }

    int reflect(int signal){
        // Reflect the signal using the reflector wiring
        return Helper.find(left, right[signal]);
    }
}
```
Here's a breakdown of the steps within the reflect method:

1. The current signal is used as an index to find its position in the left array.
2. The character at the same position in the right array is identified.
3. The identified character serves as the reflected signal.

## lets begin the encryption/encipher

We now start encrypting each character from the message one by one.

a. The electrical signal would pass through the plugboard, then through the rotors.

b. After passing through the rotors, the signal would hit the reflector and then return through the rotors and plugboard.

c. The machine's settings ensured that each keypress resulted in a different encrypted letter.

```Java
for (char c : msg.toCharArray()) {
   String curr = res;
   int idx = input.indexOf(c + "");
   idx = pb.next(idx);
   idx = rotor3.next(idx);
   idx = rotor2.next(idx);
   idx = rotor1.next(idx);
   idx = reflector.reflect(idx);
   idx = rotor1.back(idx);
   idx = rotor2.back(idx);
   idx = rotor3.back(idx);
   idx = pb.back(idx);
   res+=input.charAt(idx);
}
```
 






