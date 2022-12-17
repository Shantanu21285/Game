package com.badlogic.TankStars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.Serializable;

public class GameScreen implements Screen, Serializable
{
    Sprite ball;
    Texture ballTex;
    Vector2 gravity;
    private float throwAngle=50;
    private float deltaTime=2;
    private Vector2 initialVelocity;
    TanksStars game;
    private Texture dropImage;
    private Texture backgroundImage;
    private Texture surfaceImage;
    private Texture shooterImage;
    private Texture shooterImage2;
    private Texture tank1_Image;
    private Texture tank2_Image;
    private ShapeRenderer shape1;
    private int[][] surfaceArray;
    private TextureRegion backgroundTexture;
    private Player p1;
    private Player p2;
    OrthographicCamera camera;
    private Rectangle tank1;
    private Rectangle tank2;
    private Rectangle healthbar1;
    private Rectangle healthbar2;
    private Rectangle shooter1;
    private Rectangle shooter2;
    private int turn;
    private Tank tank_in_operation;

//    public GameScreen_1(TanksStars game) {
//        this.game = game;
//        backgroundImage = new Texture(Gdx.files.internal("GameScreen.png"));
//        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1880, 980);
//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, 1880, 980);
//        surfaceArray=new Rectangle[10][40];
//        shape1= new ShapeRenderer();
//        shape2= new ShapeRenderer();
//    }
    public GameScreen(TanksStars game, Player p_1, Player p_2)
    {
        this.game = game;
        backgroundImage = new Texture(Gdx.files.internal("GameScreen.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1880, 980);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1880, 980);
        shape1= new ShapeRenderer(); // for drawing shapes!

        //MAKING SURFACE ARRAY
        surfaceArray=new int[10][40];
        for(int i=0; i<10; i++)
        {
            for(int j=0; j<40; j++)
            {
                surfaceArray[i][j]=1; // HERE i is the vertical tile and j is horizontal tile
            }
        }

        surfaceArray[0][5]=0;

        // MAKING ELEMENTS FOR THE GAMESCREEN
        shooter1=new Rectangle();
        shooter2=new Rectangle();
        tank1=new Rectangle();
        tank2=new Rectangle();
        healthbar1=new Rectangle();
        healthbar2=new Rectangle();

        // MAKING PLAYERS FOR THE GAME
        p1=p_1;
        p2=p_2;

        // SETTING POSITIONS OF TANK AND SHOOTER WHEN THE GAME SCREEN JUST OPENS
        tank1.x=343;
        tank1.y=1080-675;
        tank1.width=200;
        tank1.height=143;

        tank2.x=1920-343;
        tank2.y=1080-675;
        tank2.width=200;
        tank2.height=143;

        shooter1.x=450;
        shooter1.y=1080-650;
        shooter1.width=267;
        shooter1.height=249;

        shooter2.x=1300;
        shooter2.y=1080-650;
        shooter2.width=267;
        shooter2.height=249;

        // FOR PROJECTILE (**TRIAL**) (NOT EXACTLY TO BE IMPLEMENTED IN THE FINAL CODE
//        ballTex=new Texture("Tile.png");
//        ball=new Sprite(ballTex);
//        ball.setSize(48,48);
//        ball.setPosition(0,0);
//
//        gravity=new Vector2(0, -Gdx.graphics.getHeight()*.05f);
//        float throwVelocity=Gdx.graphics.getWidth()*.3f;
//        initialVelocity=new Vector2((float)(throwVelocity*Math.sin(throwAngle * Math.PI / 180)),(float)(throwVelocity*Math.cos(throwAngle * Math.PI / 180)));


    }

    private void updateBall(){

        if(true){

            float delta=Gdx.graphics.getDeltaTime();
            initialVelocity.x=initialVelocity.x+gravity.x*delta*deltaTime;
            initialVelocity.y=initialVelocity.y+gravity.y*delta*deltaTime;

            ball.setPosition(ball.getX()+initialVelocity.x * delta * deltaTime,ball.getY()+initialVelocity.y * delta * deltaTime);
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta)
    {
        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        shape1.setProjectionMatrix(camera.combined);

        shape1.begin(ShapeRenderer.ShapeType.Filled);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 1880, 980);

        spawnTank1();
        spawnTank2();
        spawnShooter();
        spawnSurface();
        spawnHealthBarPlayer1();
//        ball.draw(game.batch);

        game.batch.end();
        shape1.end();

        if (Gdx.input.getX()>37 && Gdx.input.getX()<113 && Gdx.input.getY()>34 && Gdx.input.getY()<99)
        {
            if(Gdx.input.isTouched())
            {
                this.OpenPauseGameScreen();
            }
        }

        if(Gdx.input.isKeyPressed(22))
        {
            tank1.x+=5+Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(21))
        {
            tank1.x-=5+Gdx.graphics.getDeltaTime();
        }

    }

    public void OpenPauseGameScreen()
    {
        game.setScreen(new PauseGameScreen(this.game));
        dispose();
    }

    public void moveTank()
    {

    }

    public void launchProjectile()
    {

    }

    public void spawnTank1()
    {
        tank1_Image = new Texture(Gdx.files.internal(p1.getTank().spawnTankinGame_1()));
        game.batch.draw(tank1_Image, tank1.x, tank1.y);
    }

    public void spawnTank2()
    {
        tank2_Image = new Texture(Gdx.files.internal(p2.getTank().spawnTankinGame_2()));
        game.batch.draw(tank2_Image,tank2.x,tank2.y);
    }

    public void spawnSurface()
    {
        surfaceImage=new Texture(Gdx.files.internal("Tile.png"));
        //int i=0, j=480-96;
        Rectangle Tile = new Rectangle();
        for(int i=0; i<1920; i+=48)
        {
            for(int j=384; j>=0; j-=48)
            {
                if(surfaceArray[(384-j)/48][(i/48)]==1)
                {
                    Tile.x = i;
                    Tile.y = j;
                    Tile.width = 48;
                    Tile.height = 48;
                    game.batch.draw(surfaceImage, Tile.x, Tile.y);
                }
            }
        }
    }

    public void spawnShooter()
    {
        shooterImage = new Texture(Gdx.files.internal("shooter.png"));
        Rectangle shooter = new Rectangle();
        shooter.x = 450;
        shooter.y = 1080-650;
        shooter.width = 267;
        shooter.height = 249;
        game.batch.draw(shooterImage, shooter.x, shooter.y);
        shooterImage2 = new Texture(Gdx.files.internal("shooter_player2.png"));
        shooter.x = 1300;
        shooter.y = 1080-650;
        shooter.width = 267;
        shooter.height = 249;
        game.batch.draw(shooterImage2, shooter.x, shooter.y);
    }

    public void spawnHealthBarPlayer1()
    {
        shape1.setColor(Color.PURPLE);
        shape1.rect(302,900,500,60);
        shape1.setColor(Color.PURPLE);
        shape1.rect(1200,900,500,60);
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
