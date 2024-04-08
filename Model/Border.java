
package Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import view.test;


public class Border implements GameObject{
    
    private int maxStates = 1; // number of the states of the clown's picture 
    private int x;
    private int y;
    private boolean isVisible = true;
    private BufferedImage[] spriteImages= new BufferedImage[maxStates];
    private int width  ;
    private int height ;
    private int type;
    public Border(int x, int y,int width,int height,Color lineColor,int type){
        this.width = width;   
        this.height = height;
        this.x =x;
        this.y = y;
        this.type = type;
        spriteImages[0] = new BufferedImage(test.CLOWNWORLDWIDTH,test.CLOWNWORLDHEIGHT,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 =   spriteImages[0].createGraphics();
        g2.setColor(lineColor);
        g2.setBackground(lineColor);
        g2.setStroke(new BasicStroke(height));
        g2.drawLine(0 , 0 , getWidth(), 0);
        g2.dispose();
    }
    public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y ;
    }

    @Override
    public void setY(int y) {
        this.y = y;    
    }

    @Override
    public int getWidth() {
        return width;
    }   

    @Override
    public int getHeight() {
        return height;
    }
    public void setVisible(boolean visible){
            isVisible = visible;
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }
}
