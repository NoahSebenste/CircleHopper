package com.mollitiamstudios.circlehopper.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mollitiamstudios.circlehopper.Game;


/**
 * Created by aikr_ on 2/22/2017.
 */

/**
 * This will be the generic screen class that will handle all the default code
 * that is necessary for creating a screen
 */
public class GenericScreen implements Screen {

    //camera and viewport
    protected OrthographicCamera cam;
    protected Viewport gamePort;

    public int virtualWidth = 720;
    public int virtualHeight = 1280;

    public Skin skin;


    protected FreeTypeFontGenerator generator;
    protected FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    protected Label.LabelStyle scoreStyle;
    protected Label.LabelStyle goldStyle;
    protected BitmapFont font;

    protected Game game;

    public GenericScreen(Game g)
    {
        game = g;
        cam = new OrthographicCamera();
        cam.update();
        gamePort = new ExtendViewport(virtualWidth,virtualHeight,cam);
        skin = new Skin(Gdx.files.internal("neon/neon-ui.json"));
        initFont();
    }

    /**
     * Initalizes the generator and parameter objects.
     */
    private void initFont()
    {
        //handles font creation
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        //set params for scoreStyle
        parameter.size = 70;
        font = generator.generateFont(parameter);
        scoreStyle = new Label.LabelStyle(font, Color.WHITE);

        //sets params for goldStyle
        parameter.size = 40;
        font = generator.generateFont(parameter);
        goldStyle = new Label.LabelStyle(font,Color.WHITE);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        cam.update();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {


    }
}
