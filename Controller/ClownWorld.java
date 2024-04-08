
package Controller;

import Iterator.ArrayIterator;
import Model.*;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;



public class ClownWorld implements World{
    private static final int maxTime = 1 * 60 * 1000;	// 1 minute
    private final long startTime = System.currentTimeMillis();
    private int score = 0;
    private  int width;
    private int height;
    private int speed;
    private final  List<GameObject> controlable = new LinkedList<>();
    private final  List<GameObject> movable = new LinkedList<>();
    private final  List<GameObject> constant = new LinkedList<>();
    private final List<GameObject> leftHand = new LinkedList<>();
    private final List<GameObject> rightHand = new LinkedList<>();
    
    private  Shape plateType1;
    private  Shape plateType2;
    private  Shape plateType3;
    private  Shape plateType4;
    private  Shape bomb;
    private ArrayIterator iter;
    private Factory factory =  new Factory();
    private Strategy strategy;
    
    public ClownWorld(int width,int height,Strategy strategy) throws CloneNotSupportedException{
        this.width = width;
        this.height= height;
        this.strategy = strategy;
        int count = 0;
        int count1 = 0;
        GameObject background = new PictureObject(0, 0, "gamebackground.png",width,height,false);
        int borderHeight = 10;
        int borderLenght = 300;
        int x1 = 0,x2 = 0,x3 = 700,x4 = 700;
        int y1 = 30,y2 = 100,y3 = 30,y4 = 100;
        constant.add(background);
        constant.add(new Border( x1  ,y1   ,borderLenght , borderHeight,Color.BLACK,1));
        constant.add(new Border(x2  ,y2   , borderLenght,borderHeight,Color.BLACK,2));
        constant.add(new Border(x3,  y3  ,borderLenght,borderHeight,Color.BLACK,3));
        constant.add(new Border(x4 ,  y4  ,borderLenght,borderHeight,Color.BLACK,4));
        
        x3 = width -75;
        x4 = width -75;
        for(int i=0; i<25;i++){
            count1++;
            plateType1 = (Shape) factory.getshape(x1 ,y1-10 ,"plate",1);
            plateType2 = (Shape) factory.getshape(x2 ,y2-10 ,"plate",2);
            plateType3 = (Shape) factory.getshape(x3 ,y3-10 ,"plate",3);
            plateType4 = (Shape) factory.getshape(x4 ,y4-10 ,"plate",4);            
            if(count1%4==0){
                switch (count){
                    case 0://new Shape(x1+70  , y1-40    ,"bomb.png", 40, 40,1,randomNonZeroBetween(-1, 1),false)
                        bomb = (Shape) factory.getshape(x1+70 ,y1-40 ,"bomb",1); count++;
                        break;
                    case 1:
                        bomb = (Shape) factory.getshape(x2+70 ,y2-40 ,"bomb",2); count++;
                        break;
                    case 2:
                        bomb = (Shape) factory.getshape(x3+70 ,y3-40 ,"bomb",3); count++;
                        break;
                    case 3:
                        bomb = (Shape) factory.getshape(x4+70 ,y4-40 ,"bomb",4); count=0;
                        break;    
                }
                movable.add( bomb);}
            movable.add(plateType1 );
            movable.add(plateType2);
            movable.add(plateType3 );
            movable.add(plateType4);
             x1 -= 160;
             x2 -= 160;
             x3 += 160;
             x4 += 160;
        }
        controlable.add(Clown.getInstance());
        
    }
    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > maxTime; // time end and game over
        for(int i=0;i<movable.size()-1;i++){
            if(movable.get(i).getHeight() == 40){
                try{
                Shape bomb = (Shape) movable.get(i);
                Shape clone = bomb.clone() ; 
                Clown clown =  ((Clown)(controlable.get(0)) );
                moveShapes(bomb, bomb.getType());
                if(leftHand.isEmpty()){
                    if(clown.intersectLeft(bomb)){
                        score = 0;
                        bomb.setVisible(false);
                        recycle(clone);
                    }
                }
                else  if (intersect (bomb, leftHand.get(leftHand.size() - 1))){
                    score = 0;
                    bomb.setVisible(false);
                    recycle(clone);
                }
                if(rightHand.isEmpty()){
                    if(clown.intersectRight(bomb)){
                        score = 0;
                        bomb.setVisible(false);
                        recycle(clone);
                    }
                }
                else if (intersect (bomb, rightHand.get(rightHand.size() - 1)) ){
                    score = 0;
                    bomb.setVisible(false);
                    recycle(clone);
                }
                if(bomb.getY() == height){
                recycle(clone);
            }
             }catch(CloneNotSupportedException e){
                e.printStackTrace();
            }   
            }else{
            Shape object = (Shape) movable.get(i);
            try{
            Shape clone = object.clone() ; 
            Clown clown =  ((Clown)(controlable.get(0)) );
            moveShapes(object, object.getType());
            if(leftHand.isEmpty()){
                if(clown.intersectLeft(object)){
                    insertInToLeft(object, true);
                    recycle(clone);
                }
            }
            else  if (intersect (object, leftHand.get(leftHand.size() - 1))){
                insertInToLeft(object , false);
                recycle(clone);
            }
             if(rightHand.isEmpty()){
                if(clown.intersectRight(object)){
                    insertInToRight(object, true);
                    recycle(clone);
                }
            }
            else if (intersect (object, rightHand.get(rightHand.size() - 1)) ){
                insertInToRight(object, false);
                recycle(clone);
            }
//           
            }catch(CloneNotSupportedException e){
                e.printStackTrace();
            }
            checkLeftHand();
            checkRightHand();
            if(object.getY() == height){
                recycle(object);
            }
            if(rightHand.size()>=15||leftHand.size()>=15)
                return false;       
                        
    }}
          
