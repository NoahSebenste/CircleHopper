package com.mollitiamstudios.circlehopper.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mollitiamstudios.circlehopper.FileManager;
import com.mollitiamstudios.circlehopper.Game;

/**
 * Created by nsebe on 12/25/2017.
 */

public class UpgradesScreen extends GenericScreen {
    Stage stage;
    SpriteBatch batch;

    Sprite background;

    FileHandle file;
    FileManager fileManager;

    TextButton goldModifierButton;
    TextButton scoreModifierButton;
    TextButton multiTapButton;
    TextButton luckButton;
    TextButton slowButton;
    TextButton backButton;

    Label goldLabel;
    Label currentGold;
    Label costLabel;
    Label valueLabel;

    Label goldCost;
    Label scoreCost;
    Label tapCost;
    Label luckCost;
    Label slowCost;

    Label goldValue;
    Label scoreValue;
    Label tapValue;
    Label luckValue;
    Label slowValue;

    int[] goldModifierCost;
    int[] goldModifierValue;
    int[] scoreModifierCost;
    int[] scoreModifierValue;
    int[] tapModifierCost;
    int[] tapModifierValue;
    int[] luckModifierCost;
    int[] luckModifierValue;
    int slowModifierCost;
    int slowModifierValue;


    public UpgradesScreen(Game g) {
        super(g);
        stage = new Stage(gamePort);
        batch = new SpriteBatch();
        fileManager = new FileManager();

        background = new Sprite(new Texture(Gdx.files.internal("Background/pic3.jpg")));
        background.rotate(90);
        background.setPosition(-gamePort.getWorldWidth()/2,50);

        file = Gdx.files.local("upgrades.txt");

        initButtons();
        initValues();
        initLabels();

    }

    private void initValues()
    {
        goldModifierCost = new int[]{20,50,100,200,500,1000,2000,4000,5000,10000};
        goldModifierValue = new int[]{5,10,15,20,25,30,35,40,45,50};

        scoreModifierCost = new int[]{20,50,100,200,700,1200,2500,4500,7000,15000};
        scoreModifierValue = new int[]{7,14,21,28,35,42,49,56,63,70};

        tapModifierCost = new int[]{100,500,1000,2000,5000};
        tapModifierValue = new int[]{1,2,3,4,5};

        luckModifierCost = new int[]{100,200,600,800,1000,1500,2000,3000,4000,5000};
        luckModifierValue = new int[]{5,10,15,20,25,30,35,40,45,50};

        slowModifierCost = 500;
    }

    private void initLabels()
    {
        goldLabel = new Label("Gold: ",scoreStyle);
        goldLabel.setPosition(20,1150);
        stage.addActor(goldLabel);

        currentGold = new Label("" + fileManager.getGold() ,scoreStyle);
        currentGold.setPosition(200,1150);
        stage.addActor(currentGold);

        costLabel = new Label("Cost" , goldStyle);
        costLabel.setPosition(480,1150);
        stage.addActor(costLabel);

        valueLabel = new Label("Value" , goldStyle);
        valueLabel.setPosition(610,1150);
        stage.addActor(valueLabel);

        goldCost = new Label("0" , goldStyle);
        goldCost.setPosition(480,1025);
        stage.addActor(goldCost);

        scoreCost = new Label("0" , goldStyle);
        scoreCost.setPosition(480,875);
        stage.addActor(scoreCost);

        tapCost = new Label("0" , goldStyle);
        tapCost.setPosition(480,725);
        stage.addActor(tapCost);

        luckCost = new Label("0" , goldStyle);
        luckCost.setPosition(480,575);
        stage.addActor(luckCost);

        slowCost = new Label("0" , goldStyle);
        slowCost.setPosition(480,425);
        stage.addActor(slowCost);


        goldValue = new Label("0" , goldStyle);
        goldValue.setPosition(610,1025);
        stage.addActor(goldValue);

        scoreValue = new Label("0" , goldStyle);
        scoreValue.setPosition(610,875);
        stage.addActor(scoreValue);

        tapValue = new Label("0" , goldStyle);
        tapValue.setPosition(610,725);
        stage.addActor(tapValue);

        luckValue = new Label("0" , goldStyle);
        luckValue.setPosition(610,575);
        stage.addActor(luckValue);

        slowValue = new Label("0" , goldStyle);
        slowValue.setPosition(610,425);
        stage.addActor(slowValue);
    }

    private void initButtons()
    {
        goldModifierButton= new TextButton("Gold Modifier", skin);
        goldModifierButton.setSize(400,100);
        goldModifierButton.setPosition(50,1000);
        goldModifierButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {


            }
        });
        stage.addActor(goldModifierButton);

        scoreModifierButton = new TextButton("Score Modifier", skin);
        scoreModifierButton.setSize(400,100);
        scoreModifierButton.setPosition(50,850);
        scoreModifierButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {


            }
        });
        stage.addActor(scoreModifierButton);

        multiTapButton = new TextButton("Multi-Tap", skin);
        multiTapButton.setSize(400,100);
        multiTapButton.setPosition(50,700);
        multiTapButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {


            }
        });
        stage.addActor(multiTapButton);

        luckButton = new TextButton("Luck", skin);
        luckButton.setSize(400,100);
        luckButton.setPosition(50,550);
        luckButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {


            }
        });
        stage.addActor(luckButton);

        slowButton = new TextButton("Slow Down", skin);
        slowButton.setSize(400,100);
        slowButton.setPosition(50,400);
        slowButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {


            }
        });
        stage.addActor(slowButton);

        backButton = new TextButton("Back", skin);
        backButton.setSize(600,150);
        backButton.setPosition(gamePort.getWorldWidth()/2 - 300,50);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(Game.startMenu);
            }
        });
        stage.addActor(backButton);

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
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }


}
