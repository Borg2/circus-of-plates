
package Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class PictureObject implements GameObject,Cloneable{
    
    private boolean horizontalOnly;
    private int maxStates = 1;  
    private int x;
    private int y;
    private boolean isVisible = true;
    private BufferedImage[] spiritImage= new BufferedImage[maxStates];
    private int width   ;
    private int height  ;
    private String path;

    
    private void scaleImage(int newWidth, int newHeight){
        Image scaled = spiritImage[0].getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
        this.spiritImage[0] = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null),BufferedImage.TYPE_INT_ARGB);   
        this.spiritImage[0].getGraphics().drawImage(scaled, 0, 0, null);
    }
    
    public PictureObject(int x,int y,String path,int width,int height,boolean horizontalOnly){
            this.x =  x;
            this.y = y; 
            this.height = height;
            this.width = width;
            this.horizontalOnly = horizontalOnly;
            this.path = path;
        try {
            this.spiritImage[0] = ImageIO.read(getClass().getResourceAsStream(path));
            scaleImage(width, height);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } 
    @Override
    public PictureObject clone() throws CloneNotSupportedException{
        PictureObject cloned = (PictureObject) super.clone();
         
        return cloned;
    }
    public void setHorizontalOnly(boolean horizontalOnly){
        this.horizontalOnly = horizontalOnly;
    }
   public void setPath(String path){
       this.path = path;
   }
   public String getPath(){
       return path;
   }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return this.y ;
    }

    @Override
    public void setY(int y) {
        if(!horizontalOnly)
            this.y = y;    
    }

    @Override
    public int getWidth() {
        return spiritImage[0].getWidth();
    }   

    @Override
    public int getHeight() {
        return spiritImage[0].getHeight();
    }
    public void setVisible(boolean visible){
            this.isVisible = visible;
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }

    public void isvisible(boolean isVisible){
        this.isVisible = isVisible;
    }
    
    @Override
    public BufferedImage[] getSpriteImages() {
        return spiritImage;
    }
}
