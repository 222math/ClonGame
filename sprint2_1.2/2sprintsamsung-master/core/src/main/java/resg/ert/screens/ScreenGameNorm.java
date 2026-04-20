package resg.ert.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import resg.ert.Main;
import resg.ert.characters.Bird;
import resg.ert.characters.Portals;
import resg.ert.characters.Thorns;
import resg.ert.characters.Tube;
import resg.ert.components.MovingBackground;
import resg.ert.components.PointCounter;

public class ScreenGameNorm implements Screen {

    Main main;
    Bird bird;
    MovingBackground movingBackground;
    Texture floor;
    int thornsCount = 5;
    public int floorY = 150;
    Thorns[] thorns;
    Portals portals;
    PointCounter pointCounter;
    ScreenGame screenGame;
    void initThorns(){
        thorns = new Thorns[thornsCount];
        for (int i = 0; i < thornsCount; i++) {
            thorns[i] = new Thorns(i);
        }
    }

    public ScreenGameNorm(Main main){
        this.main = main;
        bird = new Bird(350 , floorY, 200 ,  100 , 100 , main);
        movingBackground = new MovingBackground("background/cube_bg.png");
        floor = new Texture("background/floor.png");
        initThorns();
        portals = new Portals();
        pointCounter = new PointCounter(450 , 500);

    }



    @Override
    public void show() {

    }



    int a;


    @Override
    public void render(float delta) {



        float deltaTime = Gdx.graphics.getDeltaTime();

        for (int i = 0; i < thornsCount; i++) {
            if(thorns[i].IsHit(bird)){
                ScreenRestart screenRestart = new ScreenRestart(this.main , main.gamePoints);
                main.setScreen(screenRestart);
            }
            if (thorns[i].NeedAddPoint(bird)){
                main.gamePoints += 1;
            }
            if (i == 0){
                a = 4;
            } else {
                a = i-1;
            }
            thorns[i].move(deltaTime , thorns[a].x);

        }
        if (portals.isInPortal(bird) && main.gamePoints >= 20 && main.gamePoints <= 25){
            screenGame = new ScreenGame(this.main , false);
            main.setScreen(screenGame);
        }


        if (Gdx.input.justTouched()){
            bird.OnClick("ScreenGameNorm");
        }
        bird.slideJump(deltaTime , floorY);


        ScreenUtils.clear(1, 0, 0, 1);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();

        movingBackground.draw(main.batch);
        main.batch.draw( floor, 0 , 0 , main.SCR_WIDTH , floorY);

        bird.draw(main.batch);
        for (int i = 0; i < thornsCount; i++) {
            thorns[i].draw(main.batch);
        }
        pointCounter.draw(main.batch , main.gamePoints);

        main.batch.end();
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
        movingBackground.dispose();
        main.batch.dispose();

        bird.dispose();
        for (int i = 0; i < thornsCount; i++) {
            thorns[i].dispose();
        }
        pointCounter.dispose();


    }
}
