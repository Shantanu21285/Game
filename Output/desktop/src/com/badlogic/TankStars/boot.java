package com.badlogic.TankStars;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class boot extends Game
{
    public static boot INSTANCE;
    private int screenWidth,screenHeight;
    private OrthographicCamera orthographicCamera;
    private Stage stage;
    public boot()
    {
        INSTANCE=this;
    }
    public void create()
    {
        this.screenWidth= Gdx.graphics.getWidth();
        this.screenHeight=Gdx.graphics.getHeight();
        this.orthographicCamera=new OrthographicCamera();
        this.orthographicCamera.setToOrtho(false,screenWidth,screenHeight);
        stage=new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    public void render()
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }
}
