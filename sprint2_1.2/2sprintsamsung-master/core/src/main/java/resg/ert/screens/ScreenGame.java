package resg.ert.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import resg.ert.characters.Bird;
import resg.ert.characters.Portals;
import resg.ert.characters.Tube;
import resg.ert.Main;
import resg.ert.components.MovingBackground;
import resg.ert.components.PointCounter;


public class ScreenGame implements Screen {

    Main main;
    Bird bird;
    Tube tube;
    MovingBackground movingBackground;
    PointCounter pointCounter;
    ScreenRestart screenRestart;
    ScreenGameNorm screenGameNorm;
    Portals portals;

    int tubeCount = 4;
    boolean next;
    Tube[] tubes;
    MovingBackground[] backgrounds;
    public boolean isGameOver;

    private void initTubes(){
        tubes = new Tube[tubeCount];
        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount , i);
        }
    }


    public ScreenGame(Main main ,boolean next) {
        this.main = main;
        bird = new Bird(300, 500 , 500 , 200 , 175  , main);
        pointCounter = new PointCounter(25 , 100);
        movingBackground = new  MovingBackground("background/game_bg.png");
        initTubes();
        portals = new Portals();
        this.next = next;
    }




    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        System.out.println("=== DEBUG ===");
        System.out.println("Bird Y: " + bird.y);
        System.out.println("Screen Height: " + Gdx.graphics.getHeight());
        System.out.println("SCR_HEIGHT: " + Main.SCR_HEIGHT);
        System.out.println("DeltaTime: " + Gdx.graphics.getDeltaTime());
        System.out.println("Game Points: " + main.gamePoints);



        if (isGameOver){
            screenRestart = new ScreenRestart(this.main , main.gamePoints);
            main.setScreen(screenRestart);
        }



        if (Gdx.input.justTouched()){
            bird.OnClick("screenGame");
        }
        float deltaTime = Gdx.graphics.getDeltaTime();
        bird.fly(deltaTime);
        if (!bird.inField()){
            System.out.println("not in field");
            isGameOver = true;
        }
        for (Tube tube : tubes) {
            tube.move(deltaTime);
            if (tube.IsHit(bird)){
                isGameOver = true;
                System.out.println("hit");
            } else if (tube.NeedAddPoint(bird)) {
                main.gamePoints += 1;
                System.out.println(main.gamePoints);
                tube.setPointReceived();
            }
            if (portals.isInPortal(bird) && main.gamePoints >= 9 && main.gamePoints<= 15){
                screenGameNorm = new ScreenGameNorm(this.main);
                main.setScreen(screenGameNorm);
            }
        }
        movingBackground.move(deltaTime);




        ScreenUtils.clear(1, 0, 0, 1);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();

        movingBackground.draw(main.batch);


        bird.draw(main.batch);


        for (Tube tube : tubes) {
            tube.drow(main.batch);
            if (main.gamePoints >= 7 && tube.tubeInx == 1 && main.gamePoints<=11){
                portals.drow(main.batch , tube.x , (tube.gapY/ + tube.gapHeight));
            }
        }


        pointCounter.draw(main.batch, main.gamePoints);



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
        bird.dispose();
        tube.dispose();
        pointCounter.dispose();
        movingBackground.dispose();
    }
}
