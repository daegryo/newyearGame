package ru.newyear;

import com.badlogic.gdx.math.MathUtils;

public class Snow {
    public float x;
    public float y;
    public float stepX;
    public float stepY;
    public float width;
    public float height;

    public Snow(float x, float y) {
        this.x = x;
        this.y = y;
        stepX = 0.1f;
        stepY = MathUtils.random(-1f, -3f);
        width = 50;
        height = 50;
    }

    public void fell(){
        if (y <= -30){
            y = MathUtils.random(720, 800);
        }
        else{
            y += stepY;
            x += stepX;
        }


    }
    public void fell(boolean santaZero) {
        y += stepY * 2;
    }

}
