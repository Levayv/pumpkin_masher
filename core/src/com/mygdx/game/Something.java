package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Something {
    Sprite sprite;
    Rectangle rectangle;
//    int x;
//    int y;
//    int width;
//    int height;

    Something(int x, int y , int width, int height  ){
        rectangle = new Rectangle();
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = width;
        rectangle.height = height;
//        System.out.println(rectangle.getX());
//        System.out.println(rectangle.getY());
//        System.out.println(rectangle.getWidth());
//        System.out.println(rectangle.getHeight());

//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;
    }
    void setSprite(Sprite sprite){
        this.sprite = sprite;
    }


}
