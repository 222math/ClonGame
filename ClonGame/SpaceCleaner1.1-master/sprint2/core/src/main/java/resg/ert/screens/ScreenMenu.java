package resg.ert.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import resg.ert.Main;
import resg.ert.components.MovingBackground;
import resg.ert.components.TextButton;

public class ScreenMenu implements Screen {

    Main main;

    MovingBackground movingBackground;

    TextButton textButton1;
    TextButton textButton2;

    int x , y;
    Texture texture;


    public ScreenMenu(Main main) {
        this.main = main;
        movingBackground = new MovingBackground("background/menu_bg.png");
        textButton1 = new TextButton(300 , 350 , "start game" , "button/button_bg.png");
        textButton2 = new TextButton(300 , 150 , "exit" , "button/button_red.png");
        texture = new Texture("obstacles/img.png");
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
            if (textButton1.IsHit(x, y)) {
                ScreenGame screenGame = new ScreenGame(main);
                main.setScreen(screenGame);
            }
            if (textButton2.IsHit(x, y)) {
                Gdx.app.exit();
            }
        }




        ScreenUtils.clear(1, 0, 0, 1);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();

        movingBackground.draw(main.batch);
        textButton1.drow(main.batch);
        textButton2.drow(main.batch);


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

    }
}
