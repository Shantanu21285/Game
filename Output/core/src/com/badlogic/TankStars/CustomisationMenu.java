package com.badlogic.TankStars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class CustomisationMenu implements Screen {

    TanksStars game;
    Texture dropImage;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    OrthographicCamera camera;

    public CustomisationMenu(TanksStars game) {
        this.game=game;
        backgroundImage = new Texture(Gdx.files.internal("CustomisationScreen.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1880, 980);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1880, 980);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 1880, 980);
        this.spawnTank1();
        this.spawnTank2();
        game.batch.end();

        if (Gdx.input.getX()>17 && Gdx.input.getX()<93 && Gdx.input.getY()>30 && Gdx.input.getY()<102) {
            if(Gdx.input.isTouched())
            {
                game.setScreen(new MainMenu(this.game));
                dispose();
            }
        }

        if (Gdx.input.getX()>601 && Gdx.input.getX()<1265 && Gdx.input.getY()>897 && Gdx.input.getY()<1048) {
            if(Gdx.input.isTouched())
            {
                game.setScreen(new GameScreen(this.game));
                dispose();
            }
        }

    }

    private void spawnTank1()
    {
        dropImage = new Texture(Gdx.files.internal("type1.gif"));
        Rectangle raindrop = new Rectangle();
        raindrop.x = 10;
        raindrop.y = 350;
        raindrop.width = 416;
        raindrop.height = 416;
        game.batch.draw(dropImage, raindrop.x, raindrop.y);
    }
    private void spawnTank2()
    {
        dropImage = new Texture(Gdx.files.internal("type3.gif"));
        Rectangle raindrop = new Rectangle();
        raindrop.x = 1000;
        raindrop.y = 350;
        raindrop.width = 416;
        raindrop.height = 416;
        game.batch.draw(dropImage, raindrop.x, raindrop.y);
    }
    private void spawnTank3()
    {
        dropImage = new Texture(Gdx.files.internal("type3.gif"));
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        game.batch.draw(dropImage, raindrop.x, raindrop.y);
    }

    @Override
    public void resize(int width, int height) {

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
