package resg.ert.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import resg.ert.Main;
import resg.ert.components.MaxGamePoint;
import resg.ert.components.MovingBackground;
import resg.ert.components.PointCounter;
import resg.ert.components.TextButton;

public class ScreenRestart implements Screen {
    Main main;

    MovingBackground movingBackground;

    TextButton textButtonRestart;
    TextButton textButtonInMenu;

    PointCounter pointCounter;

    ScreenGame screenGame;
    MaxGamePoint maxGamePoint;
    ScreenMenu screenMenu;

    int gamePoint;

    int x , y;

    public ScreenRestart(Main main , int gamePoints){

        this.main = main;
        this.gamePoint = gamePoints;

        movingBackground = new MovingBackground("background/restart_bg.png");
        textButtonRestart = new TextButton(300 , 350 , "restart" , "button/button_bg.png");
        textButtonInMenu = new TextButton(300 , 125 , "in menu" , "button/button_red.png");
        screenGame = new ScreenGame(main);
        pointCounter = new PointCounter(25 , 100);
        main.IsMaxGamePoint(gamePoint);
        maxGamePoint = new MaxGamePoint(350 , 650 , main.maxGamePoint);
        screenMenu = new ScreenMenu(main);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            Vector3 touch = main.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            x = (int) touch.x;
            y = (int) touch.y;
            if (textButtonRestart.IsHit(x, y)) {
                ScreenGame screenGame = new ScreenGame(main);
                main.setScreen(screenGame);
            }
            if (textButtonInMenu.IsHit(x, y)) {
                ScreenMenu screenMenu = new ScreenMenu(main);
                main.setScreen(screenMenu);
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();

        movingBackground.draw(main.batch);
        textButtonRestart.drow(main.batch);
        textButtonInMenu.drow(main.batch);
        pointCounter.draw(main.batch , gamePoint);
        maxGamePoint.drow(main.batch);

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
    textButtonRestart.dispose();
    maxGamePoint.dispose();

    }
}
