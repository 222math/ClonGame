package resg.ert.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import resg.ert.Main;
import resg.ert.characters.Bird;
import resg.ert.components.MovingBackground;
import resg.ert.components.TextButton;

public class ScreenMenu implements Screen {

    Main main;

    MovingBackground movingBackground;

    TextButton textButtonStartGame;
    TextButton textButtonExit;
    TextButton textButtonSkin;
    Bird bird;

    int x , y;


    public ScreenMenu(Main main) {
        this.main = main;
        movingBackground = new MovingBackground("background/menu_bg.png");
        textButtonStartGame = new TextButton(240 , 200 , "start game" , "button/button_bg.png" , 800 , 200);
        textButtonExit = new TextButton(900 , 50 , "exit" , "button/button_red.png" , 350 , 150);
        textButtonSkin = new TextButton(725 , 375 , "skin" , "button/button_blue.png" , 300 , 200);
        bird = new Bird(240 , 450 , 0 , 300 , 250  , main);
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
            if (textButtonStartGame.IsHit(x, y)) {
                ScreenGame screenGame = new ScreenGame(main , true);
                main.setScreen(screenGame);
            }
            if (textButtonExit.IsHit(x, y)) {
                Gdx.app.exit();
            }
            if (textButtonSkin.IsHit(x , y)){
                main.newSkin();
                bird = new Bird(240 , 450 , 0 , 300 , 250  , main);
            }
        }


        float deltaTime = Gdx.graphics.getDeltaTime();


        ScreenUtils.clear(1, 0, 0, 1);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();
        movingBackground.draw(main.batch);
        textButtonStartGame.drow(main.batch);
        textButtonExit.drow(main.batch);
        textButtonSkin.drow(main.batch);
        bird.draw(main.batch , deltaTime);
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
        textButtonStartGame.dispose();
        textButtonExit.dispose();
        textButtonSkin.dispose();
        bird.dispose();
    }
}
