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

How code Works:


1. To encrypt a data we first get the text to be encrypt than few parameters like which 3 rotors to be used, rotor rings, rotor key, And few pair of characters for plugboard. The use of all these thing will get cleared as we move forward.
   
2. On the basis of user data we create 3 instances of rotors,a plugboard and a reflector.
  ->In rotor ,we have 2 sequence of character ,left and right.In left Sequence we have stored the normal sequence A-Z, 
    for right we have special sequences .
  ->In plugboard again there are 2 sequences left and right,In left we have swapped the sequence that was passed initial
    in the standard sequence i.e A-Z.In right Part we have Standard sequence only,no changes.
  ->In Reflector again we have two sequence left and right. left-> Standard Sequence, right ->some special sequence.
**Now the encryption start!!!**
public class Rotor {
    // Other properties and methods...

    int next(int signal){
        return Helper.find(left, right[signal]);
    }

    int back(int signal){
        return Helper.find(right, left[signal]);
    }
}

4. We now start encrypting each character from the string one by one.

a. The electrical signal would pass through the plugboard, then through the rotors.

b. After passing through the rotors, the signal would hit the reflector and then return through the rotors and plugboard.

c. The machine's settings ensured that each keypress resulted in a different encrypted letter.

d. The encrypted letter would be transmitted and then decrypted on the receiving end using the same initial settings.
 






