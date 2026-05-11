package io.spaseCleaner.Sreen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import io.spaseCleaner.Main;

public class GameScreen extends ScreenAdapter {
    Texture texture;
    Main main;
    public GameScreen(Main main){
        this.main = main;
        texture = new Texture("libgdx.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(1, 0, 0, 1);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();

        main.batch.draw(texture , 0 , 0);

        main.batch.end();
    }
}
