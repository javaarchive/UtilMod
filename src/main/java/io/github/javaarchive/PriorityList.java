package io.github.javaarchive;
import java.util.ArrayList;
import java.util.Collections;
// import java.util.Comparator;

public class PriorityList<E extends Comparable<E>> extends ArrayList<E>{
    // private Comparator<E> comparator = null;
    private int insertionPoint(E toInsert) throws IllegalArgumentException{
        int result;
        //if(comparator == null){
            // throw new IllegalArgumentException("Not implemented for natural ordering");
            result = Collections.binarySearch(this, toInsert);
        /*}else{
            // result = Collections.binarySearch(this,toInsert,this.comparator);
            throw new IllegalArgumentException("Not implemented for natural ordering");
        }*/
        if(result < 0){
            return -result - 1;
        }else{
            return result;
        }
    }
    /*public void setComparator(Comparator<E> comparator){
        this.comparator = comparator;
    }*/
    public boolean add(E newItem){
        super.add(insertionPoint(newItem),newItem);
        return true; // weird standard?
    }
}
