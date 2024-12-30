package ru.newyear;

import com.badlogic.gdx.math.MathUtils;

public class Toy {
    public float x;
    public float y;
    public float width;
    public float height;
    public float stepX;
    public float stepY;
    public boolean touch = false;
    public boolean play = false;
    public boolean dontMove = false;
    private int oneTime = 0;


    private int time = 0;


    public boolean stopFollow = false;

    public Toy(float x, float y, float stepX, float stepY) {
        this.x = x;
        this.y = y;

        this.stepX = stepX;
        this.stepY = stepY;
        width = height = MathUtils.random(30, 70);
    }

    public void move(){

                if (!stopFollow) {
                    if (x + width >= 1280 || y + height >= 720 || x <= 0 || y <= 0) {
                        stepX = -stepX;
                        stepY = -stepY;

                    }
                    dontMove = true;
                    x += stepX;
                    y += stepY;
                }
                else{
                    time = 0;


                }

    }


    public void ftouch(float mouse_x, float mouse_y){
        if (!stopFollow) {
            this.x = mouse_x;
            this.y = mouse_y;
        }
        if (time == 0){
            time++;
        }
    }

    public void playSnd(){
        if (time == 1){
            play = true;
            time = 2;
        }
        else{
            play = false;
        }

    }

    public void collide(Toy toy1, Toy toy2){
        if ((toy1.x + toy1.width == toy2.x || toy1.x== toy2.x + toy2.width) && Math.abs(toy2.y - toy1.y) <= toy2.height){
            System.out.println(toy1.stepX );
            toy1.stepX = -toy1.stepX;
            toy2.stepX = -toy2.stepX;
            System.out.println(toy1.stepX);

        }

    }

}
