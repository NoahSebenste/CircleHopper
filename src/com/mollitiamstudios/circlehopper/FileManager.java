package com.mollitiamstudios.circlehopper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by Noah Sebenste on 12/25/2017.
 *
 * Handles everything associated with filehandling. Primary applicatoins including saving highscores and gold values.
 */

public class FileManager {

    //used for handling the gold.txt file and score.txt
    Preferences gold;
    Preferences highScore;
    Preferences upgradeGold;
    Preferences upgradeScore;
    Preferences upgradeTap;
    Preferences upgradeLuck;
    Preferences upgradeSlow;
    Preferences goldCounter;
    Preferences scoreCounter;
    Preferences tapCounter;
    Preferences luckCounter;
    Preferences slowCounter;
    private int pastScore;
    private Sound sound;
    private long id;
    private static boolean soundPlaying;

    public FileManager()
    {
        gold = Gdx.app.getPreferences("gold");
        highScore = Gdx.app.getPreferences("highScore");
        pastScore = highScore.getInteger("highScore");
        upgradeGold = Gdx.app.getPreferences("goldModifier");
        upgradeScore = Gdx.app.getPreferences("scoreModifier");
        upgradeTap = Gdx.app.getPreferences("tapModifier");
        upgradeLuck = Gdx.app.getPreferences("luckModifier");
        upgradeSlow = Gdx.app.getPreferences("slow");

        goldCounter = Gdx.app.getPreferences("goldCounter");
        scoreCounter = Gdx.app.getPreferences("scoreCounter");
        tapCounter = Gdx.app.getPreferences("tapCounter");
        luckCounter = Gdx.app.getPreferences("luckCounter");
        sound = Gdx.audio.newSound(Gdx.files.internal("woosh.wav"));
    }

    /**
     * Adds the parameter goldAmount to the overall amount of gold that the player currently has.
     * @param goldAmount
     */
    public void addGold(int goldAmount)
    {
        gold.putInteger("gold",gold.getInteger("gold") + goldAmount);
        gold.flush();
    }

    public int getGold()
    {
        return gold.getInteger("gold");
    }

    public void commitScore(int score)
    {
        if(score > pastScore)
            highScore.putInteger("highScore",score);
        highScore.flush();


    }

    public int getPastScore() { return pastScore; }

    public int getHighScore()
    {
        return highScore.getInteger("highScore");
    }

    public int getgoldCounter() { return goldCounter.getInteger("goldCounter", 0);}

    public int getScoreCounter() { return scoreCounter.getInteger("scoreCounter", 0);}

    public int getTapCounter() { return tapCounter.getInteger("tapCounter", 0);}

    public int getLuckCounter() { return luckCounter.getInteger("luckCounter", 0);}



    public int getGoldModifier() { return upgradeGold.getInteger("goldModifier"); }

    public int getScoreModifier() { return upgradeScore.getInteger("scoreModifer");}

    public int getTapModifier() { return upgradeTap.getInteger("tapModifier");}

    public int getLuckModifier() { return upgradeLuck.getInteger("luckModifier");}

    public int getSlow() { return upgradeSlow.getInteger("slow");}

    public void playWoosh()
    {
        if(!soundPlaying)
        {
            id = sound.play(1.0f);
            sound.setLooping(id, true); // keeps the sound looping
            soundPlaying = true;
        }

    }

    public void playButStill()
    {
        if(!soundPlaying)
        {
            sound = Gdx.audio.newSound(Gdx.files.internal("butstill.mp3"));
            id = sound.play(1.0f);
            sound.setLooping(id, true); // keeps the sound looping
            soundPlaying = true;
        }

    }

    public void stopSound()
    {
        soundPlaying = false;
        sound.stop(id);
    }


}
