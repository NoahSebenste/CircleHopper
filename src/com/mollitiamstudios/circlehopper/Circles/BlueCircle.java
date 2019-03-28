package com.mollitiamstudios.circlehopper.Circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Random;

/**
 * Created by nsebe on 12/20/2017.
 */

public class BlueCircle extends Circle{

    protected Sprite circle;
    protected boolean beenTouched;


    Texture grey;
    Texture t;


    public BlueCircle()
    {
        t = new Texture(Gdx.files.internal("lightBlueCircle3.png"));
        grey = new Texture(Gdx.files.internal("grey_circle.png"));
        circle = new Sprite(t);
        circle.setPosition(new Random().nextInt(660) + 1,1240);
        beenTouched = false;
    }


    public Sprite getCircle()
    {
        return circle;
    }

    public void touch(float x, float y)
    {

            if(circle.getBoundingRectangle().contains(x,y))
            {
                circle.setTexture(grey);
            }

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
