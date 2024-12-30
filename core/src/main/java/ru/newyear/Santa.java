package ru.newyear;

public class Santa {
    public float x;
    public float y;
    public float stepX;
    public float stepY;

    public Santa(float x, float y) {
        this.x = x;
        this.y = y;
        stepX = 2;
        stepY =  -8;

    }

    void fly(){
        x += stepX;
    }
    void fell(){
        stepX = 3;
        y += stepY;
        x += stepX;
    }
}
