/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Iterator;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public class ArrayIterator implements MyIterator {
    private int index ;
    private List<GameObject> list;

    public ArrayIterator(List list) {
        this.list = list;
        index=0;
    }

    @Override
    public boolean hasNext() {
        if (index < list.size()) {
            return true;
        }
        return false;
    }

    @Override
    public GameObject next() {
        if (this.hasNext()) {
            return list.get(index++);
        }
        return null;
    }

    @Override
    public void remove() {
        list.remove(--index);
    }


    @Override
    public int getIndex() {
       return index;
    }

}