        return !timeout;
    }
    

    private boolean intersect(GameObject object1 , GameObject object2){
        return Math.abs(   object1.getX()  - object2.getX()  ) <= object1.getWidth()  && Math.abs(object1.getY()   - object2.getY())  <= object1.getHeight() ;
    }
    private void insertInToLeft(GameObject object,boolean LeftStackEmpty){
        Shape plate = (Shape)object;
        Clown clown = (Clown) controlable.get(0);
        if(LeftStackEmpty){
        }
        else{
            plate.setY(leftHand.get(leftHand.size()-1).getY() - plate.getHeight());
        }
            plate.setStack(1);
            plate.setHorizontalOnly(true);
            leftHand.add(plate);
            movable.remove(plate);
            controlable.add(object);   
            plate.setLastStack(clown);
    }
        private void insertInToRight(GameObject object,boolean RightStackEmpty){
        Shape plate = (Shape)object;
        Clown clown = (Clown) controlable.get(0);
        if(RightStackEmpty){
        }
        else{
            plate.setY(rightHand.get(rightHand.size()-1).getY() - plate.getHeight());
        }
            plate.setStack(2);
            plate.setHorizontalOnly(true);
            rightHand.add(plate);
            movable.remove(plate);
            controlable.add(object);   
            plate.setLastStack(clown);
    }
        
    private void recycle(GameObject object){
        Shape shape = (Shape) object;
        if(!movable.contains(object)){
            movable.add(object);
        }
        int xOfLastItem = 0,yOfLastItem;
        int plateWidth = shape.getHeight();
        if(shape.getHeight()==40){
            xOfLastItem=bomb.getX();  
        }
        else{
            switch(shape.getType()){
                case 1: xOfLastItem = plateType1.getX(); break;
                case 2: xOfLastItem = plateType2.getX(); break;
                case 3: xOfLastItem = plateType3.getX(); break;
                case 4: xOfLastItem = plateType4.getX(); break;}     
        }
            switch (shape.getType()){
                case 1: {
                xOfLastItem = xOfLastItem - 160;
                yOfLastItem = constant.get(1).getY() - plateWidth;
                object.setX(xOfLastItem);
                object.setY(yOfLastItem);
                if(shape.getHeight()==40)
                    bomb = shape;
                else
                    plateType1 = shape;
                break;
                }
                case 2:{
                xOfLastItem = xOfLastItem - 160;
                yOfLastItem = constant.get(2).getY() -plateWidth;
                object.setX(xOfLastItem);
                object.setY(yOfLastItem);
                if(shape.getHeight()==40)
                    bomb = shape;
                else
                    plateType2 = shape;
                break;
                }
                case 3:{
                xOfLastItem = xOfLastItem + 160;
                yOfLastItem = constant.get(3).getY()-plateWidth;
                object.setX(xOfLastItem);
                object.setY(yOfLastItem); 
                if(shape.getHeight()==40)
                    bomb = shape;
                else
                    plateType3 = shape;
                break;
                }
                case 4:{
                xOfLastItem = xOfLastItem +  160;
                yOfLastItem = constant.get(4).getY() - plateWidth;
                object.setX(xOfLastItem);
                object.setY(yOfLastItem);
                if(shape.getHeight()==40)
                    bomb = shape;
                else
                    plateType4 = shape;
                break;
                }  
                default:{                

                break;
                } }}
            
            
            
            
    private void moveShapes(Shape object,int type){ 
        switch (type) {
            case 1:{
                if(object.getX() + strategy.getSpeed() <= ((Border)constant.get(1)).getWidth() && object.getY() + object.getHeight()  <= ((Border)constant.get(1)).getY()){
                    object.setX(object.getX() + strategy.getSpeed());
                }
                else {
                    object.setX(object.getX() + object.getXChange());
                    object.setY(object.getY() + strategy.getSpeed());
                }
                break;
            }
            case 2:{
              if(object.getX() + strategy.getSpeed() <= ((Border)constant.get(2)).getWidth() && object.getY() + object.getHeight()  <= ((Border)constant.get(2)).getY() )
                {
                    object.setX(object.getX() + strategy.getSpeed());
                }else {
                  object.setX(object.getX() + object.getXChange());
                    object.setY(object.getY()+strategy.getSpeed());
                }
              break;
            }
            case 3:{
                if(object.getX() + strategy.getSpeed() > width-((Border)constant.get(3)).getWidth()-object.getWidth() && object.getY() + object.getHeight()   <= ((Border)constant.get(3)).getY() ){
                    object.setX(object.getX() - strategy.getSpeed());
                }
                else {
                    object.setX(object.getX() + object.getXChange());
                    object.setY(object.getY()+strategy.getSpeed());
                }
                break;
            }    
            case 4:{
                if(object.getX() + strategy.getSpeed() > width-((Border)constant.get(4)).getWidth()-object.getWidth()  && object.getY() + object.getHeight()  <= ((Border)constant.get(4)).getY()){
                    object.setX(object.getX() - strategy.getSpeed());
                }
                else {
                    object.setX(object.getX() + object.getXChange());
                    object.setY(object.getY()+strategy.getSpeed());
                }
                break;
            }
            default: { break; }
        }
    }

    private void checkLeftHand() {
        iter = new ArrayIterator(leftHand);
        int i = 0;
        while(iter.hasNext()){
            i++;
            iter.next();
        }
        if (i >= 3) {
            Shape p1 = (Shape) leftHand.get(leftHand.size() - 1);
            Shape p2 = (Shape) leftHand.get(leftHand.size() - 2);
            Shape p3 = (Shape) leftHand.get(leftHand.size() - 3);
            if (p1.getPath().equals(p2.getPath()) && p2.getPath().equals(p3.getPath())) {
                leftHand.remove(leftHand.size() - 1);
                leftHand.remove(leftHand.size() - 1);
                leftHand.remove(leftHand.size() - 1);
                controlable.remove(p1);
                controlable.remove(p2);
                controlable.remove(p3);
                score++;
            }
        }
    }
    private void checkRightHand() {
        iter = new ArrayIterator(rightHand);
        int i = 0;
        while(iter.hasNext()){
            i++;
            iter.next();}
        if (i >= 3) {
            Shape p1 = (Shape) rightHand.get(rightHand.size() - 1);
            Shape p2 = (Shape) rightHand.get(rightHand.size() - 2);
            Shape p3 = (Shape) rightHand.get(rightHand.size() - 3);
            if (p1.getPath().equals(p2.getPath()) && p2.getPath().equals(p3.getPath())) {
                rightHand.remove(rightHand.size() - 1);
                rightHand.remove(rightHand.size() - 1);
                rightHand.remove(rightHand.size() - 1);
                controlable.remove(p1);
                controlable.remove(p2);
                controlable.remove(p3);
                score++;
            }
        }
    }
    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return movable;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return controlable;
    }

    @Override
    public int getWidth() {
         return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getStatus() {
        return "Score=" + score + "   |   Time=" + Math.max(0, (maxTime - (System.currentTimeMillis() - startTime)) / 1000);	// update status
    }

    @Override
    public int getSpeed() {
        return this.speed ;
    }

    @Override
    public int getControlSpeed() {
        return 20;
    }    
}