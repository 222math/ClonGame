package resg.ert.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import resg.ert.Main;
import resg.ert.characters.Bird;
import resg.ert.components.MovingBackground;

public class ScreenGameNorm implements Screen {
    Main main;
    Bird bird;
    MovingBackground movingBackground;
    Texture floor;
    public int floorY = 150;
    public ScreenGameNorm(Main main){
        this.main = main;
        bird = new Bird(350 , floorY, 200 ,  100 , 100);
        movingBackground = new MovingBackground("background/cube_bg.png");
        floor = new Texture("background/floor.png");
    }



    @Override
    public void show() {

    }






    @Override
    public void render(float delta) {

        float deltaTime = Gdx.graphics.getDeltaTime();

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
