/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import Controller.ClownWorld;
import Controller.Strategy;
import Controller.easyStrategy;
import Model.*;
import eg.edu.alexu.csd.oop.game.GameEngine;


public class test {
    public static int CLOWNWORLDWIDTH  = 1000;
    public static int CLOWNWORLDHEIGHT = 620;
   
    public static void main(String[] args) throws CloneNotSupportedException {
        GameEngine.start("This Is Anfield", new ClownWorld(1000, 620,(Strategy) new easyStrategy()));
    }
}
