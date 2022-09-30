package com.junwoo.objects.chapter13;

public class Square extends  Rectangle {
    public Square(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setWidth(height);
        super.setHeight(height);
    }
}
