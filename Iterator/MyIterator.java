/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Iterator;

import eg.edu.alexu.csd.oop.game.GameObject;

/**
 *
 * @author Mohamed
 */
public interface MyIterator 
{
   public boolean hasNext();
   public GameObject next();
   public void remove();
   public int getIndex();
}
