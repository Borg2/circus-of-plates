
package Model;

import eg.edu.alexu.csd.oop.game.GameObject;

public class Clown extends PictureObject{
    
    private static Clown singleInstance =  null; 
    
    private Clown(int x, int y, String path,int width,int height,boolean horizontalOnly) {
        super(x, y, path,width,height,horizontalOnly);
    }
    
    public static Clown getInstance()
    {
        if(singleInstance == null)
            singleInstance = new Clown(300,400,"Clown.png",250,200,true);
        return singleInstance;
    }
    
    public boolean intersectLeft(GameObject object){
        return Math.abs(   this.getX() - object.getX()) <= 72 && object.getY()+object.getHeight() >= this.getY() && object.getY() < this.getY() ;
    }
    
    public boolean intersectRight(GameObject object){
        return Math.abs(   (this.getX() + this.getWidth() -70) - object.getX()) <= 72 && object.getY()+object.getHeight() >= this.getY() && object.getY() < this.getY() ;
    }    
}


