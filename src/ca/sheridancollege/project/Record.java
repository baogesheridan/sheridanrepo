/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author: Baoyong zhao
 */
public class Record {
    public int numMatch;
    public int numWin;
    public int numTie;
    public int numLost;
      
  
    // Constructor
    public Record(int numMatch, int numWin, int numTie, int numLost) {
        this.numMatch = numMatch;
        this.numWin = numWin;
        this.numLost = numLost;
    }

        // Constructor
        public Record() {
        this.numMatch = 0;
        this.numWin = 0;
        this.numLost = 0;
    }
        
    // Getters
    public int getNumMatch() {
        return numMatch;
    }

    public int getNumWin() {
        return numWin;
    }


    public int getNumLost() {
        return numLost;
    }

    // Setters
    public void setNumMatch(int numMatch) {
        this.numMatch = numMatch;
    }

    public void setNumWin(int numWin) {
        this.numWin = numWin;
    }

    public void setNumLost(int numLost) {
        this.numLost = numLost;
    }
}

