package com.mollitiamstudios.circlehopper.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mollitiamstudios.circlehopper.Circles.BlueCircle;
import com.mollitiamstudios.circlehopper.Circles.Circle;
import com.mollitiamstudios.circlehopper.Circles.RedCircle;
import com.mollitiamstudios.circlehopper.FileManager;
import com.mollitiamstudios.circlehopper.Game;

import java.util.ArrayList;

/**
 * Created by nsebe on 12/20/2017.
 */

public class PlayScreen extends GenericScreen implements GestureDetector.GestureListener {

    Stage stage;
    ArrayList<Circle> circles;
    SpriteBatch batch;
    Skin skin;

    InputMultiplexer multiplexer;
    GestureDetector gd;
    float time;
    float runningTime;

    int score;
    int gold;

    Label scoreLabel;
    Label scoreL;
    Label goldLabel;
    Label goldL;
    Label endScoreLabel;
    Label endHighScoreLabel;
    Label endHighScoreValue;
    Label sessionGoldValue;
    Label totalGoldValue;

    boolean gameOver;

    Sprite background;
    Sprite gameOverSprite;


    FileManager fileManager;

    enum State{playing, gameOver}
    enum Level{L1,L2,L3,L4,L5};
    State state;
    Level level;

    TextButton playButton;
    TextButton menuButton;


    private int speed;
    private int redCircleAmt;
    private int blueCircleAmt;


    public PlayScreen(Game g) {
        super(g);
        stage = new Stage(gamePort);
        circles = new ArrayList<Circle>();
        fileManager = new FileManager();
        fileManager.playWoosh();
        skin = new Skin(Gdx.files.internal("UI/uiskin.json"));

        runningTime = 0;

        state = State.playing;
        level = Level.L1;

        speed = 2;
        redCircleAmt = 1;
        blueCircleAmt = 1;

        background = new Sprite(new Texture(Gdx.files.internal("Background/pic1.jpg")));
        background.rotate(90);
        background.setPosition(-gamePort.getWorldWidth()/2,50);

        gameOverSprite = new Sprite(new Texture(Gdx.files.internal("gameover.png")));
        gameOverSprite.setPosition((gamePort.getWorldWidth() - gameOverSprite.getWidth())/2,1000);

        batch = new SpriteBatch();
        multiplexer = new InputMultiplexer();
        gd = new GestureDetector(this);

        gameOver = false;

        initLabels();
        initButtons();
    }



