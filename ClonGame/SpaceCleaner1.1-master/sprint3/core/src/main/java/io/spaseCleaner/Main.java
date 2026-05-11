package io.spaseCleaner;

import static io.spaseCleaner.GameSetting.SCR_HEIGHT;
import static io.spaseCleaner.GameSetting.SCR_WIDTH;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.spaseCleaner.Sreen.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public SpriteBatch batch;
    public OrthographicCamera camera;




    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false , SCR_WIDTH , SCR_HEIGHT);
        GameScreen screenGame = new GameScreen(this);
        setScreen(screenGame);
    }


    @Override
    public void dispose() {
        batch.dispose();

    }
}
