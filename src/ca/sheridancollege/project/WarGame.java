/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author zhaob
 */
public class WarGame extends Game {
    private String name;
    
    public WarGame(String name){
    super(name);
    }
    
    @Override
    public void play(){
    
    }
    
    @Override
    public void declareWinner(){
    System.out.println("");
    }
    
    public static void main(String[] args) {
        
        WarGame game = new WarGame("War Card Game");
        
        Scanner scanner = new Scanner(System.in);
        WarPlayer player1 = new WarPlayer();
        System.out.println("Please input player1 name: ");        
        player1.setName(scanner.nextLine());
        System.out.println("Player1"+player1.getName()+"has been ready!");
        WarPlayer player2 = new WarPlayer();
        System.out.println("Please input player2 name: ");        
        player2.setName(scanner.nextLine());
        System.out.println("Player2"+player2.getName()+"has been ready!");
        
        game.play();
    }
    
}