    private void initButtons()
    {
        playButton = new TextButton("Play Again", skin);
        playButton.setSize(400,100);
        playButton.setPosition(gamePort.getWorldWidth()/2 - 200,500);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                fileManager.stopSound();
                game.setScreen(new PlayScreen(game));
            }
        });

        menuButton = new TextButton("Main Menu", skin);
        menuButton.setSize(400,100);
        menuButton.setPosition(gamePort.getWorldWidth()/2 - 200,300);
        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                fileManager.stopSound();
                game.setScreen(new StartMenu(game));
            }
        });
    }

    private void initLabels()
    {
        scoreLabel = new Label("Score: ",scoreStyle);
        scoreLabel.setPosition(20,1200);
        stage.addActor(scoreLabel);
        scoreL = new Label("" + score, scoreStyle);
        scoreL.setPosition(230,1200);
        stage.addActor(scoreL);

        goldLabel = new Label("Gold: ", goldStyle);
        goldLabel.setPosition(20,1150);
        stage.addActor(goldLabel);
        goldL = new Label("" + gold, goldStyle);
        goldL.setPosition(120,1150);
        stage.addActor(goldL);

        endScoreLabel = new Label("Score: ", scoreStyle);
        endScoreLabel.setPosition(50,900);

        endHighScoreLabel = new Label("High Score: " , scoreStyle);
        endHighScoreLabel.setPosition(50,800);

        endHighScoreValue = new Label("" + fileManager.getHighScore(), scoreStyle);
        endHighScoreValue.setPosition(450,800);


    }

    public void addCircle()
    {
        if(time > 2)
        {
            for(int x = 0; x < blueCircleAmt; x++)
                circles.add(new BlueCircle());

            for(int x = 0; x < redCircleAmt; x++)
                circles.add(new RedCircle());

            time = 0;
        }

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);

        time += delta;
        runningTime += delta;



        batch.begin();
        background.draw(batch);
        batch.end();

        switch(state)
        {
            case playing:
                playingState();
                break;
            case gameOver:
                gameOverState();
                break;
        }


        stage.act();
        stage.draw();
        cam.update();
    }

    public void updateLevel()
    {
        switch (level)
        {
            case L2:
                redCircleAmt = 2;
                blueCircleAmt = 2;
                speed = 4;
                break;
            case L3:
                redCircleAmt = 3;
                blueCircleAmt = 2;
                speed = 5;
                break;
            case L4:
                redCircleAmt = 4;
                blueCircleAmt = 3;
                speed = 6;
                break;
            case L5:
                redCircleAmt = 5;
                blueCircleAmt = 4;
                speed = 7;
                break;
        }
    }

    public void playingState()
    {
        //updates values based on the appropriate level
        if(runningTime >= 80) {
            level = Level.L5;
            updateLevel();
        }
        else if (runningTime >= 60) {
            level = Level.L4;
            updateLevel();
        }
        else if (runningTime >= 40) {
            level = Level.L3;
            updateLevel();
        }
        else if (runningTime >= 20) {
            level = Level.L2;
            updateLevel();
        }

        addCircle();
        for(int x = 0; x < circles.size();x++)
        {
            batch.begin();
            circles.get(x).getCircle().draw(batch);
            batch.end();

            //moves the circle down the screen
            circles.get(x).getCircle().setPosition(circles.get(x).getCircle().getX(),circles.get(x).getCircle().getY() - speed);

            //removes the circle when it moves off the screen
            if(circles.get(x).getCircle().getY() + circles.get(x).getCircle().getHeight() < 0)
            {
                circles.remove(circles.get(x));
                x--;
            }
        }
    }

    /**
     * This method handles all actions and events that occur after the game ends (IE: the player touches a red circle.
     */
    public void gameOverState()
    {
        //draw game over sign
        batch.begin();
        gameOverSprite.draw(batch);
        batch.end();

        //remove labels from top left
        goldL.remove();
        scoreLabel.remove();
        goldLabel.remove();

        //modify existing labels
        scoreL.setPosition(450, 900);
        scoreLabel.setPosition(50,700);
        goldLabel = new Label("Gold: ", scoreStyle);
        goldLabel.setPosition(50,700);

        totalGoldValue = new Label("" + fileManager.getGold(), scoreStyle);
        totalGoldValue.setPosition(250,700);
        sessionGoldValue = new Label("(+" + gold  + ")", scoreStyle);
        sessionGoldValue.setPosition(380,700);
        sessionGoldValue.setColor(Color.GREEN);

        //if a new high score is reached, make the color green
        if(score > fileManager.getPastScore())
        {
            scoreL.setColor(Color.GREEN);
            endHighScoreValue.setColor(Color.GREEN);
            endHighScoreValue.setText("" + score);
        }

        //add labels
        stage.addActor(endScoreLabel);
        stage.addActor(endHighScoreLabel);
        stage.addActor(endHighScoreValue);
        stage.addActor(totalGoldValue);
        stage.addActor(sessionGoldValue);
        stage.addActor(goldLabel);
        stage.addActor(playButton);
        stage.addActor(menuButton);
    }


    @Override
    public void show() {
        multiplexer.addProcessor(gd);
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void hide() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        touch = new Vector3(x,y,0);
        cam.unproject(touch);

        if(gameOver == false)
            for(Circle c : circles)
            {
                handleTouch(c);

            }
        System.out.println(fileManager.getHighScore());

        return false;
    }

    Vector3 touch;
    @Override
    public boolean tap(float x, float y, int count, int button) {
        touch = new Vector3(x,y,0);
        cam.unproject(touch);



        return false;
    }


    /**
     * Handles the touch event for circles. Takes appropriate action for both types.
     * @param c - The circle being evaluated for touch.
     */
    public void handleTouch(Circle c)
    {
            if(c.getCircle().getBoundingRectangle().contains(touch.x,touch.y))
            {
                if(c instanceof BlueCircle)
                {
                    BlueCircle c1 = (BlueCircle) c;
                    if(c1.hasBeenTouched()==false)
                    {
                        score++;
                        gold++;
                        scoreL.setText("" + score);
                        goldL.setText("" + gold);
                    }
                    c1.setBeenTouched(true);
                }
                else if (c instanceof RedCircle)
                {
                    RedCircle c1 = (RedCircle) c;
                    endGame();
                }


            }
    }


    /**
     * Called when a red circle is touched, thus ending the game. Commits the gold value to the persistent gold amount.
     */
    public void endGame()
    {
        gameOver = true;
        System.out.println("Game ended");
        fileManager.addGold(gold);
        fileManager.commitScore(score);
        state = State.gameOver;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
