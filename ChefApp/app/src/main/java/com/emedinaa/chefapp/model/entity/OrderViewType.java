package com.emedinaa.chefapp.model.entity;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/11/18
 */
public class OrderViewType {

    protected int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public  boolean isHeader(){
        return false;
    }
    public boolean isItem(){
        return false;
    }

    public boolean isFooter(){
        return false;
    }
}
