package com.mollitiamstudios.circlehopper.Circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Random;

/**
 * Created by nsebe on 12/20/2017.
 */

public class RedCircle extends Circle{

    protected Sprite circle;
    protected boolean beenTouched;

    Texture grey;
    Texture t;

    public RedCircle()
    {
        super();
        t = new Texture(Gdx.files.internal("red_circle.png"));

        grey = new Texture(Gdx.files.internal("grey_circle.png"));
        circle = new Sprite(t);
        circle.setSize(circle.getWidth()*2,circle.getHeight()*2);
        circle.setPosition(new Random().nextInt(660) + 1,1240);


    }


    @Override
    public Sprite getCircle() {
        return circle;
    }

    public int touch(float x, float y) {
        {
            if(circle.getBoundingRectangle().contains(x,y))
            {
                System.out.println("end");
            }
        }

        return 2;
    }

    public boolean hasBeenTouched()
    {
        return beenTouched;
    }

    public void setBeenTouched(boolean x)
    {
        beenTouched = x;
    }
}
