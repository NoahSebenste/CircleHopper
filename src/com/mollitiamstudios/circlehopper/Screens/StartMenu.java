package com.mollitiamstudios.circlehopper.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mollitiamstudios.circlehopper.FileManager;
import com.mollitiamstudios.circlehopper.Game;


public class StartMenu extends GenericScreen {
    Stage stage;
    Skin skin;

    TextButton upgradesButton;
    TextButton scoreBoardButton;
    TextButton settingsButton;
    ImageButton playButton;

    Label highScoreLabel;
    Label highScore;
    Label goldLabel;
    Label gold;

    //file manager
    FileManager fileManager;

    //objects for font
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    Label.LabelStyle scoreStyle;
    BitmapFont font;

    Sprite background;
    SpriteBatch batch;

    public StartMenu(Game g)
    {
       super(g);

        stage = new Stage(gamePort);
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("neon/neon-ui.json"));

        fileManager = new FileManager();
        fileManager.playButStill();

        initFont();
        initButtons();
        initLabels();

        background = new Sprite(new Texture(Gdx.files.internal("Background/pic1.jpg")));
        background.rotate(90);
        background.setPosition(-gamePort.getWorldWidth()/2,50);
        batch = new SpriteBatch();

        //Game.playServices.signIn();


    }

    /**
     * Initalizes the generator and parameter objects.
     */
    private void initFont()
    {
        //handles font creation
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        font = generator.generateFont(parameter);
        scoreStyle = new Label.LabelStyle(font, Color.WHITE);

    }

    /**
     * Initializes the buttons to the start menu. Sets them to be in the middle of the screen
     */
    private void initButtons()
    {
        upgradesButton = new TextButton("Upgrades", skin);
        upgradesButton.setSize(400,100);
        upgradesButton.setPosition(gamePort.getWorldWidth()/2 - 200,290);
        upgradesButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                game.setScreen(Game.upgradesScreen);
            }
        });
        stage.addActor(upgradesButton);

        scoreBoardButton = new TextButton("Scoreboard",skin);
        scoreBoardButton.setSize(400,100);
        scoreBoardButton.setPosition(gamePort.getWorldWidth()/2 - 200,170);
        scoreBoardButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                Game.playServices.showScore();

            }
        });
        stage.addActor(scoreBoardButton);

        settingsButton = new TextButton("Settings", skin);
        settingsButton.setSize(400,100);
        settingsButton.setPosition(gamePort.getWorldWidth()/2 - 200,50);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {


            }
        });
        stage.addActor(settingsButton);

        SpriteDrawable s = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("playButton.png"))));//temp sprite drawable
        playButton = new ImageButton(s);
        playButton.setPosition(gamePort.getWorldWidth()/2 - (playButton.getWidth()/2),500);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Play Button Pressed");
                fileManager.stopSound();
                game.setScreen(new PlayScreen(game));
            }
        });
        stage.addActor(playButton);

    }

    /**
     * Adds labels to the main start menu.
     */
    private void initLabels()
    {
        highScoreLabel = new Label("High Score: ", scoreStyle);
        highScoreLabel.setPosition(20,1180);
        stage.addActor(highScoreLabel);

        highScore = new Label("" + fileManager.getHighScore(),scoreStyle);
        highScore.setPosition(300,1180);
        stage.addActor(highScore);

        goldLabel = new Label("Gold: ", scoreStyle);
        goldLabel.setPosition(20, 1100);
        stage.addActor(goldLabel);

        gold = new Label("" + fileManager.getGold(), scoreStyle);
        gold.setPosition(160, 1100);
        stage.addActor(gold);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        background.draw(batch);
        batch.end();

        stage.act();
        stage.draw();

        cam.update();
    }

    @Override
    public void hide() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {

    }
}
