
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Shape;
import eg.edu.alexu.csd.oop.game.GameObject;

/**
 *
 * @author Mohamed Wael
 */
public class Factory {
    
    public GameObject getshape(int x,int y,String shape,int type)
    {
        if(shape == null)
        {
            return null;
        }
        else if(shape.equals("plate"))
        {
            return new Shape(x,y,"Plate"+randombetween(1,4)+".png",75,10,type,randomNonZeroBetween(-1,1),false);
        }
        else if(shape.equals("bomb"))
        {
            return new Shape(x,y,"bomb.png",40,40,type,randomNonZeroBetween(-1, 1),false);
        }
        return null;
    }
    
    public int randombetween(int min, int  max){
        return (int) (Math.random()*(max-min)+min);
    }    
    
    private int randomNonZeroBetween(int min, int  max){
        int temp = (int) (Math.random()*2);
        if(temp==0)
            return (int) (Math.random()*(-1-min)+min);
        else return (int) (Math.random() *(max-1)+1);
    }    
}
