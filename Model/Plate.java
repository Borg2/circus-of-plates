
package Model;


import eg.edu.alexu.csd.oop.game.GameObject;


public class Plate extends PictureObject implements Cloneable{
        
    
    private int type;
    private int xChange = 0;
    private int stack = 0 ;
    private GameObject lastStack ;
    
    public Plate(int x,int y,String path,int width,int height,int type,int xChange,boolean horizontalOnly){
        super(x, y, path, width, height, horizontalOnly);

        this.type = type;
        this.xChange = xChange;
    }
    @Override
    public Plate clone() throws CloneNotSupportedException{
        Plate cloned = (Plate) super.clone();

        return cloned;
    }

    public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
   public void setXChange(int xChange){
       this.xChange = xChange;
   }
   public int getXChange(){
       return xChange;
   }
   public void setStack(int stack){
       this.stack = stack;
   }
   public int getStack(){
       return stack;
   }
   
   public void setLastStack(GameObject object){
       this.lastStack = object;
   }
   public GameObject getLastStack(){
       return lastStack;
   }


    @Override
    public void setX(int x) {
        switch (getStack()) {
            case 1 -> super.setX(lastStack.getX());
            case 2 -> super.setX(lastStack.getX()+ lastStack.getWidth()-getWidth());
            default -> super.setX(x);
        }
        
    }

    
}


